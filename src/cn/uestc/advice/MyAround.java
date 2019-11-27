package cn.uestc.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Auther: cmz
 * @Date: 2019/11/27
 * @Description: 环绕通知(schema-based)
 * 前置通知和后置通知写到一个方法中，则是环绕通知
 * @version: 1.0
 */
public class MyAround implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("环绕-前置通知");
        Object proceed = methodInvocation.proceed();
        System.out.println("环绕-后置通知");
        return proceed;
    }
}
