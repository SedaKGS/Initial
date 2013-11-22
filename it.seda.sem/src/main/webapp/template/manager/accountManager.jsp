<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="x" uri="http://template.seda.it/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form Client</title>
</head>
<body>

	
	
	<c:set value="POST" var="method"></c:set>
	<c:if test="${action=='delete'}"> 
	 <c:set value="DELETE" var="method"></c:set>
	 <c:set value="true"   var="readOnly"></c:set>
	</c:if>
	<c:if test="${action=='edit'}">  
	  <c:set value="PUT" var="method"></c:set>
	  <c:set value="/sem/manager/account/" var="actionForm"></c:set>
	</c:if>
	
	<c:url value="/manager/account" var="actionForm">
		<c:param name="pageNumber" value="${pageNumber}"/>
		<c:param name="rowsPerPage" value="${rowsPerPage}"/>
	</c:url>
	
    <div id="divErrors" class="errors">
		 ${x:i18n('accountData.esito')}
	</div>
	
	<h3> ${x:i18n('account.manager.title')}</h3>
	
	<div id="formBox" style="background-color:rgb(240,240,240);margin-top:15px;margin-bottom:15px;padding-top: 10px;margin-left: 10px;padding-bottom: 10px;">
	 <c:if test="${action=='edit'||action=='delete'}">
	  <a href="<c:url value="/manager/account"/>" class="fright" style="margin-right:10px;"><input type="submit" value="${x:i18n('account.manager.newAccount')}" style="width:100%;"/></a>
	  </c:if>
	 <form:form method="${method}" action="${actionForm}" commandName="accountData">
	  <div class="seda-ui-divrow"> 
	   <c:if test="${action!='edit'}">  
	     <label class="seda-ui-labelrow" id="username">${x:i18n('account.manager.username')}</label>
	    <form:input readonly="${readOnly}" path="username" maxlength="15" class="seda-ui-inputrow"/>
	   </c:if>
	   <c:if test="${action=='edit'}">  
	    <form:hidden  path="username" maxlength="15" class="seda-ui-inputrow"/>
	   </c:if>
	  </div>
	  <form:errors path="username"  cssStyle="color:red"/>
	 <div class="seda-ui-divrow">
	  <label class="seda-ui-labelrow" id="firstname">${x:i18n('account.manager.firstname')}</label>
	  <form:input readonly="${readOnly}" path="firstName"  class="seda-ui-inputrow" />
	 </div>
	   <form:errors path="firstName"  cssStyle="color:red"/>
	 <div class="seda-ui-divrow">
	  <label class="seda-ui-labelrow" id="lastname">${x:i18n('account.manager.lastname')}</label>
	  <form:input readonly="${readOnly}" path="lastName"  class="seda-ui-inputrow"/>
	 </div>
	  <form:errors path="lastName"  cssStyle="color:red" />
	 <div class="seda-ui-divrow">
	  <label class="seda-ui-labelrow" id="email">${x:i18n('account.manager.email')}</label>
	  <form:input readonly="${readOnly}" path="email"  class="seda-ui-inputrow"/>
	 </div>
	  <form:errors path="email"  cssStyle="color:red"/>
	 <div class="seda-ui-divrow">
	  <label class="seda-ui-labelrow" id="email">${x:i18n('account.manager.group')}</label>
	  <form:select disabled="${readOnly}" path="groupName" class="seda-ui-inputrow">
                     <form:options items="${groupsMap}"  />
      </form:select> 
	 </div>
	 <div class="seda-ui-divrow">
	  <label class="seda-ui-labelrow" style="height:30px" id="checkboxLabelVoid"></label>
	   <form:checkbox disabled="${readOnly}" path="enabled" class="fleft"/>
	  <label  id="enabled" class="seda-ui-labelrow" style="text-align:left;padding-left:5px">${x:i18n('account.manager.enabled')}</label>
	  </div>
	  <c:if test="${action=='edit'}"> 
	  
	<input type="submit" value="${x:i18n('account.manager.modify')}"/>
	</c:if>
	<c:if test="${action=='delete'}"> 
		<input type="submit" value="${x:i18n('account.manager.cancel')}"/>
	</c:if>
	<c:if test="${action==null}"> 
		<input type="submit" value="${x:i18n('account.manager.insert')}"/>
	</c:if>
	</form:form>
	</div>
	
	<x:datagrid action="" pageset="${accountsPage}" var="accountRow" >
	  <x:dgcolumn title="${x:i18n('account.manager.username')}">${accountRow.username}</x:dgcolumn>
	  <x:dgcolumn title="${x:i18n('account.manager.firstname')}">${accountRow.firstName}</x:dgcolumn>
	  <x:dgcolumn title="${x:i18n('account.manager.lastname')}">${accountRow.lastName}</x:dgcolumn>
	  <x:dgcolumn title="${x:i18n('account.manager.email')}">${accountRow.email}</x:dgcolumn>
	  <x:dgcolumn title="${x:i18n('account.manager.group')}">${accountRow.groupName}</x:dgcolumn>
	  <x:dgcolumn title="${x:i18n('account.manager.enabled')}">${accountRow.enabled}</x:dgcolumn>
	  <x:dgcolumn title="edit"><a href="<c:url value="/manager/account/${accountRow.username}?action=edit&pageNumber=${pageNumber}&rowsPerPage=${rowsPerPage}"/>">Edit</a></x:dgcolumn>
	  <x:dgcolumn title="cancel"><a href="<c:url value="/manager/account/${accountRow.username}?action=delete&pageNumber=${pageNumber}&rowsPerPage=${rowsPerPage}"/>">Cancel</a></x:dgcolumn>
	</x:datagrid>

</body>
</html>