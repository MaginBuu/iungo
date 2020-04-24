<%--
  Created by IntelliJ IDEA.
  User: maginbuu
  Date: 4/24/2020
  Time: 09:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Title</title>
    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/css/base/baseStyle.css">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>


</head>
<body>
<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <div class="container custom-div">
            <h1>Search Responsible</h1>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Name </label></div>
                <div class="col-sm-3 input-column"><input id="name" name="name" class="form-control"
                                                          type="text"></div>
                <div class="col-sm-2 label-column"><label class="col-form-label">Surname </label></div>
                <div class="col-sm-3 input-column"><input id="surname" name="name" class="form-control"
                                                          type="text"></div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label class="col-form-label">Surname2 </label></div>
                <div class="col-sm-3 input-column"><input id="secondSurbame" name="name" class="form-control"
                                                          type="text"></div>
                <div class="col-sm-2 label-column"></div>
                <div class="col-sm-2"><a class="btn btn-light">search</a></div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
