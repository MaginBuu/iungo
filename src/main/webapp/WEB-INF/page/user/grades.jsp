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
                    <th><strong>Observations</strong></th>
                    <th>Grade</th>
                </tr>
                </thead>
                <tbody id="myTable">
                <c:forEach items="${userTasks}" var="userTasks">
                    <tr>
                        <td style="vertical-align: middle; horiz-align: center">${userTasks.task.title}</td>
                        <td style="vertical-align: middle; horiz-align: center">${userTasks.observations}</td>
                        <td style="vertical-align: middle; horiz-align: center">${userTasks.grade}</td
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
