<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>UserCreation</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker3.css">
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/Pretty-Registration-Form.css">
    <link rel="stylesheet" href="/resource/css/creation/styles.css">

</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <c:url value="/ticket/creation" var="url"></c:url>
        <form:form class="custom-form" method="post" action="/ticket/modify" modelAttribute="ticket">
            <h1>Update Ticket</h1>
            <form:hidden path="ticketId"/>
            <form:hidden path="title"/>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Title </label></div>
                <div class="col-sm-8 input-column">${ticket.title}</div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Ticket ID </label></div>
                <div class="col-sm-8 input-column">${ticket.ticketId}</div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Date </label></div>
                <div class="col-sm-8 input-column">${ticket.creationDate}</div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Description </label></div>
                <div class="col-sm-8 input-column">${ticket.description}</div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><form:label path="adminResponse"
                                                               class="col-form-label">Title </form:label></div>
                <div class="col-sm-8 input-column"><form:input path="adminResponse" class="form-control"
                                                               type="text"></form:input></div>
            </div>

            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Status </label></div>
                <div class="col-sm-2 input-column">
                    <form:select class="selectpicker" data-width="100%" path="status">
                        <form:option value="CREATED">Created</form:option>
                        <form:option value="ONGOING">Ongoing</form:option>
                        <form:option value="CANCELLED">Cancelled</form:option>
                        <form:option value="RESOLVED">Resolved</form:option>
                    </form:select>
                </div>
            </div>

            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Update</button>
        </form:form>
    </div>
</div>

<script type="text/javascript">
    function Validate() {
        return true;
    }
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.es.min.js"></script>
<script src="/resource/js/My-Date-Picker.js"></script>
</body>

</html>