<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->


<html>
<head>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" type="text/css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="/resource/css/messageStyle.css">
    <link rel="stylesheet" href="/resource/css/message.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="/resource/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
<%@ include file="navbar.jsp" %>
<input id="selected-conversation" type="hidden" value="">
<div class="container">
    <h3 class=" text-center">Messaging</h3>
    <div class="messaging">
        <div class="inbox_msg">
            <div class="inbox_people">
                <div class="heading_srch">
                    <div class="recent_heading">
                        <h4>Recent</h4>
                    </div>
                    <div class="srch_bar">
                        <div class="stylish-input-group">
                            <input type="text" class="search-bar">
                            <span class="input-group-addon">
                <button type="button"> <i class="fa fa-search" aria-hidden="true"></i> </button>
                </span></div>
                    </div>
                </div>
                <div class="inbox_chat">
                    <c:forEach items="${conversations}" var="conversation">
                        <div class="chat_list" onmouseout="unHoverElement(${conversation.conversationId})"
                             onmouseover="hoverElement(${conversation.conversationId})" id="${conversation.conversationId}">
                            <div class="chat_people">
                                <div class="chat_ib">
                                    <c:choose>
                                        <c:when test="${conversation.reported eq true}">
                                            <h5>${conversation.title} <span class="chat_date"><i class="fa fa-envelope"
                                                                                                 aria-hidden="true"></i></span>
                                            </h5>
                                        </c:when>
                                        <c:otherwise>
                                            <h5>${conversation.title} <span class="chat_date"><i
                                                    class="fa fa-envelope-open-o" aria-hidden="true"></i></span></h5>
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
                        <h4>Recent</h4>
                    </div>
                    <div class="srch_bar">
                        <div class="stylish-input-group">
                            <input type="text" class="search-bar">
                            <span class="input-group-addon">
                <button type="button"> <i class="fa fa-search" aria-hidden="true"></i> </button>
                </span></div>
                    </div>
                </div>
                <div class="msg_history">
                    <div class="card" style="width: 100%">
                        <div id="anselm" class="container card-header-no-hover"><span class="space"></span>
                            <p class="subject" id="heading-card">Classe dema per la tarda</p>
                            <p class="referer" id="heading-card"><strong>From:</strong> Angela Martin <strong>
                                at </strong> 02/08/2020</p>
                        </div>
                        <div class="card-body">
                            <p class="card-text sub-text-color">"Lorem ipsum dolor sit amet, consectetur adipiscing
                                elit,
                                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
                                veniam,
                                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function hoverElement(elem) {
        let div = document.getElementById(elem);
        div.style.backgroundColor = "rgba(222, 157, 63, 0.3)";
    }

    function unHoverElement(elem) {
        let div = document.getElementById(elem);
        div.style.backgroundColor = "#f8f8f8";
    }

    function selectConversation(elem){

    }

</script>

</body>
</html>
