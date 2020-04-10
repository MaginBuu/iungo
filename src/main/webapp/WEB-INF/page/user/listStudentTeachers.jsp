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
    <link rel="stylesheet" href="/resource/css/ticket/cards.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">
</head>

<script type="text/javascript">
    function Validate() {
        return true;
    }
</script>

<body>
<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <br><br>
        <div class="container custom-div">
            <h1>Profiles Found</h1>
            <input class="form-control mb-4" id="tableSearch" type="text"
                   placeholder="Type something to search list items">
            <table class="table table-borderless table-striped">
                <thead>
                <tr>
                    <th><strong>Name</strong></th>
                    <th><strong>Department</strong></th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="myTable">
                <c:forEach items="${teachers}" var="teacher">
                    <tr>
                        <td style="vertical-align: middle; horiz-align: center">${teacher.userR.name} ${teacher.userR.surname} ${teacher.userR.secondSurname}</td>
                        <td style="vertical-align: middle; horiz-align: center">${teacher.department}</td>
                        <td style="vertical-align: middle; horiz-align: center"><a href="/user/teacher/${teacher.userR.userId}">View profile</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>


<script type="text/javascript">
    function Validate() {
        return true;
    }
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>

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
</script>


</html>

