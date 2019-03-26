<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/20
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="/cmz_ReviewMybatis/account" method="post">
    转账账户:<input type="text" name="payAccount"/><br/>
    密码:<input type="password" name="password"/><br/>
    金额:<input type="text" name="money"/><br/>
    收款帐号:<input type="text" name="gainAccount"/><br/>
    收款姓名:<input type="text" name="name"/><br/>
    <input type="submit" value="转账"/>
  </form>
  </body>
</html>
