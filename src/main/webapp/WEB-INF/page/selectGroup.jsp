<%@ page import="com.model.enums.Stage" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Select Group</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/ticket/cards.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
</head>

<body>

<script>

    function selectedStage() {
        $.ajax({
            type: "GET",
            url: "/getLevelsByStage",
            dataType: "json",
            contentType: 'application/json',
            data: {
                "stage": $("#stage").val(),
            }, //aqui es passen els parametres
            success: function (data) {
                let selectLevel = document.getElementById('level');
                selectLevel.disabled = false;
                selectLevel.options.length = 0;
                selectLevel.options.add(new Option("Select a level" , ""));
                $.each(data, function (index, current) {
                    console.log(current);
                    selectLevel.options.add(new Option(current , current));

                });

                $("#level").selectpicker("refresh");

            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });

    }

    function selectedLevel() {
        $.ajax({
            type: "GET",
            url: "/getGroupsByStageAndLevel",
            dataType: "json",
            contentType: 'application/json',
            data: {
                "stage": $("#stage").val(),
                "level": $("#level").val(),
            }, //aqui es passen els parametres
            success: function (data) {
                let selectGroup = document.getElementById('groupSelect');
                selectGroup.disabled = false;
                selectGroup.options.length = 0;
                $.each(data, function (index, current) {
                    console.log(current);
                    selectGroup.options.add(new Option(current , current));

                });

                $("#groupSelect").selectpicker("refresh");

            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });

    }





</script>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <br><br>
        <form:form class="custom-form" action="/teacher/getStudentsGroup" method="get" modelAttribute="group">
            <h1>Select Group</h1>
            <div class="form-row form-group">
                <c:set var="enumValues" value="<%=Stage.values()%>"/>
                <div class="col-sm-3 label-column"><label path="name" class="col-form-label">Stage </label></div>
                <div class="col-sm-3 label-column">
                    <form:select path="stage" class="selectpicker" data-width="100%" id="stage"
                                 name="stage" onchange="selectedStage()">
                        <option disabled="disabled" selected="selected" value="">Select a Stage</option>
                        <c:forEach items="${enumValues}" var="enumValue">
                            <form:option value="${enumValue}"><a style="text-transform: lowercase; text-transform: capitalize">${enumValue}</a></form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Level </label></div>
                <div class="col-sm-3 input-column">
                    <form:select path="level" disabled="true" class="selectpicker" data-width="100%" id="level" name="level" onchange="selectedLevel()">
                        <option disabled="disabled" selected="selected" value="">Select level</option>
                    </form:select></div>
                <div class="col-sm-2 label-column"><label class="col-form-label">Group </label></div>
                <div class="col-sm-3 input-column">
                    <form:select path="group" disabled="true" class="selectpicker" data-width="100%" id="groupSelect" name="groupSelect">
                        <option disabled="disabled" selected="selected" value="">Select a Group</option>
                    </form:select></div>
            </div>
            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Search</button>
        </form:form>
    </div>
</div>

<script type="text/javascript">
    function Validate() {
        return true;
    }
</script>

</body>

</html>

