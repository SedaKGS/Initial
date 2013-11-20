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

	<c:url value="" var="actionForm">
		<c:param name="pageSize" value="${pageSize}"/>
		<c:param name="rowsPerPage" value="${rowsPerPage}"/>
	</c:url>
	
	<c:set value="POST" var="method"></c:set>
	<c:if test="${action=='delete'}"> 
	 <c:set value="DELETE" var="method"></c:set>
	 <c:set value="true"   var="readOnly"></c:set>
	</c:if>
	<c:if test="${action=='edit'}">  
	 <c:set value="PUT" var="method"></c:set>
	</c:if>
	
    <div id="divErrors" class="errors">
		 ${x:i18n('accountData.esito')}
	</div>
	
	<h3> ${x:i18n('account.manager.title')}</h3>
	<form:form method="${method}" commandName="accountData">
	<div id="formBox" style="background-color:rgb(240,240,240);margin-top:15px;margin-bottom:15px;padding-top: 10px;margin-left: 10px;">
	 <div class="seda-ui-divrow">
	  <label class="seda-ui-labelrow" id="username">${x:i18n('account.manager.username')}</label>  
	  <form:input readonly="${readOnly}" path="username" maxlength="15" class="seda-ui-inputrow" />
	 </div>
	  <form:errors path="username"  cssStyle="color:red"/>
	 <div class="seda-ui-divrow">
	  <label class="seda-ui-labelrow" id="firstname">${x:i18n('account.manager.firstname')}</label>
	  <form:input readonly="${readOnly}" path="firstName" maxlength="15" class="seda-ui-inputrow" />
	 </div>
	   <form:errors path="firstName"  cssStyle="color:red"/>
	 <div class="seda-ui-divrow">
	  <label class="seda-ui-labelrow" id="lastname">${x:i18n('account.manager.lastname')}</label>
	  <form:input readonly="${readOnly}" path="lastName" maxlength="15" class="seda-ui-inputrow"/>
	 </div>
	  <form:errors path="lastName"  cssStyle="color:red" />
	 <div class="seda-ui-divrow">
	  <label class="seda-ui-labelrow" id="email">${x:i18n('account.manager.email')}</label>
	  <form:input readonly="${readOnly}" path="email" maxlength="15" class="seda-ui-inputrow"/>
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
	</div>
	</form:form>
	
   <form:form method="${method}" commandName="accountData">
		<table>
		    <c:if test="${action!='edit'}">
			<tr>
				<td>${x:i18n('account.manager.username')}</td>
				<td><form:input readonly="${readOnly}" path="username" maxlength="15"/>
				</td>
				<td><form:errors path="username"  cssStyle="color:red" />
				</td>
			</tr>
			</c:if>
			<tr>
				<td>${x:i18n('account.manager.firstname')}</td>
				<td><form:input readonly="${readOnly}" path="firstName" />
				</td>
				<td><form:errors path="firstName" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td>${x:i18n('account.manager.lastname')}</td>
				<td><form:input readonly="${readOnly}" path="lastName" />
				</td>
				<td><form:errors path="lastName" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td>${x:i18n('account.manager.email')}</td>
				<td><form:input readonly="${readOnly}" path="email" />
				</td>
				<td><form:errors path="email" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td>${x:i18n('account.manager.group')}</td>
				<td>
				  <form:select disabled="${readOnly}" path="groupName">
                     <form:options items="${groupsMap}" />
                  </form:select>
				</td>
				<td><form:errors path="groupName" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td>${x:i18n('account.manager.enabled')}</td>
				<td>
				 <form:checkbox disabled="${readOnly}" path="enabled"/>
				</td>
				<td>
				 <form:errors path="enabled" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
			    <c:if test="${action=='edit'}"> 
				   <td colspan="3"><input type="submit" value="${x:i18n('account.manager.modify')}"/></td>
				</c:if>
				<c:if test="${action=='delete'}"> 
				   <td colspan="3"><input type="submit" value="${x:i18n('account.manager.cancel')}"/></td>
				</c:if>
				<c:if test="${action==null}"> 
				   <td colspan="3"><input type="submit" value="${x:i18n('account.manager.insert')}"/></td>
				</c:if>
			</tr>
		</table>
	</form:form>
	
	
	
	<x:datagrid action="" pageset="${accountsPage}" var="accountRow" >
	  <x:dgcolumn title="username">${accountRow.username}</x:dgcolumn>
	  <x:dgcolumn title="firstname">${accountRow.firstName}</x:dgcolumn>
	  <x:dgcolumn title="lastName">${accountRow.lastName}</x:dgcolumn>
	  <x:dgcolumn title="email">${accountRow.email}</x:dgcolumn>
	  <x:dgcolumn title="groupName">${accountRow.groupName}</x:dgcolumn>
	   <x:dgcolumn title="active">${accountRow.enabled}</x:dgcolumn>
	  <x:dgcolumn title="edit"><a href="<c:url value="/manager/account/${accountRow.username}?action=edit"/>">Edit</a></x:dgcolumn>
	  <x:dgcolumn title="cancel"><a href="<c:url value="/manager/account/${accountRow.username}?action=delete"/>">Cancel</a></x:dgcolumn>
	</x:datagrid>

</body>
</html>