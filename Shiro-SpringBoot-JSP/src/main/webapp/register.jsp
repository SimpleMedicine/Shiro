<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>register</title>
</head>
<body>
    <h1>注册页面</h1>
    <form action="${pageContext.request.contextPath}/user/register">
        用户名:<input type="text" name="username"><br>
        密码:<input type="text" name="password"><br>
        <input type="submit" value="立即注册">
    </form>
</body>
</html>