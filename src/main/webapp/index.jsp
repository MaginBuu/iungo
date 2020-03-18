<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>test</title>
    <link rel="stylesheet" href="resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="resource/fonts/index/font-awesome.min.css">
    <link rel="stylesheet" href="resource/css/index/Dark-NavBar-1.css">
    <link rel="stylesheet" href="resource/css/index/Features-Boxed.css">
    <link rel="stylesheet" href="resource/css/index/styles.css">
</head>

<body style="height: 1200px;">
<%@ include file="WEB-INF/page/navbar.jsp" %>
<div class="features-boxed">
    <div class="container">
        <div class="intro">
            <h2 class="text-center">Penis </h2>
            <p class="text-center">"${pageContext.request.userPrincipal}"</p>
        </div>
        <div class="row justify-content-center features">
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-user-plus icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/user/creation">
                        <h3 class="name">Create User</h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-comments icon"></i>
                    <h3 class="name">Messages</h3>
                    <a class="learn-more" href="#">Learn more >></a></div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-list-alt icon"></i>
                    <h3 class="name">Tickets </h3>
                    <a class="learn-more" href="/user/tickets">Learn more >></a></div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-ticket icon"></i>
                    <h3 class="name">Ticket </h3>
                    <a class="learn-more" href="/ticket/creation">Learn more >></a></div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-tasks icon"></i>
                    <h3 class="name">Procedure </h3>
                    <a class="learn-more" href="/procedure/creation">Learn more >></a></div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-wrench icon"></i>
                    <h3 class="name">Settings</h3>
                    <a class="learn-more" href="/test/search">Learn more >></a></div>
            </div>
        </div>
    </div>
</div>
<div></div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resource/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>

