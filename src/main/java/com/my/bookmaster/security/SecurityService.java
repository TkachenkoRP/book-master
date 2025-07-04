package com.my.bookmaster.security;

import com.my.bookmaster.exception.RefreshTokenException;
import com.my.bookmaster.model.RefreshToken;
import com.my.bookmaster.model.RoleType;
import com.my.bookmaster.model.UserAuth;
import com.my.bookmaster.model.dto.RefreshTokenRequestDto;
import com.my.bookmaster.model.dto.RefreshTokenResponseDto;
import com.my.bookmaster.model.dto.UserLoginRequestDto;
import com.my.bookmaster.model.dto.UserLoginResponseDto;
import com.my.bookmaster.model.dto.UserRegistrationRequestDto;
import com.my.bookmaster.repository.UserRepository;
import com.my.bookmaster.security.jwt.JwtUtils;
import com.my.bookmaster.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;

    public UserAuth register(UserRegistrationRequestDto request) {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(request.email());
        userAuth.setName(request.name());
        userAuth.setPassword(passwordEncoder.encode(request.password()));
        userAuth.setRoles(Collections.singleton(RoleType.ROLE_USER));
        return userRepository.save(userAuth);
    }

    public UserLoginResponseDto login(UserLoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUserPrincipal userDetails = (AppUserPrincipal) authentication.getPrincipal();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        return new UserLoginResponseDto(jwtUtils.generateJwtToken(userDetails), refreshToken.getToken());
    }

    public void logout() {
        var currentPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentPrincipal instanceof AppUserPrincipal userPrincipal) {
            Long userId = userPrincipal.getId();

            refreshTokenService.deleteByUserId(userId);
        }
    }

    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto request) {
        String requestRefreshToken = request.refreshToken();

        return refreshTokenService.findByRefreshToken(requestRefreshToken)
                .map(refreshTokenService::checkRefreshToken)
                .map(RefreshToken::getUserId)
                .map(userId -> {
                    UserAuth tokenOwner = userRepository.findById(userId).orElseThrow(() ->
                            new RefreshTokenException("Exception trying to get token for userId: " + userId));
                    String token = jwtUtils.generateTokenFromUserEmail(tokenOwner.getEmail());
                    return new RefreshTokenResponseDto(token, refreshTokenService.createRefreshToken(userId).getToken());
                }).orElseThrow(() -> new RefreshTokenException(requestRefreshToken, "Refresh token not found"));
    }
}
