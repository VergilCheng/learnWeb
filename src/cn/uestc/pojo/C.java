package cn.uestc.pojo;

/**
 * @Auther: cmz
 * @Date: 2019/6/5
 * @Description: cn.uestc.pojo
 * 用于测试不属于spring框架管理的bean能否进行DI依赖注入
 * @version: 1.0
 */
public class C {

    private People people;

    @Override
    public String toString() {
        return "C{" +
                "people=" + people +
                '}';
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
