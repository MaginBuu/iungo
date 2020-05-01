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
    <link rel="stylesheet" href="/resource/css/base/baseModal.css">

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
<%@ include file="../navbar.jsp" %>
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
                <p><strong>Responsibles:</strong></p>
            <table class="table table-borderless">
                <tbody id="responsibles">
                <c:forEach items="${responsibles}" var="responsible">
                    <tr>
                        <td style="vertical-align: middle;">${responsible.userR.name} ${responsible.userR.surname} ${responsible.userR.secondSurname}</td>
                        <td style="vertical-align: middle; text-align: right">
                            <button class="btn btn-light" data-toggle="modal" data-target="#modalCreateMessage">Send Message</button>

                        </td><td>
                            <a class="btn btn-danger" name="btn-incidence" value="incidenceFromProfile" id="resp-profile" style="background-color: #DE9D3F;border-color:#DE9D3F"
                               href="/teacher/responsible/${responsible.userR.userId}">View profile
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <a class="btn btn-light submit-button" value="update" name="buttonName" id="generateReport" href="/user/allGrades?userId=${user.userId}">Generate user's report
            </a>
            <c:set var = "authority" value = "${pageContext.request.userPrincipal.authorities}"/>
            <c:if test="${(authority ne '[STUDENT]') and (authority ne '[RESPONSIBLE]')}">
                <a class="btn btn-light submit-button" value="comment" name="buttonName" id="comment" style="background-color: #4C4C47"
                        href="/teacher/comment?userId=${user.userId}">Write a comment
                </a>
                <a class="btn btn-danger submit-button" name="btn-incidence" value="incidenceFromProfile" id="incidenceFromProfile" style="background-color: #B3001B "
                        href="/teacher/incidence?userId=${user.userId}&origin=profile">Set incidence
                </a>
            </c:if>
        </div>
    </div>
</div>

<!-- Create message Modal -->
<div class="modal fade" id="modalCreateMessage" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row base-form-modal">
                    <div class="col-md-10 offset-md-1">
                        <form:form class="custom-form" method="post" action="/message/creation/directMessage" modelAttribute="message">
                            <form:hidden path="receiver" value="${user.userId}"></form:hidden>
                            <h1>Create new message</h1>
                            <div class="form-group">
                                <div class="label-column"><form:label path="subject"
                                                                      class="col-form-label">Subject </form:label></div>
                                <div class="input-column"><form:input path="subject" class="form-control"
                                                                      type="text"></form:input></div>
                                <div class="label-column"><form:label path="messageBody" class="col-form-label">Body </form:label></div>
                                <div class="input-column"><form:textarea path="messageBody" class="form-control"
                                                                         type="text"></form:textarea></div>
                            </div>
                            <button class="btn btn-light submit-button" type="submit" onclick="return true">Send</button>
                        </form:form>
                    </div>
                </div>
            </div>
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