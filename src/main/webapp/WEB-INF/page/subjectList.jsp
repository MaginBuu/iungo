<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Subjects</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>


</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <form:form class="custom-form" method="post" action="/user/antibullying/report" modelAttribute="report">
            <form:hidden path="observed"/>
            <h1><i class="fa fa-heart icon"></i></h1>
            <p><i>Feeling safe and good about ourselves as with each other is very important. This is a safe space to
                express our worries and talk with confidence about our or others issues.</i></p>
            <br><br>
            <div class="form-group">
                <div class="label-column" style="text-align: center"><form:label path="description"
                                                               class="col-form-label">Description </form:label></div>
                <div class="input-column"><form:textarea path="description" class="form-control"
                                                               type="text"></form:textarea></div>
            </div>
            <div class="row form-group">
                <div class="col label-column"><label class="col-form-label">Report Anonymously </label><span></span></div>
                <div class="col input-column"><form:checkbox name="anonymously" path="anonymous" class="form-control"></form:checkbox></div>
            </div>

            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Send</button>
        </form:form>
    </div>
</div>

<script type="text/javascript">
    function Validate() {
        return true;
    }
</script>

</body>

</html>