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
    <p>${result.message}</p>
    <table h>
      <tr>
        <th>编号</th>
        <th>转账账号</th>
        <th>收款账号</th>
        <th>转账金额</th>
      </tr>
      <c:forEach items="${pageInfo.list}" var="log">
        <tr>
          <td>${log.id}</td>
          <td>${log.acconOut}</td>
          <td>${log.acconIn}</td>
          <td>${log.balance}</td>
        </tr>
      </c:forEach><br/>
    </table>
    <a href="log?pageNumber=${pageInfo.pageNumber-1 }&pageSize=${pageInfo.pageSize}" <c:if test="${pageInfo.pageNumber<=1 }">  onclick="javascript:return false;" </c:if> >上一页</a>
    <a href="log?pageNumber=${pageInfo.pageNumber+1 }&pageSize=${pageInfo.pageSize}" <c:if test="${pageInfo.pageNumber>=pageInfo.total }">  onclick="javascript:return false;" </c:if> >下一页</a>
    </table>
  </body>
</html>
