<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="divChangePassword" class="securityForm">
    <c:if test="${!changePassword.esito}">
		<div id="divErrors" class="errors">
		  Aggiornamento non riuscito, reinserisci i dati.
		</div>
	</c:if>
   <form:form method="POST" commandName="changePassword">
		<table>
		    <tr>
		      <td>
		         <form:errors path="*" class="errors"/>       
		      </td>
		    </tr>
			<tr>
				<td>Username :</td>
				<td><form:input path="username" class="inputSignIn"/>
				</td>
				<td><form:errors path="username" class="inputSignIn" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td>Vecchia password :</td>
				<td><form:input path="oldPassword" class="inputSignIn"/>
				</td>
				<td><form:errors path="oldPassword" class="inputSignIn" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td>Nuova password :</td>
				<td><form:input path="newPassword" class="inputSignIn"/>
				</td>
				<td><form:errors path="newPassword" class="inputSignIn" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td>Conferma la nuova password :</td>
				<td><form:input path="confirm" class="inputSignIn"/>
				</td>
				<td><form:errors path="confirm" class="inputSignIn" cssStyle="color:red" />
				</td>
			</tr>
			<tr>
				<td height="101px" colspan="3"><input type="submit" /></td>
			</tr>
		</table>
	</form:form>
 </div>
