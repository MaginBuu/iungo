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
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css"/>

</head>

<body onload="initGrade(${grade})">
<script>

    function initGrade(gradeValue){
        var grade = document.getElementById("grade");
        if(grade == "-"){
            grade.innerHTML = "-";
        }else{
            var a = parseFloat(gradeValue);
            grade.innerHTML = a;
            if(a < 5.0) grade.style.backgroundColor = "#ffd6cc";
            else grade.style.backgroundColor = "#B9D2B1";
        }
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
            <button class="btn btn-light submit-button" style="background-color: lightgrey">Report task</button>
        </div>
    </div>
</div>

</body>

</html>