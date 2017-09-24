<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
success
<a href="teacher">teacher click me</a><br>
<a href="admin">admin click me</a><br>
<a href="student">student click me</a>

<shiro:hasRole name="admin">
	我有admin的角色哦  --<shiro:principal/>
</shiro:hasRole>
<shiro:hasPermission name="student:create">
	我有student:create权限 -- <shiro:principal/>
</shiro:hasPermission>

</body>
</html>