package cn.uestc.pojo;

/**
 * @Auther: cmz
 * @Date: 2019/5/23
 * @Description: cn.uestc.pojo
 * 实例工厂
 * 用于测试spring通过实例工厂来创建多个对象（多种对象或者多种子类对象）
 * @version: 1.0
 */
public class PeopleFactory {

    public People createPeople() {
        return new People();
    }

    public People createPeople1(String type) {
        switch (type) {
            case "A":
                //创建对象之前可处理一些简单逻辑
                return new A();
            case  "B":
                //创建对象之前可处理一些简单逻辑
                return new B();
            default:
                //创建对象之前可处理一些简单逻辑
                return createPeople();
        }
    }

}
