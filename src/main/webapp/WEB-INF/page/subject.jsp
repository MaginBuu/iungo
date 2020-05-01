<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Subjects</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/index/indexStyle.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="custom-div">
            <h1 class="text-center">My Subjects</h1>
        <div class="features-boxed">
            <div class="container">
                <div class="intro">
                </div>
                <c:forEach items="${subjects}" var="subject">
                    <div class="row justify-content-center" >
                        <div class="col item" style="max-height: 100%;">
                            <div class="box2" style="max-height: 100%; width:100%;margin: 0 auto;">
                                <a class="nav-link stretched-link" href="${path}${subject.subjectId}"><a class="subject"><strong>${subject.name}</strong> -
                                        ${subject.subjectGroup.level} ${subject.subjectGroup.stage} ${subject.subjectGroup.group}
                                        "${subject.subjectGroup.name}"
                                </a></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        </div>
    </div>

</div>

<script type="text/javascript">
    function Validate() {
        return true;
    }

    function paprika() {
        document.getElementById("lluis").style.backgroundColor = "hsla(39.17, 97.65%, 57.42%, 0.08)";
        var table = document.getElementById("lluisa");


    }
</script>

</body>

</html>

