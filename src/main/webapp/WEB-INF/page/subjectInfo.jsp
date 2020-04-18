<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Modify Subject</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">
            <form method="post" action="/teacher/subjects/modify/${subject.subjectId}">
            <h1>Modify Subject</h1>
            <input type="hidden" value="${subject.subjectId}"/>
            <input type="hidden" value="${subject.groupId}"/>
            <div class="container">
                <div class="row">
                    <div align="right" class="col"><strong>Name:</strong></div>
                    <div align="left" class="col">${subject.name}</div>
                </div>
                <div class="row justify-content-center">
                    <div align="right" class="col"><strong>Subject ID:</strong></div>
                    <div align="left" class="col">${subject.subjectId}</div>
                </div>
                <div class="row justify-content-center">
                    <div align="right" class="col"><strong>Group:</strong></div>
                    <div align="left" class="col">${subject.subjectGroup.stage} ${subject.subjectGroup.level} ${subject.subjectGroup.group}</div>
                </div>
            </div><br><br>
            <table class="table table-borderless table-striped">
                <thead>
                <tr>
                    <th><strong>Task name</strong></th>
                    <th><strong>Chapter</strong></th>
                    <th><strong>Deadline</strong></th>
                    <th><strong>Type</strong></th>
                    <th><strong>Value</strong></th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="teachers">
                <c:forEach items="${subject.chapters}" var="chapter">
                    <c:forEach items="${chapter.tasks}" var="task">
                        <tr>
                            <td style="vertical-align: middle; horiz-align: center">${task.title}</td>
                            <td style="vertical-align: middle; horiz-align: center">${task.chapter.title}</td>
                            <td style="vertical-align: middle; horiz-align: center">${task.deadline}</td>
                            <td style="vertical-align: middle; horiz-align: center">${task.taskType}</td>
                            <td style="vertical-align: middle; horiz-align: center">${task.value}%</td>
                            <td style="vertical-align: middle; text-align: center"><a class="btn btn-danger" href="/"
                                                                                      data-toggle="modal"
                                                                                      data-target="#myModal"
                                                                                      onclick="deleteClicked('${subject.subjectId}', '${subject.name}')"><i
                                    class="fa fa-trash-o" aria-hidden="true"></i></a></td>
                        </tr>
                    </c:forEach>
                </c:forEach>
                </tbody>
            </table>
            <button class="btn btn-light submit-button" type="submit" value="addTeacher" name="buttonName"
                    id="addTeacher" onclick="return Validate()">Add Task
            </button>
        </div>
    </div>
</div>

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
                <p id="deleteText">Do you really want to delete this task?
                    This process cannot be undone.</p>
            </div>
            <input type="hidden" name="elementType" id="elementType" value=""/>
            <input type="hidden" name="elementId" id="elementId" value=""/>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteElement()">Delete
                </button>
            </div>
        </div>
    </div>
</div>


</form>

<script type="text/javascript">
    function Validate() {
        return true;
    }

    function deleteClicked(elementId, elementType) {
        var hiddenInputId = jQuery('#elementId');
        var hiddenInputType = jQuery('#elementType');
        hiddenInputId.val(elementId);
        hiddenInputType.val(elementType);
    }

    function deleteElement() {
        var hiddenInputId = jQuery('#elementId');
        var hiddenInputType = jQuery('#elementType');
        var type = hiddenInputType.val()
        if (type === 'timeLine') {
            window.location.href = '/subject/delete/' + type.toLowerCase() + '?' + type + 'Id=' + hiddenInputId.val();

        } else if (type === 'teacher') {
            var subjectId = jQuery('#subjectId').val();
            window.location.href = '/subject/delete/' + type + '?' + type + 'Id=' + hiddenInputId.val() + '&subjectId=' + subjectId;
        }
    }


</script>


</body>

</html>