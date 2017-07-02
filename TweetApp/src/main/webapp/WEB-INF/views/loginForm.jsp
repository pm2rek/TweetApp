<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>${invalid}</p>
	<form:form method="post" modelAttribute="user">
	<form:errors path="login" cssClass="error" element="div" />
	Login:
	<form:input path="login" />
	<br>
	<form:errors path="password" cssClass="error" element="div" />
	Password:
	<form:password path="password" />
	<br>
	<input type="submit" value="Submit">

	</form:form>
	
	<a href="./register"> Sign up</a>
</body>
</html>