<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="x" uri="http://template.seda.it/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="title" style="text-align: center">
	<h3>${x:i18n('server.manager.title')}</h3>
</div>


<form:form method="POST" action="testbind" commandName="testData">

	<div id="formBox" style="background-color: rgb(240, 240, 240); margin-top: 15px; margin-bottom: 15px; padding-top: 10px; margin-left: 10px; padding-bottom: 10px;">

		<div class="seda-ui-divrow ">
			<label class="seda-ui-labelrow">Data</label>
			
			<form:select path="date.dayOfMonth" items="${daysOfMonth}" />
			<form:select path="date.monthOfYear" items="${monthsOfYear}"/>			
			<form:select path="date.year" items="${years}"/>			
			
			<form:errors path="date"  cssStyle="color:red;margin-left: 10px;"/>      		 
		</div>
		
		<div class="seda-ui-divrow ">
			<label class="seda-ui-labelrow">Time</label>
			
			<form:select path="time.hourOfDay" items="${hoursOfDay}" />
			<form:select path="time.minuteOfHour" items="${minutesOfHour}"/>			
			
			<form:errors path="time"  cssStyle="color:red;margin-left: 10px;"/>      		 
		</div>		

		<input type="submit" value="bind" style="width: auto;"/>
	</div>
</form:form>
