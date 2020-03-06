<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>UserCreation</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker3.css">
	<link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/resource/css/creation/Pretty-Registration-Form.css">
	<link rel="stylesheet" href="/resource/css/creation/styles.css">

</head>

<body>
<%@ include file="navbar.jsp"%>
<div class="row creation-form">
	<div class="col-md-8 offset-md-2">
		<c:url value="/procedure/creation" var="url"></c:url>
		<form:form class="custom-form" method="post" action="/procedure/creation" commandName="procedure">
			<h1>Create Ticket</h1>
			<div class="form-row form-group">
				<div class="col-sm-3 label-column"><form:label path="title"
															   class="col-form-label">Title </form:label></div>
				<div class="col-sm-8 input-column-Procedure"><form:input path="title" class="form-control"
															   type="text"></form:input></div>
			</div>

			<div class="form-row form-group">
				<div class="col-sm-3 label-column"><form:label path="description"
															   class="col-form-label">Description </form:label></div>
				<div class="col-sm-8 input-column"><form:textarea path="description" class="form-control"
																  type="text"></form:textarea></div>
			</div>

			<button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	function Validate() {
		return true;
	}
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.es.min.js"></script>
<script src="/resource/js/My-Date-Picker.js"></script>
</body>

</html>