package cn.uestc.test;

import org.junit.Before;
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
    
    private ApplicationContext context;
    
    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    /**
     * 测试AOP前置方法与后置方法
     */
    @Test
    public void test01() {
        Demo1 demo1 = context.getBean("demo1", Demo1.class);
        demo1.demo1();
        demo1.demo2();
        demo1.demo3();
        demo1.demo4("demo4");
        demo1.demo5();
    }
    
    /**
     * 测试AOP异常通知
     */
    @Test
    public void test02() {
        Demo1 demo1 = context.getBean("demo1", Demo1.class);
        try {
            demo1.demo6();
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
    
    
}
