package cn.uestc.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: cmz
 * @Date: 2019/5/23
 * @Description: cn.uestc.pojo
 * @version: 1.0
 */
public class People {
    private int id;
    private String name;
    private People people;
    private List<Integer> list;
    private Set<String> set;
    private Map<String, String> map;
    private String[] strs;


    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", people=" + people +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", strs=" + Arrays.toString(strs) +
                '}';
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public People(int id, String name, People people) {
        System.out.println("执行有参数构造方法");
        this.id = id;
        this.name = name;
        this.people = people;
    }

    public People() {
        System.out.println("执行构造方法");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        System.out.println("执行setId");
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("执行setName");
        this.name = name;
    }

    public String[] getStrs() {
        return strs;
    }

    public void setStrs(String[] strs) {
        this.strs = strs;
    }
}
