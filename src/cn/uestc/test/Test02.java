package cn.uestc.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: cmz
 * @Date: 2019/10/20
 * @Description: cn.uestc.test
 * AOP的测试
 * @version: 1.0
 */
public class Test02 {
    /**
     * 测试AOP前置方法
     */
    @Test
    public void test01() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Demo1 demo1 = context.getBean("demo1", Demo1.class);
        demo1.demo1();
        demo1.demo2();
        demo1.demo3();
        demo1.demo4("demo4");
        demo1.demo5();
    }
}
