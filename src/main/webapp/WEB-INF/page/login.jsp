<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>

<head>
    <sec:authorize access="isAuthenticated()">
        <% response.sendRedirect("/"); %>
    </sec:authorize>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Log In</title>
    <link rel="stylesheet" href="resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="resource/fonts/ionicons.min.css">
    <link rel="stylesheet" href="resource/css/loginStyle.css">
    <link rel="stylesheet" href="resource/css/creation/creationStyle.css">
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="login-clean">
    <form name="loginForm" action="<c:url value="/j_spring_security_check"/>" method="post">
        <h2 class="sr-only">Login Form</h2>
        <!-- will display after contactUs form registerd successfully -->
        <c:if test="${!empty querySuccess}">
            <div class="error" style="color: #ff0000;">${querySuccess}</div>
        </c:if>

        <!-- will display after registration form registerd successfully -->
        <c:if test="${not empty registrationSuccess}">
            <div class="error" style="color: #ff0000;">${registrationSuccess}</div>
        </c:if>
        <!-- will display after logged out successfull -->
        <c:if test="${not empty logout}">
            <div class="error" style="color: #ff0000;">${logout}</div>
        </c:if>

        <fieldset>
            <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
            <div class="form-group"><input class="form-control" placeholder="E-mail" name="j_username" type="email">
            </div>
            <div class="form-group"><input class="form-control" placeholder="Password" name="j_password"
                                           type="password"></div>
            <div class="form-group">
                <button class="btn btn-primary btn-block" type="submit">Log In</button>
            </div>
            <a class="forgot" href="#">Forgot your email or password?</a>
    </form>
    </fieldset>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resource/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>