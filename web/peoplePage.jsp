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
      <table>
          <tr>
              <th>编号</th>
              <th>姓名</th>
              <th>年龄</th>
          </tr>
          <c:forEach items="${pageInfo.list}" var="peo">
              <tr>
                  <td>${peo.id}</td>
                  <td>${peo.name}</td>
                  <td>${peo.age}</td>
              </tr>
          </c:forEach><br/>
      </table>
      <a href="peoplePage?pageNumber=${pageInfo.pageNumber-1 }&pageSize=${pageInfo.pageSize}" <c:if test="${pageInfo.pageNumber<=1 }">  onclick="javascript:return false;" </c:if> >上一页</a>
      <a href="peoplePage?pageNumber=${pageInfo.pageNumber+1 }&pageSize=${pageInfo.pageSize}" <c:if test="${pageInfo.pageNumber>=pageInfo.total }">  onclick="javascript:return false;" </c:if> >下一页</a>
  $END$
  </body>
</html>
