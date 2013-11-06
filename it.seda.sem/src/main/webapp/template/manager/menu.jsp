<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p>
<h3>Menu</h3>
<ul>
	<c:url var="managerUrl" value="/manager" />
	<li><a id="navManagerLink" href="${managerUrl}">Manager</a></li>
	<c:url var="logoutUrl" value="/security/logout" />
	<li><a href="${logoutUrl}">Logout</a></li>
</ul>