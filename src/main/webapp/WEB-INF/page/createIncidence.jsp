<%@ page import="com.model.enums.Stage" %>
<%@ page import="com.model.enums.FaultType" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - New Incidence</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">


</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <form:form class="custom-form" method="post" action="/incidence/creation" modelAttribute="incidence">
            <form:hidden path="user.userId"/>
            <h1>Create Incidence</h1>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Fault type </label></div>
                <div class="col-sm-4 input-column">
                    <c:set var="enumValues" value="<%=FaultType.values()%>"/>
                    <form:select class="selectpicker" data-width="100%" path="faultType">
                        <c:forEach items="${enumValues}" var="enumValue">
                            <form:option
                                    value="${enumValue}">${fn:toUpperCase(fn:substring(enumValue.name(),0,1))}${fn:toLowerCase(fn:substring(enumValue.name(),1,fn:length(enumValue.name())))}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><form:label path="description"
                                                               class="col-form-label">Description </form:label></div>
                <div class="col-sm-7 input-column-Procedure"><form:textarea path="description" class="form-control"
                                                                            type="text"></form:textarea></div>
            </div>
            <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
        </form:form>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
</body>

</html>