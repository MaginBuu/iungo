<%@ page import="com.model.enums.Typology" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Modify Space</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker3.css">

</head>

<body>
<%@ include file="navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">

        <div class="container custom-div">
            <form:form method="post" action="/space/modify" modelAttribute="space">
            <h1>Modify Space</h1>
            <form:hidden path="spaceId"/>
            <form:hidden path="name"/>
            <table class="table table-borderless">
                <tbody id="myTable">
                <tr>
                    <td style="vertical-align: middle; text-align: right"><strong>Name:</strong></td>
                    <td style="vertical-align: middle; text-align: left">${space.name}</td>
                </tr>
                <tr>
                    <td style="vertical-align: middle; text-align: right"><strong>Space ID:</strong></td>
                    <td style="vertical-align: middle; text-align: left">${space.spaceId}</td>
                </tr>
                <tr>
                    <td style="horiz-align: right; text-align: right"><strong>Capacity:</strong></td>
                    <td style="horiz-align: right;">
                        <form:input path="capacity" data-width="30%" class="form-control" type="number"></form:input>
                    </td>
                </tr>
                <tr>
                    <c:set var="enumValues" value="<%=Typology.values()%>"/>
                    <td style="horiz-align: right; text-align: right"><strong>Typology:</strong></td>
                    <td style="horiz-align: right;">
                        <form:select class="selectpicker" data-width="100%" path="typology">
                            <form:option disabled="disabled" selected="selected" value="">Select a typology
                            </form:option>
                            <c:forEach items="${enumValues}" var="enumValue">
                                <form:option value="${enumValue}"></form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                </tbody>
            </table>
            <table class="table table-borderless">
                <tbody id="booleans">
                <tr>
                    <td style="horiz-align: right; text-align: right"><strong>Blackboard:</strong></td>
                    <td>
                        <form:checkbox path="blackboard"></form:checkbox>
                    </td>
                    <td style="horiz-align: right; text-align: right"><strong>Interior:</strong></td>
                    <td>
                        <form:checkbox path="interior"></form:checkbox>
                    </td>
                </tr>
                <tr>
                    <td style="horiz-align: right; text-align: right"><strong>Projector:</strong></td>
                    <td>
                        <form:checkbox path="projector"></form:checkbox>
                    </td>
                    <td style="horiz-align: right; text-align: right"><strong>Platform:</strong></td>
                    <td>
                        <form:checkbox path="platform"></form:checkbox>
                    </td>
                </tr>
                <tr>
                    <td style="horiz-align: right; text-align: right"><strong>Tables:</strong></td>
                    <td>
                        <form:checkbox path="tables"></form:checkbox>
                    </td>
                </tr>
                </tbody>
            </table>
            <table class="table table-borderless table-striped">
                <thead>
                    <tr>
                        <th><strong>Starting hour</strong></th>
                        <th><strong>Finishing hour</strong></th>
                        <th><strong>Week Day</strong></th>
                        <th><strong>Subject</strong></th>
                    </tr>
                </thead>
                <tbody id="timelines">
                <c:forEach items="${space.timelines}" var="timeline">
                    <tr>
                        <td style="vertical-align: middle; horiz-align: center">${timeline.startingHour}</td>
                        <td style="vertical-align: middle; horiz-align: center">${timeline.finishingHour}</td>
                        <td style="vertical-align: middle; horiz-align: center">${timeline.weekday}</td>
                        <td style="vertical-align: middle; horiz-align: center">${timeline.subjectName}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
                <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Update</button>
        </div>
    </div>
</div>
</form:form>
</div>
</div>

<script type="text/javascript">
    function Validate() {
        return true;
    }
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="/resource/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
</body>

</html>