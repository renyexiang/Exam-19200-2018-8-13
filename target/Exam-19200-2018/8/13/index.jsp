<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>配置了拦截器，没登录之前，点击添加电影、所有电影列表都会跳转到登录界面</h1>
<ul>
    <li><a href="login.jsp">登录</a></li>
    <li><a href="addFilm.jsp">添加电影</a></li>
    <li><a href="main.jsp">所有电影列表</a></li>
</ul>
</body>
</html>
