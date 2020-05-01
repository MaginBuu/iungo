<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - ${student.userR.name}'s Evaluation</title>

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
<form:form method="post" action="/teacher/evaluate/${student.userR.userId}/save/${evaluation}"
           modelAttribute="subjects">
    <div class="row creation-form">
        <div class="col-md-8 offset-md-2">
            <br><br>
            <div class="container custom-div">
                <h1>Evaluation</h1>
                <div class="row">
                    <div class="col-md-12">
                        <div class="container">
                            <c:forEach items="${subjects.userSubjects}" var="userSubject" varStatus="status">
                                <div class="row" style="border-bottom: 1px solid #ccc;margin-bottom: 15px">
                                    <div class="container">
                                        <input type="hidden" name="userSubjects[${status.index}].subject.subjectId" value="${userSubject.subject.subjectId}"/>
                                        <div class="row">

                                            <div class="col-md-8" style="margin-left:0px"><h5 class="pull-left"><strong>${userSubject.subject.name}</strong></h5></div>
                                            <div class="col-md-4">
                                            <p class="pull-right"><small>Check tasks <a><i class="fa fa-eye"
                                                                                          style="color:#DE9D3F"
                                                                                          aria-hidden="true"></i></a></small>
                                            </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="container">
                                        <div class="row" style="margin-bottom:10px">
                                            <div class="col-md-8">
                                                <div class="row"><h6>Observations:</h6></div>
                                                <div class="row"><textarea class="text-area-observation" name="userSubjects[${status.index}].observations" value="${userSubject.observations}">${userSubject.observations}</textarea></div>
                                            </div>
                                            <div class="col-md-4">
                                            <div class="row"><h6>Final grade:</h6></div>
                                                <div class="row"><input type="number" class="text-area-grade" name="userSubjects[${status.index}].grade" value="${userSubject.grade}"></div>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <input class="btn btn-light submit-button" type="submit" value="Save"/>
            </div>
        </div>
    </div>
</form:form>



<script type="text/javascript">
    function Validate() {
        return true;
    }
</script>
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

