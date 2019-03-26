package cn.uestc.service.impl;

import cn.uestc.pojo.PageInfo;
import cn.uestc.service.PageService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * 分页服务层实现
 */
public class LogPageSizeImpl implements PageService {
    @Override
    public PageInfo findPage(int pageSize, int pageNumber) throws IOException {

        PageInfo pageInfo = new PageInfo();

        InputStream resource = Resources.getResourceAsStream("mybatis.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        HashMap<String, Object> map = new HashMap<>();
        map.put("pageStart", (pageNumber - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<Object> list = sqlSession.selectList("cn.uestc.mapper.logMapper.selectPage", map);
        long count = sqlSession.selectOne("cn.uestc.mapper.logMapper.selCount");
        long total = count%pageSize==0?count/pageSize:count/pageSize+1;
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageSize(pageSize);
        pageInfo.setList(list);
        pageInfo.setTotal(total);
        return pageInfo;
    }
}
