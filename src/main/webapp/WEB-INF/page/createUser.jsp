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
		<c:url value="/user/creation" var="url"></c:url>
		<form:form class="custom-form" method="post" action="/user/creation" commandName="user">
			<h1>Register Form</h1>
			<div class="form-row form-group">
				<div class="col-sm-3 label-column"><form:label path="name" class="col-form-label">Name </form:label></div>
				<div class="col-sm-3 input-column"><form:input path="name" class="form-control" type="text"></form:input></div>
				<div class="col-sm-2 label-column"><form:label path="surname" class="col-form-label">Surname </form:label></div>
				<div class="col-sm-3 input-column"><form:input path="surname" class="form-control" type="text"></form:input></div>
			</div>
			<div class="form-row form-group">
				<div class="col-sm-3 label-column"><form:label path="secondSurname" class="col-form-label">Surname2 </form:label></div>
				<div class="col-sm-3 input-column"><form:input path="secondSurname" class="form-control" type="text"></form:input></div>
				<div class="col-sm-2 label-column"><form:label path="birth" class="col-form-label">Birth Date </form:label></div>
				<div id="datetimepicker1" class="col-sm-3 input-column date">
					<form:input path="birth" class="form-control" type="text"></form:input>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
				</div>
			</div>
			<div class="form-row form-group">
				<div class="col-sm-3 label-column"><form:label path="emailId" class="col-form-label">Email </form:label></div>
				<div class="col-sm-8 input-column"><form:input path="emailId" class="form-control" type="email"></form:input></div>
			</div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Gender </label></div>
                <div class="col-sm-8 input-column">
                    <div class="dropdown">
                        <form:select class="btn btn-light dropdown-toggle" data-toggle="dropdown" type="button" path="gender">
                            <form:option class="dropdown-item" value="MALE">Male</form:option>
                            <form:option class="dropdown-item" value="FEMALE">Female</form:option>
                            <form:option class="dropdown-item" value="PIKACHU">Pikachu</form:option>
                        </form:select>
                    </div>
                </div>
            </div>
			<div class="form-row form-group">
				<div class="col-sm-3 label-column"><label class="col-form-label">Role </label></div>
				<div class="col-sm-9 checkbox-column"><label class="col-form-label">
				<div class="form-check form-check-inline">
					<form:checkbox class="form-check-input" path="role" value="STUDENT"/>
					<label class="form-check-label"> Student</label>
				</div>
				<div class="form-check form-check-inline">
					<form:checkbox class="form-check-input" path="role" value="RESPONSIBLE"/>
					<label class="form-check-label"> Responsible</label>
				</div>
				<div class="form-check form-check-inline">
					<form:checkbox class="form-check-input" path="role" value="TEACHER"/>
					<label class="form-check-label">Teacher</label>
				</div>
				<div class="form-check form-check-inline">
					<form:checkbox class="form-check-input" path="role" value="SECRETARY"/>
					<label class="form-check-label">Secretary</label>
				</div>
				<div class="form-check form-check-inline">
					<form:checkbox class="form-check-input" path="role" value="ADMIN"/>
					<label class="form-check-label">Admin</label>
				</div>
                </label></div>
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