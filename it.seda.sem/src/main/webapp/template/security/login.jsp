<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="x" uri="http://template.seda.it/tags" %>

<sec:authorize access="hasRole('ROLE_ANONYMOUS')">

<div id="divLogin" >
	<c:if test="${param.failed=='true'}">
		<div>
			${x:i18n('login.credentials.error')}
		</div>
		<div>${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
	</c:if>
	<!-- 
	<h3>
        <spring:message code="login.title" text="default text" />
        <br>
        Current Locale : ${pageContext.response.locale}
    </h3>
     -->
     <h3>LOG IN:</h3>

    <c:url var="loginUrl" value="/j_spring_security_check" />
	<form class="main" action="${loginUrl}" method="post">
		<ul style="list-style-type: none;">
			<li>
				<label for="j_username">${x:i18n('login.form.username')}</label>
			</li>
			<li>
				<input type="text" name="j_username"/>
			</li>
			<li>
				<label for="j_password">${x:i18n('login.form.password')}</label>
			</li>
			<li>
				<input type="password" name="j_password"/>
			</li>
			<li>
				<input type="checkbox" name="_spring_security_remember_me" /> ${x:i18n('security.remember.me')}
			</li>
		</ul>

		<input type="submit" value="${x:i18n('login.form.button')}" />		 

	</form>
	<!--  
	<sec:authentication var="user" property="principal" />
	DETAILS: ${user}
	-->
</div>
 </sec:authorize>
