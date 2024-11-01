package com.kwonjh0406.joon_bank.global;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GlobalAOPLogging {

        // 메소드 실행 전 로그
        @Before("execution(* com.kwonjh0406.joon_bank.domain..*(..))")
        public void logBeforeMethod(JoinPoint joinPoint) {
            System.out.println(joinPoint.getSignature().getName() + " -> ");
        }
}
