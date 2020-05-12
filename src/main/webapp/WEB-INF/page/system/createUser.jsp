<%@ page import="com.model.enums.Department" %>
<%@ page import="com.model.enums.GenderType" %>
<%@ page import="com.model.enums.Role" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - New User</title>

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
        <c:url value="/user/creation" var="url"></c:url>
        <form:form class="custom-form" method="post" action="/user/creation" commandName="user">
        <h1>Register Form</h1>
        <input type="hidden" name="usernameRelate" id="usernameRelate" value="${sessionScope.userRelate}"/>
        <div class="form-row form-group">
            <div class="col-sm-2 label-column"><form:label path="name"
                                                           class="col-form-label">Name </form:label></div>
            <div class="col-sm-3 input-column"><form:input path="name" class="form-control"
                                                           type="text" id="name"></form:input></div>
            <div class="col-sm-2 label-column"><form:label path="surname"
                                                           class="col-form-label">Surname </form:label></div>
            <div class="col-sm-3 input-column"><form:input path="surname" class="form-control"
                                                           type="text" id="surname"></form:input></div>
        </div>
        <div class="form-row form-group">
            <div class="col-sm-2 label-column"><form:label path="secondSurname"
                                                           class="col-form-label">2nd Surname </form:label></div>
            <div class="col-sm-3 input-column"><form:input path="secondSurname" class="form-control"
                                                           type="text"></form:input></div>
            <div class="col-sm-2 label-column"><form:label path="birth" class="col-form-label">Birth Date </form:label></div>
            <div class="col-sm-3 input-column">
                <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                    <form:input path="birth" type="text" class="form-control datetimepicker-input" data-target="#datetimepicker1" id="birth"/>
                    <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                </div>
            </div>
        </div>
        <c:set var="enumValues" value="<%=GenderType.values()%>"/>
        <div class="form-row form-group">
            <div class="col-sm-2 label-column"><form:label path="nif" class="col-form-label">NIF </form:label></div>
            <div class="col-sm-3 input-column"><form:input path="nif" class="form-control" type="text" id="nif"></form:input></div>
            <div class="col-sm-2 label-column"><label class="col-form-label">Gender </label></div>
            <div class="col-sm-3 input-column">
                <form:select class="selectpicker" data-width="100%" path="gender">
                    <c:forEach items="${enumValues}" var="enumValue">
                        <form:option value="${enumValue}">${fn:toUpperCase(fn:substring(enumValue.name(),0,1))}${fn:toLowerCase(fn:substring(enumValue.name(),1,fn:length(enumValue.name())))}</form:option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="form-row form-group">
            <div class="col-sm-2 label-column"><label class="col-form-label">Roles </label></div>
            <div class="col-sm-3 input-column">
                <c:set var="enumValues" value="<%=Role.values()%>"/>
                <form:select class="selectpicker" multiple="true" data-width="100%" path="role" id="role-select"
                             name="role-select" onchange="roleSelectChanged()">
                    <c:forEach items="${enumValues}" var="enumValue">
                        <form:option value="${enumValue}"></form:option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="col-sm-2 label-column" id="departmentLabel" style="display: none"><label class="col-form-label">Department </label></div>
            <div class="col-sm-3 input-column" id="departmenInputDiv" style="display: none">
                <c:set var="enumValues" value="<%=Department.values()%>"/>
                <form:select class="selectpicker" data-width="100%"
                             path="department" id="department-select"
                             name="department-select">
                    <form:option selected="selected" value="">Select a department</form:option>
                    <c:forEach items="${enumValues}" var="enumValue">
                        <form:option value="${enumValue}"></form:option>
                    </c:forEach>
                </form:select>
            </div>
        </div>

        <%------------------- STUDENT PART ---------------------%>
        <div id="studentDiv" style="display:none;">
            <br><br>
            <h2>Student</h2>

            <%------------- SELECT GROUP -----------------------%>
            <div class="form-row form-group">
                <div class="col-sm-2 label-column">
                    <label class="col-form-label">Group </label></div>
                <div class="col-sm-8 input-column">
                    <form:select class="selectpicker" data-width="100%"  id="group-select" name="group" data-size="10" data-dropup-auto="false" path="group">
                        <form:option value="">Select a group</form:option>
                        <c:forEach items="${groups}" var="group">
                            <form:option value="${group.groupId}">${group.stage} ${group.level} ${group.group}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <label class="col-form-label" style="text-align: center; font-size: 18px;">Responsibles</label>

            <%---------------- RESPONSIBLES TABLE ------------------------%>

            <table class="table table-borderless table-striped" style="width:100%" id="myTable">
            </table>

            <form:hidden path="responsiblesIds" id="hiddenResponsibles"></form:hidden>


            <%---------------- SELECT RESPONSIBLE ----------------------------%>
            <br><br>
            <div class="form-row form-group">
                <div class="col-sm-2 label-column"><label class="col-form-label">Name </label></div>
                <div class="col-sm-3 input-column"><input id="nameSearch" name="nameSearch" class="form-control" type="text"></div>
                <div class="col-sm-2 label-column"><label class="col-form-label">Surname </label></div>
                <div class="col-sm-3 input-column"><input id="surnameSearch" name="surnameSearch" class="form-control" type="text"></div>
            </div>
            <div class="form-row form-group">
                <div class="col-sm-2 label-column"><label class="col-form-label">2nd Surname </label></div>
                <div class="col-sm-3 input-column"><input id="secondSurnameSearch" name="secondSurnameSearch" class="form-control" type="text"></div>
                <div class="col-sm-2 label-column"></div>
                <div class="col-sm-3"><a class="btn btn-light" id="btn-ajax">Search</a></div>
            </div>

            <div class="form-row form-group">
                <div class="col-sm-2 label-column"><label path="name" class="col-form-label">Responsible </label></div>
                <div class="col-sm-5 input-column">
                    <select class="selectpicker" data-live-search="true" data-width="100%" id="select-responsible" name="select-responsible"></select>
                </div>
                <div class="col-sm-3"><a class="btn btn-light" id="addResponsibleButton">Add Responsible</a></div>
            </div>


        </div>
        <button class="btn btn-light submit-button" type="submit" onclick="return Validate()">Create</button>
    </div>
    </form:form>
