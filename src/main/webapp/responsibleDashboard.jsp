<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Main Page</title>
    <link rel="stylesheet" href="resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="resource/fonts/index/font-awesome.min.css">
    <link rel="stylesheet" href="/resource/css/index/indexStyle.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="/resource/css/base/dropdownModal.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.1.1/aos.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="resource/bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>
</head>

<body style="height: 1200px;">

<script>

    function initModal(){
        document.getElementById("modalText").style.backgroundColor = "";
        document.getElementById("default-select").selected = "true";
        var e = document.getElementById("studentId");
        e.val("");
    }

    function navigateSubjects() {
        var student = jQuery('#studentId');
        if(student.val() != ""){
            window.location.href = '/responsible/' + student.val() + '/subjects';
        }else{
            document.getElementById("modalText").style.backgroundColor = "#ffd6cc";
            document.getElementById("modalText").style.borderRadius = "3px";
        }
    }

    function navigateProfile() {
        var student = jQuery('#studentId');
        if(student.val() != ""){
            window.location.href = '/responsible/' + student.val() + '/profile';
        }else{
            document.getElementById("modalText").style.backgroundColor = "#ffd6cc";
            document.getElementById("modalText").style.borderRadius = "3px";
        }
    }

    function selectedChild(){
        var e = document.getElementById("student-pick");
        var result = e.options[e.selectedIndex].value;
        var hiddenInput = jQuery('#studentId');
        hiddenInput.val(result);
        document.getElementById("butt").disabled = false;
    }

</script>

<%@ include file="WEB-INF/page/navbar.jsp" %>
<div class="features-boxed">
    <div class="container">
        <div class="intro">
            <h2 class="text-center">Responsible </h2>
        </div>
        <div class="row justify-content-center features">
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-child icon"></i>
                    <a class="nav-link stretched-link" data-toggle="modal" data-target="#myModal" onclick="initModal()">
                        <h3 class="name">Children</h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-paper-plane-o icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/user/messages">
                        <h3 class="name">Messages</h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-briefcase icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/user/procedures">
                        <h3 class="name">Procedures </h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-graduation-cap icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/user/teachers">
                        <h3 class="name">Teachers </h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-life-ring icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href=/user/antibullying?observed=true">
                        <h3 class="name">Anti Bullying </h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-calendar icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/calendar">
                        <h3 class="name">Calendar </h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-ticket icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/ticket/creation">
                        <h3 class="name">Create Ticket </h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-history icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/user/tickets">
                        <h3 class="name">Ticket History </h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-wrench icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/configuration">
                        <h3 class="name">Configuration </h3></a>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal HTML -->
    <div id="myModal" name="myModal" class="modal fade">
        <div class="modal-dialog modal-confirm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <h4 class="modal-title">Select a child</h4>
                    <p id="modalText">Select a child in order to navigate through the options.</p>
                    <div class="row" style="justify-content: center">
                        <select class="selectpicker" data-width="100%" style="vertical-align: middle;" id="student-pick" name="student-pick" onchange="selectedChild()">
                            <option id="default-select" disabled="disabled" selected="selected" value="">Select a child</option>
                            <c:forEach items="${children}" var="child">
                                <option value="${child.userR.userId}" style="color:#000000">${child.userR.name} ${child.userR.surname} ${child.userR.secondSurname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <input type="hidden" name="studentId" id="studentId" value=""/>

                <div class="row justify-content-center" style="margin-bottom: 0%">
                    <div class="col item">
                        <div class="box"><i class="fa fa-book icon"></i>
                            <a id="butt" class="nav-link stretched-link" style="color:#000000;"
                               onclick="navigateSubjects()">
                                <h3 class="name">Subjects</h3></a>
                        </div>
                    </div>
                    <div class="col item">
                        <div class="box"><i class="fa fa-user icon"></i>
                            <a class="nav-link stretched-link" style="color:#000000;"
                               onclick="navigateProfile()">
                                <h3 class="name">Profile</h3></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

</html>

