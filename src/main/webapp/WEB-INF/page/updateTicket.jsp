<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Update Ticket</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker3.css">
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/ticket/ticket.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">

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
        <div class="container">
            <div class="row justify-content-md-center">
                <table class="table table-borderless" style="width: 85%">
                    <tbody id="myTable">
                    <tr>
                        <td style="vertical-align: middle; text-align: right"><strong>Title:</strong></td>
                        <td style="vertical-align: middle; text-align: left">${ticket.title}</td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle; text-align: right"><strong>Ticket ID:</strong></td>
                        <td style="vertical-align: middle; text-align: left">${ticket.ticketId}</td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle; text-align: right"><strong>Creation Date:</strong></td>
                        <td style="vertical-align: middle; text-align: left">${ticket.creationDate}</td>
                    </tr>
                    <tr>
                        <td style="horiz-align: right; text-align: right"><strong>Description:</strong></td>
                        <td style="text-justify: distribute; text-align: left">${ticket.description}</td>
                    </tr>
                    <tr>
                        <td style="horiz-align: right; text-align: right"><strong>Response:</strong></td>
                        <td><form:input path="adminResponse" class="form-control" type="text"/></td>
                    </tr>
                    <tr>
                        <td style="horiz-align: right; text-align: right"><strong>Status:</strong></td>
                        <td style="align-content: start">
                            <form:select class="selectpicker" data-width="100%" path="status">
                                <form:option value="CREATED">Created</form:option>
                                <form:option value="ONGOING">Ongoing</form:option>
                                <form:option value="CANCELLED">Cancelled</form:option>
                                <form:option value="RESOLVED">Resolved</form:option>
                            </form:select></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="row justify-content-md-center">
                <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Update</button>
                </form:form>
                <div>
                </div>
            </div>

            <script type="text/javascript">
                function Validate() {
                    return true;
                }
            </script>

            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
            <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
</body>

</html>