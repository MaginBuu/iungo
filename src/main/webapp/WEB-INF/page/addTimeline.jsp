<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Add timeline</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker3.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

</head>

<body>
<%@ include file="navbar.jsp" %>
<input id="hores-inicials" type="hidden">
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">
            <form:form modelAttribute="timeline">
                <h1>Add timeline</h1>
                <div class="row justify-content-md-center">
                    <div class="col col-md-auto"><strong>Spaces:</strong></div>
                    <div class="col">
                        <form:select class="selectpicker" data-width="100%" path="finishingHour" id="select-ajax">
                            <form:option selected="selected" value="">Select a typology
                            </form:option>
                            <c:forEach items="${spaces}" var="space">
                                <form:option value="${space.spaceId}">${space.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>

                </div>
            </form:form>
            <div class="container">
                <div class="col col-md-auto">
                    <button class="btn btn-light" id="btn-ajax">Search availability</button>
                </div>
                <div class="row justify-content-md-center" id="availability-table">
                    <table class="table table-borderless">
                        <tbody id="myTable">
                        <tr>
                            <td style="horiz-align: right; text-align: right"><strong>Text 1:</strong></td>
                            <td style="horiz-align: right;" id="potato"></td>
                        </tr>
                        <tr>
                            <td style="horiz-align: right; text-align: right"><strong>Text 2:</strong></td>
                            <td style="horiz-align: right;" id="potato2"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<script type="text/javascript">

    console.log("inici ajax");
    $("#btn-ajax").click(function () {
        console.log("function ajax");
        $.ajax({

            type: "GET",
            url: "ajaxdos",
            dataType: "json",
            contentType: 'application/json',
            data: {
                "var1": $("#select-ajax option:selected").val()
            }, //aqui es passen els parametres
            success: function (data) {
                $.each(data, function (index, currEmp) {
                    console.log(currEmp); //to print name of employee
                    $("#potato").html("changed value");
                    $("#potato2").html("angawa value");
                    $("#hores-inicials").html(data);
                });


            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });
    });

</script>
</body>

</html>