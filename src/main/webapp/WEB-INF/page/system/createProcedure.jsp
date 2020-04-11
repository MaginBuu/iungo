<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - New Procedure</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/fonts/index/font-awesome.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>


</head>

<body>

<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <form:form class="custom-form" method="post" action="/procedure/creation" modelAttribute="procedure" commandName="procedure">
        <h1>Create Procedure</h1>
        <div class="form-row form-group">
            <div class="col-sm-3 label-column"><form:label path="title"
                                                           class="col-form-label">Title </form:label></div>
            <div class="col-sm-8 input-column"><form:input path="title" class="form-control"
                                                           type="text" id="title"></form:input></div>
        </div>

        <div class="form-row form-group">
            <div class="col-sm-3 label-column"><form:label path="description"
                                                           class="col-form-label">Description </form:label></div>
            <div class="col-sm-8 input-column-Procedure"><form:textarea path="description" class="form-control"
                                                                        type="text"></form:textarea></div>
        </div>
        <div class="form-row form-group">
            <div class="col-sm-3 label-column">
                <form:label path="userP" class="col-form-label">Users </form:label></div>
            <div class="col-sm-3 input-column">
                <form:select class="selectpicker" data-live-search="true" path="userP.userId" id="userP">
                <form:option disabled="disabled" selected="selected" value="">Select a user</form:option>
                <c:forEach items="${users}" var="user">
                <form:option
                        value="${user.userId}">${user.name} ${user.surname} ${user.secondSurname}</form:option>
                </c:forEach>
                </form:select></div>
            <div class="col-sm-2 label-column">
                <form:label path="online" class="col-form-label">Online </form:label></div>
            <div class="col-sm-2 checkbox-column">
                <form:checkbox path="online"></form:checkbox></div>
        </div>
        <div class="form-row form-group">
            <div class="col-sm-3 label-column">
                <form:label path="limitDate" class="col-form-label">Limit date </form:label></div>
            <div class="col-sm-3 form-group" style="position: relative">
                <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                    <form:input path="limitDate" type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1"/>
                    <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                </div>
            </div>
            <div class="col-sm-2 label-column">
                <label class="col-form-label">Hour </label></div>
            <div class="col-sm-3 form-group" style="position: relative">
                <div class="input-group date" id="datetimepicker3" data-target-input="nearest">
                    <input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1" value="23:59"/>
                    <div class="input-group-append" data-target="#datetimepicker3" data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
                    </div>
                </div>
            </div>
        </div>
        <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
        </form:form>
    </div>
</div>



    <script type="text/javascript">

        function Validate() {
            if ($('#userP').val() !== "" && $('#title').val()!=="") {

                var tempDate = $('#datetimepicker1').find("input").val();
                tempDate = tempDate.split("/").reverse().join("/");
                tempDate += " " + $('#datetimepicker3').find("input").val() + ":00"
                $('#datetimepicker1').find("input").val(tempDate);

                return true;
            }
            alert("please fill the obligatory camps");
            return false
        }

        $(function () {
            $('#datetimepicker1').datetimepicker({
                format: 'DD/MM/YYYY',

            });
        });

        $(function () {
            $('#datetimepicker3').datetimepicker({
                format: 'HH:mm',
                pick12HourFormat: false,

            });
        });
    </script>
</body>

</html>