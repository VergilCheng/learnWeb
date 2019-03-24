package cn.uestc.service.impl;

import cn.uestc.pojo.People;
import cn.uestc.service.PeopleService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 在数据访问层处理异常和在控制器中处理异常，service中只抛出异常
 */
public class PeopleServiceImpl implements PeopleService {
    @Override
    public List<People> show() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<People> list = sqlSession.selectList("cn.uestc.mapper.PeopleMapper.selAll");
        return list;
    }
}
