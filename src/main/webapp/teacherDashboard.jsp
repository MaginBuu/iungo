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
</head>

<body style="height: 1200px;">
<%@ include file="WEB-INF/page/navbar.jsp" %>
<div class="features-boxed">
    <div class="container">
        <div class="intro">
            <h2 class="text-center">Teacher </h2>
            <p class="text-center">${pageContext.request.userPrincipal}</p>
        </div>
        <div class="row justify-content-center features">
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-book icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/teacher/subjects">
                        <h3 class="name">My Subjects</h3></a>
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
                <div class="box"><i class="fa fa-university icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/teacher/select/group">
                        <h3 class="name">Class</h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-pie-chart icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="/teacher/evaluate">
                        <h3 class="name">Evaluation </h3></a>
                </div>
            </div>
            <div class="col-sm-6 col-md-5 col-lg-4 item">
                <div class="box"><i class="fa fa-bomb icon"></i>
                    <a class="nav-link stretched-link" style="color:#000000;" href="">
                        <h3 class="name">XXX </h3></a>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <!-- 16:9 aspect ratio -->
                    <div class="row justify-content-center" style="margin-bottom: 0%">
                        <div class="col item">
                            <div class="box"><i class="fa fa-street-view icon"></i>
                                <a class="nav-link stretched-link" style="color:#000000;"
                                   href="/user/antibullying?observed=false">
                                    <h3 class="name">Report Personal Issue</h3></a>
                            </div>
                        </div>
                        <div class="col item">
                            <div class="box"><i class="fa fa-eye icon"></i>
                                <a class="nav-link stretched-link" style="color:#000000;"
                                   href="/user/antibullying?observed=true">
                                    <h3 class="name">Report Other Issues</h3></a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="resource/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>

