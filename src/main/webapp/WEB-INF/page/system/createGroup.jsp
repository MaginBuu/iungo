<%@ page import="com.model.enums.Stage" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - New Group</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">


</head>

<body onload="changeDisabled()">

<script>
    function Validate() {
        var validated = true;
        var name = document.getElementById("name-input");
        var department = document.getElementById("stage");
        var group = document.getElementById("group-input");
        var level = document.getElementById("level-input");
        if(name.value == "")  {
            name.style.backgroundColor = "#ffd6cc";
            validated = false;
        }else name.style.backgroundColor = "#ffffffff";
        if(group.value == "")  {
            group.style.backgroundColor = "#ffd6cc";
            validated = false;
        }else group.style.backgroundColor = "#ffffffff";
        if(level.value < 1)  {
            level.style.backgroundColor = "#ffd6cc";
            validated = false;
        }else level.style.backgroundColor = "#ffffffff";
        if(department.value.toString() == "")  {
            $('*[data-id="stage"]').css("background-color","#ffd6cc","important");
            validated = false;
        }else $('*[data-id="stage"]').css("background-color","#ffffffff","important");
        return validated;
    }



    function changeDisabled() {
        var select1 = jQuery('#year');
        var checkbox = jQuery('#thisCourse');
        select1.prop('disabled', checkbox.prop("checked")).selectpicker('refresh');
    }
</script>



<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <c:url value="/group/creation" var="url"></c:url>
        <form:form class="custom-form" method="post" action="/group/creation" modelAttribute="group"
                   commandName="group">
            <h1>Create Group</h1>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><form:label path="level"
                                                               class="col-form-label">Level </form:label></div>
                <div class="col-sm-3 input-column"><form:input path="level" class="form-control" id="level-input"
                                                               type="number"></form:input></div>
                <div class="col-sm-2 label-column"><form:label path="group"
                                                               class="col-form-label">Group </form:label></div>
                <div class="col-sm-3 input-column"><form:input path="group" class="form-control" id="group-input"
                                                               type="text"></form:input></div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><form:label path="name"
                                                               class="col-form-label">Name </form:label></div>
                <div class="col-sm-8 input-column"><form:input path="name" class="form-control" id="name-input"
                                                               type="text"></form:input></div>
            </div>
            <c:set var="enumValues" value="<%=Stage.values()%>"/>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <form:label path="Stage" class="col-form-label">Stage </form:label></div>
                <div class="col-sm-8 input-column">
                    <form:select class="selectpicker" data-width="100%" path="stage" id="stage" name="stage">
                        <form:option disabled="disabled" selected="selected" value="">Select a stage</form:option>
                        <c:forEach items="${enumValues}" var="enumValue">
                            <form:option value="${enumValue}"></form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label"> </label></div>
                <div class="col-sm-9 checkbox-column"><label class="col-form-label">
                    <div class="form-check form-check-inline">
                        <input type="checkbox" class="form-check-input" checked="checked" onclick="changeDisabled()"
                               id="thisCourse"/>
                        <label class="form-check-label"> This group is created in this academic course</label>
                    </div>
                </label></div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <form:label path="course.courseId" class="col-form-label">Year </form:label></div>
                <div class="col-sm-8 input-column">
                    <form:select class="selectpicker" data-width="100%" path="course.courseId" id="year" name="year">
                        <c:forEach items="${courses}" var="course">
                            <form:option
                                    value="${course.courseId}"> ${course.startDate} - ${course.endDate}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
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