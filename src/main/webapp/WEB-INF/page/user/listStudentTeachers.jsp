<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Profiles</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="/resource/css/profile/profileStyle.css">
    <link rel="stylesheet" href="/resource/css/ticket/cards.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
</head>

<script type="text/javascript">
    function Validate() {
        return true;
    }

    $(document).ready(function () {
        $("#tableSearch").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });

    function getTeachers(button) {
        var url;
        if (button == "other") url = "/teacher/getOtherTeachers";
        if (button == "my") url = "/teacher/getMyTeachers";

        $.ajax({

            type: "GET",
            url: url,
            dataType: "json",
            contentType: 'application/json',
            data: {},
            success: function (data) {
                console.log(data);

                var table = document.getElementById('teacherTable');
                table.innerHTML = "";
                $.each(data, function (index, current) {
                    table.innerHTML += '<tr> <td style="vertical-align: middle; horiz-align: center">' + current.name + '</td>' +
                        '<td style="vertical-align: middle; horiz-align: center">' + current.department + '</td>' +
                        '<td style="vertical-align: middle; horiz-align: center"><a href="/user/teacher/' + current.teacherId + '">View profile</a></td></tr>';
                });
            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });
    }

</script>

<body>
<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <br><br>
        <div class="container custom-div">
            <h1>Profiles Found</h1>
            <div class="container">
            <div class="row" style="display: flex; justify-content: center;">
                <a class="btn alt-submit-button" style="margin-right: 10px" id="btn-ajax"
                   onclick="getTeachers('other')">All teachers</a>
                <a class="btn alt-submit-button" id="btn-ajax2" onclick="getTeachers('my')">My teachers</a>
            </div>
            <div class="row justify-content-center features">

                <c:forEach items="${teachers}" var="teacher">
                    <div class="col-sm-6 col-md-5 col-lg-4 item">
                        <div class="profile_card">
                            <div class="profile_box">
                                <div class="img">
                                    <img src="/resource/img/placeholder-profile.jpg">
                                </div>
                                <h2>${teacher.userR.name} ${teacher.userR.surname} ${teacher.userR.secondSurname}<br>
                                    <span id="span-${teacher.userR.userId}">${teacher.department}</span>
                                </h2>
                                <a class="a_profile" type="button" href="/user/teacher/${teacher.userR.userId}">View profile</a>
                            </div>
                        </div>
                    </div>
                    <c:choose>
                    <c:when test="${teacher.department eq 'LANGUAGE'}">
                        <script>
                            document.getElementById("span-${teacher.userR.userId}").style.background = "#AF3800";
                        </script>
                    </c:when>
                    <c:when test="${teacher.department eq 'HUMANITIES'}">
                        <script>
                            d = document.getElementById("span-${teacher.userR.userId}");
                            d.style.background = "#F8F991";
                            d.style.color = "#000000"
                        </script>
                    </c:when>
                    <c:when test="${teacher.department eq 'SCIENCE'}">
                        <script>
                            d = document.getElementById("span-${teacher.userR.userId}");
                            d.style.background = "#9AB87A";
                            d.style.color = "#000000"
                        </script>
                    </c:when>
                    <c:otherwise>
                        <script>
                            d = document.getElementById("span-${teacher.userR.userId}");
                            d.style.background = "#D36582";

                        </script>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            </div>
        </div>
    </div>
</div>
</body>


</html>

