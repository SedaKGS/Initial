<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://template.seda.it/tags" prefix="template" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../resources/layout_it.css">
<title>Insert title here</title>
</head>
<body>
 <div id="Container">
  <div id="Header">
    <div id="HeaderLeft">
      <template:include parameter="headerLeft"/>         
    </div>
    <div id="HeaderRight">
       <template:include parameter="headerRight"/>
    </div>
  </div>
  <div id="Context">
    <div id="ContextLeft">
      <template:include parameter="contextLeft"/>
    </div>
	<div id="ContextRight">
      <template:include parameter="contextRight"/>
      <div id="ContextTop">
         <template:include parameter="contextTop"/>
      </div> 
      <div id="ContextBottom">
         <template:include parameter="contextBottom"/>
      </div>         
    </div>
  </div>
  <div id="footer">
    <template:include parameter="footer"/>
  </div>
</div>
</body>
</html>