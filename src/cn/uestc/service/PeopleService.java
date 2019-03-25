package cn.uestc.service;

import cn.uestc.pojo.People;

import java.io.IOException;
import java.util.List;

/**
 * MVC开发模式，业务层接口
 */
public interface PeopleService {
    /**
     * 显示全部
     */
    List<People> show() throws IOException;
}
