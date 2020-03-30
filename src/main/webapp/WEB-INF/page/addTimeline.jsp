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
            <form:hidden path="subjectId"/>
            <form:form path="/subject/add/timeline" method="post" modelAttribute="timeline">
                <h1>Add timeline</h1>
                <div class="row justify-content-md-center">
                    <div class="col justify-content-md-center"><strong>Spaces:</strong></div>
                    <div class="col">
                        <form:select class="selectpicker" path="finishingHour" id="select-ajax">
                            <form:option selected="selected" value="">Select a typology
                            </form:option>
                            <c:forEach items="${spaces}" var="space">
                                <form:option value="${space.spaceId}">${space.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="col justify-content-md-center"><strong>Weekday:</strong></div>
                    <div class="col">
                        <form:select class="selectpicker" path="weekday" id="select-weekday">
                            <form:option selected="selected" value="">Select a typology
                            </form:option>
                            <form:option value="MONDAY">Monday</form:option>
                            <form:option value="TUESDAY">Tuesday</form:option>
                            <form:option value="WEDNESDAY">Wednesday</form:option>
                            <form:option value="THURSDAY">Thursday</form:option>
                            <form:option value="FRIDAY">Friday</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="row justify-content-md-center">
                    <div class="col justify-content-md-center"><strong>Starting Hour:</strong></div>
                    <div class="col">
                        <select class="selectpicker" id="select-start" name="select-start">
                        </select>
                    </div>
                    <div class="col justify-content-md-center"><strong>Finishing Hour:</strong></div>
                    <div class="col">
                        <select class="selectpicker" id="select-finish" name="select-finish">
                        </select>
                    </div>
                </div>
            </form:form>
            <div class="row justify-content-md-center">
                <div class="col justify-content-md-center">
                    <button class="btn btn-dark" id="btn-ajax">Search availability</button>
                </div>
                <div class="col justify-content-md-center">
                    <button type="submit" class="btn btn-dark" id="btn-smb">Save</button>
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
                "var1": $("#select-ajax option:selected").val(),
                "weekday": $("#select-weekday option:selected").val()
            }, //aqui es passen els parametres
            success: function (data) {
                console.log("success ajax");
                var options, index, select, select2, option;
                $.each(data, function (i, result) {
                    console.log(result);
                });

                // Get the raw DOM object for the select box
                select = document.getElementById('select-start');
                select2 = document.getElementById('select-finish');
                // Clear the old options
                select.options.length = 0;
                console.log("abans del for ajax");
                var i;
                for (i = 8; i < 18/*cars.length*/; ++i) {

                    //option = cars[index];
                    //select.options.add(new Option(cars[index], cars[index]));
                    select.options.add(new Option(i + ":00", i + ":00"));
                    select.options.add(new Option(i + ":30", i + ":30"));

                }
                options = select.innerHTML;
                select2.innerHTML = options;

                console.log("despres del for ajax");

                // Load the new options
                //options = data.options; // Or whatever source information you're working with
                console.log("abans del disable ajax");
                $.each(data.start, function (index, currEmp) {
                    console.log(typeof currEmp)
                    $("#select-start option[value='" + currEmp + "']").attr("disabled", "disabled");
                    console.log(currEmp); //to print name of employee
                });

                $.each(data.end, function (index, currEmp) {
                    console.log(typeof currEmp)
                    $("#select-finish option[value='" + currEmp + "']").attr("disabled", "disabled");
                    console.log(currEmp); //to print name of employee
                });

                $('#select-finish').selectpicker('refresh');
                $('#select-start').selectpicker('refresh');


            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });
    });

</script>
</body>

</html>