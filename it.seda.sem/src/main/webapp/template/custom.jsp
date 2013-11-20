<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://template.seda.it/tags" prefix="x" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../resources/Seda.css">
<link rel="stylesheet" type="text/css" href="${x:theme('css')}">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title>${x:i18n('title')}</title>
</head>
<body dir="${x:i18ndir()}">
	<div id="container">
		<div id="header">
			<div id="headerLeft">
				<x:include parameter="headerLeft" />
			</div>
			<div id="headerRight">
				<x:include parameter="headerRight" />
			</div>
		</div>
		<div id="content">
			<div id="contentLeft">
				<x:include parameter="contentLeft" />
			</div>
			<div id="contentRight">
				<x:include parameter="contentRight" />
				<div id="contentTop">
					<x:include parameter="contentTop" />
				</div>
				<div id="contentBottom">
					<x:include parameter="contentBottom" />
				</div>
			</div>
		</div>
		<div id="push">
		</div>
	</div>
	<div id="footer">
			<x:include parameter="footer" />
	</div>
</body>
</html>