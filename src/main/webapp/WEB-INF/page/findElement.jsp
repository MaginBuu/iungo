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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row register-form">
    <div class="col-md-8 offset-md-2">
        <c:url value="/element/find" var="url"></c:url>
        <br><br>
        <div class="container">
            <h1>Ticket List</h1>
            <form action="/element/find" method="get">
                <form:select class="selectpicker" data-width="100%" path="elementKind" id="elementKind" name="elementKind"
                        onchange="selectChange(value)">
                    <form:option value="profile">Profile</form:option>
                    <form:option value="space">Space</form:option>
                    <form:option value="subject">Subject</form:option>
                    <form:option value="group">Group</form:option>
                </form:select>
                <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
            </form>

        </div>
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
</body>

</html>

