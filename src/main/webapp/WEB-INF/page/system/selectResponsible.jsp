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
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>


</head>

<body>
<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <form class="custom-form" action="/user/creation/selectChild" method="post">
            <h1>Select Responsible for ${sessionScope.userRelateName}</h1>
            <div class="form-row form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-8 label-column">
                    <table class="table table-borderless table-striped">
                        <thead>
                        <tr>
                            <th><strong>Name</strong></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="myTable">
                        <c:forEach items="${responsibles}" var="responsible">
                            <tr>
                                <td style="vertical-align: middle; horiz-align: center">${responsible.userR.name} ${responsible.userR.surname} ${responsible.userR.secondSurname}</td>
                                <td style="vertical-align: middle; text-align: center"><a class="btn btn-danger" href="/" data-toggle="modal" data-target="#myModal" onclick="deleteClicked('${responsible.userR.userId}')"><i class="fa fa-trash-o" aria-hidden="true"></i> Delete</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <a class="btn btn-light submit-button" href="/user/searchResponsible">Add new Responsible to the student</a>
            <a class="btn btn-light submit-button" href="/user/creation">Add new Responsible into database</a>
            <a class="btn btn-light submit-button" onclick="relate()">Accept</a>
        </form>
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
    function relate() {
        window.location.href = '/user/creation/finishRelateResponsible';
    }

    function addResponsible() {
    }

    function deleteClicked(elementId, elementType) {
        var hiddenInputId = jQuery('#elementId');
        hiddenInputId.val(elementId);
        hiddenInputType.val(elementType);
    }

    function deleteElement() {
        var hiddenInputId = jQuery('#elementId');
        var type = hiddenInputType.val()
        if(type === 'timeLine'){
            window.location.href = '/subject/delete/' + type.toLowerCase()+ '?' + type + 'Id=' + hiddenInputId.val();

        }else if(type === 'teacher'){
            window.location.href = '/ResponsibleRelation/delete/' + type+ '?' + type + 'Id=' + hiddenInputId.val();
        }
    }
</script>

</body>

</html>