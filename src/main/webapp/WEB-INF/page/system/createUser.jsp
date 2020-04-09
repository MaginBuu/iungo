<%@ page import="com.model.enums.Department" %>
<%@ page import="com.model.enums.GenderType" %>
<%@ page import="com.model.enums.Role" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - New User</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker3.css">
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.es.min.js"></script>
    <script src="/resource/js/My-Date-Picker.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>


</head>

<body>
<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <c:url value="/user/creation" var="url"></c:url>
        <form:form class="custom-form" method="post" action="/user/creation" commandName="user">
        <h1>Register Form</h1>
        <input type="hidden" name="usernameRelate" id="usernameRelate" value="${sessionScope.userRelate}"/>
        <div class="form-row form-group" ;>
            <div class="col-sm-3 label-column"><form:label path="name"
                                                           class="col-form-label">Name </form:label></div>
            <div class="col-sm-3 input-column"><form:input path="name" class="form-control"
                                                           type="text"></form:input></div>
            <div class="col-sm-2 label-column"><form:label path="surname"
                                                           class="col-form-label">Surname </form:label></div>
            <div class="col-sm-3 input-column"><form:input path="surname" class="form-control"
                                                           type="text"></form:input></div>
        </div>
        <div class="form-row form-group">
            <div class="col-sm-3 label-column"><form:label path="secondSurname"
                                                           class="col-form-label">Surname2 </form:label></div>
            <div class="col-sm-3 input-column"><form:input path="secondSurname" class="form-control"
                                                           type="text"></form:input></div>
            <div class="col-sm-2 label-column"><form:label path="birth"
                                                           class="col-form-label">Birth Date </form:label></div>
            <div id="datetimepicker1" class="col-sm-3 input-column date">
                <form:input path="birth" class="form-control" type="text"></form:input>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
        </div>
        <div class="form-row form-group">
            <div class="col-sm-3 label-column"><form:label path="emailId"
                                                           class="col-form-label">Email </form:label></div>
            <div class="col-sm-8 input-column"><form:input path="emailId" class="form-control"
                                                           type="email"></form:input></div>
        </div>
        <c:set var="enumValues" value="<%=GenderType.values()%>"/>
        <div class="form-row form-group">
            <div class="col-sm-3 label-column"><label class="col-form-label">Gender </label></div>
            <div class="col-sm-2 input-column">
                <form:select class="selectpicker" data-width="100%" path="gender">
                    <c:forEach items="${enumValues}" var="enumValue">
                        <form:option
                                value="${enumValue}">${fn:toUpperCase(fn:substring(enumValue.name(),0,1))}${fn:toLowerCase(fn:substring(enumValue.name(),1,fn:length(enumValue.name())))}</form:option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="col-sm-1 label-column"><label class="col-form-label">Roles </label></div>
            <div class="col-sm-5 input-column">
                <c:set var="enumValues" value="<%=Role.values()%>"/>
                <form:select class="selectpicker" multiple="true" data-width="100%" path="role" id="role-select"
                             name="role-select" onchange="changeDisableDept()">
                    <c:forEach items="${enumValues}" var="enumValue">
                        <form:option value="${enumValue}"></form:option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="form-row form-group">
            <div class="col-sm-3 label-column"><label class="col-form-label">Department </label></div>
            <div class="col-sm-8 input-column">
                <c:set var="enumValues" value="<%=Department.values()%>"/>
                <form:select class="selectpicker" disabled="true" data-width="100%"
                             path="department" id="department-select"
                             name="department-select">
                    <form:option selected="selected" value="">Select a department</form:option>
                    <c:forEach items="${enumValues}" var="enumValue">
                        <form:option value="${enumValue}"></form:option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
    </div>
    </form:form>
</div>

<script type="text/javascript">
    function Validate() {
        var select = jQuery("#role-select");
        var usernameRelate = jQuery("#usernameRelate");
        var roles = select.val().toString().split(',');
        if (roles.length > 1 && roles[0] === "STUDENT") {
            alert("student can only have 1 role assigned");
            return false;
        } else if (roles[0] === "") {
            alert("user has to have at least one role assigned");
            return false;
        } else if (usernameRelate.val() !== "" && usernameRelate.val() != null) {
            if (roles[0] !== "RESPONSIBLE") {
                alert("This user must be a responsible");
                return false;
            }
        }

        return true;
    }

    function changeDisableDept() {
        var select = $('#department-select');
        var select2 = $('#role-select');
        var valors = $('#role-select').val().toString().split(',');


        select.prop('disabled', !(valors.includes("TEACHER")
            || valors.includes("COORDINATOR")
                || valors.includes("TUTOR"))).selectpicker('refresh');
        if(!(valors.includes("TEACHER") || valors.includes("COORDINATOR") || valors.includes("TUTOR"))){
            document.getElementById("department-select").selectedIndex = "0";
        }

        if(valors.includes("COORDINATOR") || valors.includes("TUTOR")){
            var values = select2.val();
            values.push("TEACHER");
            select2.val(values);
            select2.selectpicker('refresh');

        }

        select.selectpicker('refresh');
    }

    function selectTeacher(){
        var valors = $('#role-select').val().toString().split(',');
        if(valors.includes("COORDINATOR") || valors.includes("TUTOR")){
            document.getElementById("department-select").selectedIndex = "0";
        }
    }

</script>

</body>

</html>