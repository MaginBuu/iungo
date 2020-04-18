<%--
  Created by IntelliJ IDEA.
  User: magitellbonet
  Date: 2020-04-16
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Iungo - subject</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div-top">
            <div class="parallax">
                <p class="parallax-text">${subject.name}</p>
            </div>
            <c:forEach items="${subject.chapters}" var="chapter">
                <h2>${chapter.title}</h2>
                <br>
                <c:forEach items="${chapter.tasks}" var="task">
                    <c:choose>
                        <c:when test="${task.taskType == 'OPEN_QUESTION'}">
                            <a href="/user/task/${task.taskId}"><i class="fa fa-file-text-o" aria-hidden="true"></i>   ${task.title}</a>
                        </c:when>
                        <c:when test="${task.taskType == 'TEST'}">
                            <a href="/user/task/${task.taskId}"><i class="fa fa-file-o" aria-hidden="true"></i>   ${task.title}</a>
                        </c:when>
                        <c:when test="${task.taskType == 'SURVEY'}">
                            <a href="/user/task/${task.taskId}"><i class="fa fa-dot-circle-o" aria-hidden="true"></i>   ${task.title}</a>
                        </c:when>
                        <c:when test="${task.taskType == 'DELIVERABLE'}">
                            <a href="/user/task/${task.taskId}"><i class="fa fa-folder-open-o" aria-hidden="true"></i>   ${task.title}</a>
                        </c:when>
                    </c:choose>
                    <br><br>
                    <c:if test="${!empty task.description}">
                        <p class="description">${task.description}</p>
                    </c:if>
                    <br><br>
                </c:forEach>

                <c:forEach items="${chapter.resources}" var="resource">
                    <a href="/user/resource/${resource.resourceId}"><i class="fa fa-book" aria-hidden="true"></i>   ${resource.title}</a>
                    <br><br>
                    <br><br>
                </c:forEach>

            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
