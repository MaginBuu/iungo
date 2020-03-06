<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Untitled</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/ticket/Animation-Cards.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="/resource/css/ticket/styles.css">
</head>

<body>
<%@ include file="/WEB-INF/page/navbar.jsp" %>


<div style="justify-content: center;text-align: center; padding-top: 40px">
    <h1 align="center">Ticket History</h1>
</div>
<div class="row d-flex justify-content-center" style="margin: auto">
    <a class="fa fa-square" style="color: #7C7C7C"> Created</a>
    <a class="fa fa-square" style="padding-left:5px;color: #73956F"> Ongoing</a>
    <a class="fa fa-square" style="padding-left:5px;color: #776871"> Resolved</a>
    <a class="fa fa-square" style="padding-left:5px;color: #E19268"> Cancelled</a>
</div>


<table id="dtBasicExample" class="table table-borderless table-sm" cellspacing="0" width="70%">
    <tbody>

    <c:forEach items="${user.tickets}" var="ticket">
        <tr>
            <td>
            <div class="row">
                <div class="card cards-shadown cards-hover">
                    <div id="${ticket.ticketId}" class="card-header cards-header-hover"><span class="space"><a
                            href="#"></a></span>
                        <div class="row justify-content-between cardheader-text">
                            <div class="col-4"><h4 id="heading-card">${ticket.title}</h4></div>
                            <div class="col-4" align="right"><p id="cardheader-subtext">Ticket
                                reference: ${ticket.ticketId}</p></div>
                        </div>
                    </div>
                    <div class="card-body">
                        <p class="card-text sub-text-color"><strong>Description:</strong></p>
                        <p class="card-text sub-text-color">${ticket.description}</p>
                        <c:if test="${ticket.adminResponse ne null}">
                            <p class="card-text sub-text-color"><strong>Admin Response:</strong></p>
                            <p class="card-text sub-text-color">${ticket.adminResponse}</p>
                        </c:if>

                        <div class="row justify-content-between cardheader-text">
                            <p class="card-text sub-text-color" style="padding-left: 15px">
                                <strong>Status: </strong>${ticket.status}</p>
                            <p class="card-text sub-text-color" style="padding-right: 15px"><strong>Creation
                                date: </strong>${ticket.creationDate}</p>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${ticket.status eq 'CREATED'}">
                    <script>
                        document.getElementById("${ticket.ticketId}").style.background = "#7C7C7C";
                    </script>
                </c:when>
                <c:when test="${ticket.status eq 'RESOLVED'}">
                    <script>
                        document.getElementById("${ticket.ticketId}").style.background = "#73956F"; //86CD82
                    </script>
                </c:when>
                <c:when test="${ticket.status eq 'ONGOING'}">
                    <script>
                        document.getElementById("${ticket.ticketId}").style.background = "#776871";
                    </script>
                </c:when>
                <c:otherwise>
                    <script>
                        document.getElementById("${ticket.ticketId}").style.background = "#E19268";
                    </script>
                </c:otherwise>
            </c:choose>
        </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    // Basic example
    $(document).ready(function () {
        $('#dtBasicExample').DataTable({
            "paging": true
        });
        $('.dataTables_length').addClass('bs-select');
    });
    $(document).ready(function () {
        $('#dtBasicExample').DataTable({
            "pagingType": "simple_numbers"
        });
        $('.dataTables_length').addClass('bs-select');
    });
</script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
</body>

</html>