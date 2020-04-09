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

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>

<body onload="loadTable()">

<script>
    function loadTable(){
        $.ajax({

            type: "GET",
            url: "../teacher/getTimeLines",
            dataType: "json",
            contentType: 'application/json',
            data: {
                //"teacherId": elem
            }, //aqui es passen els parametres
            success: function (data) {
                console.log(data);
                /*let style, lastVisit, msgDate, i;
                lastVisit  = new Date(data.last);
                // Disable the booked options in both select, each one with its list

*/
                $.each(data, function (index, current) {
                    $.each(current.start, function (indexIn, currentIn) {
                        console.log(currentIn);
                        var timeSpace = document.getElementById(currentIn+current.day);
                        timeSpace.className = "timetable-workout";
                        timeSpace.innerHTML += current.subject+' <br> ' +current.space;
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
<!------ Include the above in your HEAD tag ---------->

<!-- time-table -->
<div class="content">
    <div class="container">
        <div class="row">
            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 text-center mb30">
                <h2>TIMETABLE</h2>
            </div>
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
                        <th scope="col">Saturday</th>
                        <th scope="col">Sunday</th>
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
                        <td id="07005"></td>
                        <td id="07006"></td>
                    </tr>
                    <tr>
                        <th scope="row">07:30 - 08:00</th>
                        <td id="07300"></td>
                        <td id="07301"></td>
                        <td id="07302"></td>
                        <td id="07303"></td>
                        <td id="07304"></td>
                        <td id="07305"></td>
                        <td id="07306"></td>
                    </tr>
                    <tr>
                        <th scope="row">08:00 - 08:30</th>
                        <td id="08000"></td>
                        <td id="08001"></td>
                        <td id="08002"></td>
                        <td id="08003"></td>
                        <td id="08004"></td>
                        <td id="08005"></td>
                        <td id="08006"></td>
                    </tr>
                    <tr>
                        <th scope="row">08:30 - 09:00</th>
                        <td id="08300"></td>
                        <td id="08301"></td>
                        <td id="08302"></td>
                        <td id="08303"></td>
                        <td id="08304"></td>
                        <td id="08305"></td>
                        <td id="08306"></td>
                    </tr>
                    <tr>
                        <th scope="row">09:00 - 09:30</th>
                        <td id="09000"></td>
                        <td id="09001"></td>
                        <td id="09002"></td>
                        <td id="09003"></td>
                        <td id="09004"></td>
                        <td id="09005"></td>
                        <td id="09006"></td>
                    </tr>
                    <tr>
                        <th scope="row">09:30 - 10:00</th>
                        <td id="09300"></td>
                        <td id="09301"></td>
                        <td id="09302"></td>
                        <td id="09303"></td>
                        <td id="09304"></td>
                        <td id="09305"></td>
                        <td id="09306"></td>
                    </tr>
                    <tr>
                        <th scope="row">10:00 - 10:30</th>
                        <td id="10000"></td>
                        <td id="10001"></td>
                        <td id="10002"></td>
                        <td id="10003"></td>
                        <td id="10004"></td>
                        <td id="10005"></td>
                        <td id="10006"></td>
                    </tr>
                    <tr>
                        <th scope="row">10:30 - 11:00</th>
                        <td id="10300"></td>
                        <td id="10301"></td>
                        <td id="10302"></td>
                        <td id="10303"></td>
                        <td id="10304"></td>
                        <td id="10305"></td>
                        <td id="10306"></td>
                    </tr>
                    <tr>
                        <th scope="row">11:00 - 11:30</th>
                        <td id="11000"></td>
                        <td id="11001"></td>
                        <td id="11002"></td>
                        <td id="11003"></td>
                        <td id="11004"></td>
                        <td id="11005"></td>
                        <td id="11006"></td>
                    </tr>
                    <tr>
                        <th scope="row">11:30 - 12:00</th>
                        <td id="11300"></td>
                        <td id="11301"></td>
                        <td id="11302"></td>
                        <td id="11303"></td>
                        <td id="11304"></td>
                        <td id="11305"></td>
                        <td id="11306"></td>
                    </tr>
                    <tr>
                        <th scope="row">12:00 - 12:30</th>
                        <td id="12000"></td>
                        <td id="12001"></td>
                        <td id="12002"></td>
                        <td id="12003"></td>
                        <td id="12004"></td>
                        <td id="12005"></td>
                        <td id="12006"></td>
                    </tr>
                    <tr>
                        <th scope="row">12:30 - 13:00</th>
                        <td id="12300"></td>
                        <td id="12301"></td>
                        <td id="12302"></td>
                        <td id="12303"></td>
                        <td id="12304"></td>
                        <td id="12305"></td>
                        <td id="12306"></td>
                    </tr>
                    <tr>
                        <th scope="row">13:00 - 13:30</th>
                        <td id="13000"></td>
                        <td id="13001"></td>
                        <td id="13002"></td>
                        <td id="13003"></td>
                        <td id="13004"></td>
                        <td id="13005"></td>
                        <td id="13006"></td>
                    </tr>
                    <tr>
                        <th scope="row">13:30 - 14:00</th>
                        <td id="13300"></td>
                        <td id="13301"></td>
                        <td id="13302"></td>
                        <td id="13303"></td>
                        <td id="13304"></td>
                        <td id="13305"></td>
                        <td id="13306"></td>
                    </tr>
                    <tr>
                        <th scope="row">14:00 - 14:30</th>
                        <td id="14000"></td>
                        <td id="14001"></td>
                        <td id="14002"></td>
                        <td id="14003"></td>
                        <td id="14004"></td>
                        <td id="14005"></td>
                        <td id="14006"></td>
                    </tr>
                    <tr>
                        <th scope="row">14:30 - 15:00</th>
                        <td id="14300"></td>
                        <td id="14301"></td>
                        <td id="14302"></td>
                        <td id="14303"></td>
                        <td id="14304"></td>
                        <td id="14305"></td>
                        <td id="14306"></td>
                    </tr>
                    <tr>
                        <th scope="row">15:00 - 15:30</th>
                        <td id="15000"></td>
                        <td id="15001"></td>
                        <td id="15002"></td>
                        <td id="15003"></td>
                        <td id="15004"></td>
                        <td id="15005"></td>
                        <td id="15006"></td>
                    </tr>
                    <tr>
                        <th scope="row">15:30 - 16:00</th>
                        <td id="15300"></td>
                        <td id="15301"></td>
                        <td id="15302"></td>
                        <td id="15303"></td>
                        <td id="15304"></td>
                        <td id="15305"></td>
                        <td id="15306"></td>
                    </tr>
                    <tr>
                        <th scope="row">16:00 - 16:30</th>
                        <td id="16000"></td>
                        <td id="16001"></td>
                        <td id="16002"></td>
                        <td id="16003"></td>
                        <td id="16004"></td>
                        <td id="16005"></td>
                        <td id="16006"></td>
                    </tr>
                    <tr>
                        <th scope="row">16:30 - 17:00</th>
                        <td id="16300"></td>
                        <td id="16301"></td>
                        <td id="16302"></td>
                        <td id="16303"></td>
                        <td id="16304"></td>
                        <td id="16305"></td>
                        <td id="16306"></td>
                    </tr>
                    <tr>
                        <th scope="row">17:00 - 17:30</th>
                        <td id="17000"></td>
                        <td id="17001"></td>
                        <td id="17002"></td>
                        <td id="17003"></td>
                        <td id="17004"></td>
                        <td id="17005"></td>
                        <td id="17006"></td>
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
