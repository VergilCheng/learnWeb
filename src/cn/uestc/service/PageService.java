package cn.uestc.service;

import cn.uestc.pojo.PageInfo;

import java.io.IOException;
import java.util.List;

/**
 * 分页服务层接口
 */
public interface PageService {
    PageInfo findPage(int pageSize, int pageNumber) throws IOException;
}
