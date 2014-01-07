<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://template.seda.it/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<sec:authentication var="user" property="principal" />
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')" var="authenticated"/>
<table>
	<tr>
		<td>
			<c:if test="${authenticated}">${x:i18n('application.welcome')} ${user.firstName} ${user.lastName}</c:if>
			<c:if test="${!authenticated}">${x:i18n('application.welcome')}</c:if>
		</td>
		<td>
			<c:if test="${authenticated}"><a href="<c:url value="/security/logout" />">Logout</a></c:if>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>
			<c:if test="${authenticated}"><a href="<c:url value="/security/changePassword" />">Change Password</a></c:if>
		</td>
	</tr>
	
	<tr>
  
	<c:choose>
		<c:when test="${fn:startsWith(x:i18nlocale(),'it')}"><c:set var="styleIT" value="font-weight: bold;"/></c:when>
		<c:when test="${fn:startsWith(x:i18nlocale(),'en')}"><c:set var="styleEN" value="font-weight: bold;"/></c:when>
		<c:otherwise>${pageContext.response.locale}</c:otherwise>
	</c:choose>
	
		<td colspan="2"> 
			<ul class="languages">
				<li><a href="?language=it" style="${styleIT}">${x:i18n('language.italian')}</a></li>
				<li><a href="?language=en" style="${styleEN}">${x:i18n('language.english')}</a></li>
			</ul>
		</td>
	</tr>
</table>