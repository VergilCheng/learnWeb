package cn.uestc.test;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @Auther: cmz
 * @Date: 2019/10/20
 * @Description: cn.uestc.test
 * @version: 1.0
 */
public class Demo1 {

    public void demo1() {
        System.out.println("demo1");

    }

    public void demo2() {
        System.out.println("demo2");

    }

    public void demo3() {
        System.out.println("demo3");

    }
    
    public void demo4(String name) {
        System.out.println(name);
        
    }
    
    public String demo5() {
        return "demo5";
    }
    
    public void demo6 () throws Exception {
        int i = 5 / 0;
        System.out.println("异常通知demo6");
    }
    
    public void demo7 () throws Exception {
        int i = 5 / 0;
        System.out.println("异常通知demo7");
    }
    
    public String demo8() {
        return "环绕通知demo8测试方法";
    }

    public static void main(String[] args) {
        // 纵向执行流程
        Demo1 demo1 = new Demo1();
        demo1.demo1();
        demo1.demo2();
        demo1.demo3();
        
    }
    
    @Override
    public String toString() {
        return "Demo1{}";
    }
}
