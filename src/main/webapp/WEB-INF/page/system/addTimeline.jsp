<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Add Timeline</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>

</head>

<body>
<%@ include file="../navbar.jsp" %>
<input id="hores-inicials" type="hidden">
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">
            <form:form method="post" action="/subject/add/timeline" modelAttribute="timeline">
                <form:hidden path="timelineSubjectId"/>
                <h1>Add timeline</h1>
                <div class="row justify-content-md-center">
                    <div class="col justify-content-md-center"><strong>Spaces:</strong></div>
                    <div class="col">
                        <form:select class="selectpicker" path="timelineSpaceId" id="select-ajax" onchange="onChangeSpace()">
                            <form:option selected="selected" value="">Select an space
                            </form:option>
                            <c:forEach items="${spaces}" var="space">
                                <form:option value="${space.spaceId}">${space.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="col justify-content-md-center"><strong>Weekday:</strong></div>
                    <div class="col">
                        <form:select class="selectpicker" disabled="true" path="weekday" id="select-weekday" onchange="onChangeWeekday()">
                            <form:option selected="selected" id="default-weekday" value="">Select a day
                            </form:option>
                            <form:option value="MONDAY">Monday</form:option>
                            <form:option value="TUESDAY">Tuesday</form:option>
                            <form:option value="WEDNESDAY">Wednesday</form:option>
                            <form:option value="THURSDAY">Thursday</form:option>
                            <form:option value="FRIDAY">Friday</form:option>
                        </form:select>
                    </div>
                </div>
                <div class="row justify-content-md-center" style="padding-top: 5px;">
                    <div class="col justify-content-md-center"><strong>Starting Hour:</strong></div>
                    <div class="col">
                        <form:select class="selectpicker" disabled="true" id="select-start" name="select-start" path="startingHour" onchange="onChangeHours()">
                            <form:option selected="selected" id="default-start" value="">Select an hour </form:option>
                        </form:select>
                    </div>
                    <div class="col justify-content-md-center"><strong>Finishing Hour:</strong></div>
                    <div class="col">
                        <form:select class="selectpicker" disabled="true" id="select-finish" name="select-finish" path="finishingHour" onchange="onChangeHours()">
                            <form:option selected="selected" id="default-end" value="">Select an hour </form:option>
                        </form:select>
                    </div>
                </div>
                <div class="row" style="padding-top: 5px;">
                    <div class="col-md-2 pull-right" style="margin-right:20px"><strong>Teacher:</strong></div>
                    <div class="col-md-3">
                        <form:select class="selectpicker" disabled="true" id="select-teachers" name="select-teachers" path="timelineTeacherId" onchange="onChangeTeacher()">
                            <form:option selected="selected" id="default-start" value="">Select a teacher </form:option>
                        </form:select>
                    </div>
                </div>
                <div class="row justify-content-center" style="padding-top: 5px;">
                    <div class="col">
                        <button type="submit" disabled="true" class="btn submit-button" id="btn-smb">Save</button>
                    </div>
                </div>
            </form:form>

        </div>
    </div>
</div>
</div>


<script type="text/javascript">

    function onChangeSpace(){
        var space = document.getElementById("select-ajax");
        var weekday = $("#select-weekday")
        if(space.value == "") {
            weekday.attr('disabled',true);
            $('#select-weekday option[value=""]').attr("selected",true);
            weekday.selectpicker('refresh');
        }else{
            weekday.attr('disabled',false);
            weekday.selectpicker('refresh');
            if(weekday.value != ""){
                onChangeWeekday();
            }
        }
    }

    function onChangeWeekday() {
        var start = $('#select-start');
        var finish = $('#select-finish');
        if (document.getElementById("select-weekday").value != "") {
            $.ajax({

                type: "GET",
                url: "requestHours",
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
                    select.options.add(new Option("Select an hour", "",true,true));
                    for (i = 8; i < 17; ++i) {
                        select.options.add(new Option(i + ":00", i + ":00"));
                        select.options.add(new Option(i + ":30", i + ":30"));
                    }
                    select.options.add(new Option("17:00", "17:00"));

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
            start.attr('disabled',false);
            start.selectpicker('refresh');
            finish.attr('disabled',false);
            finish.selectpicker('refresh');
        }else{
            start.attr('disabled',true);

            $('#select-start option[value=""]').attr("selected",true);
            start.selectpicker('refresh');
            finish.attr('disabled',true);
            $('#select-finish option[value=""]').attr("selected",true);
            finish.selectpicker('refresh');
        }
    }

    function onChangeHours(){
        var start = document.getElementById("select-start");
        var finish = document.getElementById("select-finish");
        console.log(start.value+", "+finish.value);
        if(start.value != "" && finish.value != ""){
            var startHour = start.value.split(":")[0];
            var startMin = start.value.split(":")[1];
            console.log(startHour+", "+startMin);
            var finishHour = finish.value.split(":")[0];
            var finishMin = finish.value.split(":")[1];
            console.log(finishHour+", "+finishMin);
            if(parseInt(startHour) < parseInt(finishHour)
                || (parseInt(startHour) == parseInt(finishHour) && parseInt(startMin) < parseInt(finishMin))){
                $.ajax({

                    type: "GET",
                    url: "requestAvailableTeachers",
                    dataType: "json",
                    contentType: 'application/json',
                    data: {
                        "subjectId": $("#select-ajax option:selected").val(),
                        "weekday": $("#select-weekday option:selected").val(),
                        "start": startHour+"s"+startMin,
                        "end": finishHour+"s"+finishMin
                    }, //aqui es passen els parametres
                    success: function (data) {
                        let options, select;
                        console.log(data);
                        select = document.getElementById('select-teachers');

                        // Clear the old options
                        select.options.length = 0;

                        // Adds the available hours (from 8 to 17:30)
                        select.options.add(new Option("Select a teacher", "",true,true));

                        // In order of just doing this process once, we clone the options to the other select
                        options = select.innerHTML;

                        // Disable the booked options in both select, each one with its list
                        $.each(data, function (index, current) {
                            select.options.add(new Option(current.name+" "+current.surname+" "+current.secondsurname, current.userid));
                            if("false" == current.available) {
                                console.log("hola")
                                $("#select-teachers option[value='" + current.userid + "']").attr("disabled", "disabled");
                            }
                        });

                        // Selectpicker refresh
                        $('#select-teachers').attr('disabled',false);
                        $('#select-teachers').selectpicker('refresh');


                    }
                }).done(function () {

                }).fail(function () {
                    console.log("Error Ajax");
                });
            }else{
                var teach = $('#select-teachers');
                teach.attr('disabled',true);

                $('select-teachers option[value=""]').attr("selected",true);
                teach.selectpicker('refresh');
            }
        }else{
            var teach = $('#select-teachers');
            teach.attr('disabled',true);

            $('select-teachers option[value=""]').attr("selected",true);
            teach.selectpicker('refresh');
        }
    }

    function onChangeTeacher() {
        var teach = document.getElementById("select-teachers");
        if(teach != "") document.getElementById("btn-smb").disabled = false;
    }

</script>
</body>

</html>