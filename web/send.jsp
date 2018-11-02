<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/20
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
<script src="<%=path%>/static/js/jquery.min.js"></script>
<script>
    $.ajax({
        type: "POST",
        url: "/qqPhone/success",
        contentType: "application/json; charset=utf-8",
        data: '{"qq": "695079132","account": "151562637","status": 5}',
        dataType: "json",
        success: function (message) {
        },
        error: function (message) {
        }
    });
</script>
</html>
