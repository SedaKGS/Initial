<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose language</title>
</head>
<body>
       <h3>
        <spring:message code="generalMessages.titolo" text="default text" />
        <br>
        Current Locale : ${pageContext.response.locale}
    </h3>
    <a href="welcome">avanti</a>
</body>
</html>