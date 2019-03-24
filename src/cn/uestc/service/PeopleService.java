package cn.uestc.service;

import cn.uestc.pojo.People;

import java.io.IOException;
import java.util.List;

public interface PeopleService {
    /**
     * 显示全部
     */
    List<People> show() throws IOException;
}
