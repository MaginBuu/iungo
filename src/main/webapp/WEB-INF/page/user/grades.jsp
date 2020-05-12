<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: maginbuu
  Date: 4/21/2020
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Iungo - Grades</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>


<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">

            <h2>Grades</h2>
            <table class="table table-borderless table-striped">
                <thead>
                <tr>
                    <th><strong>Task</strong></th>
                    <th><strong>value</strong></th>
                    <th><strong>Observations</strong></th>
                    <th>Grade</th>
                </tr>
                </thead>
                <tbody id="myTable">
                <c:forEach items="${userTasks}" var="userTasks">
                    <tr>
                        <td style="vertical-align: middle; text-align: center">${userTasks.task.title}</td>
                        <td style="vertical-align: middle; text-align: center">${userTasks.task.value}</td>
                        <td style="vertical-align: middle; text-align: center">${userTasks.observations}</td>
                        <td style="vertical-align: middle; text-align: center">${userTasks.grade}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${userSubject.grade eq null}">
                <label class="label-column">Final grade: not evaluated yet</label>
            </c:if>

            <c:if test="${userSubject.grade ne null}">
                <label class="label-column">Final grade: ${userSubject.grade}</label>
            </c:if>
            <br>
            <a class="btn btn-light submit-button" href="/subject/${subjectId}"><i class="fa fa-arrow-left" aria-hidden="true"></i> Return to subject</a>
        </div>
    </div>
</div>

</body>
</html>
