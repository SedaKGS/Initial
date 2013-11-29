

	

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="x" uri="http://template.seda.it/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:set value="POST" var="method"></c:set>
  <c:if test="${ action=='delete'}"> 
    <c:set value="DELETE" var="method"></c:set>
    <c:set value="true"   var="readOnly"></c:set>
  </c:if>

<c:if test="${ action=='edit'}">  
  <c:set value="PUT" var="method"></c:set>
</c:if>
	
<c:url value="test" var="actionForm">
   <c:param name="pageNumber" value="${ pageNumber}"/>
   <c:param name="rowsPerPage" value="${ rowsPerPage}"/>
</c:url>
	
<c:if test="${ action=='delete'}">
   <c:url value="test/${testData.id}" var="actionForm">  
   <c:param name="pageNumber" value="${ pageNumber}"/>
   <c:param name="rowsPerPage" value="${ rowsPerPage}"/>
</c:url>
	</c:if> 
	
<div id="divErrors" class="errors">
    ${ x:i18n('testData.esito')}
</div>
	
<div class="title" style="text-align:center">
    <h3> ${ x:i18n('formFormTest.title')}</h3>
</div>
	
	
<div class="newTest" style="margin-left:10px;min-width:70px;max-width:200px;">
    <a href="<c:url value="test"/>" style="margin-right:10px;"><input type="submit" value="${ x:i18n('formFormTest.new')}" style="width:100%;"/></a>
</div> 
	  
<form:form method="${ method}" action="${ actionForm}" commandName="testData">
  <div id="formBox" style="background-color:rgb(240,240,240);margin-top:15px;margin-bottom:15px;padding-top: 10px;margin-left: 10px;padding-bottom: 10px;"> 
       <div class="seda-ui-divrow" style="height:0px;">
           <label class="seda-ui-labelrow" style="height:30px" id="id">${ x:i18n('formFormTest.id')}</label>
           <form:input readonly="true"  path="id" maxlength="15" class="seda-ui-inputrow"/> 
       </div>                         
        <div class="seda-ui-divrow ">   
              <label class="seda-ui-labelrow" id="name">${ x:i18n('formFormTest.name')}</label>
              <form:input readonly="${ readOnly}" path="name" maxlength="$field.maxlenght" class="seda-ui-inputrow" style="width:200px"/>
              <form:errors path="name"  cssStyle="color:red;float:left;margin-left: 10px;"/>
        </div>
        <div class="seda-ui-divrow ">   
             <label class="seda-ui-labelrow" id="cognome">${ x:i18n('formFormTest.cognome')}</label>
             <form:input readonly="${ readOnly}" path="cognome" maxlength="$field.maxlenght" class="seda-ui-inputrow" style="width:200px"/>
             <form:errors path="cognome"  cssStyle="color:red;float:left;margin-left: 10px;"/>
        </div>
        <div class="seda-ui-divrow ">   
              <label class="seda-ui-labelrow" id="registrazione">${ x:i18n('formFormTest.registrazione')}</label>
              <form:select readonly="${ readOnly}" path="registrazione" maxlength="$field.maxlenght" class="seda-ui-inputrow" style="width:200px"/>
              <form:errors path="registrazione"  cssStyle="color:red;float:left;margin-left: 10px;"/>
        </div>
        <c:if test="${ action=='edit'}"> 
           <input type="submit" value="${ x:i18n('formFormTest.modify')}" style="width: auto;"/>
        </c:if>
        <c:if test="${ action=='delete'}"> 
           <input type="submit" value="${ x:i18n('formFormTest.cancel')}" style="width: auto;"/>
        </c:if>
        <c:if test="${ action==null}"> 
           <input type="submit" value="${ x:i18n('formFormTest.insert')}" style="width: auto;"/>
        </c:if>
  </div>
</form:form>
		
<x:datagrid action="" pageset="${ TestPage}" var="rowTest"  >
   <x:dgcolumn title="${ x:i18n('formForm.name')}">${ rowTest.name}</x:dgcolumn>
   <x:dgcolumn title="${ x:i18n('formForm.cognome')}">${ rowTest.cognome}</x:dgcolumn>
   <x:dgcolumn title="${ x:i18n('formForm.registrazione')}">${ rowTest.registrazione}</x:dgcolumn>
    <x:dgcolumn title="edit"><a href="<c:url value="$idUrlRow/${rowTest.id}?action=edit&pageNumber=${pageNumber}&rowsPerPage=${rowsPerPage}"/>">${ x:i18n('formFormTest.edit')}</a></x:dgcolumn>
    <x:dgcolumn title="cancel"><a href="<c:url value="$idUrlRow/${rowTest.id}?action=delete&pageNumber=${pageNumber}&rowsPerPage=${rowsPerPage}"/>">${ x:i18n('formFormTest.cancel')}</a></x:dgcolumn>
</x:datagrid>