<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

 <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
<div id="divLogin" class="securityForm">
	<c:if test="${param.failed=='true'}">
		<div>Your login attempt failed. Please try again.</div>
		<div>${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
	</c:if>
	<!-- 
	<h3>
        <spring:message code="login.title" text="default text" />
        <br>
        Current Locale : ${pageContext.response.locale}
    </h3>
     -->
     <h1>LOG IN:</h1>
     <p>
    <c:url var="loginUrl" value="/j_spring_security_check" />
	<form class="main" action="${loginUrl}" method="post">
		<input type="text" name="j_username" class="inputSignIn" value="Username" /><br /> 
		<input type="password" name="j_password" class="inputSignIn"/><br /> 
		<input type="checkbox" name="_spring_security_remember_me" /> Remember me<br /> 
		<p>
		<h1>
		<input type="submit" value='<spring:message code="login.form.button" />' />
		</h1>
	</form>
	<!--  
	<sec:authentication var="user" property="principal" />
	DETAILS: ${user}
	-->
</div>
 </sec:authorize>
