<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form Client</title>
</head>
<body>
   <form:form method="POST" commandName="initialClientData">
		<!--<form:errors path="*" cssClass="errorblock" element="div" />-->
		<table>
			<tr>
				<td>Cliente :</td>
				<td><form:input path="cliente" />
				</td>
				<td><form:errors path="cliente"  cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td>Descrizione :</td>
				<td><form:input path="descrizione" />
				</td>
				<td><form:errors path="descrizione" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td>Registrazione :</td>
				<td><form:input path="registrazione" />
				</td>
				<td><form:errors path="registrazione" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" /></td>
			</tr>
		</table>
	</form:form>
 
</body>
</html>