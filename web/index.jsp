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
      <img src="/image/a.png"><br/>
      <table>
          <tr>
              <th>编号</th>
              <th>姓名</th>
              <th>年龄</th>
          </tr>
          <c:forEach items="${list}" var="peo">
              <tr>
                  <td>${peo.id}</td>
                  <td>${peo.name}</td>
                  <td>${peo.age}</td>
              </tr>
          </c:forEach><br/>
      </table>

  $END$
  </body>
</html>
