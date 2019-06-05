package cn.uestc.pojo;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @Auther: cmz
 * @Date: 2019/5/23
 * @Description: cn.uestc.pojo
 * @version: 1.0
 */
public class A extends People {

    @Override
    public String toString() {
        return "A{}";
    }

    public A() {
        System.out.println("执行子类A的构造方法");
    }
}
