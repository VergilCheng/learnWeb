package cn.uestc.servlet;

import cn.uestc.pojo.People;
import cn.uestc.service.PeopleService;
import cn.uestc.service.impl.PeopleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//大部分注解都有默认属性，如果注解中只给默认属性赋值，可以省略属性名称
//否则，要采用(属性名=属性值)的格式
//如果一个属性是数组类型格式，则需要采用属性名={值，值}，如果该数组只有一个值，可以省略{}
//如果属性值不是基本数据类型或者String类型，而是一个类的累心个，语法：属性名=@类型
//注解中用@表示注解声明,注解中Servlet资源的路径url为虚拟路径，也就是说我们的PeopleServlet的资源存放在项目的web/homework/people文件
//夹下，并以selAll命名，虚拟路径虽然不存在在实际的项目根目录下，但是是可以实际访问到的。我们可以把它认为是实际存在与项目根路径下的，
//也就是web/下。
@WebServlet(value = {"/homework/people/selAll"})
public class PeopleServlet extends HttpServlet {

    private PeopleService peopleServlet = new PeopleServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<People> peopleList = peopleServlet.show();
        req.setAttribute("list", peopleList);
        //相对路径
        //只要不以/开头的都是相对路径，相对路径是从当前资源所在的虚拟文件夹出发，找到其他资源的过程

        // 绝对路径
        //只要路径中以/开头的都叫做全路径，从项目根目录web出发找到其他资源的过程

        //如果请求是转发，/表示项目根目录，也就是idea中当前web项目中的web文件夹路径，为web
        //由于我们的servlet目前资源在web/homework/people下，所以如果要跳转到index.jsp（位置在web/index.jsp下）
        //需要向上跳跃两个目录,写成../../index.jsp,或者写绝对路径/index.jsp。
        // PS:如果请求是重定向，或者浏览器请求静态资源引用，如css，href，img，script。其中/表示的是tomcat文件夹下的
        // webapps文件夹，也就是tomcat/webapp文件夹，叫做服务器根目录。我们的项目是放在webapp下
        // PS：也就是说，除了请求是转发，/表示项目根目录，其他所有请求例如重定向或者请求静态资源，/表示的都是服务器根目录。

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
