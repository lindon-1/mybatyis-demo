package com.example.demo.aop.aspect;

import com.example.demo.mapper.UserMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/19 16:32
 */
@Component
@Aspect
public class UserLogAspect {

    public static final Logger log = LoggerFactory.getLogger(UserLogAspect.class);
    @Pointcut("execution(* com.example.demo.mapper..*.*(..))")
    public void pointcut() {
    }

    @Before(value = "pointcut()")
    public void before() {
        log.info("执行方法前");
    }

    @After(value = "pointcut()")
    public void after(JoinPoint joinPoint) {
        log.info("执行方法后");
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        System.out.println(name);
        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.asList(args).toString());
    }

    @Around(value = "pointcut()")
    public Object arround(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
        try {
            //proceedingJoinPoint.proceed()结果被拦截,有结果返回为null
            Object proceed = proceedingJoinPoint.proceed();
            log.info("执行结果： {}", proceed.toString());
            log.info("around end......");
            Type[] genericInterfaces = AopUtils.getTargetClass(proceedingJoinPoint.getTarget()).getGenericInterfaces();
            System.out.println(genericInterfaces[0]);

            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = signature.getMethod();
            System.out.println(method);

            Object[] args = proceedingJoinPoint.getArgs();
            System.out.println(Arrays.asList(args).toString());
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("arround end ..");
        return null;
    }
}
