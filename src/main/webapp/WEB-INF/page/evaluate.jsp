<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Subjects</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="/resource/css/ticket/cards.css">
    <link rel="stylesheet" href="/resource/css/profile/profileStyle.css">

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/resource/css/base/dropdownModal.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
</head>


<body>

<script type="text/javascript">
    function Validate() {
        return true;
    }

    function evaluateClicked(student) {
        console.log(student)
        var hiddenInput = jQuery('#studentId');
        hiddenInput.val(student);
    }

    function evaluateStudent() {
        var student = jQuery('#studentId');
        var evaluation = jQuery('#evaluationId');
        window.location.href = '/teacher/evaluate/' + student.val() + '?evaluationId=' + evaluation.val();
    }

    function selectedEvaluation(){
        var e = document.getElementById("evaluation-pick");
        var result = e.options[e.selectedIndex].value;
        var hiddenInput = jQuery('#evaluationId');
        hiddenInput.val(result);
        document.getElementById("continue-btn").disabled = false;
    }

</script>

<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <br><br>
        <div class="container custom-div">
            <h1>Evaluation</h1>
            <div class="row justify-content-center features">
            <c:forEach items="${listStudents}" var="student">
                <div class="col-sm-6 col-md-5 col-lg-4 item">
                    <div class="profile_card">
                        <div class="profile_box">
                            <div class="img">
                                <img src="/resource/img/placeholder-profile.jpg">
                            </div>
                            <h2>${student.userR.name} ${student.userR.surname} ${student.userR.secondSurname}<br>
                            </h2>
                            <a style="margin-top:10px" class="btn btn-light submit-button" href="/" data-toggle="modal" data-target="#myModal"
                               onclick="evaluateClicked('${student.userR.userId}')">Evaluate</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </div>
        </div>
    </div>
</div>

<!-- Modal HTML -->
<div id="myModal" name="myModal" class="modal fade">
    <div class="modal-dialog modal-confirm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <h4 class="modal-title">Select an evaluation</h4>
                <p id="deleteText">Select an existing evaluation from the list below.</p>
                <div class="row" style="justify-content: center">
                    <select class="selectpicker" data-width="100%" style="vertical-align: middle;" id="evaluation-pick" name="evaluation-pick" onchange="selectedEvaluation()">
                        <option disabled="disabled" selected="selected" value="">Select an evaluation</option>
                        <c:forEach items="${evaluationList}" var="eval">
                            <option value="${eval.evaluationId}" style="color:#000000">
                                    ${fn:substring(eval.term.toString(),0,1)}${fn:toLowerCase(fn:substring(eval.term.toString(),1,(fn:length(eval.term.toString()))))}
                                        term evaluation (${eval.course.startDate}-${eval.course.endDate})</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <input type="hidden" name="studentId" id="studentId" value=""/>
            <input type="hidden" name="evaluationId" id="evaluationId" value=""/>
            <div class="modal-footer">
                <button type="button" style="vertical-align: middle;" class="btn-modal btn-info" data-dismiss="modal">Cancel</button>
                <button id="continue-btn" style="vertical-align: middle;" class="btn-modal btn-modal-access btn-light submit-button" disabled="true" data-dismiss="modal" onclick="evaluateStudent()">Evaluate</button>
            </div>
        </div>
    </div>
</div>


</body>


</html>

