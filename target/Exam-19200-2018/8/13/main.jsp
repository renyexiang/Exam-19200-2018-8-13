<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Entity.Film" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ryx
  Date: 2018/8/13
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/resources/js/jquery-3.0.0.min.js"></script>
</head>
<body>
<%List<Film> films=(List)request.getSession().getAttribute("films");%>
<div>
    <a href="addFilm.jsp">添加</a>
</div>
<div>
<table style="border:1px solid #d9d9d9 ">
    <tr>
        <th>id</th>
        <th>标题</th>
        <th>描述</th>
        <th>语言</th>
        <th>更新</th>
        <th>删除</th>
    </tr>
    <c:forEach var="film" items='<%=films %>'>
        <tr>
            <td>${film.id}</td>
            <td>${film.title}</td>
            <td>${film.description}</td>

            <td>${film.language_name}</td>
            <td><a href="updateFilm.jsp?id=${film.id}&&title=${film.title}&&description=${film.description}&&name=${film.language_name}">更新</a>
            </td>
            <td><a href="/deleteServlet?id=${film.id}">删除</a></td>
        </tr>

    </c:forEach>
</table>
</div>
</body>
</html>
