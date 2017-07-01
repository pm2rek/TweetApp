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
<form:form method="post" modelAttribute="tweet">
		<form:errors path="title" cssClass="error" element="div" />
		Title:<form:input path="title" /><br>
		<form:errors path="tweetText" cssClass="error" element="div"/>
		Text: <form:input path="tweetText" /><br>
		<form:errors path="user" cssClass="error" element="div"/>
		User: <form:select path="user.id" items="${users}" itemlabel="fullName" itemValue="id"/><br>
		<input type="submit" value="Submit">

	</form:form>
	<a href="..${path}/list"> back</a>
</body>
</html>