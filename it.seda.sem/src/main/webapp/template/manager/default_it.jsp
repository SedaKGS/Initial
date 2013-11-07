<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://template.seda.it/tags" prefix="template" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
     <template:include parameter="title" args="${titleargs}"/>
</title>
</head>
<body>
<br>
Questa è la default_it.jsp, il footer non c'è!
${template:i18n('Questo messaggio è stampato dalla funzione i18n!')}
${template:i18n2('manager.formclient.title',titleargs)}

<table style="width: 100%;">
    <tr><td><template:include parameter="myMessage" hasRoles="notExists"></template:include></td></tr>
    <tr><td colspan="2"><template:include parameter="header"/></td></tr>
	<tr>
		<td style="width: 200px;"><template:include parameter="menu"/></td>
		<td><template:include parameter="body"/></td>		
	</tr>	
</table>

</body>
</html>