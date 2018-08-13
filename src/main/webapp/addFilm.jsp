<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Entity.Film" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: ryx
  Date: 2018/8/13
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/resources/js/jquery-3.0.0.min.js"></script>
</head>
<body>
<%ArrayList list=(ArrayList)request.getSession().getAttribute("languages");%>
<form action="/addServlet">

    标题：<input type="text" name="title"><br>
    描述: <input type="text" name="description"><br>
    语言:
    <select name="language"  >
        <c:forEach var="lan" items='<%=list %>'>
            <option>${lan.name}</option>
        </c:forEach>
   </select>
    <br>
    <%--<select name="language" id="typeId">
        <option>请选择</option>
    </select>
    <br>--%>
    <input type="submit" value="添加">
</form>
<%--<script>
    $(document).ready(function(){
        $("#typeId").onclick(function(){
            $.ajax({
                type:"get",
                url:"${pageContext.request.contextPath }/languageServlet",
                success:function(data) {
                    for (var i = 0; i < data.length; i++) {
                        $("#typeId").append("<option>"+data[i].name+"</option>");
                    }

                }
            });

        });
    })
</script>--%>
</body>
</html>
