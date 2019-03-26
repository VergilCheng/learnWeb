package cn.uestc.servlet;

import cn.uestc.pojo.AccountResult;
import cn.uestc.service.AccountService;
import cn.uestc.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private AccountService service = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String payAccount = req.getParameter("payAccount");
        String password = req.getParameter("password");
        String money = req.getParameter("money");
        String gainAccount = req.getParameter("gainAccount");
        String name = req.getParameter("name");
        //防止乱码
        resp.setCharacterEncoding("utf-8");
        AccountResult result = service.transferAccount(payAccount, password, money, gainAccount, name);
        HttpSession session = req.getSession();
        session.setAttribute("result",result);
        if (result.getState().equalsIgnoreCase(AccountResult.ERROR)) {
            resp.sendRedirect("/cmz_ReviewMybatis/error.jsp");
        } else {
            resp.sendRedirect("/cmz_ReviewMybatis/log");
        }

    }
}
