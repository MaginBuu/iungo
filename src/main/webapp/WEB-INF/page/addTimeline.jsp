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
            <form:form action="/subject/add/timeline" method="post" modelAttribute="timeline">
                <h1>Add timeline</h1>
                <div class="row justify-content-md-center">
                    <div class="col justify-content-md-center"><strong>Spaces:</strong></div>
                    <div class="col">
                        <form:select class="selectpicker" path="spaceTimeLine" id="select-ajax">
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
                        <form:select class="selectpicker" id="select-start" name="select-start" path="startingHour">
                        </form:select>
                    </div>
                    <div class="col justify-content-md-center"><strong>Finishing Hour:</strong></div>
                    <div class="col">
                        <form:select class="selectpicker" id="select-finish" name="select-finish" path="finishingHour">
                        </form:select>
                    </div>
                </div>
                <div class="row justify-content-md-center">
                    <div class="col justify-content-md-center">
                        <a class="btn btn-warning" id="btn-ajax">Search availability</a>
                    </div>
                    <div class="col justify-content-md-center">
                        <button type="submit" class="btn btn-dark" id="btn-smb">Save</button>
                    </div>
                </div>
            </form:form>

        </div>
    </div>
</div>
</div>


<script type="text/javascript">

    $("#btn-ajax").click(function () {
        $.ajax({

            type: "GET",
            url: "ajaxdos",
            dataType: "json",
            contentType: 'application/json',
            data: {
                "id": $("#select-ajax option:selected").val(),
                "weekday": $("#select-weekday option:selected").val()
            }, //aqui es passen els parametres
            success: function (data) {
                let options, select, selectFinish, i;

                // Get the raw DOM object for the select box
                select = document.getElementById('select-start');
                selectFinish = document.getElementById('select-finish');
                
                // Clear the old options
                select.options.length = 0;
                
                // Adds the available hours (from 8 to 17:30)
                for (i = 8; i < 18; ++i) {
                    select.options.add(new Option(i + ":00", i + ":00"));
                    select.options.add(new Option(i + ":30", i + ":30"));
                }
                
                // In order of just doing this process once, we clone the options to the other select
                options = select.innerHTML;
                selectFinish.innerHTML = options;

                // Disable the booked options in both select, each one with its list
                $.each(data.start, function (index, current) {
                    $("#select-start option[value='" + current + "']").attr("disabled", "disabled");
                });

                $.each(data.end, function (index, current) {
                    $("#select-finish option[value='" + current + "']").attr("disabled", "disabled");
                });

                // Selectpicker refresh
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