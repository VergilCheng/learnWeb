package cn.uestc.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: cmz
 * @Date: 2019/11/25
 * @Description: cn.uestc.test
 * @version: 1.0
 */
public class Test03 {
    
    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
    
    /**
     * 基于schema-base的方式进行异常处理
     */
    @Test
    public void test1() {
        Demo1 demo = context.getBean("demo", Demo1.class);
        try {
            demo.demo7();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
