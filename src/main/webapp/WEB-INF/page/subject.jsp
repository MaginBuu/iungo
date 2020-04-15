<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Find Elements</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/ticket/cards.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <br><br>
        <form class="custom-form" action="/element/find" method="get">
            <h1>Find Element</h1>
            <div class="form-row form-group" id="lluis">
                <div class="col-sm-3 label-column"><select class="selectpicker" data-width="100%" id="elementKind"
                                                           name="elementKind" onchange="paprika()">
                    <option value="" selected>Select a subject</option>
                    <c:forEach items="${subjects}" var="subject">
                        <option value="user">${subject.name}</option>
                    </c:forEach>
                </select>
                </div>
            </div>
            <div class="form-row form-group" id="lluisa">
                <div class="col-sm-3 label-column" >

                </div>
            </div>
            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Search</button>
        </form>
    </div>
</div>

<script type="text/javascript">
    function Validate(){
        return true;
    }

    function paprika() {
        document.getElementById("lluis").style.backgroundColor = "hsla(39.17, 97.65%, 57.42%, 0.08)";
        var table = document.getElementById("lluisa");
        $.ajax({

            type: "GET",
            url: "/teacher/getSubjectInformation",
            //dataType: "json",
            //contentType: 'application/json',
            data: {
            }, //aqui es passen els parametres
            success: function (data) {
                $("#lluisa").html(data);
            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });

    }
</script>

</body>

</html>

