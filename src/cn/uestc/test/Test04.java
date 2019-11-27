package cn.uestc.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: cmz
 * @Date: 2019/11/27
 * @Description: cn.uestc.test
 * 测试环绕通知
 * @version: 1.0
 */
public class Test04 {
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");
    
    /**
     * 测试环绕通知
     */
    @Test
    public void test01() {
        Demo1 demo = context.getBean("demo", Demo1.class);
        demo.demo8();
    }
}
