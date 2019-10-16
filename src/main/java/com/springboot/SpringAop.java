package com.springboot;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Aspect
@Component
public class SpringAop {
    @Pointcut(value = "execution(* com.springboot..*.run(..))")
    public void aop(){

    }
    @Before("aop()")
    public void before(){
        System.out.println("before:执行方法前");
    }
    @After("aop()")
    public void after(){
        System.out.println("after:执行方法后");
    }
    @AfterThrowing("aop()")
    public void afterThrow(){
        System.out.println("afterthrow:抛出异常后执行");
    }
    @AfterReturning("aop()")
    public void afterReturn(){
        System.out.println("afterreturn: 放过返回后执行");
    }
    @Around("aop()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around: 环绕通知前");
            joinPoint.proceed();
        System.out.println("around:环绕通知后");
    }

    //注入事务管理器
    @Autowired
    public PlatformTransactionManager platformTransactionManager;
    //设置事务拦截器
    @Bean
    public TransactionInterceptor transactionInterceptor(){
        DefaultTransactionAttribute defaultTransactionAttribute = new DefaultTransactionAttribute();
        defaultTransactionAttribute.setReadOnly(false);
        NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource = new NameMatchTransactionAttributeSource();
        nameMatchTransactionAttributeSource.addTransactionalMethod("save",defaultTransactionAttribute);
        return new TransactionInterceptor(platformTransactionManager,nameMatchTransactionAttributeSource);
    }

    @Bean
    public Advisor advisor(){
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* com.springboot..*.save(..))");
        return new DefaultPointcutAdvisor(aspectJExpressionPointcut,transactionInterceptor());
    }
}
