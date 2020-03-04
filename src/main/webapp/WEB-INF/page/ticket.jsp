<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

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
<%@ include file="/WEB-INF/page/navbar.jsp"%>
<div class="row space-rows">
    <div class="col">
    <c:forEach items="${user.tickets}" var="ticket">
        <div class="row" style="padding-top: 10px">
        <div class="card cards-shadown cards-hover" >
            <div class="card-header cards-header-hover"><span class="space"><a href="#"></a></span>
                <div class="row justify-content-between cardheader-text">
                    <div class="col-4"><h4 id="heading-card">${ticket.title}</h4></div>
                    <div class="col-4" align="right"><p id="cardheader-subtext">Ticket reference: ${ticket.ticketId}</p></div>
                </div>
            </div>
            <div class="card-body">
                <p class="card-text sub-text-color"><strong>Description:</strong></p>
                <p class="card-text sub-text-color">${ticket.description}</p>
                <p class="card-text sub-text-color"><strong>Status: </strong>${ticket.status}</p>
            </div>
        </div>
        </div>
    </c:forEach>
    </div>
</div>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.js"></script>
</body>

</html>