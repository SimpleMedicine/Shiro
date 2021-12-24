<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%--引入Shiro标签--%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
</head>
<body>
    <h1>系统主页V1.0</h1>
    <a href="${pageContext.request.contextPath}/user/logout">退出用户</a>

    <ul>
        <%--user或者admin都能看到--%>
        <shiro:hasAnyRoles name="user,admin">
            <li><a href="">用户管理</a>
                <ul>
                    <%--user对所有资源具有add操作权限--%>
                    <shiro:hasPermission name="user:add:*">
                        <li><a href="">添加</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:delete:*">
                        <li><a href="">删除</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="user:update:*">
                        <li><a href="">修改</a></li>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="order:find:*">
                        <li><a href="">查询</a></li>
                    </shiro:hasPermission>
                </ul>
            </li>
        </shiro:hasAnyRoles>
        <%--具有admin角色才能看见--%>
        <shiro:hasRole name="admin">
            <li><a href="">商品管理</a></li>
            <li><a href="">订单管理</a></li>
            <li><a href="">物流管理</a></li>
        </shiro:hasRole>
    </ul>
</body>
</html>