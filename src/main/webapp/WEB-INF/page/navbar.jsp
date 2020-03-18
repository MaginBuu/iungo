<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>test</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="/resource/fonts/index/font-awesome.min.css">
    <link rel="stylesheet" href="/resource/css/index/Dark-NavBar-1.css">

</head>

<body style="height: 1200px;">
<div>
    <nav class="navbar navbar-light navbar-expand-md sticky-top navigation-clean-button"
         style="height: 65px;background-color: #de9d3f;color: #ffffff;">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">
                <i class="fa fa-globe"></i>
                &nbsp;iUNGO</a>
            <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                    class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation">
                        <a class="nav-link" style="color:#ffffff;" href="#">
                            Home</a></li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link" style="color: #ffffff;" href="#">
                            <i class="fa fa-wpexplorer"></i>&nbsp;Explore</a></li>
                    <li class="nav-item" role="presentation"><a class="nav-link" style="color:#ffffff;" href="#"><i
                            class="fa fa-star-o"></i>&nbsp;Features</a></li>

                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <li class="nav-item" role="presentation"><a class="nav-link" style="color:#ffffff;"
                                                                        href="#"><i
                                    class="fa fa-user-circle-o"></i>&nbsp;Perfil</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item" role="presentation"><a class="nav-link" style="color:#ffffff;"
                                                                        href="#"><i
                                    class="fa fa-user-circle-o"></i> ${sessionScope.name}</a></li>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <li class="nav-item" role="presentation"><a class="nav-link" style="color:#ffffff;"
                                                                        href="/login"><i
                                    class="fa fa-sign-in"></i>&nbsp;Sign In</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item" role="presentation"><a class="nav-link" style="color:#ffffff;"
                                                                        href="/logout"><i
                                    class="fa fa-sign-out"></i>&nbsp;Sign out</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
</div>
</body>

</html>