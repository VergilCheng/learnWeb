package cn.uestc.advice;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Auther: cmz
 * @Date: 2019/10/20
 * @Description: cn.uestc.advice
 * Demo1中方法demo2的前置通知的advisor，继承MethodBeforeAdvice，则改advisor可以需要重写befor方法，为前置通知方法
 * @version: 1.0
 */
public class MyBeforeAdvice implements MethodBeforeAdvice {
    
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("切点方法对象：" + method);
        System.out.println("切点方法名：" + method.getName());
        if (objects!=null&&objects.length>0) {
            for (Object object:objects) {
                System.out.println("切点方法参数："+ object.toString());
            }
        }
        System.out.println("该方法的对象：" + o);
    }
}
