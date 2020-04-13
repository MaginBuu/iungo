<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Navigation Bar</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">

    <link rel="stylesheet" href="/resource/fonts/index/font-awesome.min.css">
    <link rel="stylesheet" href="/resource/css/index/navigationStyle.css">

</head>

<script>

    function  hideNotifications() {
        document.getElementById("notf-num").style.display = "none";
    }
    function loadNotifications(elem) {

        var table = document.getElementById("tbody");
        table.innerHTML = "";
        $.ajax({

            type: "GET",
            url: "/user/getNotifications",
            dataType: "json",
            contentType: 'application/json',
            data: {
            }, //aqui es passen els parametres
            success: function (data) {
                let notfs, lastVisit, msgDate, i;
                // Disable the booked options in both select, each one with its list
                notfs = 0;
                $.each(data, function (index, current) {
                    notfs = notfs+1;
                    table.innerHTML += '<tr id="'+current.id+'"> <td> <div class="form-row form-group"><div class="col float-left">'+
                    '<a><strong>'+current.title+'</strong> </a> </div> <div class="col float-right"> <a class="fa fa-trash"></a>'+
                    '</div> </div> <div class="form-row form-group" <a style="font-size: 10px">'+current.body+
                    '</a> </div> </td> </tr>';
                    if(current.pending == true){
                        document.getElementById(current.id).style.backgroundColor = "hsla(39.17, 97.65%, 57.42%, 0.08)";
                    }
                });
                document.getElementById("notf-num").innerHTML = notfs;
            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });
    }
</script>

<body style="height: 1200px;" onload="loadNotifications()">
<div>
    <nav class="navbar navbar-light navbar-expand-md sticky-top navigation-clean-button"
         style="height: 65px;background-color: #de9d3f;color: #ffffff;">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">
                <i class="fa fa-globe"></i>
                &nbsp;iUNGO</a>
            <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span
                    class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item" role="presentation">
                        <a class="nav-link" style="color:#ffffff;" href="#">
                            Home</a></li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link" style="color: #ffffff;" href="#">
                            <i class="fa fa-wpexplorer"></i>&nbsp;Explore</a></li>
                    <li class="nav-item dropdown" role="presentation">
                        <a class="nav-link dropdown-toggle waves-effect waves-light" id="navbarDropdownMenuLink-5" style="color: #ffffff;" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true" onclick="hideNotifications()">
                            <span id="notf-num" class="badge badge-danger ml-2"></span>
                            <i class="fa fa-bell"></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-lg-right dropdown-secondary" aria-labelledby="navbarDropdownMenuLink-5" style="width: 300px">

                                <table class="table table-bordered">
                                    <tbody id="tbody">
                                    </tbody>
                                </table>
                        </div>
                    </li>

                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <li class="nav-item" role="presentation"><a class="nav-link" style="color:#ffffff;"
                                                                        href="#"><i
                                    class="fa fa-user-circle-o"></i>&nbsp;Perfil</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item" role="presentation"><a class="nav-link" style="color:#ffffff;"
                                                                        href="#"><i
                                    class="fa fa-user-circle-o"></i> ${sessionScope.name}</a></li>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name == null}">
                            <li class="nav-item" role="presentation"><a class="nav-link" style="color:#ffffff;"
                                                                        href="/login"><i
                                    class="fa fa-sign-in"></i>&nbsp;Sign In</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item" role="presentation"><a class="nav-link" style="color:#ffffff;"
                                                                        href="/logout"><i
                                    class="fa fa-sign-out"></i>&nbsp;Sign out</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
</div>

</body>

</html>