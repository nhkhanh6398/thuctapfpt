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
public class LogView {
    @Pointcut("within(vn.fpt.controller.ViewController*)")
    public void allMethodView(){};
    @Before("allMethodView()")
    public void before(JoinPoint joinPoint){
        System.err.println("Name method: "+ joinPoint.getSignature().getName()+ " Time: "+ LocalDate.now());
    }

    @After("allMethodView()")
    public void after(JoinPoint joinPoint){
        System.err.println("End method: "+ joinPoint.getSignature().getName()+ " Time: "+ LocalDate.now());
    }
}
