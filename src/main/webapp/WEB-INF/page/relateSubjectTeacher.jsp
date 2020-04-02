<%@ page import="com.model.enums.Department" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Select Responsible</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <form class="custom-form">
            <h1>Select teacher for ${subject.name}</h1>
            <div class="form-row form-group">
                <div class="col-sm-2 label-column"><label class="col-form-label">Department </label></div>
                <div class="col-sm-3 input-column">
                    <c:set var="enumValues" value="<%=Department.values()%>"/>
                    <select class="selectpicker" data-width="100%" multiple="true" id="select-department"
                            name="select-department">
                        <option selected="selected" value="">Select a department</option>
                        <c:forEach items="${enumValues}" var="enumValue">
                            <option value="${enumValue}">${enumValue}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-sm-2 label-column">
                    <a class="btn alt-submit-button" id="btn-ajax">Search teacher</a></div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <label class="col-form-label">Teacher </label></div>
                <div class="col-sm-7 input-column">
                    <select class="selectpicker" data-live-search="true" data-width="100%" multiple="true"
                            id="select-teacher" name="select-teacher">
                        <c:forEach items="${teachers}" var="teacher">
                        <option value="${teacher.userId}">${teacher.name} ${teacher.surname} ${teacher.secondSurname}</option>
                        </c:forEach>
                    </select>
                </div>
                <input type="hidden" name="subjectId" id="subjectId" value="${subject.subjectId}"/>
            </div>
            <a class="btn btn-light submit-button" onclick="relate()">Accept</a>
        </form>
    </div>
</div>


<script type="text/javascript">
    function relate() {
        var teacherId = jQuery("#teacher").val().toString();
        var subjectId = jQuery("#subjectId").val().toString();
        if (teacherId == "") {
            alert("Select a teacher");
        } else {
            window.location.href = '/subject/relate/setTeacher?subjectId=' + subjectId + '&teachersId=' + teacherId;
        }
    }

    $("#btn-ajax").click(function () {
        $.ajax({

            type: "GET",
            url: "requestTeachers",
            dataType: "json",
            contentType: 'application/json',
            data: {
                "department": $("#select-department option:selected").val()
            }, //aqui es passen els parametres
            success: function (data) {
                let options, select, selectFinish, i;

                // Get the raw DOM object for the select box
                select = document.getElementById('select-teacher');

                // Clear the old options
                select.options.length = 0;

                console.log(data.teachers);

                // Disable the booked options in both select, each one with its list
                //$.each(data.teachers, function (index, current) {
                //    select.options.add(new Option(current.getName()+,))
                //});

                // Selectpicker refresh
                //$('#select-finish').selectpicker('refresh');
                //$('#select-start').selectpicker('refresh');


            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });
    });
</script>


</body>

</html>