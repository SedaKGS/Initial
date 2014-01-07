<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="x" uri="http://template.seda.it/tags" %>

<title>${x:i18n('formClient.page.Title')}</title>

<c:set value="POST" var="method"></c:set>
<c:if test="${action=='delete'}">
	<c:set value="DELETE" var="method"></c:set>
	<c:set value="true" var="readOnly"></c:set>
</c:if>
<c:if test="${action=='edit'}">
	<c:set value="PUT" var="method"></c:set>
</c:if>

<c:url value="/manager/cliente" var="actionForm">
	<c:param name="pageNumber" value="${pageNumber}" />
	<c:param name="rowsPerPage" value="${rowsPerPage}" />
</c:url>

<c:if test="${action=='delete'}">
	<c:url value="/manager/cliente/${clienteData.id}" var="actionForm">
		<c:param name="pageNumber" value="${pageNumber}" />
		<c:param name="rowsPerPage" value="${rowsPerPage}" />
	</c:url>
</c:if>
 

<div id="divErrors" class="errors">${x:i18n(clienteData.esito)}</div>

<div class="title" style="text-align: center">
	<h3>${x:i18n('client.manager.title')}</h3>
</div>

<div class="newClient"
	style="margin-left: 10px; min-width: 70px; max-width: 200px;">
	<a href="<c:url value="/manager/cliente"/>" style="margin-right: 10px;"><input
		type="submit" value="${x:i18n('client.manager.newClient')}"
		style="width: 100%;" /></a>
</div>


<form:form method="${method}" action="${actionForm}" commandName="clienteData">

	<div id="formBox"
		style="background-color: rgb(240, 240, 240); margin-top: 15px; margin-bottom: 15px; padding-top: 10px; margin-left: 10px; padding-bottom: 10px;">

		<div class="seda-ui-divrow" style="height: 0px;">
			<form:hidden path="id" maxlength="15" class="seda-ui-inputrow" />
		</div>

		<div class="seda-ui-divrow">
			<label class="seda-ui-labelrow" id="name">${x:i18n('client.manager.nome')}</label>
			<form:input readonly="${readOnly}" path="nome" maxlength="15"
				class="seda-ui-inputrow" style="width:200px" />
			<form:errors path="nome"
				cssStyle="color:red;float:left;margin-left: 10px;" />
		</div>

		<div class="seda-ui-divrow" style="height: 100px">
			<label class="seda-ui-labelrow" id="descrizione">${x:i18n('client.manager.descrizione')}</label>
			<form:textarea readonly="${readOnly}" path="descrizione"
				class="seda-ui-inputrow" style="width:300px;height:70px" />
			<form:errors path="descrizione"
				cssStyle="color:red;float:left;margin-left: 10px;" />
		</div>

		<c:if test="${action=='edit'}">
			<input type="submit" value="${x:i18n('client.manager.modify')}"
				style="width: auto;" />
		</c:if>

		<c:if test="${action=='delete'}">
			<input type="submit" value="${x:i18n('client.manager.cancel')}"
				style="width: auto;" />
		</c:if>
		<c:if test="${action==null}">
			<input type="submit" value="${x:i18n('client.manager.insert')}"
				style="width: auto;" />
		</c:if>

	</div>
</form:form>


<x:datagrid action="" pageset="${clientiPage}" var="clientRow">
	<x:dgcolumn title="${x:i18n('client.manager.nome')}">${clientRow.nome}</x:dgcolumn>
	<x:dgcolumn title="${x:i18n('client.manager.descrizione')}">${clientRow.descrizione}</x:dgcolumn>
	<x:dgcolumn title="${x:i18n('client.manager.registrazione')}">${clientRow.registrazione}</x:dgcolumn>
	<x:dgcolumn title="edit">
		<a href="<c:url value="/manager/cliente/${clientRow.id}?action=edit&pageNumber=${pageNumber}&rowsPerPage=${rowsPerPage}"/>">Edit</a>
	</x:dgcolumn>
	<x:dgcolumn title="cancel">
		<a href="<c:url value="/manager/cliente/${clientRow.id}?action=delete&pageNumber=${pageNumber}&rowsPerPage=${rowsPerPage}"/>">Cancel</a>
	</x:dgcolumn>
</x:datagrid>

