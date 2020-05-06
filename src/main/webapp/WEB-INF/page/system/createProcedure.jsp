<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - New Procedure</title>

    <link rel="stylesheet" href="/resource/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resource/fonts/index/font-awesome.min.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/css/bootstrap-select.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.1/js/bootstrap-select.min.js"></script>


</head>

<body>

<%@ include file="../navbar.jsp" %>
<div class="row creation-form">
    <div class="col-md-8 offset-md-2">
        <form:form class="custom-form" method="post" action="/procedure/creation" modelAttribute="procedure" commandName="procedure">
            <h1>Create Procedure</h1>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><form:label path="title"
                                                               class="col-form-label" id="titleLabel">Title </form:label></div>
                <div class="col-sm-8 input-column"><form:input path="title" class="form-control"
                                                               type="text" id="titleInput"></form:input></div>
            </div>

            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><form:label path="description"
                                                               class="col-form-label">Description </form:label></div>
                <div class="col-sm-8 input-column-Procedure"><form:textarea path="description" class="form-control"
                                                                            type="text"></form:textarea></div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <form:label path="limitDate" class="col-form-label" id = "datetimepicker1Label">Limit date </form:label></div>
                <div class="col-sm-3 form-group" style="position: relative">
                    <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                        <form:input path="limitDate" type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1"/>
                        <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-2 label-column">
                    <label class="col-form-label" id="datetimepicker3Label">Hour </label></div>
                <div class="col-sm-3 form-group" style="position: relative">
                    <div class="input-group date" id="datetimepicker3" data-target-input="nearest">
                        <input type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1" value="23:59" id="limitHour"/>
                        <div class="input-group-append" data-target="#datetimepicker3" data-toggle="datetimepicker">
                            <div class="input-group-text"><i class="fa fa-clock-o"></i></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-3 label-column">
                    <form:label path="online" class="col-form-label">Online </form:label></div>
                <div class="col-sm-2 checkbox-column">
                    <form:checkbox path="online"></form:checkbox></div>
            </div>

            <h2>User</h2>

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
                <div class="col-sm-3 input-column"><input id="secondSurname" name="name" class="form-control"
                                                          type="text"></div>
                <div class="col-sm-2 label-column"></div>
                <div class="col-sm-2"><a class="btn btn-light" id="btn-ajax">search</a></div>
            </div>

            <div class="form-row form-group">
                <div class="col-sm-3 label-column"><label path="name" class="col-form-label">Responsible </label></div>
                <div class="col-sm-7 input-column">
                    <form:select class="selectpicker" data-live-search="true" data-width="100%" multiple="false" id="select-user" name="select-user" path="userP.userId">
                    </form:select>
                </div>
            </div>
            
            
            
            
        <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
        </form:form>
    </div>
</div>



    <script type="text/javascript">

        function Validate() {
            let validated = true;

            if(document.getElementById("titleInput").value === ""){
                document.getElementById("titleInput").style.backgroundColor = "#ffd6cc";
                document.getElementById("titleInput").style.borderRadius = "3px";
                validated = false;
            }else{
                document.getElementById("titleInput").style.backgroundColor = "#ffffff";
            }
            if($('#datetimepicker1').find("input").val() === ""){
                document.getElementById("limitDate").style.backgroundColor = "#ffd6cc";
                document.getElementById("limitDate").style.borderRadius = "3px";
                validated = false;
            }else{
                document.getElementById("limitDate").style.backgroundColor = "#ffffff";
            }
            if($('#datetimepicker3').find("input").val() === ""){
                document.getElementById("limitHour").style.backgroundColor = "#ffd6cc";
                document.getElementById("limitHour").style.borderRadius = "3px";
                validated = false;
            }else{
                document.getElementById("limitHour").style.backgroundColor = "#ffffff";
            }
            if($('#select-user').val() == null || $('#select-user').val().toString() === ""){
                $('*[data-id="select-user"]').css("background-color","#ffd6cc","important");
                validated = false;
            }else {
                $('*[data-id="select-user"]').css("background-color","#ffffff","important");
            }

            if (validated) {
                var tempDate = $('#datetimepicker1').find("input").val();
                tempDate = tempDate.split("/").reverse().join("/");
                tempDate += " " + $('#datetimepicker3').find("input").val() + ":00"
                $('#datetimepicker1').find("input").val(tempDate);
            }
            return validated;
        }

        $(function () {
            $('#datetimepicker1').datetimepicker({
                format: 'DD/MM/YYYY',

            });
        });

        $(function () {
            $('#datetimepicker3').datetimepicker({
                format: 'HH:mm',
                pick12HourFormat: false,

            });
        });



        $("#btn-ajax").click(function () {
            $.ajax({

                type: "GET",
                url: "/user/requestUsers",
                dataType: "json",
                contentType: 'application/json',
                data: {
                    "name": $("#name").val(),
                    "surname": $("#surname").val(),
                    "secondSurname": $("#secondSurname").val()
                }, //aqui es passen els parametres
                success: function (data) {
                    let options, select, selectFinish, i;

                    // Get the raw DOM object for the select box
                    select = document.getElementById('select-user');

                    // Clear the old options
                    select.options.length = 0;

                    select.options.add(new Option("Select an user", ""));

                    // Disable the booked options in both select, each one with its list
                    $.each(data, function (index, current) {
                        select.options.add(new Option(current.name + " " + current.surname + " " + current.secondSurname, current.id));
                    });

                    // Selectpicker refresh
                    $('#select-user').selectpicker('refresh');


                }
            }).done(function () {

            }).fail(function () {
                console.log("Error Ajax");
            });
        });

        
    </script>
</body>

</html>