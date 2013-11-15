<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1>Menu</h1>

<table border="0">
 <tr>
  <sec:authorize access="hasRole('ROLE_ADMIN')">
  <td>Applicazioni solo per ROLE_ADMIN:</td>
  </sec:authorize>
 </tr>
 <tr>
  <td>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
     <a href="/sem/manager">Manager</a>
    </sec:authorize>
  </td>
  </tr>
  <tr>
    <td>Applicazioni visibili atutti:</td>
  </tr>
  <tr>
    <td><a href="/sem/events/someFreeApplication">Applicazione 1</a></td>
  </tr>
  <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
  <tr>
   <td>
     Login:
   </td>
  </tr>
  <tr>
    <td><a href="/sem/security/login">Login</a></td>
  </tr>
  </sec:authorize>
</table>


