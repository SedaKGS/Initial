<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Please log in</title>
</head>
<body>
	<c:if test="${param.failed == true}">
		<div>Your login attempt failed. Please try again.</div>
		<div>${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
	</c:if>
	<h1>log in personalizzato</h1>
	<h3>
        <spring:message code="login.title" text="default text" />
        <br>
        Current Locale : ${pageContext.response.locale}
    </h3>
    <c:url var="loginUrl" value="/j_spring_security_check" />
	<form class="main" action="${loginUrl}" method="post">
		Username: <input type="text" name="j_username" /><br /> Password: <input
			type="password" name="j_password" /><br /> <input type="checkbox"
			name="_spring_security_remember_me" /> Remember me<br /> <input
			type="submit" value='<spring:message code="login.form.button" />' />
	</form>
</body>
</html>
