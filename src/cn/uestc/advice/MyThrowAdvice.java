package cn.uestc.advice;

import org.springframework.aop.ThrowsAdvice;

/**
 * @Auther: cmz
 * @Date: 2019/11/25
 * @Description: 报异常，才会触发该异常通知
 * 在Spring中只有AspectJ提供了异常通知的方法
 *
 * Spring的事务回滚机制是建立在该异常通知之上的，而事务回滚通常在service层的方法中，
 * 所以一般不对service方法进行try-catch，如果进行try-catch，则不会进行异常通知，也不会
 * 进行事务回滚。
 * @version: 1.0
 */
public class MyThrowAdvice implements ThrowsAdvice {
    public void myException(Exception e11) {
        System.out.println("执行异常通知，输出异常信息："+e11);
    }
}
