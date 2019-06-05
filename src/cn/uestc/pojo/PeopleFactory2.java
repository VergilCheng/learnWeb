package cn.uestc.pojo;

/**
 * @Auther: cmz
 * @Date: 2019/5/23
 * @Description: cn.uestc.pojo
 * 静态工厂
 * 用于测试spring通过实例工厂来创建多个对象（多种对象或者多种子类对象）
 * @version: 1.0
 */
public class PeopleFactory2 {

    public static People newInstance() {
        return new People();
    }



}
