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
    <link rel="stylesheet" href="resource/css/index/indexStyle.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">
</head>

<body style="height: 1200px;">
<%@ include file="WEB-INF/page/navbar.jsp" %>
<div class="features-boxed">
    <div class="container">
        <div class="intro">
            <h2 class="text-center">System Administrator </h2>
            <p class="text-center">${pageContext.request.userPrincipal}</p>
        </div>
        <div class="row justify-content-center features">
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-plus icon"></i>
                    <a class="nav-link stretched-link" data-toggle="modal" data-target="#myModal">
                        <h3 class="name">Create Element</h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-pencil-square-o icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/element/access">
                        <h3 class="name">Element Edition</h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-list-alt icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/ticket/access">
                        <h3 class="name">Ticket list</h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-ticket icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/ticket/creation">
                        <h3 class="name">New ticket </h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-tasks icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/procedure/creation">
                        <h3 class="name">New Procedure</h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-wrench icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/">
                        <h3 class="name">Settings </h3></a>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <!-- 16:9 aspect ratio -->
                    <div class="row justify-content-center" style="margin-bottom: 0%">
                        <div class="col item">
                            <div class="box"><i class="fa fa-user-plus icon"></i>
                                <a class="nav-link stretched-link" style="color:#000000;" href="/user/creation">
                                    <h3 class="name">New User</h3></a>
                            </div>
                        </div>
                        <div class="col item">
                            <div class="box"><i class="fa fa-users icon"></i>
                                <a class="nav-link stretched-link" style="color:#000000;" href="/group/creation">
                                    <h3 class="name">New Group</h3></a>
                            </div>
                        </div>
                    </div>
                    <div class="row justify-content-center" style="margin-bottom: 0%">
                        <div class="col item">
                            <div class="box"><i class="fa fa-book icon"></i>
                                <a class="nav-link stretched-link" style="color:#000000;" href="/subject/creation">
                                    <h3 class="name">New Subject</h3></a>
                            </div>
                        </div>
                        <div class="col item">
                            <div class="box"><i class="fa fa-building-o icon"></i>
                                <a class="nav-link stretched-link" style="color:#000000;" href="/space/creation">
                                    <h3 class="name">New Space</h3></a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>


</div>
<div></div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resource/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>

