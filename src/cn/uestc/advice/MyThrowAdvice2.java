package cn.uestc.advice;

import jdk.internal.org.objectweb.asm.commons.Method;
import org.springframework.aop.ThrowsAdvice;

import java.rmi.RemoteException;

/**
 * @Auther: cmz
 * @Date: 2019/11/25
 * @Description: 基于schama-base的方式进行异常通知，需要重写afterThrowing方法
 * 该方法没有在ThrowsAdvice中提供接口，需要自己书写
 * @version: 1.0
 */
public class MyThrowAdvice2 implements ThrowsAdvice {
    // 方式1
    public void afterThrowing(Exception ex) throws Throwable {
        System.out.println("方式1：demo7异常处理");
    }
    
    // 方式2
    // spring进行方法重载时，是倒序检查，会执行最后的方法
    public void afterThrowing(ArithmeticException ex){
        System.out.println("方式2：执行demo7异常处理");
    }
}
