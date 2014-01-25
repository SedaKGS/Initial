<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://template.seda.it/tags" prefix="x"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/default.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/js/timepicker/jquery-ui-timepicker-addon.css"/>">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script src="<c:url value="/resources/js/timepicker/jquery-ui-timepicker-addon.js"/>"></script>

<script>
	$(document).ready(function() {
		// date picker i18N
		$.datepicker.setDefaults($.datepicker.regional["${pageContext.response.locale}"]);
	});
</script>

<title>${x:i18n('application.title')}</title>
</head>

<body dir="${x:i18ndir()}">
	<div id="container">
		<div id="headerLogo" style="margin-bottom: 5px;">
			<img src="<c:url value="/resources/images/logo_seda.png"/>" width="150"/>
		</div>	
		<div id="header">
			<x:include parameter="header" />
		</div>
		<div id="navigation">
			<x:include parameter="navigation" hasRoles="ROLE_USER,ROLE_ADMIN"/>
		</div>		
		<div id="content-container">
			<x:include parameter="content" />
		</div>
		<div id="footer">
			<x:include parameter="footer" />
		</div>		
	</div>
</body>
</html>
