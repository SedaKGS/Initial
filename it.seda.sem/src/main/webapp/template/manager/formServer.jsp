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
<title>${x:i18n('formServer.page.Title')}</title>
</head>
<body>
	<c:set value="POST" var="method"></c:set>
	<c:if test="${action=='delete'}"> 
	 <c:set value="DELETE" var="method"></c:set>
	 <c:set value="true"   var="readOnly"></c:set>
	</c:if>
	<c:if test="${action=='edit'}">  
	  <c:set value="PUT" var="method"></c:set>
	  <c:set value="/sem/manager/client/" var="actionForm"></c:set>
	</c:if>
	
	<c:url value="/manager/client" var="actionForm">
		<c:param name="pageNumber" value="${pageNumber}"/>
		<c:param name="rowsPerPage" value="${rowsPerPage}"/>
	</c:url>
	
    <div id="divErrors" class="errors">
		 ${x:i18n('serverData.esito')}
	</div>
	
	<h3> ${x:i18n('server.manager.title')}</h3>
	
	<div id="formBox" style="background-color:rgb(240,240,240);margin-top:15px;margin-bottom:15px;padding-top: 10px;margin-left: 10px;padding-bottom: 10px;">
	 <c:if test="${action=='edit'||action=='delete'}">
	  <a href="<c:url value="/manager/client"/>" class="fright" style="margin-right:10px;"><input type="submit" value="${x:i18n('server.manager.newServer')}" style="width:100%;"/></a>
	  </c:if>
	 <form:form method="${method}" action="${actionForm}" commandName="clientData">
	   <div class="seda-ui-divrow" style="height:0px;">
	     <form:hidden  path="id" maxlength="15" class="seda-ui-inputrow"/> 
	  </div>
	  <div class="seda-ui-divrow">   
	    <label class="seda-ui-labelrow" id="name">${x:i18n('server.manager.nome')}</label>
	    <form:input readonly="${readOnly}" path="nome" maxlength="15" class="seda-ui-inputrow" style="width:300px"/>
	  </div>
	  <form:errors path="nome"  cssStyle="color:red"/>
	 <div class="seda-ui-divrow">
	  <label class="seda-ui-labelrow" id="descrizione">${x:i18n('server.manager.descrizione')}</label>
	  <form:input readonly="${readOnly}" path="descrizione"  class="seda-ui-inputrow" style="width:300px" />
	 </div>
	 <div class="seda-ui-divrow">
	  <label class="seda-ui-labelrow" id="descrizione">${x:i18n('server.manager.ip')}</label>
	  <form:input readonly="${readOnly}" path="ip"  class="seda-ui-inputrow" style="width:300px" />
	 </div>
	   <form:errors path="descrizione"  cssStyle="color:red"/>
	 
	 
	  <c:if test="${action=='edit'}"> 
	  
	<input type="submit" value="${x:i18n('server.manager.modify')}" style="width: auto;"/>
	</c:if>
	<c:if test="${action=='delete'}"> 
		<input type="submit" value="${x:i18n('server.manager.cancel')}" style="width: auto;"/>
	</c:if>
	<c:if test="${action==null}"> 
		<input type="submit" value="${x:i18n('server.manager.insert')}" style="width: auto;"/>
	</c:if>
	</form:form>
	</div>
	
	<x:datagrid action="" pageset="${serversPage}" var="severRow"  >
	  <x:dgcolumn title="${x:i18n('server.manager.nome')}">${serverRow.nome}</x:dgcolumn>
	  <x:dgcolumn title="${x:i18n('server.manager.descrizione')}">${serverRow.descrizione}</x:dgcolumn>
	  <x:dgcolumn title="${x:i18n('server.manager.registrazione')}">${serverRow.registrazione}</x:dgcolumn>
	  <x:dgcolumn title="edit"><a href="<c:url value="/manager/client/${serverRow.id}?action=edit&pageNumber=${pageNumber}&rowsPerPage=${rowsPerPage}"/>">Edit</a></x:dgcolumn>
	  <x:dgcolumn title="cancel"><a href="<c:url value="/manager/client/${serverRow.id}?action=delete&pageNumber=${pageNumber}&rowsPerPage=${rowsPerPage}"/>">Cancel</a></x:dgcolumn>
	</x:datagrid>

</body>
</html>