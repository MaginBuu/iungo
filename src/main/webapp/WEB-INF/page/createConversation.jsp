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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">


</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <form:form class="custom-form" action="/conversation/creation" method="post" modelAttribute="conversation" commandName="conversation">
            <h1>Create conversation</h1>
            <input id="roleUser" type="hidden" value="${pageContext.request.userPrincipal.authorities}">
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <label class="col-form-label" id="titleLabel">Title </label></div>
                <div class="col-sm-8 input-column">
                    <form:input path="title" class="form-control" type="text" id="titleInput"></form:input></div>
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
                            name="users" data-dropup-auto="false">
                        <c:if test="${pageContext.request.userPrincipal.authorities ne 'RESPONSIBLE'}">
                            <optgroup label="Students">
                            <c:forEach items="${students}" var="user">
                                <option value="${user.userId}">${user.name} ${user.surname} ${user.secondSurname}</option>
                            </c:forEach>
                        </c:if>

                        <optgroup label="Teachers">
                        <c:forEach items="${teachers}" var="user">
                            <option value="${user.userId}">${user.name} ${user.surname} ${user.secondSurname}</option>
                        </c:forEach>

                        <c:if test="${(pageContext.request.userPrincipal.authorities ne 'RESPONSIBLE')
                                            and (pageContext.request.userPrincipal.authorities ne 'STUDENT')}">
                            <optgroup label="Responsibles">
                            <c:forEach items="${responsibles}" var="user">
                                <option value="${user.userId}">${user.name} ${user.surname} ${user.secondSurname}</option>
                            </c:forEach>

                            <optgroup label="secretaries">
                            <c:forEach items="${secretaries}" var="user">
                                <option value="${user.userId}">${user.name} ${user.surname} ${user.secondSurname}</option>
                            </c:forEach>

                            <optgroup label="Admins">
                            <c:forEach items="${admins}" var="user">
                                <option value="${user.userId}">${user.name} ${user.surname} ${user.secondSurname}</option>
                            </c:forEach>
                        </c:if>
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

        let role = $("#roleUser").val().toString();
        let validated = true

        if(document.getElementById("titleInput").value === ""){
            document.getElementById("titleInput").style.backgroundColor = "#ffd6cc";
            validated = false;
        } else{
            document.getElementById("titleInput").style.backgroundColor = "#ffffff";
        }
        if($('#users').val().length == 0){
            $('*[data-id="users"]').css("background-color","#ffd6cc","important");
            validated = false;
        }else {
            $('*[data-id="users"]').css("background-color","#ffffff","important");
        }
        if ((role === "[STUDENT]" || role === "[RESPONSIBLE]") && $('#users').val().length > 1 && teacher === true) {
            alert("Teacher cannot be in a group of students");
            return false;
        }
        return validated;
    }

    function addResponsible() {
    }
</script>

</body>

</html>