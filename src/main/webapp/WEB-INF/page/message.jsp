<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->


<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Iungo - Messages</title>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css" rel="stylesheet"/>

    <link rel="stylesheet" href="/resource/css/messageStyle.css">
    <link rel="stylesheet" href="/resource/css/message.css">
    <link rel="stylesheet" href="/resource/css/creation/creationStyle.css">
    <link rel="stylesheet" href="/resource/css/base/deleteModal.css">
    <link rel="stylesheet" href="/resource/css/base/baseModal.css">

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>

<!--JS-->
<script type="text/javascript">
    
    function hoverElement(elem) {
        var div = document.getElementById(elem);
        div.style.backgroundColor = "rgba(222, 157, 63, 0.3)";
    }

    function unHoverElement(elem) {
        var hiddenSelected = document.getElementById('selected-conversation');
        if(hiddenSelected.value != elem) {
            var div = document.getElementById(elem);
            div.style.backgroundColor = "#f8f8f8";
        }
    }

    function selectConversation(elem){
        var hiddenSelected = document.getElementById('selected-conversation'); //""
        var icon = document.getElementById(elem+'i'); //Icono del div que seleccionem  sobert o sobre tancat
        if("" !== hiddenSelected.value) {
            document.getElementById(hiddenSelected.value).style.backgroundColor = "#f8f8f8";
        } //Si hi ha algo seleccionat deselecciona
        hiddenSelected.value = elem; //Posa al hidden la id del que cliquem
        document.getElementById(elem).style.backgroundColor = "rgba(222, 157, 63, 0.3)";
        if (icon.classList.contains('fa-envelope')){
            icon.classList.remove('fa-envelope');
            icon.classList.add('fa-envelope-open-o');
        }
        var missatgeria = document.getElementById("div-messages");
        missatgeria.innerHTML = "";
        document.getElementById("btn-reply-msg").style.display="block";
        document.getElementById("btn-delete").style.display="block";
        document.getElementById("btn-refresh").style.display="block";
        document.getElementById("btn-report").style.display="block";
        $.ajax({

            type: "GET",
            url: "../conversation/getMessages",
            dataType: "json",
            contentType: 'application/json',
            data: {
                "conversationId": elem
            }, //aqui es passen els parametres
            success: function (data) {
                let style, lastVisit, msgDate, i;
                lastVisit  = new Date(data.last);
                // Disable the booked options in both select, each one with its list
                $.each(data.msg, function (index, current) {
                    console.log("ID: "+current.id+" Date: "+current.date+" Subj: "+current.subject+" Body: "+current.body);
                    console.log(lastVisit);

                    msgDate = new Date(current.date);
                    console.log(current.date);
                    console.log(msgDate);
                    console.log(lastVisit>msgDate);
                    if(lastVisit<msgDate){
                        style = "msg-header-no-hover-unread"
                    }else{
                        style = "msg-header-no-hover"
                    }
                    missatgeria.innerHTML += ' <div class="msg" style="width: 100%">'+
                        ' <div id="m'+current.id+'" class="container '+style+'"><span class="space"></span> '+
                        ' <p class="subject" id="subject">'+current.subject+'</p> '+
                        ' <p class="referer" id="referer"><strong>From: </strong>'+current.sender+'<strong> '+
                        ' at </strong>'+current.stringDate+'</p> </div> <div class="msg-body"> <p class="msg-text sub-text-color">'+current.body+'</p> '+
                        ' </div> </div>';
                });
                // Selectpicker refresh
                //$('#select-teacher').selectpicker('refresh');
            }
        }).done(function () {

        }).fail(function () {
            console.log("Error Ajax");
        });
    }


    $(document).ready(function () {
        $("#search").on("keyup", function () {
            var value = $(this).val().toString().toLowerCase();
            $('#conversations').find('div').each(function(){
                var childId = $(this).attr('id');
                var containsText = false;
                if(childId != undefined && childId !== ""){
                    $(this).find('h5, p').each(function(){
                        if($(this).text().toString().includes(value)){
                            containsText = true;
                        }
                    });
                    if(containsText)
                        $(this).show()
                    else
                        $(this).hide()
                }
            });
        });
    });

    function deleteElement(elem) {
        var hiddenSelected = document.getElementById('selected-conversation');
        var valueHidden = window.location.href = '../conversation/delete?conversationId=' + hiddenSelected.value;
    }

    function setConversationToForm(){
        var conversationId = document.getElementById("selected-conversation").value;
        document.getElementById("selected-conversation-form").value = conversationId.toString();
    }

    function validateMessage() {
        return false;
        var subject = document.getElementById("input-subject");
        if(subject.value === "")  {
            subject.style.backgroundColor = "#ffd6cc";
            return false;
        }else{
            subject.style.backgroundColor = "#ffffffff";}
        return false;
    }

</script>

