<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Procedures</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/ticket/cards.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="/resource/css/ticket/styles.css">
</head>

<body>
<%@ include file="navbar.jsp" %>

<div style="justify-content: center;text-align: center; padding-top: 40px">
    <h1 align="center">Procedures</h1>
</div>


<c:if test="${procedures ne null}">
    <div class="container" style="padding-top: 15px">
        <input class="form-control mb-4" id="tableSearch" type="text"
               placeholder="Type something to search list items">
        <table class="table table-borderless table-sm" cellspacing="0" width="100%" style="width: 100%">
            <tbody id="myTable">
            <c:forEach items="${procedures}" var="procedure">
                <tr>
                    <td>
                        <div class="card " style="width: 100%">
                            <div id="${procedure.procedureId}" class="card-header-no-hover"><span class="space"></span>
                                <div class="justify-content-between cardheader-text">
                                <p id="title" style="font-size:25px;margin-bottom: 0;padding-left: 1.5%;"><strong>${procedure.title}</strong></p>
                                <p id="deadline" style="font-size:12px;padding-left: 1.5%;">
                                    <strong>Creation date: </strong>${procedure.creationDate}
                                    <strong>Deadline: </strong>${procedure.limitDate}
                                </p>
                                </div>
                            </div>
                            <div class="card-body">
                                <p class="card-text sub-text-color">${procedure.description}</p>
                                <c:if test="${procedure.online eq true}">
                                    <div class="row float-right" style="align-content: end">
                                        <button class="btn btn-light submit-button" style="margin-right: 10px" onclick="decision(${procedure.procedureId},true)">Accept</button>
                                        <button class="btn btn-light submit-button" style="margin-right: 20px" onclick="decision(${procedure.procedureId},false)">Deny</button>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test="${procedures eq null}">
    <br><br>
    <div class="container" style="margin: auto;text-align: center;">
        <div class="d-flex justify-content-center">
            <div class="col-md-6 login-form-1 ">
                <svg xmlns="http://www.w3.org/2000/svg" width="100" fill="#4c565e" height="100" viewBox="0 0 24 24">
                    <path d="M12 2c5.514 0 10 4.486 10 10s-4.486 10-10 10-10-4.486-10-10 4.486-10 10-10zm0-2c-6.627 0-12 5.373-12 12s5.373 12 12 12 12-5.373 12-12-5.373-12-12-12zm4 17h-8v-2h8v2zm-.499-6.296l-1.298 1.296-1.203-1.204 1.298-1.296-1.298-1.296 1.203-1.204 1.298 1.296 1.296-1.296 1.203 1.204-1.297 1.296 1.297 1.296-1.202 1.204-1.297-1.296zm-7 0l-1.298 1.296-1.203-1.204 1.298-1.296-1.298-1.296 1.203-1.204 1.298 1.296 1.296-1.296 1.203 1.204-1.297 1.296 1.297 1.296-1.202 1.204-1.297-1.296z"/>
                </svg>
                <h5 style="color: #4c565e">Ehem... that's awkward</h5><br>
                <h2 style="color: #4c565e">No tickets yet!</h2>
                <br><br>
                <button class="btn btn-sm btn-outline-secondary align-items-center" onclick="history.back()">Go back
                </button>
            </div>
        </div>
    </div>
</c:if>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.es.min.js"></script>
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

    function decision(id, decision){
            window.location.href = "/user/procedure/response?id="+id+"&decision="+decision;
    }

</script>


</html>


