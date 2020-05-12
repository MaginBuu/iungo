<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - New Report</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">
    <link rel="stylesheet" href="/resource/css/base/baseModal.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>


</head>

<body>

<script>

    function Validate() {
        let validated = true;
        let checked = $("#anonymously").is(":checked");
        let observed = document.getElementById("observed").value;
        let description = document.getElementById("description");

        if(!(checked.toString() === 'false' && observed.toString() === 'false') && description.value.toString() === ""){
            document.getElementById("description").style.backgroundColor = "#ffd6cc";
            validated = false;
        }else{
            title.style.backgroundColor = "#ffffff";
        }
        return validated;
    }

</script>

<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <form:form class="custom-form" method="post" action="/user/antibullying/report" modelAttribute="report">
            <form:hidden path="observed" id="observed"/>
            <h1><i class="fa fa-heart icon"></i></h1>
            <p><i>Feeling safe and good about ourselves as with each other is very important. This is a safe space to
                express our worries and talk with confidence about our or others issues.</i></p>
            <br><br>
            <div class="form-group">
                <div class="label-column" style="text-align: center"><form:label path="description"
                                                               class="col-form-label" >Description </form:label></div>
                <div class="input-column"><form:textarea path="description" class="form-control"
                                                               type="text" id="description"></form:textarea></div>
            </div>
            <div class="row form-group">
                <div class="col label-column"><label class="col-form-label">Report Anonymously </label><span></span></div>
                <div class="col input-column"><form:checkbox name="anonymously" path="anonymous" class="form-control" id="anonymously"></form:checkbox></div>
            </div>

            <a class="btn btn-light submit-button" href="/" data-toggle="modal" data-target="#myModal">Report</a>
        </form:form>
    </div>
</div>

<!-- Modal HTML -->
<div id="myModal" name="myModal" class="modal fade">
    <div class="modal-dialog modal-confirm">
        <div class="modal-content">

            <div class="modal-body">
                <h4 class="modal-title" style="text-align: center">Are you sure?</h4><br>
                <p id="deleteText">Do you really want to report?
                    Reporting a false case could carry sanctions or unnecessary work, please report only true facts.</p>
            </div>
            <input type="hidden" name="elementId" id="elementId" value=""/>
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal" style="background-color:lightgray;border-color:lightGray">Cancel</button>
                <button class="btn btn-light" style="background-color: #DE9D3F; border-color: #DE9D3F" type="submit" onclick="return Validate()">Report
                </button>
            </div>
        </div>
    </div>
</div>

</body>

</html>