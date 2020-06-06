<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>登录界面</title>
</head>
<body>
    <h1 style="color:red">登录</h1>
    <form id="indexform" name="indexForm" action="/user/login" method="post">
                账号：<input type="text" name="username">
                密码：<input type="password" name="password">
        <input type="submit" value="登录" style="color:#BC8F8F">
        
    </form>
</body>
</html>
