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
<form:form method="post" modelAttribute="user">
		<form:errors path="login" cssClass="error" element="div" />
		Login:<form:input path="login" /><br>
		<form:errors path="password" cssClass="error" element="div"/>
		Password: <form:password path="password" /><br>
		<form:errors path="email" cssClass="error" element="div"/>
		Email: <form:input path="email" /><br>
		<form:errors path="firstName" cssClass="error" element="div" />
		First Name:*<form:input path="firstName" /><br>
		<form:errors path="lastName" cssClass="error" element="div"/>
		Last Name:* <form:input path="lastName" /><br>
		<p>* - optional </p>
		<input type="submit" value="Submit">

	</form:form>
</body>
</html>