package com.my.bookmaster.aspect;

import com.my.bookmaster.security.AppUserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private final Marker marker = MarkerFactory.getMarker("AUDITION");

    @Around("@within(com.my.bookmaster.annotation.Loggable) || @annotation(com.my.bookmaster.annotation.Loggable)")
    public Object logControllerMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder();
        var currentPrincipal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentPrincipal instanceof AppUserPrincipal userPrincipal) {
            Long userId = userPrincipal.getId();
            stringBuilder.append("User ID: ").append(userId).append(" ");
        }
        String methodName = joinPoint.getSignature().toShortString();
        stringBuilder.append("Entering method: ").append(methodName).append(" ");
        Object[] methodArgs = joinPoint.getArgs();
        stringBuilder.append("Method parameters: ").append(Arrays.toString(methodArgs));
        log.info(marker, stringBuilder.toString());
        stringBuilder.setLength(0);
        try {
            Object result = joinPoint.proceed();
            stringBuilder.append("Exiting controller method: ").append(methodName).append(" ");
            stringBuilder.append("Method execution result: ");
            log.info(marker, stringBuilder.toString());
            return result;
        } catch (Exception e) {
            log.error("Exception in controller method: {}", methodName, e);
            throw e;
        }
    }
}
