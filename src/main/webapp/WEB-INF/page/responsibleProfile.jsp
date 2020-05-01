<%@ page import="com.model.enums.Role" %>
<%@ page import="com.model.enums.GenderType" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - ${user.name}'s Profile</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="/resource/css/tabNavStyle.css">
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">





</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">
            <h1>${user.name}'s Profile</h1>
                <table class="table table-borderless">
                <tbody id="myTable">
                <tr>
                    <td style="vertical-align: middle; text-align: right"><strong>Name:</strong></td>
                    <td style="vertical-align: middle; text-align: left">${user.name}</td>
                    <td style="vertical-align: middle; text-align: right"><strong>email:</strong></td>
                    <td style="vertical-align: middle; text-align: left">${user.emailId}</td>
                </tr>
                <tr>
                    <td style="vertical-align: middle; text-align: right"><strong>Surname:</strong></td>
                    <td style="vertical-align: middle; text-align: left">${user.surname}</td>
                    <td style="vertical-align: middle; text-align: right"><strong>Birth:</strong></td>
                    <td style="vertical-align: middle; text-align: left">${user.birth}</td>
                </tr>
                <tr>
                    <td style="vertical-align: middle; text-align: right"><strong>Second surname:</strong></td>
                    <td style="vertical-align: middle; text-align: left">${user.secondSurname}</td>
                    <td style="vertical-align: middle; text-align: right"><strong>username:</strong></td>
                    <td style="vertical-align: middle; text-align: left">${user.username}</td>
                </tr>
                </tbody>
                </table>
                <p><strong>Children:</strong></p>
            <table class="table table-borderless" width="60%">
                <tbody id="responsibles">
                <c:forEach items="${children}" var="child">
                    <tr>
                        <td style="vertical-align: middle;">${child.userR.name} ${child.userR.surname} ${child.userR.secondSurname}</td>
                        <td>
                            <a class="btn btn-danger" style="background-color: #DE9D3F;border-color:#DE9D3F"
                               href="/teacher/${child.userR.userId}/profile">View profile
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a class="btn btn-light submit-button" value="update" name="buttonName" id="generateReport" href="/user/allGrades?userId=${user.userId}">Message
            </a>
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