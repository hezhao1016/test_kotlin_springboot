<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>

    <title>Data List</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
    <h2>用户列表</h2>
    <hr/>
    <table width="300"  border="1" style="border-collapse:collapse; text-align: center;font-size:12px;">
        <thead>
        <tr>
            <th>id</th>
            <th>姓名</th>
            <th>用户名</th>
        <tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.userName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>