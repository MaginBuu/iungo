<%@ page import="com.model.enums.Typology" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - New Space</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker3.css">
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
</head>

<body>
<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <c:url value="/space/creation" var="url"></c:url>
        <form:form class="custom-form" method="post" action="/space/creation" modelAttribute="space"
                   commandName="space">
        <h1>Create Space</h1>
        <div class="form-row form-group">
            <div class="col-sm-3 label-column"><form:label path="name"
                                                           class="col-form-label">Name </form:label></div>
            <div class="col-sm-8 input-column"><form:input path="name" class="form-control"
                                                           type="text"></form:input></div>
        </div>
        <div class="form-row form-group">
            <div class="col-sm-3 label-column"><form:label path="capacity"
                                                           class="col-form-label">Capacity </form:label></div>
            <div class="col-sm-8 input-column"><form:input path="capacity" class="form-control"
                                                           type="number"></form:input></div>
        </div>
        <c:set var="enumValues" value="<%=Typology.values()%>"/>
        <div class="form-row form-group">
            <div class="col-sm-3 label-column">
                <form:label path="typology" class="col-form-label">Typology </form:label></div>
            <div class="col-sm-8 input-column">
                <form:select class="selectpicker" data-width="100%" path="typology">
                    <form:option disabled="disabled" selected="selected" value="">Select a typology</form:option>
                    <c:forEach items="${enumValues}" var="enumValue">
                        <form:option value="${enumValue}"></form:option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="form-row form-group">
            <div class="col-sm-2 label-column"></div>
            <div class="col-sm-8 label-column">
                <table class="table table-borderless">
                    <tbody id="booleans">
                    <tr>
                        <td style="horiz-align: right; text-align: right; color: #5F5F5F;">Blackboard:</td>
                        <td>
                            <form:checkbox path="blackboard"></form:checkbox>
                        </td>
                        <td style="horiz-align: right; text-align: right; color: #5F5F5F;">Interior:</td>
                        <td>
                            <form:checkbox path="interior"></form:checkbox>
                        </td>
                    </tr>
                    <tr>
                        <td style="horiz-align: right; text-align: right; color: #5F5F5F;">Projector:</td>
                        <td>
                            <form:checkbox path="projector"></form:checkbox>
                        </td>
                        <td style="horiz-align: right; text-align: right; color: #5F5F5F;">Platform:</td>
                        <td>
                            <form:checkbox path="platform"></form:checkbox>
                        </td>
                    </tr>
                    <tr>
                        <td style="horiz-align: right; text-align: right; color: #5F5F5F;">Tables:</td>
                        <td>
                            <form:checkbox path="tables"></form:checkbox>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div>
            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
            </form:form>
        </div>
    </div>


    <script type="text/javascript">
        function Validate() {
            var userP = document.getElementById("userP").value;
            if (userP == "") {
                alert("Select an user");
                return false;
            }
            return true;
        }
    </script>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.es.min.js"></script>
    <script src="/resource/js/My-Date-Picker.js"></script>
</body>

</html>