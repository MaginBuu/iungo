<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Subjects</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="/resource/css/ticket/cards.css">

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
</head>


<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <br><br>
        <div class="container custom-div">
            <h1>Evaluation</h1>
            <table class="table table-borderless table-striped">
                <thead>
                <tr>
                    <th><strong>Name</strong></th>
                    <th><strong>Surname</strong></th>
                    <th><strong>Second Surname</strong></th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="myTable">
                <c:forEach items="${listStudents}" var="student">
                    <tr>
                        <td style="vertical-align: middle; horiz-align: center">${student.userR.name}</td>
                        <td style="vertical-align: middle; horiz-align: center">${student.userR.surname}</td>
                        <td style="vertical-align: middle; horiz-align: center">${student.userR.secondSurname}</td>
                        <td style="vertical-align: middle; text-align: center"><a class="btn btn-info" href="">
                            <i class="fa fa-gavel" aria-hidden="true"></i> Evaluate</a></td>
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
</body>


</html>

