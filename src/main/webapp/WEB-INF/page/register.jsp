<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Here</title>
<link rel="icon" type="image/x-icon"
	href="<c:url value="/resource/images/favicon1.png"/>" />
<link rel="stylesheet"
	href="<c:url value="/resource/bootstrap/css/bootstrap.min.css"/>">
<script src="<c:url value="/resource/js/jquery.js"/>"></script>
<script src="<c:url value="/resource/bootstrap/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resource/css/register.css"/>">

</head>
<body>
	<%@ include file="navbar.jsp"%>
	<div class="container" style="margin-bottom: 19px">
		<h1 class="well">Register Here !</h1>
		<div class="col-lg-12 well">
			<div class="row">

				<!--  RegisterServlet  form -->
				<c:url value="/customer/registration" var="url"></c:url>
				<form:form method="post" action="${url}" commandName="user"
					enctype="multipart/form-data">
					<div class="col-sm-12">
						<div class="row">
							<div>
								<center>User Details</center>
							</div>
							<div class="col-sm-6 form-group">
								<form:label path="name">First Name</form:label>
								<form:input type="text" placeholder="Enter First Name.."
									class="form-control" path="name"></form:input>
							</div>
							<div class="col-sm-6 form-group">
								<form:label path="surname">Last Name</form:label>
								<form:input type="text" placeholder="Enter Last Name.."
									class="form-control" path="surname"></form:input>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-6 form-group">
								<form:label path="emailId">Email Id</form:label>
								<form:input type="text" placeholder="Enter Email ID.."
									class="form-control" path="emailId"></form:input>
							</div>
							<div class="col-sm-6 form-group">
								<form:label path="phone">Phone Number</form:label>
								<form:input type="text" placeholder="Enter Phone Number.."
									class="form-control" path="phone"></form:input>
							</div>
						</div>

						<div class="form-actions">
							<button type="submit" class="btn btn-lg btn-info"
								onclick="return Validate()">Submit</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>

	<!-- Validating Password -->
	<script type="text/javascript">
		function Validate() {
			var password = document.getElementById("pass").value;
			var confirmpass = document.getElementById("confirmpass").value;
			if (password != confirmpass) {
				alert("Password does Not Match.");
				return false;
			}
			return true;
		}
	</script>
</body>
</html>