<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: ryx
  Date: 2018/8/13
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
<%ArrayList list=(ArrayList)request.getSession().getAttribute("languages");
%>
<form action="/updateServlet">
    id:<input type="text" name="id" value="<%=request.getParameter("id")%>" readonly="readonly"/><br>
    标题：<input type="text" name="title" placeholder="<%=request.getParameter("title")%>" value="<%=request.getParameter("title")%>"><br>
    描述: <input type="text" name="description" placeholder="<%=request.getParameter("description")%>" value="<%=request.getParameter("description")%>"><br>
    语言:
    <select name="language"  >
        <option><%=request.getParameter("name")%></option>
        <c:forEach var="lan" items='<%=list %>'>

                <option>${lan.name}</option>

        </c:forEach>
    </select>
    <br>
    <input type="submit" value="更改">
</form>
</body>
</html>
