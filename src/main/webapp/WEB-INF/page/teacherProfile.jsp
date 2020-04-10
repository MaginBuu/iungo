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
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="/resource/css/timeTableStyle.css">
<link rel="stylesheet" href="/resource/css/base/baseStyle.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>

<body onload="loadTable(document.getElementById('userId').value)">

<script>
    function loadTable(teacherId){
        $.ajax({

            type: "GET",
            url: "../teacher/getTimeLines",
            dataType: "json",
            contentType: 'application/json',
            data: {
                "teacherId": teacherId
            }, //aqui es passen els parametres
            success: function (data) {
                console.log(data);
                /*let style, lastVisit, msgDate, i;
                lastVisit  = new Date(data.last);
                // Disable the booked options in both select, each one with its list

*/
                $.each(data, function (index, current) {
                    $.each(current.start, function (indexIn, currentIn) {
                        var timeSpace = document.getElementById(currentIn+current.day);
                        if("consultation-hours" == current.subject) {
                            timeSpace.className = "timetable-ch";
                            timeSpace.innerHTML += 'Consultation hour <br> ' +current.space;
                        }else {
                            timeSpace.className = "timetable-workout";
                            timeSpace.innerHTML += current.subject + ' <br> ' + current.space;
                        }
                    });
                });
                // Selectpicker refresh
                //$('#select-teacher').selectpicker('refresh');
            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });
    }
</script>

<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">
            <form:form method="post" action="/user/modify" modelAttribute="teacher">
            <h1>Teacher ${teacher.userR.name}</h1>
            <form:hidden name="userId" id="userId" path="userR.userId"/>
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
            </form:form>
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
</body>
</html>
