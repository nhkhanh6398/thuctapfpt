package vn.fpt.logbook;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Aspect
@Component
public class LogBook {
    @Pointcut("within(vn.fpt.controller.BookController*)")
    public void allMethod(){};

    @Before("allMethod()")
    public void beforeCallMethod(JoinPoint joinPoint){
        System.err.println("Start method name: "+ joinPoint.getSignature().getName()+ " Time: "+ LocalDate.now());
    }
    @After("allMethod()")
    public void afterCallMethod(JoinPoint joinPoint){
        System.err.println("End method name: "+ joinPoint.getSignature().getName()+ " Time: "+ LocalDate.now());
    }
}
