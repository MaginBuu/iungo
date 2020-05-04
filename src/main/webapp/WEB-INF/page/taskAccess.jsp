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
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

</head>

<body onload="initGrade(${grade}), loadNotifications(), loadRoles()">
<script>

    function initGrade(gradeValue){
        var grade = document.getElementById("grade");
        if(gradeValue == "-"){
            grade.innerHTML = "-";
        }else{
            var a = parseFloat(gradeValue);
            grade.innerHTML = a;
            if(a < 5.0) grade.style.backgroundColor = "#ffd6cc";
            else grade.style.backgroundColor = "#B9D2B1";
        }
    }

    function report() {
        window.location.href = '${task.taskId}/report';
    }


</script>


<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">
            <input type="hidden" name="subjectId" id="subjectId" value="${subjectId}"/>
            <h1>${task.title}</h1>
            <!-- Title -->
            <div class="form-row form-group">
                <div class="col-sm-12" style="font-size: 16px; text-align: center;margin-bottom: 20px">${task.description}</div>
            </div>
            <!-- Deadline -->
            <div class="form-row form-group">
                <div class="col-sm-3 label-column" style="margin-right:20px"><strong>Deadline: </strong></div>
                <div class="col-sm-3 input-column" style="text-align: left;">${task.deadline}</div>
            </div>
            <!-- Description -->
            <div class="form-row form-group">
                <div class="col-sm-3 label-column" style="margin-right:20px"><strong>Grade: </strong></div>
                <div id="grade" style="border-radius: 3px" class="col-sm-2 input-column"></div>
                <div class="col-sm-3 label-column" style="margin-right:20px"><strong>Value: </strong></div>
                <div class="col-sm-2 input-column" style="text-align: left;">${task.value} %</div>
            </div>
            <!-- Observations -->
            <div class="form-row form-group">
                <div class="col-sm-3 label-column" style="margin-right:20px"><strong>Observations: </strong></div>
                <div id="observation" class="col-sm-8 input-column" style="text-align: left;">${observations}</div>
            </div>
            <c:if test="${solved eq false}">
                <button class="btn btn-light submit-button">Answer task</button>
            </c:if>
            <c:if test="${task.reports <4}">
                <a class="btn btn-light submit-button" data-toggle="modal" data-target="#myModal" style="background-color: lightgrey">Report task</a>
            </c:if>
        </div>
    </div>
</div>

<!-- Modal HTML -->
<div id="myModal" name="myModal" class="modal fade">
    <div class="modal-dialog modal-confirm">
        <div class="modal-content">
            <div class="modal-body">
                <h4 class="modal-title">Are you sure you want to report?</h4>
                <p id="deleteText">False reporting could carry sanctions. If you want to give further details about the report, please contact the teacher.</p>
            </div>
            <input type="hidden" name="spaceId" id="spaceId" value=""/>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-info" data-dismiss="modal" onclick="report()">Report an error</button>
            </div>
        </div>
    </div>
</div>

</body>

</html>