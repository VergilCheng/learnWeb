package cn.uestc.test;

import cn.uestc.pojo.A;
import cn.uestc.pojo.People;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: cmz
 * @Date: 2019/5/23
 * @Description: cn.uestc.test
 * @version: 1.0
 */
public class Test01 {
    /**
     * 测试IOC（控制反转）
     * 1.默认无参构造器创建bean
     */
    @Test
    public void test1() {
        //spring容器中管理的对象是在配置文件被加载后就立刻创建的
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object p = context.getBean("p");//将applicationContext.xml文件中的bean标签的属性id：“p”传入，返回Object对象
        People p1 = context.getBean("p", People.class);//上述方法的重载，也可以在传入id的同时制定返回对象类别
        System.out.println(p == p1);//true
        System.out.println(p);

        //获取spring容器中所有管理的对象的id,方便debug
        String[] names = context.getBeanDefinitionNames();
        for (String name :
                names) {
            System.out.println(name);
        }
    }
    /**
     * 测试IOC（控制反转）
     * 2.有参构造器创建bean
     */
    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        People p1 = context.getBean("people", People.class);
        System.out.println(p1);
    }
    /**
     * 测试IOC（控制反转）
     * 3.实例化工厂创建bean
     */
    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people2 = context.getBean("people2", People.class);
        System.out.println(people2);
        A a = context.getBean("a", A.class);
        System.out.println(a);
    }
    /**
     * 测试IOC（控制反转）
     * 4.静态工厂创建bean
     */
    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people = context.getBean("people3", People.class);
        System.out.println(people);
    }
    /**
     * 测试IOC
     * 5.set方法进行注入
     */
    @Test
    public void test5() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people = context.getBean("p2", People.class);
        System.out.println(people);
    }
}
