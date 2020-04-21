<%@ page import="com.model.enums.Stage" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Find Elements</title>

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
        document.getElementById("level").disabled = false;
    }

</script>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <br><br>
        <form class="custom-form" action="/element/find" method="get">
            <h1>Find Element</h1>
            <div class="form-row form-group">
                <c:set var="enumValues" value="<%=Stage.values()%>"/>
                <div class="col-sm-3 label-column"><label path="name" class="col-form-label">Stage </label></div>
                <div class="col-sm-3 label-column"><select class="selectpicker" data-width="100%" id="stage"
                                                           name="stage" onchange="selectedStage()">
                    <option disabled="disabled" selected="selected" value="">Select a typology</option>
                    <c:forEach items="${enumValues}" var="enumValue">
                        <option value="${enumValue}"><a style="text-transform: lowercase; text-transform: capitalize">${enumValue}</a></option>
                    </c:forEach>
                </select>
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Level </label></div>
                <div class="col-sm-3 input-column"><select  disabled="true" class="selectpicker" data-width="100%" id="level"
                                                           name="level">
                    <option disabled="disabled" selected="selected" value="">Select a typology</option>
                </select></div>
                <div class="col-sm-2 label-column"><label class="col-form-label">Group </label></div>
                <div class="col-sm-3 input-column"><select disabled="true" class="selectpicker" data-width="100%" id="group"
                                                           name="group">
                    <option disabled="disabled" selected="selected" value="">Select a typology</option>
                </select></div>
            </div>
            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Search</button>
        </form>
    </div>
</div>

<script type="text/javascript">
    function Validate() {
        return true;
    }
</script>

</body>

</html>

