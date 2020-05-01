<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Guillem
  Date: 2020-04-09
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - ${teacher.userR.name}'s Profile</title>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link rel="stylesheet" href="/resource/css/timeTableStyle.css">
<link rel="stylesheet" href="/resource/css/base/baseStyle.css">
<link rel="stylesheet" href="/resource/css/base/deleteModal.css">
<link rel="stylesheet" href="/resource/css/base/baseModal.css">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />

</head>

<body onload="loadTable()">

<script>
    function loadTable(){
        $.ajax({

            type: "GET",
            url: "/teacher/getTimeLines",
            dataType: "json",
            contentType: 'application/json',
            data: {
                "teacherId": document.getElementById('userId').value
            },
            success: function (data) {
                console.log(data);

                $.each(data, function (index, current) {
                    $.each(current.start, function (indexIn, currentIn) {
                        var timeSpace = document.getElementById(currentIn+current.day);
                        if("consultation-hours" == current.subject) {
                            timeSpace.className = "timetable-ch";
                            timeSpace.innerHTML += 'Consultation hour <br> ' + current.space;
                        }else {
                            timeSpace.className = "timetable-workout";
                            timeSpace.innerHTML += current.subject + ' <br> ' + current.space;
                        }
                    });
                });
            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });
    }


    $(function () {
        $('#datetimepicker1').datetimepicker({
            format: 'DD/MM/YYYY',

        });
    });

    $(function () {
        $('#datetimepicker3').datetimepicker({
            format: 'HH:mm',
            pick12HourFormat: false,

        });
    });

    function Validate() {

        var tempDate = $('#datetimepicker1').find("input").val();
        tempDate = tempDate.split("/").reverse().join("/");
        tempDate += " " + $('#datetimepicker3').find("input").val() + ":00"
        $('#datetimepicker1').find("input").val(tempDate);

        return true;
    }

</script>

<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">
            <h1>Teacher ${teacher.userR.name}</h1>
            <input type="hidden" name="userId" id="userId" value="${teacher.userR.userId}"/>
            <table class="table table-borderless">
                <tbody id="myTable">
                    <tr>
                        <td style="vertical-align: middle; text-align: right"><strong>Name:</strong></td>
                        <td style="vertical-align: middle;">${teacher.userR.name}</td>
                        <td style="vertical-align: middle; text-align: right"><strong>Surname:</strong></td>
                        <td style="vertical-align: middle;">${teacher.userR.surname}</td>
                        <td style="vertical-align: middle; text-align: right"><strong>Second surname:</strong></td>
                        <td style="vertical-align: middle;">${teacher.userR.secondSurname}</td>
                    </tr>
                    <tr>
                        <td style="vertical-align: middle; text-align: right"><strong>E-mail:</strong></td>
                        <td style="vertical-align: middle;">${teacher.userR.emailId}</td>
                        <td style="vertical-align: middle; text-align: right"><strong>Username:</strong></td>
                        <td style="vertical-align: middle; text-align: left">${teacher.userR.username}</td>
                        <td style="vertical-align: middle; text-align: right"><strong>Department:</strong></td>
                        <td style="vertical-align: middle; text-align: left">${teacher.department}</td>
                    </tr>
                </tbody>
            </table>
            <button class="btn btn-light custom-button" data-toggle="modal" data-target="#modalCreateMessage">Send Message</button>
            <button class="btn btn-light custom-button" data-toggle="modal" data-target="#modalCreateReunion">Request meeting</button>
        </div>
    </div>
</div>

<!-- time-table -->
<div class="content">
    <div class="container">
        <div class="row">
            <div class="table-responsive">
                <table class="timetable table table-striped ">
                    <thead>
                    <tr class="text-center">
                        <th scope="col"></th>
                        <th scope="col">Monday</th>
                        <th scope="col">Tuesday</th>
                        <th scope="col">Wednesday</th>
                        <th scope="col">Thursday</th>
                        <th scope="col">Friday</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th scope="row">07:00 - 07:30</th>
                        <td id="07000"></td>
                        <td id="07001"></td>
                        <td id="07002"></td>
                        <td id="07003"></td>
                        <td id="07004"></td>
                    </tr>
                    <tr>
                        <th scope="row">07:30 - 08:00</th>
                        <td id="07300"></td>
                        <td id="07301"></td>
                        <td id="07302"></td>
                        <td id="07303"></td>
                        <td id="07304"></td>
                    </tr>
                    <tr>
                        <th scope="row">08:00 - 08:30</th>
                        <td id="08000"></td>
                        <td id="08001"></td>
                        <td id="08002"></td>
                        <td id="08003"></td>
                        <td id="08004"></td>
                    </tr>
                    <tr>
                        <th scope="row">08:30 - 09:00</th>
                        <td id="08300"></td>
                        <td id="08301"></td>
                        <td id="08302"></td>
                        <td id="08303"></td>
                        <td id="08304"></td>
                    </tr>
                    <tr>
                        <th scope="row">09:00 - 09:30</th>
                        <td id="09000"></td>
                        <td id="09001"></td>
                        <td id="09002"></td>
                        <td id="09003"></td>
                        <td id="09004"></td>
                    </tr>
                    <tr>
                        <th scope="row">09:30 - 10:00</th>
                        <td id="09300"></td>
                        <td id="09301"></td>
                        <td id="09302"></td>
                        <td id="09303"></td>
                        <td id="09304"></td>
                    </tr>
                    <tr>
                        <th scope="row">10:00 - 10:30</th>
                        <td id="10000"></td>
                        <td id="10001"></td>
                        <td id="10002"></td>
                        <td id="10003"></td>
                        <td id="10004"></td>
                    </tr>
                    <tr>
                        <th scope="row">10:30 - 11:00</th>
                        <td id="10300"></td>
                        <td id="10301"></td>
                        <td id="10302"></td>
                        <td id="10303"></td>
                        <td id="10304"></td>
                    </tr>
                    <tr>
                        <th scope="row">11:00 - 11:30</th>
                        <td id="11000"></td>
                        <td id="11001"></td>
                        <td id="11002"></td>
                        <td id="11003"></td>
                        <td id="11004"></td>
                    </tr>
                    <tr>
                        <th scope="row">11:30 - 12:00</th>
                        <td id="11300"></td>
                        <td id="11301"></td>
                        <td id="11302"></td>
                        <td id="11303"></td>
                        <td id="11304"></td>
                    </tr>
                    <tr>
                        <th scope="row">12:00 - 12:30</th>
                        <td id="12000"></td>
                        <td id="12001"></td>
                        <td id="12002"></td>
                        <td id="12003"></td>
                        <td id="12004"></td>
                    </tr>
                    <tr>
                        <th scope="row">12:30 - 13:00</th>
                        <td id="12300"></td>
                        <td id="12301"></td>
                        <td id="12302"></td>
                        <td id="12303"></td>
                        <td id="12304"></td>
                    </tr>
                    <tr>
                        <th scope="row">13:00 - 13:30</th>
                        <td id="13000"></td>
                        <td id="13001"></td>
                        <td id="13002"></td>
                        <td id="13003"></td>
                        <td id="13004"></td>
                    </tr>
                    <tr>
                        <th scope="row">13:30 - 14:00</th>
                        <td id="13300"></td>
                        <td id="13301"></td>
                        <td id="13302"></td>
                        <td id="13303"></td>
                        <td id="13304"></td>
                    </tr>
                    <tr>
                        <th scope="row">14:00 - 14:30</th>
                        <td id="14000"></td>
                        <td id="14001"></td>
                        <td id="14002"></td>
                        <td id="14003"></td>
                        <td id="14004"></td>
                    </tr>
                    <tr>
                        <th scope="row">14:30 - 15:00</th>
                        <td id="14300"></td>
                        <td id="14301"></td>
                        <td id="14302"></td>
                        <td id="14303"></td>
                        <td id="14304"></td>
                    </tr>
                    <tr>
                        <th scope="row">15:00 - 15:30</th>
                        <td id="15000"></td>
                        <td id="15001"></td>
                        <td id="15002"></td>
                        <td id="15003"></td>
                        <td id="15004"></td>
                    </tr>
                    <tr>
                        <th scope="row">15:30 - 16:00</th>
                        <td id="15300"></td>
                        <td id="15301"></td>
                        <td id="15302"></td>
                        <td id="15303"></td>
                        <td id="15304"></td>
                    </tr>
                    <tr>
                        <th scope="row">16:00 - 16:30</th>
                        <td id="16000"></td>
                        <td id="16001"></td>
                        <td id="16002"></td>
                        <td id="16003"></td>
                        <td id="16004"></td>
                    </tr>
                    <tr>
                        <th scope="row">16:30 - 17:00</th>
                        <td id="16300"></td>
                        <td id="16301"></td>
                        <td id="16302"></td>
                        <td id="16303"></td>
                        <td id="16304"></td>
                    </tr>
                    <tr>
                        <th scope="row">17:00 - 17:30</th>
                        <td id="17000"></td>
                        <td id="17001"></td>
                        <td id="17002"></td>
                        <td id="17003"></td>
                        <td id="17004"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!-- timetable -->
        </div>
    </div>
</div>

<!-- Create meeting Modal -->
<div class="modal fade" id="modalCreateReunion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row base-form-modal">
                    <div class="col-md-10 offset-md-1">
                        <form:form class="custom-form" method="post" action="/procedure/createMeetingRequest" modelAttribute="procedure" commandName="procedure">
                            <form:hidden path="userP.userId" value="${teacher.userR.userId}"/>
                            <h1>Request meeting</h1>
                            <div class="form-group">
                                <div class="label-column"><form:label path="description" class="col-form-label">Message </form:label></div>
                                <div class="input-column"><form:textarea path="description" class="form-control"
                                                                     type="text"></form:textarea></div>
                            </div>
                            <div class="form-row form-group">
                                <div class="col-sm-2 label-column-row">
                                    <form:label path="limitDate" class="col-form-label">Day </form:label></div>
                                <div class="col-sm-10 input-column-row" style="position: relative">
                                    <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                                        <form:input path="limitDate" type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1"/>
                                        <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-row form-group">
                                <div class="col-sm-2 label-column-row">
                                    <label class="col-form-label">Hour </label></div>
                                <div class="col-sm-10 input-column-row" style="position: relative">
                                    <div class="input-group date" id="datetimepicker3" data-target-input="nearest">
                                        <input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1" value="23:59"/>
                                        <div class="input-group-append" data-target="#datetimepicker3" data-toggle="datetimepicker">
                                            <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Send</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<!-- Create message Modal -->
<div class="modal fade" id="modalCreateMessage" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row base-form-modal">
                    <div class="col-md-10 offset-md-1">
                        <form:form class="custom-form" method="post" action="/message/creation/directMessage" modelAttribute="message" commandName="message">
                            <form:hidden path="receiver" value="${teacher.userR.userId}"></form:hidden>
                            <h1>Create new message</h1>
                            <div class="form-group">
                                <div class="label-column"><form:label path="subject"
                                                                      class="col-form-label">Subject </form:label></div>
                                <div class="input-column"><form:input path="subject" class="form-control"
                                                                      type="text"></form:input></div>
                                <div class="label-column"><form:label path="messageBody" class="col-form-label">Body </form:label></div>
                                <div class="input-column"><form:textarea path="messageBody" class="form-control"
                                                                         type="text"></form:textarea></div>
                            </div>
                            <button class="btn btn-light submit-button" type="submit" onclick="return true">Send</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
