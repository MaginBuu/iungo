<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>SelectChild</title>
	<link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="/resource/css/creation/Pretty-Registration-Form.css">
	<link rel="stylesheet" href="/resource/css/creation/styles.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">


</head>

<body>
<%@ include file="navbar.jsp"%>
<div class="row creation-form">
	<div class="col-md-8 offset-md-2">
		<form class="custom-form" action="/user/creation/selectChild" method="post">
			<h1>Select child</h1>
			<div class="form-row form-group">
				<div class="col-sm-4 label-column">
					<label path="userP" class="col-form-label">Students </label></div>
				<div class="col-sm-7 input-column">
					<select class="selectpicker" data-live-search="true" data-width="100%" multiple="true" id="child" name="child">
						<c:forEach items="${users}" var="user" >
							<option value="${user.userId}">${user.name} ${user.surname} ${user.secondSurname}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Accept</button>
		</form>
	</div>
</div>


<script type="text/javascript">
	function Validate() {
		var userP = document.getElementById("child").value;
		if (userP == "") {
			alert("Select an Student");
			return false;
		}
		return true;
	}
</script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/js/My-Date-Picker.js"></script>
</body>

</html>