package cn.uestc.test;

import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
* 在context.xml文件中我们将数据库连接池交给tomcat管理，这是使用JNTI技术的（并且在context.xml文件中配置数据库连接池会覆盖mybatis.xml中连接池的配置）
* 由于使用JNTI技术，我们无法直接通过java代码获取数据库连接池。所以只能写一个Servlet来检测我们的连接池是否配置成功。
*
* */
//WebServlet注解可以配置servlet的访问url
@WebServlet("/demo")
public class DemoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取tomcat的Context对象，包含了context.xml文件中的各种信息
        Context context = null;
        try {
            //设置返回类型以及解决乱码
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            context = new InitialContext();
            PrintWriter writer = resp.getWriter();
            //使用lookup方法，按名字查找资源，返回我们命名的test名称的数据库连接池对象
            DataSource ds = (DataSource) context.lookup("java:comp/env/test");
            Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from flower");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                writer.print(resultSet.getObject(1) + "&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getObject(2) + "&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getObject(3)
                        + "&nbsp;" + resultSet.getObject(4));
            }
            writer.print("success");
            writer.flush();
            writer.close();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
