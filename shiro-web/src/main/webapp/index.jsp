<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<html>
<body>
<h2>Hello World!</h2>
<shiro:authenticated>
    <shiro:principal />
    <shiro:hasRole name="admin"><a href="/user/getAll">查询所有用户</a></shiro:hasRole>

    <a href="/user/logout">登出</a>
</shiro:authenticated>
<shiro:notAuthenticated>
    <a href="/user/login">请登录</a>
</shiro:notAuthenticated>
</body>
</html>
