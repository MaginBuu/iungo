<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Create conversation</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">


</head>

<body>
<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <form:form class="custom-form" action="/conversation/creation" method="post" modelAttribute="conversation" commandName="conversation">
            <h1>Create conversation</h1>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <label class="col-form-label">Title </label></div>
                <div class="col-sm-8 input-column">
                    <form:input path="title" class="form-control" type="text"></form:input></div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <label class="col-form-label">Description </label></div>
                <div class="col-sm-8 input-column-Conversation">
                    <form:textarea path="description" class="form-control" type="text"></form:textarea></div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                        <label class="col-form-label">Users </label></div>
                <div class="col-sm-8 input-column">
                    <form:select path = "usersTemp" class="selectpicker" data-live-search="true" data-width="100%" multiple="true" id="users"
                            name="users">
                        <optgroup label="Students">
                        <c:forEach items="${students}" var="user">
                            <option value="${user.userId}">${user.name} ${user.surname} ${user.secondSurname}</option>
                        </c:forEach>
                        <optgroup label="Teachers">
                        <c:forEach items="${teachers}" var="user">
                            <option value="${user.userId}">${user.name} ${user.surname} ${user.secondSurname}</option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
        </form:form>
    </div>
</div>


<script type="text/javascript">

    function Validate() {
        var teacher = false;

        $('#users').find("option:selected").each(function(){
            //optgroup label
            var label = $(this).parent().attr("label");
            if(label === "Teachers") {
                teacher = true;
            }

        });

        if ($('#users').val().length > 1 && teacher === true) {
            alert("Teacher cannot be in a group of students");
            return false;
        } else {
            return true;
        }
    }

    function addResponsible() {
    }
</script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</body>

</html>