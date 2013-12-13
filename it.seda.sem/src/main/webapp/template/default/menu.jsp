<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://template.seda.it/tags"%>

<h1>Menu</h1>
<%-- example of two level menu. Do we want create a template tag??? --%>
<ul id="main-menu">
	<c:forEach var="item" items="${menu_items}">
		<c:choose>
			<c:when test="${item.hasLink}"><c:url var="href" value="${item.path}" /></c:when>
			<c:otherwise><c:set var="href" value="#" /></c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${item.selected && item.hasLink}"><c:set var="menu_item" value="menu-item-link menu-link-selected" /></c:when>
			<c:when test="${item.selected && !item.hasLink}"><c:set var="menu_item" value="menu-item menu-selected" /></c:when>
			<c:when test="${item.hasLink}"><c:set var="menu_item" value="menu-item-link" /></c:when>
			<c:otherwise><c:set var="menu_item" value="menu-item" /></c:otherwise>
		</c:choose>

		<li id="${item.id}" class="${menu_item}">
			<%-- Use resource bundle to get text --%>
			<c:set var="itemlabel" value="menu-${item.id}"/> 
			<a href="${href}">${x:i18n(itemlabel)}</a>
			<c:if test="${item.hasChildren}">
				<ul>
					<c:forEach var="subitem" items="${item.children}">
						<c:choose>
							<c:when test="${subitem.hasLink}"><c:url var="href" value="${subitem.path}" /></c:when>
							<c:otherwise><c:set var="href" value="#" /></c:otherwise>
						</c:choose>
	
						<c:choose>
							<c:when test="${subitem.selected && item.hasLink}"><c:set var="menu_item" value="submenu-item-link menu-link-selected" /></c:when>
							<c:when test="${subitem.selected && !item.hasLink}"><c:set var="menu_item" value="submenu-item menu-selected" /></c:when>
							<c:when test="${subitem.hasLink}"><c:set var="menu_item" value="submenu-item-link" /></c:when>
							<%-- test="${!item.hasLink}" --%>
							<c:otherwise><c:set var="menu_item" value="submenu-item" /></c:otherwise>
						</c:choose>
						<li id="${subitem.id}" class="${menu_item}">
							<c:set var="itemlabel" value="menu-${subitem.id}"/> 
							<a href="${href}">${x:i18n(itemlabel)}</a>
						</li>
					</c:forEach>
				</ul>
			</c:if>
		</li>

	</c:forEach>
</ul>
<%--
<table border="">
	<tr>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<td>${x:i18n('menu.top.title')}</td>
		</sec:authorize>
	</tr>
	<tr>
		<td><sec:authorize access="hasRole('ROLE_ADMIN')">
				<div class="seda-ui-divrow">
					<a href="/sem/manager/account">${x:i18n('menu.accountManager.title')}</a>
				</div>
				<div class="seda-ui-divrow">
					<a href="/sem/manager/cliente">${x:i18n('menu.clientManager.title')}</a>
				</div>
				<div class="seda-ui-divrow">
					<a href="/sem/manager/server">${x:i18n('menu.serverManager.title')}</a>
				</div>
				<div class="seda-ui-divrow">
					<a href="/sem/manager/societa">${x:i18n('menu.societaManager.title')}</a>
				</div>
				<div class="seda-ui-divrow">
					<a href="/sem/manager/test">Test</a>
				</div>
			</sec:authorize></td>
	</tr>
	<tr>
		<td>${x:i18n('menu.bottom.title')}</td>
	</tr>
	<tr>
		<td><a href="/sem/manager/someFreeApplication">Applicazione 1</a></td>
	</tr>
	<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
		<tr>
			<td>Login:</td>
		</tr>
		<tr>
			<td><a href="/sem/security/login">Login</a></td>
		</tr>
	</sec:authorize>
</table>
 --%>

