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
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="/resource/css/ticket/cards.css">
    <link rel="stylesheet" href="/resource/css/profile/profileStyle.css">

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

</head>

<body>

<script>

    function recover() {
        var email = document.getElementById("email");
        var inputEmail = document.getElementById("input-email").value;
        email.value = inputEmail;
        if(email.value != ""){
            window.location.href = '/recover/password/'+email.value;
        }else{
            document.getElementById("modalText").style.backgroundColor = "#ffd6cc";
            document.getElementById("modalText").style.borderRadius = "3px";
        }
    }

</script>

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
            <a class="forgot" data-toggle="modal" data-target="#myModal">Forgot your email or password?</a>
        </fieldset>
    </form>
</div>

<!-- Modal HTML -->
<div id="myModal" name="myModal" class="modal fade">
    <div class="modal-dialog modal-confirm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <h4 class="modal-title">Password recover</h4>
                <p id="modalText">If you don't remember your password, <strong id="strongModal">please write your email and visit secretary
                    tomorrow</strong>. Thanks!</p>
                <div class="row" style="justify-content: center">
                    <input id="input-email" class="form-control" type="text"></div>
            </div>
            <a class="btn submit-button" style="line-height: 30px;" onclick="recover()">Request new password</a>
        </div>
        <input type="hidden" name="email" id="email"  value=""/>
    </div>
</div>

</body>

</html>