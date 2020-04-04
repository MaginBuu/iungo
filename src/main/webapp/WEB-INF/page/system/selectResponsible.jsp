<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Select Responsible</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">


</head>

<body>
<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <form class="custom-form" action="/user/creation/selectChild" method="post">
            <h1>Select Responsible for ${sessionScope.userRelateName}</h1>
            <div class="form-row form-group">
                <div class="col-sm-4 label-column">
                    <label path="userP" class="col-form-label">Responsible </label></div>
                <div class="col-sm-7 input-column">
                    <select class="selectpicker" data-live-search="true" data-width="100%" multiple="true"
                            id="responsible" name="responsible">
                        <c:forEach items="${users}" var="user">
                            <option value="${user.userId}">${user.name} ${user.surname} ${user.secondSurname}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <a class="btn btn-light submit-button" href="/user/creation">Add new Responsible</a>
            <a class="btn btn-light submit-button" onclick="relate()">Accept</a>
        </form>
    </div>
</div>


<script type="text/javascript">
    function relate() {
        var select = jQuery("#responsible");
        var responsibles = select.val().toString();
        if (responsibles == "") {
            alert("Select a responsible");
        } else {
            window.location.href = '/user/creation/setParentalRelationship?responsibles=' + responsibles;
        }
    }

    function addResponsible() {
    }
</script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</body>

</html>