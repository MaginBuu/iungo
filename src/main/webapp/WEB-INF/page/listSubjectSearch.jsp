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

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
</head>

<script type="text/javascript">
    function Validate() {
        return true;
    }

    //triggered when modal is about to be shown
    function deleteClicked(subjectId, subjectName) {
        var hiddenInput = jQuery('#elementId');
        var deleteText = jQuery('#deleteText');
        deleteText.html("Do you really want to delete the subject <\strong>" + subjectName + "</strong>? This process cannot be undone.")
        hiddenInput.val(subjectId);
    }

    function deleteElement() {
        var hiddenInput = jQuery('#elementId');
        window.location.href = '/subject/delete?subjectId=' + hiddenInput.val();
    }
</script>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <br><br>
        <div class="container custom-div">
            <h1>Subjects Found</h1>
            <input class="form-control mb-4" id="tableSearch" type="text"
                   placeholder="Type something to search list items">
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th><strong>ID</strong></th>
                    <th><strong>Name</strong></th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="myTable">
                <c:forEach items="${subjects}" var="subject">
                    <tr>
                        <td style="vertical-align: middle; horiz-align: center">${subject.subjectId}</td>
                        <td style="vertical-align: middle; horiz-align: center">${subject.name}</td>
                        <td style="vertical-align: middle; text-align: center"><a class="btn btn-warning" href="/subject/modify/${subject.subjectId}"><i
                                class="fa fa-pencil" aria-hidden="true"></i> Edit</a></td>
                        <td style="vertical-align: middle; text-align: center"><a class="btn btn-danger" href="/" data-toggle="modal" data-target="#myModal"
                                onclick="deleteClicked('${subject.subjectId}', '${subject.name}')"><i
                                class="fa fa-trash-o" aria-hidden="true"></i> Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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
    function Validate() {
        return true;
    }
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
</body>


<script type="text/javascript">
    $(document).ready(function () {
        $("#tableSearch").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });

</script>

</html>

