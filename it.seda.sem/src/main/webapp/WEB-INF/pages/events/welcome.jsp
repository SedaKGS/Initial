<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Message : ${message}</h1>
	<ul>
		<c:url var="managerUrl" value="/manager/" />
		<sec:authorize url="${managerUrl}">
			<li><a id="navManagerLink" href="${managerUrl}">Manager</a></li>
		</sec:authorize>
		
		<c:url var="logoutUrl" value="/security/logout" />
		<li><a href="${logoutUrl}">Logout</a></li>
	</ul>
	
	
</body>
</html>
