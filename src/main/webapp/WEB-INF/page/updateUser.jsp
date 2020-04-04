<%@ page import="com.model.enums.Role" %>
<%@ page import="com.model.enums.GenderType" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Modify User</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="/resource/css/tabNavStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.es.min.js"></script>
    <script src="/resource/js/My-Date-Picker.js"></script>


</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">
            <form:form method="post" action="/user/modify" modelAttribute="user">
            <h1>Modify User</h1>
            <form:hidden name="userId" id="userId" path="userId"/>
            <form:hidden path="username"/>
            <form:hidden path="role" id="roleHidden" name="roleHidden"/>
                <table class="table table-borderless">
                <tbody id="myTable">
                <tr>
                    <td style="vertical-align: middle; text-align: right"><strong>Name:</strong></td>
                    <td><form:input path="name" data-width="30%" class="form-control" type="text"></form:input></td>
                    <td style="vertical-align: middle; text-align: right"><strong>Surname:</strong></td>
                    <td><form:input path="surname" data-width="30%" class="form-control" type="text"></form:input></td>
                </tr>
                <tr>
                    <td style="vertical-align: middle; text-align: right"><strong>Second surname:</strong></td>
                    <td><form:input path="secondSurname" data-width="30%" class="form-control" type="text"></form:input></td>
                    <td style="vertical-align: middle; text-align: right"><strong>Second surname:</strong></td>
                    <td><form:input path="birth" class="form-control" type="text"></form:input>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span></td>
                </tr>
                <tr>
                    <td style="vertical-align: middle; text-align: right"><strong>email:</strong></td>
                    <td><form:input path="emailId" data-width="30%" class="form-control" type="text"></form:input></td>
                    <td style="vertical-align: middle; text-align: right"><strong>username:</strong></td>
                    <td style="vertical-align: middle; text-align: left">${user.username}</td>
                </tr>
                <tr>
                    <c:set var="enumValues" value="<%=GenderType.values()%>"/>
                    <td style="horiz-align: right; text-align: right"><strong>gender:</strong></td>
                    <td style="horiz-align: right;">
                        <form:select class="selectpicker" data-width="100%" path="gender">
                            <c:forEach items="${enumValues}" var="enumValue">
                                <form:option
                                        value="${enumValue}">${fn:toUpperCase(fn:substring(enumValue.name(),0,1))}${fn:toLowerCase(fn:substring(enumValue.name(),1,fn:length(enumValue.name())))}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>

                    <c:set var="enumValuesRole" value="<%=Role.values()%>"/>
                    <td style="horiz-align: right; text-align: right"><strong>Role:</strong></td>
                    <td style="horiz-align: right;">
                        <form:select class="selectpicker" multiple="true" data-width="100%" path="role" id="role-select" name="role-select" onchange="changeDisableDept()">
                            <c:forEach items="${enumValuesRole}" var="enumValue">
                                <form:option
                                        value="${enumValue}">${fn:toUpperCase(fn:substring(enumValue.name(),0,1))}${fn:toLowerCase(fn:substring(enumValue.name(),1,fn:length(enumValue.name())))}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                </tbody>
            </table>

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


</form:form>

<script type="text/javascript">
    function Validate() {
        return true;
    }

    window.onload = function () {
        var select2 = $('#role-select');
        var hiddenRole = $('#roleHidden').val().split(',');
        var roles = select2.val();

        for (var i = 0; i <  hiddenRole.length; i++){
            roles.push(hiddenRole[i]);
        }

        select2.val(roles);
        select2.selectpicker('refresh');
    }

    function changeDisableDept() {
        var select = $('#department-select');
        var select2 = $('#role-select');
        var valors = $('#role-select').val().toString().split(',');


        select.prop('disabled', !(valors.includes("TEACHER")
            || valors.includes("COORDINATOR")
            || valors.includes("TUTOR"))).selectpicker('refresh');
        if(!(valors.includes("TEACHER") || valors.includes("COORDINATOR") || valors.includes("TUTOR"))){
            document.getElementById("department-select").selectedIndex = "0";
        }

        if(valors.includes("COORDINATOR") || valors.includes("TUTOR")){
            var values = select2.val();
            values.push("TEACHER");
            select2.val(values);
            select2.selectpicker('refresh');

        }

        select.selectpicker('refresh');
    }

    function deleteClicked(elementId, elementType) {
        var hiddenInputId = jQuery('#elementId');
        var hiddenInputType= jQuery('#elementType');
        hiddenInputId.val(elementId);
        hiddenInputType.val(elementType);
    }

    function deleteElement() {
        var hiddenInputId = jQuery('#elementId');
        var hiddenInputType= jQuery('#elementType');
        var type = hiddenInputType.val()
        if(type === 'timeLine'){
            window.location.href = '/subject/delete/' + type.toLowerCase()+ '?' + type + 'Id=' + hiddenInputId.val();

        }else if(type === 'teacher'){
            var subjectId = jQuery('#subjectId').val();
            window.location.href = '/subject/delete/' + type+ '?' + type + 'Id=' + hiddenInputId.val() + '&subjectId=' + subjectId;
        }
    }


</script>


</body>

</html>