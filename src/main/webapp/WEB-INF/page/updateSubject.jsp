<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Modify Subject</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">

        <div class="container custom-div">
            <form:form method="post" action="/subject/modify" modelAttribute="subject">
            <h1>Modify Subject</h1>
            <form:hidden path="subjectId"/>
            <form:hidden path="groupId"/>
            <table class="table table-borderless">
                <tbody id="myTable">
                <tr>
                    <td style="vertical-align: middle; text-align: right"><strong>Name:</strong></td>
                    <td><form:input path="name" data-width="30%" class="form-control" type="text"></form:input></td>
                </tr>
                <tr>
                    <td style="vertical-align: middle; text-align: right"><strong>Subject ID:</strong></td>
                    <td style="vertical-align: middle; text-align: left">${subject.subjectId}</td>
                </tr>
                <tr>
                    <td style="horiz-align: right; text-align: right"><strong>Group:</strong></td>

                    <td style="vertical-align: middle; text-align: left">${subject.subjectGroup.name}</td>

                </tr>
                </tbody>
            </table>
            <table class="table table-borderless table-striped">
                <thead>
                <tr>
                    <th><strong>Starting hour</strong></th>
                    <th><strong>Finishing hour</strong></th>
                    <th><strong>Week Day</strong></th>
                    <th><strong>Space</strong></th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="timelines">
                <c:forEach items="${subject.timeline}" var="timelines">
                    <tr>
                        <td style="vertical-align: middle; horiz-align: center">${timelines.startingHour}</td>
                        <td style="vertical-align: middle; horiz-align: center">${timelines.finishingHour}</td>
                        <td style="vertical-align: middle; horiz-align: center">${timelines.weekday}</td>
                        <td style="vertical-align: middle; horiz-align: center">${timelines.spaceName}</td>
                        <td style="vertical-align: middle; text-align: center"><a class="btn btn-danger" href="/" data-toggle="modal" data-target="#myModal" onclick="deleteClicked('${timelines.timeLineId}')"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <button class="btn btn-light submit-button" type="submit" value="add" name="buttonName"
                    id="add" onclick="return Validate()">Add timeline
            </button>

            <button class="btn btn-light submit-button" type="submit" value="update" name="buttonName" id="update"
                    onclick="return Validate()">Update
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
                <p id="deleteText">Do you really want to delete this time line?
                    This process cannot be undone.</p>
            </div>
            <input type="hidden" name="timeLineId" id="timeLineId" value=""/>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteSpace()">Delete
                </button>
            </div>
        </div>
    </div>
</div>


</form:form>

<script type="text/javascript">
    function Validate() {
        return true;
    }

    function deleteClicked(timeLineId) {
        console.log(timeLineId);
        var hiddenInput = jQuery('#timeLineId');
        hiddenInput.val(timeLineId);
    }

    function deleteSpace(){
        var hiddenInput = jQuery('#timeLineId');
        window.location.href = '/subject/delete/timeline?timeLineId=' + hiddenInput.val();
    }

</script>


</body>

</html>