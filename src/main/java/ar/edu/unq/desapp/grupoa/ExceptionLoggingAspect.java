package ar.edu.unq.desapp.grupoa;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Slf4j
@Configuration
public class ExceptionLoggingAspect {

    @AfterThrowing(pointcut = "within(@org.springframework.stereotype.Service *)", throwing = "ex")
    public void logAfterThrowingAllMethods(JoinPoint pointcut, RuntimeException ex) {
        log.info("Exception thrown calling method: {} with arguments: {}. Got the following message: {}", pointcut.getSignature(), pointcut.getArgs(), ex.getMessage());
    }

}
