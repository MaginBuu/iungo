<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Tickets</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/ticket/cards.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
</head>

<body>
<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <br><br>
        <div class="container custom-div">
            <h1>Ticket List</h1>
            <input class="form-control mb-4" id="tableSearch" type="text"
                   placeholder="Type something to search list items">
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Title</th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="myTable">
                <c:forEach items="${tickets}" var="ticket">
                    <tr>
                        <td style="vertical-align: middle; horiz-align: center">${ticket.creationDate}</td>
                        <td style="vertical-align: middle; horiz-align: center">${ticket.status}</td>
                        <td style="vertical-align: middle; horiz-align: center">${ticket.title}</td>
                        <td style="vertical-align: middle; text-align: center"><a class="btn btn-info"
                                href="/ticket/modify?ticketId=${ticket.ticketId}">Edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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

