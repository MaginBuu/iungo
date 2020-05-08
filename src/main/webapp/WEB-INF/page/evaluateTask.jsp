<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Task Evaluation</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="/resource/css/ticket/cards.css">

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
<%@ include file="navbar.jsp" %>
<form:form method="post" action="/teacher/task/saveEvaluation/${subjectId}/${taskInfo.taskId}" modelAttribute="taskList">
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <br><br>
        <div class="container custom-div">
            <h1>Task "${taskInfo.title}" Evaluation</h1>
            <table class="table table-borderless table-striped" style="width:100%">
                <thead>
                <tr>
                    <th><strong>Name</strong></th>
                    <th><strong>Surname</strong></th>
                    <th><strong>Sec. Surname</strong></th>
                    <th><strong>Observation</strong></th>
                    <th><strong>Grade</strong></th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="myTable">
                <c:forEach items="${taskList.tasks}" var="task" varStatus="status">
                    <tr>
                        <td style="vertical-align: middle; horiz-align: center">${task.student.userR.name}</td>
                        <td style="vertical-align: middle; horiz-align: center">${task.student.userR.surname}</td>
                        <td style="vertical-align: middle; horiz-align: center">${task.student.userR.secondSurname}</td>
                        <td style="vertical-align: middle; horiz-align: center" width="50%"><input name="tasks[${status.index}].observations" style="width:100%" value="${task.observations}"/></td>
                        <td style="vertical-align: middle; horiz-align: center" width="5%"><input id="grade-${task.student.roleId}" name="tasks[${status.index}].grade" style="width:100%" value="${task.grade}" min="0" max="10" onfocusout="check(${task.student.roleId})"/>
                            <input type="hidden" name="tasks[${status.index}].student.roleId" value="${task.student.roleId}"/></td>
                        <td width="5%"><a><i class="fa fa-eye" style="color:#DE9D3F" aria-hidden="true"></i></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <input class="btn btn-light submit-button" type="submit" value="Save" />
        </div>
    </div>
</div>
</form:form>
<!-- Modal HTML -->
<div id="myModal" name="myModal" class="modal fade">
    <div class="modal-dialog modal-confirm">
        <div class="modal-content">
            <div class="modal-header">
                <div class="icon-box">
                    <i class="material-icons">&#xE5CD;</i>
                </div>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <h4 class="modal-title">Are you sure?</h4>
                <p id="deleteText">Do you really want to delete this space?
                    This process cannot be undone.</p>
            </div>
            <input type="hidden" name="elementId" id="elementId" value=""/>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteElement()">Delete
                </button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    function check(id) {
        var input = document.getElementById("grade-"+id);
        if(input.value > 10 || input.value < 0){
            if(input.value > 10) {
                input.value = 10.0;
                input.style.backgroundColor = "#ffd6cc";
            }
            if(input.value < 0) {
                input.value = 0.0;
                input.style.backgroundColor = "#ffd6cc";
            }
        }else if(input.value < 10 && input.value > 0) input.style.backgroundColor = "#ffffffff";
    }

</script>
</body>


</html>

