<%@ page import="com.model.enums.Stage" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<script>
	function disable() {
		var select1 = jQuery('#stage');
		select1.prop('disabled', true).selectpicker('refresh');
	}
	function enable() {
		var select1 = jQuery('#stage');
		select1.prop('disabled', false).selectpicker('refresh');
	}
	function changeDisabled() {
		var select1 = jQuery('#stage');
		var checkbox = jQuery('#thisCourse');
		//if(checkbox)
	}
</script>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>GroupCreation</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker3.css">
	<link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/resource/css/creation/Pretty-Registration-Form.css">
	<link rel="stylesheet" href="/resource/css/creation/styles.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">


</head>

<body>
<%@ include file="navbar.jsp"%>
<div class="row creation-form">
	<div class="col-md-8 offset-md-2">
		<c:url value="/group/creation" var="url"></c:url>
		<form:form class="custom-form" method="post" action="/group/creation" modelAttribute="group" commandName="group">
			<h1>Create Group</h1>
			<div class="form-row form-group">
				<div class="col-sm-3 label-column"><form:label path="level" class="col-form-label">Level </form:label></div>
				<div class="col-sm-3 input-column"><form:input path="level" class="form-control" type="number"></form:input></div>
				<div class="col-sm-2 label-column"><form:label path="group" class="col-form-label">Group </form:label></div>
				<div class="col-sm-3 input-column"><form:input path="group" class="form-control" type="text"></form:input></div>
			</div>
			<div class="form-row form-group">
				<div class="col-sm-3 label-column"><form:label path="name" class="col-form-label">Name </form:label></div>
				<div class="col-sm-8 input-column"><form:input path="name" class="form-control" type="text"></form:input></div>
			</div>
			<c:set var="enumValues" value="<%=Stage.values()%>"/>
			<div class="form-row form-group">
				<div class="col-sm-3 label-column">
					<form:label path="Stage" class="col-form-label">Stage </form:label></div>
				<div class="col-sm-8 input-column">
					<form:select class="selectpicker" data-width="100%" path="stage" id="stage" name="stage">
						<form:option disabled="disabled" selected="selected" value="">Select a Stage</form:option>
						<c:forEach items="${enumValues}" var="enumValue" >
							<form:option value="${enumValue}"></form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="form-row form-group">
				<div class="col-sm-3 label-column"><label class="col-form-label"> </label></div>
				<div class="col-sm-9 checkbox-column"><label class="col-form-label">
					<div class="form-check form-check-inline">
						<input type="checkbox" class="form-check-input" checked="checked" onclick="toggleSelect()" id="thisCourse"/>
						<label class="form-check-label"> This group is created in this academic course</label>
					</div>
				</label></div>
			</div>

			<select id="mySelect">
				<option>Apple</option>
				<option>Pear</option>
				<option>Banana</option>
				<option>Orange</option>
			</select>
			<br><br>
			<input type="button" onclick="disable()" value="Disable list">
			<input type="button" onclick="enable()" value="Enable list">
			<button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
		</form:form>
	</div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.es.min.js"></script>
<script src="/resource/js/My-Date-Picker.js"></script>
</body>

</html>