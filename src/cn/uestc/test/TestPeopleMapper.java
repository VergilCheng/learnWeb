package cn.uestc.test;

import cn.uestc.pojo.People;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 测试mybatis能否返回数据库中people的数据
 */
public class TestPeopleMapper {
    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<People> list = sqlSession.selectList("cn.uestc.mapper.PeopleMapper.selAll");
        for (People p:
             list) {
            System.out.println(p);

        }
        int count = sqlSession.selectOne("cn.uestc.mapper.PeopleMapper.selOne");
        System.out.println(count);
        Map<String, People> map = sqlSession.selectMap("cn.uestc.mapper.PeopleMapper.selMap", "name");
        System.out.println(map);
    }
}
