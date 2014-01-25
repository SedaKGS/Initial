<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="x" uri="http://template.seda.it/tags"%>

<div id="aside">
	<c:url var="loginUrl" value="/j_signon" />
	<form action="${loginUrl}" method="post">
		<div style="padding: 10px; background-color: #CCCCCC;">
			<h3>${x:i18n('login.form.title')}</h3>
			<hr/>
			<c:if test="${param.failed=='true'}">
				<div style="color: red; font-size: 12px;">${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
			</c:if>					
			<div>
				<label for="j_username">${x:i18n('login.form.username')}</label>
			</div>
			<div>
				<input type="text" name="j_username" />
			</div>
			<div>
				<label for="j_password">${x:i18n('login.form.password')}</label>
			</div>
			<div>
				<input type="password" name="j_password" />
			</div>
			<hr/>
			<div>
				<input type="submit" value="${x:i18n('login.form.button')}" />
			</div>							
		</div>
	</form>
</div>
<div id="content" style="font-size: 13px;">
	<p style="margin-top: 0px;">${x:i18n('login.content.message')}</p>
	<div style="margin-top: 10px;">
		<p>${x:i18n('login.content.languages')}</p>
		<div class="languages">
			<ul>
				<li><a href="?language=en">${x:i18n('login.content.languages.english')}</a></li>
				<li><a href="?language=it">&nbsp;|&nbsp;${x:i18n('login.content.languages.italiano')}</a></li>
			</ul>
		</div>
	</div>
</div>
<div style="clear:both;"></div>