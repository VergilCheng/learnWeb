package cn.uestc.test;

import cn.uestc.pojo.People;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试mybatis能否返回数据库中people的数据
 */
public class TestPeopleMapper {
    /**
     * 测试全部查询
     * @throws IOException
     */
    @Test
    public  void test() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<People> list = sqlSession.selectList("cn.uestc.mapper.PeopleMapper.selAll");
        for (People p:
             list) {
            System.out.println(p);

        }
        int count = sqlSession.selectOne("cn.uestc.mapper.PeopleMapper.selCount");
        System.out.println(count);
        Map<String, People> map = sqlSession.selectMap("cn.uestc.mapper.PeopleMapper.selMap", "name");
        System.out.println(map);
        sqlSession.close();
    }
    /**
     * 测试参数注入sql查询
     */
    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        /*带参数查询，参数为基本类型或者String都可*/
        int id = 1;
        People p1 = sqlSession.selectOne("cn.uestc.mapper.PeopleMapper.selById", id);
        System.out.println(p1);

        /*带参数查询，参数为对象，对应的mapper.xml文件中的参数应该使用#{Field}
        * 并且对象支持多参数查询，将参数放到对象中即可*/
        People p = new People();
        p.setId(2);
        p.setName("李四");
        p.setAge(22);
        People p0 = sqlSession.selectOne("cn.uestc.mapper.PeopleMapper.selByPeopleId", p);
        System.out.println(p0);

        /*带参数查询，参数为map，对应的mapper.xml文件中的参数应该使用#{key}
         * 并且对象支持多参数查询，将参数放到map中即可
         *
         * mybatis现阶段只提供了通过对象或者map来进行多参数传递的方式，后续和spring进行整合后
         * 可以通过@Param注解来实现多参数注入查询
         * */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 1);
        map.put("name", "张三");
        People p2 = sqlSession.selectOne("cn.uestc.mapper.PeopleMapper.selByMap", p);
        System.out.println(p2);
        sqlSession.close();
    }
    /**
     * 测试插入，删除，更新
     * mybatis框架默认关闭JDBC的自动提交，所以需要手动commit。
     * 可以通过方法openSession(true)设置——>JDBC自动提交.setAutoCommit(true)，但是一般不这么做
     * 在调用openSession()时 Mybatis 会创建 SqlSession 时同时创建一个Transaction(事务对象),两者绑定，
     * 表示一个SqlSession执行的是一个事务，同时 autoCommit 都为 false，如果出现异常,应该 session.rollback()回滚事务。
     *
     */
    @Test
    public void test3() throws IOException {
        //插入
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        People people = new People();
        people.setName("徐飞");
        //people.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");//测试数据库出错回滚以及日志记录
        people.setAge(24);
        /*记住写操作要提交，不然即使控制台输出insert结果为1，但是由于没有提交，数据是不会更新到数据库中
         * 去的，日志也只会记录到sql执行成功而不会记录sql执行成功后的事务提交相关日志信息，
         * 我们可以通过查看日志文件来debug到没有提交这一点*/
        try {
            int insert = sqlSession.insert("cn.uestc.mapper.PeopleMapper.insertPeople", people);
            if (insert > 0) {
                System.out.println(insert + ",成功");
            } else {
                System.out.println("错误");
            }
            sqlSession.commit();
        } catch (Exception e) {//在catch块中捕获异常，可以抛出或者处理，并记录日志
            Logger logger= Logger.getLogger(TestPeopleMapper.class);
            logger.error(e.getMessage());
            sqlSession.rollback();
        }
        //更新
        People peo = new People();
        peo.setId(10);
        peo.setName("王五");
        int index = sqlSession.update("cn.uestc.mapper.PeopleMapper.upd", peo);
        //下面包括删除都是为了省事不写try-catch了，工作开发是要写的
        if(index>0){
            System.out.println("成功");
            sqlSession.commit();
        }else{
            System.out.println("失败");
            sqlSession.rollback();
        }
        //删除
        int del = sqlSession.delete("cn.uestc.mapper.PeopleMapper.del",10);
        if (del > 0) {
            System.out.println("成功");
            sqlSession.commit();
        } else {
            System.out.println("失败");
            sqlSession.rollback();
        }

        sqlSession.close();

    }

    /**
     * 测试分页查询,
     * #{}（在sql中被替换为?）不允许在关键字前后进行数学运算(可以进行逻辑运算),需要在代码中计算完成后传递到 mapper.xml 中
     */
    @Test
    public void test4() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int pageSize = 2;
        int pageNumber = 1;
        Map<String, Integer> map = new HashMap<>();
        map.put("pageSize", pageSize);
        map.put("pageStart", (pageNumber - 1) * pageSize);
        List<Object> list = sqlSession.selectList("cn.uestc.mapper.PeopleMapper.page", map);
        for (Object p :list) {
            System.out.println(p.toString());
        }
    }
    /**
     * 测试别名,mybatis.xml中typeAlias标签和package标签
     *
     */
    @Test
    public void test5() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        People people= sqlSession.selectOne("cn.uestc.mapper.PeopleMapper.selOne2",1);
        System.out.println(people);
    }
}
