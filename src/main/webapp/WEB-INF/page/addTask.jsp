<%@ page import="com.model.enums.Typology" %>
<%@ page import="com.model.enums.TaskType" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Add Task</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css"/>

</head>

<body>
<script>

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

    function Validate() {

        var validated = true;

        var name = document.getElementById("name");
        var valueInput = document.getElementById("value");
        var limitDate = document.getElementById("limitDate");
        var limitHour = document.getElementById("limitHour");
        var chapter = document.getElementById("select-chapter");
        var typology = document.getElementById("select-typology");

        if(name.value == "")  {
            name.style.backgroundColor = "#ffd6cc";
            validated = false;
        }else
            name.style.backgroundColor = "#ffffffff";
        if($('#datetimepicker1').find("input").val() === ""){
            limitDate.style.backgroundColor = "#ffd6cc";
            validated = false;
        }else{
            limitDate.style.backgroundColor = "#ffffff";
        }
        if($('#datetimepicker3').find("input").val() === ""){
            limitHour.style.backgroundColor = "#ffd6cc";
            validated = false;
        }else{
            limitHour.style.backgroundColor = "#ffffff";
        }
        if(valueInput.value == "" || valueInput.value < 0 || valueInput.value > 100)  {
            valueInput.style.backgroundColor = "#ffd6cc";
            validated = false;
        }else valueInput.style.backgroundColor = "#ffffffff";
        if(chapter.value.toString() == "")  {
            $('[data-id="select-chapter"]').css("background-color","#ffd6cc","important");
            validated = false;
        }else
            $('[data-id="select-chapter"]').css("background-color","#ffffffff","important");
        if(typology.value.toString() == "")  {
            $('[data-id="select-typology"]').css("background-color","#ffd6cc","important");
            validated = false;
        }else
            $('[data-id="select-typology"]').css("background-color","#ffffffff","important");

        if(validated) {
            var tempDate = $('#datetimepicker1').find("input").val();
            tempDate = tempDate.split("/").reverse().join("/");
            tempDate += " " + $('#datetimepicker3').find("input").val() + ":00"
            $('#datetimepicker1').find("input").val(tempDate);
        }

        return validated;

    }

</script>


<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <form:form class="custom-form" method="post" action="/teacher/subjects/task/create" modelAttribute="task">
            <input type="hidden" name="subjectId" id="subjectId" value="${subjectId}"/>
            <h1>Add Task</h1>
            <!-- Title -->
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><form:label path="title"
                                                               class="col-form-label">Name </form:label></div>
                <div class="col-sm-8 input-column"><form:input path="title" class="form-control"
                                                               type="text" id="name"></form:input></div>
            </div>
            <!-- Deadline -->
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <form:label path="deadline" class="col-form-label">Day </form:label></div>
                <div class="col-sm-8 input-column" style="position: relative">
                    <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                        <form:input path="deadline" type="text" class="form-control datetimepicker-input"
                                    data-target="#datetimepicker1" id="limitDate"/>
                        <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <label class="col-form-label">Hour </label></div>
                <div class="col-sm-8 input-column" style="position: relative">
                    <div class="input-group date" id="datetimepicker3" data-target-input="nearest">
                        <input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1" value="23:59" id="limitHour"/>
                        <div class="input-group-append" data-target="#datetimepicker3" data-toggle="datetimepicker">
                            <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Description -->
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><form:label path="description"
                                                               class="col-form-label">Description </form:label></div>
                <div class="col-sm-8 input-column"><form:textarea path="description" class="form-control"
                                                               type="text"></form:textarea></div>
            </div>
            <!-- Value -->
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><form:label path="value"
                                                               class="col-form-label">Value </form:label></div>
                <div class="col-sm-8 input-column"><form:input path="value" class="form-control"
                                                               type="number" id="value" min="0" max="100"></form:input></div>
            </div>
            <!-- Typology -->
            <c:set var="enumValues" value="<%=TaskType.values()%>"/>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <form:label path="taskType" class="col-form-label">Task Type </form:label></div>
                <div class="col-sm-8 input-column">
                    <form:select class="selectpicker" data-width="100%" path="taskType" id="select-typology">
                        <form:option disabled="disabled" selected="selected" value="">Select a typology</form:option>
                        <c:forEach items="${enumValues}" var="enumValue">
                            <form:option value="${enumValue}"></form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <!-- Chapter -->
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <form:label path="chapter.chapterId" class="col-form-label">Chapter </form:label></div>
                <div class="col-sm-8 input-column">
                    <form:select class="selectpicker" data-width="100%" path="chapter.chapterId" id="select-chapter">
                        <form:option disabled="disabled" selected="selected" value="">Select a chapter</form:option>
                        <c:forEach items="${chapters}" var="chapter">
                            <form:option value="${chapter.chapterId}">${chapter.title}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
        </form:form>
    </div>
</div>

</body>

</html>