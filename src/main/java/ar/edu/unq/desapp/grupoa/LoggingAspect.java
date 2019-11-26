package ar.edu.unq.desapp.grupoa;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class LoggingAspect {

    @Around("execution(* ar.edu.unq.desapp.grupoa.model..*(..)) ||\n" +
            "execution(* ar.edu.unq.desapp.grupoa.service..*(..)) ||\n" +
            "execution(* ar.edu.unq.desapp.grupoa.persistence..*(..)) ||\n" +
            "execution(* ar.edu.unq.desapp.grupoa.webservice..*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ret = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        log.info("Time Taken by method {}>>{} is: {}ms", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), timeTaken);
        return ret;
    }
}