<!--HTML-->
<%@ include file="navbar.jsp" %>
<input id="selected-conversation" type="hidden" value="">
<div class="container custom-div-message">
    <div class="messaging">
        <div class="inbox_msg">
            <div class="inbox_people">
                <div class="heading_srch">
                    <div class="recent_heading">
                        <h4>Recent</h4>
                    </div>
                    <div class="srch_bar">
                        <div class="stylish-input-group">
                            <input type="text" id="search" class="search-bar">
                            <span class="input-group-addon">
                                <a href="/conversation/creation"><button type="button" style="padding-top: 7px; padding-left: 20px;"><i class="fa fa-plus" aria-hidden="true"></i> </button></a>

                <button type="button"> <i class="fa fa-search" aria-hidden="true"></i> </button>
                </span></div>
                    </div>
                </div>
                <div id="conversations" class="inbox_chat">
                    <c:forEach items="${conversations}" var="conversation">
                        <input id="last-${conversation.conversationId}" type="hidden" value="">
                        <div class="chat_list" onmouseout="unHoverElement('${conversation.conversationId}')"
                             onmouseover="hoverElement('${conversation.conversationId}')"
                             onclick="selectConversation('${conversation.conversationId}')"
                             id="${conversation.conversationId}">
                            <div class="chat_people">
                                <div class="chat_ib">
                                    <c:choose>
                                        <c:when test="${conversation.unread eq true}">
                                            <h5 id="icon-"+${conversation.conversationId}>${conversation.title}
                                                <span class="chat_date"><i id="${conversation.conversationId}i"
                                                   class="fa fa-envelope" aria-hidden="true"></i>
                                                </span>
                                            </h5>
                                        </c:when>
                                        <c:otherwise>
                                            <h5 id="icon-"+${conversation.conversationId}>${conversation.title}
                                                <span class="chat_date"><i id="${conversation.conversationId}i"
                                                    class="fa fa-envelope-open-o" aria-hidden="true"></i>
                                                </span>
                                            </h5>
                                        </c:otherwise>
                                    </c:choose>
                                    <p>${conversation.description}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="mesgs">
                <div class="heading_srch">
                    <div class="recent_heading">
                        <h4 id="chat-title" style="color: #DE9D3F">placeholder</h4>
                    </div>
                    <div class="srch_bar">
                       <span class="input-group-addon">
                           <button type="button" id="btn-reply-msg" style="display:none" onclick="setConversationToForm()"> <i class="fa fa-reply" aria-hidden="true" data-toggle="modal" data-target="#modalCreateMessage"></i> </button>
                           <button type="button" id="btn-report" style="display:none"> <i class="fa fa-exclamation-circle" aria-hidden="true" data-toggle="modal" data-target="#myModal"></i> </button>
                           <button type="button" id="btn-refresh" style="display:none" onclick="selectConversation(document.getElementById('selected-conversation').value)"> <i class="fa fa-refresh" aria-hidden="true"></i></button>
                           <button type="button" id="btn-delete" style="display:none"> <i class="fa fa-trash" data-toggle="modal" data-target="#deleteModal" aria-hidden="true"></i></button></span>
                    </div>
                </div>
                <div class="msg_history" id="div-messages">

                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <div class="icon-box">
                    <i class="material-icons">&#xE5CD;</i>
                </div>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <h4 class="modal-title">Are you sure?</h4>
                <p id="deleteText">Do you really want to report this conversation?
                    False reporting can carry consequences.</p>
            </div>
            <input type="hidden" name="elementId" id="elementId" value=""/>
            <div class="modal-footer">
                <button type="button" class="btn-modal btn-info" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn-modal btn-danger" data-dismiss="modal" onclick="reportConversation()">Report
                </button>
            </div>

        </div>
    </div>
</div>

<!-- Modal Delete -->
<div id="deleteModal" name="deleteModal" class="modal fade">
    <div class="modal-dialog modal-confirm">
        <div class="modal-content">
            <div class="modal-header">
                <div class="icon-box">
                    <i class="material-icons">&#xE5CD;</i>
                </div>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
            <div class="modal-body">
                <h4 class="modal-title">Are you sure?</h4>
                <p>Do you really want to delete this conversation?
                    You will stop receiving messages from this chat.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="deleteElement()">Delete
                </button>
            </div>
        </div>
    </div>
</div>



<!-- Create message Modal -->
<div class="modal fade" id="modalCreateMessage" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row base-form-modal">
                    <div class="col-md-10 offset-md-1">
                        <form:form class="custom-form" method="post" action="/message/creation" modelAttribute="message" commandName="message">
                            <h1>Create new message</h1>
                            <form:hidden path="conversation.conversationId" id="selected-conversation-form"/>
                            <div class="form-group">
                                <div class="label-column"><form:label path="subject"
                                                                           class="col-form-label">Subject </form:label></div>
                                <div class="input-column"><form:input path="subject" class="form-control" type="text" id="input-subject"></form:input></div>
                                <div class="label-column"><form:label path="messageBody" class="col-form-label">Body </form:label></div>
                                <div class="input-column"><form:textarea path="messageBody" class="form-control"
                                                                      type="text"></form:textarea></div>
                            </div>
                            <form:button class="btn btn-light submit-button" type="submit" onclick="validateMessage()">Send</form:button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



</body>
</html>
