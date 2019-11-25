package cn.uestc.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @Auther: cmz
 * @Date: 2019/10/20
 * @Description: cn.uestc.advice
 * Demo1中方法demo2的后置通知的advisor，继承AfterReturningAdvice，则改advisor可以需要重写afterReturning方法，为后置通知方法
 * @version: 1.0
 */
public class MyAfterAdvisor implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("方法的返回值：" + returnValue);
        System.out.println("方法名称：" + method.getName());
        System.out.println("方法参数：" + args);
        System.out.println("拥有方法的对象：" + target);
    }
}
