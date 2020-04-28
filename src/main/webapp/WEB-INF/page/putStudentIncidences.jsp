<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: maginbuu
  Date: 4/25/2020
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Iungo - Incidences</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">
            <h1>Incidences</h1>
            <div class="form-row form-group">
                <div class="col-sm-2"></div>
                <div class="col-sm-8 label-column">
                    <table class="table table-bordered table-striped">
                        <thead>
                        <tr>
                            <th><strong>Name</strong></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody id="myTable">
                        <c:forEach items="${students}" var="student">
                            <tr>
                                <td style="vertical-align: middle; horiz-align: center">${student.name} ${student.surname} ${student.secondSurname}</td>
                                <td style="vertical-align: middle; text-align: center"><a class="btn btn-danger" href="/teacher/incidence?userId=${student.userId}">
                                                    <i class="fa fa-exclamation-triangle" aria-hidden="true"></i> Set incidence</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <a class="btn btn-light submit-button" onclick="relate()">Accept</a>
        </form>
    </div>
</div>
</body>
</html>
