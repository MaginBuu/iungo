<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - New Ticket</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

</head>

<body>

<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <c:url value="/ticket/creation" var="url"></c:url>
        <form:form class="custom-form" method="post" action="/ticket/creation" modelAttribute="ticket">
            <h1>Create Ticket</h1>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><form:label path="title"
                                                               class="col-form-label">Title </form:label></div>
                <div class="col-sm-8 input-column"><form:input path="title" class="form-control" id="title-input"
                                                               type="text"></form:input></div>
            </div>

            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><form:label path="description"
                                                               class="col-form-label">Description </form:label></div>
                <div class="col-sm-8 input-column"><form:textarea path="description" class="form-control" id="description-input"
                                                                  type="text"></form:textarea></div>
            </div>

            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
        </form:form>
    </div>
</div>

<script type="text/javascript">
    function Validate() {
        var validated = true;
        var title = document.getElementById("title-input");
        var description = document.getElementById("description-input");
        if(title.value == "")  {
            title.style.backgroundColor = "#ffd6cc";
            validated = false;
        }else title.style.backgroundColor = "#ffffffff";
        if(description.value == "")  {
            description.style.backgroundColor = "#ffd6cc";
            validated = false;
        }else description.style.backgroundColor = "#ffffffff";
        return validated;
    }
</script>

</body>

</html>