<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Tickets history</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/ticket/cards.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <c:url value="/element/find" var="url"></c:url>
        <br><br>
        <form class="custom-form" action="/element/find" method="get">
            <h1>Find Element</h1>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label path="name" class="col-form-label">Kind </label></div>
                <div class="col-sm-3 label-column"><select class="selectpicker" data-width="100%" id="elementKind"
                                                           name="elementKind">
                    <option value="profile">Profile</option>
                    <option value="space">Space</option>
                    <option value="subject">Subject</option>
                    <option value="group">Group</option>
                </select>
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Name </label></div>
                <div class="col-sm-3 input-column"><input id="name" name="name" class="form-control" type="text"></input></div>
                <div class="col-sm-2 label-column"><label class="col-form-label">ID Number </label></div>
                <div class="col-sm-3 input-column"><input id="idNumber" name="idNumber" class="form-control" type="text"></input></div>
            </div>
            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Search</button>
        </form>
    </div>
</div>

<script type="text/javascript">
    function Validate() {
        return true;
    }
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

</body>

</html>

