<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="x" uri="http://template.seda.it/tags"%>

<h1>Menu</h1>

<table border="0">
	<tr>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<td>${x:i18n('menu.top.title')}</td>
		</sec:authorize>
	</tr>
	<tr>
		<td>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
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
			</sec:authorize>
		</td>
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


