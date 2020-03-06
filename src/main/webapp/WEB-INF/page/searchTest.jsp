<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>UserCreation</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">


</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row register-form">
    <div class="col-md-8 offset-md-2">
        <c:url value="/test/search" var="url"></c:url>
        <div class="container">
            <input class="form-control mb-4" id="tableSearch" type="text"
                   placeholder="Type something to search list items">
            <table class="table table-bordered table-striped" >
                <thead>
                <tr>
                    <th>Name</th>
                    <th>email</th>
                </tr>
                </thead>
                <tbody id="myTable">
                <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.name} ${user.surname} ${user.secondSurname}</td>
                    <td>${user.emailId}</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/locales/bootstrap-datepicker.es.min.js"></script>
</body>


<script type="text/javascript">
    $(document).ready(function(){
        $("#tableSearch").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>



</html>

