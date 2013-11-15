<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://template.seda.it/tags" %>
<table>
<sec:authentication var="user" property="principal" />
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
  <tr>
    <td>
       Welcome:&nbsp;
       ${user.firstName} 
       &nbsp;
       ${user.lastName}
    </td>
    <td>
       <a href="<c:url value="/j_spring_security_logout" />">Logout</a>
    </td>
  </tr>
  <tr>
   <td>
    Roles:&nbsp;
   <sec:authorize access="hasRole('ROLE_USER')">
    ROLE_USER&nbsp;
   </sec:authorize>
   <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
    ROLE_ADMIN
   </sec:authorize>
   </td>
   <td>
       <a href="<c:url value="/security/changePassword" />">Change Password</a>
    </td>
  </tr>
  <tr>
     <td>Password Expiration: ${user.expiration}</td>
  </tr>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
  <tr>
   <td>User not logged in</td>
  </tr>
  <tr>
   <td>${user}</td>
  </tr>
</sec:authorize>
  <tr>
   <td>
     <ul>
 <li>
  <a href="?language=it">${x:i18n('language.italian')}</a>
 </li>
 <li>
  <a href="?language=en">${x:i18n('language.english')}</a>
 </li>
</ul>
   </td>
  </tr>

</table>