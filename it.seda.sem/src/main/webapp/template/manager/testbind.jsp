<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="x" uri="http://template.seda.it/tags"%>

<script type="text/javascript">
$(function() {
	
	var dateYearDa=${years[0]};
	var dateYearA=${years[fn:length(years)-1]};
	
		$("#date.year").datepicker({
			minDate: new Date(dateYearDa, 0, 1),
			maxDate: new Date(dateYearA, 11, 31),
			yearRange: dateYearDa + ":" + dateYearA,
			showOn: "button",
			buttonImage: "../resources/images/calendar.gif",
			buttonImageOnly: true,
			onSelect: function(dateText, inst) {$("#tx_periodo_a_day_id").val(dateText.substr(0,2));
												$("#tx_periodo_a_month_id").val(dateText.substr(3,2));
												$("#tx_periodo_a_year_id").val(dateText.substr(6,4));
												},
			beforeShow: function(input, inst) {
				//imposta il valore del calendario in base a quanto impostato nelle 3 dropdownlist
	            updateValoreDatePickerFromDdl("tx_periodo_a_day_id",
				                            "tx_periodo_a_month_id",
				                            "tx_periodo_a_year_id",
				                            "tx_periodo_a_hidden");
			}
		});
	});
</script>

<div class="title" style="text-align: center">
	<h3>${x:i18n('server.manager.title')}</h3>
</div>
 

<form:form method="POST" action="testbind" commandName="testData" >

	<div id="formBox" style="background-color: rgb(240, 240, 240); margin-top: 15px; margin-bottom: 15px; padding-top: 10px; margin-left: 10px; padding-bottom: 10px;">

		<div class="seda-ui-divrow ">
			<label class="seda-ui-labelrow">Data</label>

			<x:datetime path="date"/>
			
			<form:errors path="date"  cssStyle="color:red;margin-left: 10px;"/>      		 
		</div>
		
		<div class="seda-ui-divrow ">
			<label class="seda-ui-labelrow">Time</label>
			
			<x:datetime path="time" selector="time"/>
			
			<form:errors path="time"  cssStyle="color:red;margin-left: 10px;"/>      		 
		</div>		
		
		<div class="seda-ui-divrow ">
			<label class="seda-ui-labelrow">DateTime</label>
			
			<x:datetime path="datetime" selector="datetime"/>
			
			<form:errors path="datetime"  cssStyle="color:red;margin-left: 10px;"/>      		 
		</div>		

		<input type="submit" value="bind" style="width: auto;"/>
	</div>
</form:form>
