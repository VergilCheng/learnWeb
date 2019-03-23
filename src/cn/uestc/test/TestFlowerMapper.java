package cn.uestc.test;

import cn.uestc.pojo.Flower;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


public class TestFlowerMapper {
    public static void main(String[] args) throws IOException {
        //获取mybatis所提供的资源加载类Resources在classPath下读取mybatis的配置文件：mybatis.xml。
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        //使用建造者模式（Builder）创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //使用工厂创建SqlSession:通过反射调用mapper.xml中的查询方法
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 查询list，适用于需要在查询结果中通过某列的值取到这行数据的需求
        // 传入sql查询语句的参数,namespace+id，为方法参数s
        List<Flower> list = sqlSession.selectList("cn.uestc.mapper.FlowerMapper.selAll");
        for (Flower flower : list) {
            System.out.println(flower.toString());
        }


        //适用于返回结果只是变量或一行数据时
        //查询count（*）
        int count = sqlSession.selectOne("cn.uestc.mapper.FlowerMapper.selOne");
        System.out.println(count);

        /*
            查询结果封装在map中,map的key为数据库中指定列的值,为下面方法的s1参数
            适用于需要在查询结果中通过某列的值取到这行数据的需求，比如通讯录
         */
        Map<Object, Object> selectMap = sqlSession.selectMap("cn.uestc.mapper.FlowerMapper.selMap", "name");
        System.out.println(selectMap);

        //与JDBC一样，使用完成后需要放开资源。
        sqlSession.close();
    }
}