</div>

<script type="text/javascript">
    function Validate() {
        $("#departmentLabel").hide();
        $("#departmenInputDiv").hide();
        let validated = true;
        let re = /((\d{2})([/]{1})){2}(\d{4})/;
        var tempDate = $('#datetimepicker1').find("input").val();

        var select = $("#role-select");
        var roles = select.val().toString().split(',');
        var group = $("#group-select").val();
        var usernameRelate = jQuery("#usernameRelate");
        var nif = document.getElementById("nif").value;


        if(document.getElementById("name").value === ""){
            document.getElementById("name").style.backgroundColor = "#ffd6cc";
            validated = false;
        }else{
            document.getElementById("name").style.backgroundColor = "#ffffff";
        }
        if(document.getElementById("surname").value === ""){
            document.getElementById("surname").style.backgroundColor = "#ffd6cc";
            validated = false;
        }else{
            document.getElementById("surname").style.backgroundColor = "#ffffff";
        }

        if(document.getElementById("birth").value === ""){
            document.getElementById("birth").style.backgroundColor = "#ffd6cc";
            validated = false;
        }else {
            var execValidation = re.exec(tempDate);
            if(execValidation == null) {
                document.getElementById("birth").style.backgroundColor = "#ffd6cc";
                validated = false;
            }else{
                var age = calculateAge(tempDate)
                if(age < 0 || (roles[0] !== 'STUDENT' && age < 18)){
                    validated = false;
                    document.getElementById("birth").style.backgroundColor = "#ffd6cc";}
                else
                    document.getElementById("birth").style.backgroundColor = "#ffffff";
            }

        }if(document.getElementById("nif").value === "" && roles[0] !== "STUDENT"){
            document.getElementById("nif").style.backgroundColor = "#ffd6cc";
            validated = false;
        }else{
            document.getElementById("nif").style.backgroundColor = "#ffffff";

        }if (roles[0] === "") {
            $('[data-id="role-select"]').css("background-color","#ffd6cc","important");
            validated = false;
        }else
            $('[data-id="role-select"]').css("background-color","#ffffff","important");
        if (roles.length > 1 && roles[0] === "STUDENT") {
            alert("student can only have 1 role assigned");
            $('[data-id="role-select"]').css("background-color","#ffd6cc","important");
            validated = false;
        }

        if(roles[0] === "STUDENT"){
            if(group === "") {
                console.log("group is null")
                $('[data-id="group-select"]').css("background-color", "#ffd6cc", "important");
                validated = false;
            }else{
                $('[data-id="group-select"]').css("background-color", "#ffffff", "important");
            }

            var hiddenResponsibles = $("#hiddenResponsibles").val();
            if(hiddenResponsibles.length < 2){
                alert("An student needs a responsible");
                validated = false;
            }
        }


        /*
        if (usernameRelate.val() !== "" && usernameRelate.val() != null) {
            if (roles[0] !== "RESPONSIBLE") {
                alert("This user must be a responsible");
                $('[data-id="role-select"]').css("background-color","#ffd6cc","important");
                validated = false;
            }
        }*/


        if(validated && nif !== ""){
            var existNIF = checkNIF();
            if(existNIF == 'true'){
                alert("This NIF is already registered");
                validated = false;
            }
        }

        var tempDate = $('#datetimepicker1').find("input").val();

        var age = calculateAge(tempDate);


        if(validated) {
            var tempDate = $('#datetimepicker1').find("input").val();
            tempDate = tempDate.split("/").reverse().join("/");
            $('#datetimepicker1').find("input").val(tempDate);
        }
        return validated;
    }

    function calculateAge(dateString) {
        var stringSplitted = dateString.split("/");
        var a = new Date(stringSplitted[2], stringSplitted[1] - 1, stringSplitted[0]);
        var d = new Date();
        var o = new Date(d.getTime()-a.getTime());
        var n = d.getFullYear();
        return (o.getFullYear()-1970);
    }


    function checkNIF(){
        var registered;
        $.ajax({
            async : false,
            type: "GET",
            url: "/user/existNIF",
            dataType: "json",
            contentType: 'application/json',
            data: {
                "nif": $("#nif").val(),
            }, //aqui es passen els parametres
            success: function (data) {
                $.each(data, function (index, current) {
                    registered = current.toString();
                });
            },
        }).done(function () {
            return registered;
        }).fail(function () {
            console.log("Error Ajax");
        });
        return registered;
    }


    function roleSelectChanged() {
        var select = $('#department-select');
        var select2 = $('#role-select');
        var valors = $('#role-select').val().toString().split(',');


        if(valors.includes("COORDINATOR") || valors.includes("TUTOR")){
            var values = select2.val();
            values.push("TEACHER");
            valors.push("TEACHER");
            select2.val(values);
            select2.selectpicker('refresh');

        }
        if(valors.includes("TEACHER")){
            $("#departmentLabel").show();
            $("#departmenInputDiv").show();
        }else{
            $("#departmentLabel").hide();
            $("#departmenInputDiv").hide();
        }
        if(valors.includes("STUDENT")){
            $("#studentDiv").show();
        }else{
            $("#studentDiv").hide();
        }


        if(!(valors.includes("TEACHER") || valors.includes("COORDINATOR") || valors.includes("TUTOR"))){
            document.getElementById("department-select").selectedIndex = "0";
        }


        select.selectpicker('refresh');
    }

    function selectTeacher(){
        var valors = $('#role-select').val().toString().split(',');
        if(valors.includes("COORDINATOR") || valors.includes("TUTOR")){
            document.getElementById("department-select").selectedIndex = "0";
        }
    }

    $(function () {
        $('#datetimepicker1').datetimepicker({
            format: 'DD/MM/YYYY',
        });
    });

    $("#btn-ajax").click(function () {
        $.ajax({

            type: "GET",
            url: "/user/requestResponsibles",
            dataType: "json",
            contentType: 'application/json',
            data: {
                "name": $("#nameSearch").val(),
                "surname": $("#surnameSearch").val(),
                "secondSurname": $("#secondSurnameSearch").val()
            }, //aqui es passen els parametres
            success: function (data) {
                let options, select, selectFinish, i;

                // Get the raw DOM object for the select box
                select = document.getElementById('select-responsible');

                // Clear the old options
                select.options.length = 0;

                // Disable the booked options in both select, each one with its list
                $.each(data, function (index, current) {
                    select.options.add(new Option(current.name + " " + current.surname + " " + current.secondSurname, current.id + "/" + current.name + " " + current.surname + " " + current.secondSurname));
                });

                // Selectpicker refresh
                $('#select-responsible').selectpicker('refresh');


            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });
    });

    $("#addResponsibleButton").click(function () {
        var select = $("#select-responsible"),
            responsible = select.val().toString().split("/"),
            hiddenResponsibles = $("#hiddenResponsibles").val(),
            hiddenResponsiblesSplitted = hiddenResponsibles.split(",");

        console.log(hiddenResponsibles);
        if(!hiddenResponsiblesSplitted.includes(responsible[0])){
            hiddenResponsiblesSplitted.push(responsible[0]);
            console.log(hiddenResponsiblesSplitted);
            if(hiddenResponsiblesSplitted[0] === "")
                hiddenResponsiblesSplitted.splice(0, 1);
            console.log(hiddenResponsiblesSplitted);
            $("#hiddenResponsibles").val(hiddenResponsiblesSplitted.join());
            console.log( $("#hiddenResponsibles").val());


            var tb = document.getElementById("myTable");

            var tr = tb.insertRow(-1);
            var td = tr.insertCell()
            td.style.verticalAlign = "middle";
            td.appendChild(document.createTextNode(responsible[1]));

            var td = tr.insertCell(1)
            var obj = document.createElement('a');
            obj.className = 'btn btn-danger';
            obj.setAttribute("href", "#");
            obj.innerHTML = "<\i class=\"fa fa-trash-o\" aria-hidden=\"true\"></i> Delete";
            obj.onclick = function(){ deleteResponsible(this, responsible[0]); }
            td.appendChild(obj);


        }
    });


    function deleteResponsible(r, id){
        var i = r.parentNode.parentNode.rowIndex,
            hiddenResponsibles = $("#hiddenResponsibles").val(),
            hiddenResponsiblesSplitted = hiddenResponsibles.split(",");
        document.getElementById("myTable").deleteRow(i);
        hiddenResponsiblesSplitted.splice(hiddenResponsiblesSplitted.indexOf(id), 1);
        $("#hiddenResponsibles").val(hiddenResponsiblesSplitted.join(","));
    }

</script>

</body>

</html>