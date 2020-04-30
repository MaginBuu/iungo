<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<%--
  Created by IntelliJ IDEA.
  User: maginbuu
  Date: 4/28/2020
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Iungo - Grades Report</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.2.61/jspdf.min.js"></script>
    <script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
</head>
<body>


<script>

    function printDiv() {
        w=window.open();
        w.document.write($('#gradesDiv').html());
        w.print();
        w.close();
    }

    /*function printDiv() {
        var HTML_Width = $("#gradesDiv").width();
        var HTML_Height = $("#gradesDiv").height();
        var top_left_margin = 15;
        var PDF_Width = HTML_Width + (top_left_margin * 2);
        var PDF_Height = (PDF_Width * 1.5) + (top_left_margin * 2);
        var canvas_image_width = HTML_Width;
        var canvas_image_height = HTML_Height;

        var totalPDFPages = Math.ceil(HTML_Height / PDF_Height) - 1;

        html2canvas(document.getElementById("gradesDiv")).then(function (canvas) {
            var imgData = canvas.toDataURL("image/jpeg", 0.9);
            console.log(imgData);
            var pdf = new jsPDF('p', 'pt', [PDF_Width, PDF_Height]);
            pdf.addImage(imgData, 'JPG', top_left_margin, top_left_margin, canvas_image_width, canvas_image_height);
            console.log(totalPDFPages)
            for (var i = 1; i <= totalPDFPages; i++) {
                pdf.addPage(PDF_Width, PDF_Height);
                pdf.addImage(imgData, 'JPG', top_left_margin, -(PDF_Height*i)+(top_left_margin*4),canvas_image_width,canvas_image_height);
            }
            pdf.save("Your_PDF_Name.pdf");
            //$("#gradesDiv").hide();
        });
    }*/
</script>



<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">
            <div id="gradesDiv" name="gradesDiv">
                <h1>Grades Report</h1>

                <c:forEach items="${grades}" var="grade">
                    <br><br>
                    <h2>${grade.subject.name}</h2>

                    <c:if test="${grade.userTasks.size() gt 0}">
                            <table class="table table-borderless table-striped">
                                <thead>
                                <tr>
                                    <th><strong>Task</strong></th>
                                    <th><strong>value</strong></th>
                                    <th><strong>Observations</strong></th>
                                    <th>Grade</th>
                                </tr>
                                </thead>
                                <tbody id="myTable">
                                <c:forEach items="${grade.userTasks}" var="userTasks">
                                    <tr>
                                        <td style="vertical-align: middle; horiz-align: center">${userTasks.task.title}</td>
                                        <td style="vertical-align: middle; horiz-align: center">${userTasks.task.value}</td>
                                        <td style="vertical-align: middle; horiz-align: center">${userTasks.observations}</td>
                                        <td style="vertical-align: middle; horiz-align: center">${userTasks.grade}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                    </c:if>
                    <c:if test="${grade.userTasks.size() eq 0}">
                        <br><p>This subject has not evaluation tasks</p><br>
                    </c:if>


                    <c:if test="${grade.userSubject.grade eq null}">
                        <label class="label-column">Final grade: not evaluated yet</label>
                    </c:if>

                    <c:if test="${grade.userSubject.grade ne null}">
                        <label class="label-column">Final grade: ${userSubject.grade}</label>
                    </c:if>

                </c:forEach>

                <br><br>
                <h2>Incidences</h2>

                <table class="table table-borderless table-striped">
                    <thead>
                    <tr>
                        <th><strong>Incidence type</strong></th>
                        <th><strong>Day</strong></th>
                        <th><strong>Commentary</strong></th>
                        <th>Justified</th>
                    </tr>
                    </thead>
                    <tbody id="myTable">
                    <c:forEach items="${incidences}" var="incidence">
                        <tr>
                            <td style="vertical-align: middle; horiz-align: center">${incidence.faultType}</td>
                            <td style="vertical-align: middle; horiz-align: center">   <fmt:formatDate type="date" value="${incidence.creationDate}" pattern="dd-MM-yyyy HH:mm" /></td>
                            <td style="vertical-align: middle; horiz-align: center">${incidence.description}</td>
                            <td style="vertical-align: middle; horiz-align: center"> <c:if test="${incidence.faultType eq 'ATTENDANCE'}">
                                                <c:if test="${incidence.justified eq true}">
                                                    ${incidence.justified}
                                                </c:if>
                                            <c:if test="${incidence.justified ne true}">
                                                false
                                            </c:if>
                                        </c:if>
                                    <c:if test="${incidence.faultType ne 'ATTENDANCE'}"> - </c:if></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>

            <a class="btn btn-light submit-button" onclick="printDiv()">generate PDF</a>
        </div>
    </div>
</div>
</body>
</html>
