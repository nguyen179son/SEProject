<%--
  Created by IntelliJ IDEA.
  User: chihiro
  Date: 19/04/2018
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link data-require="bootstrap-css@3.1.1" data-semver="3.1.1" rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"/>
    <script data-require="bootstrap@*" data-semver="3.1.1"
            src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="stylesheets/css/sidebar.css">
    <link rel="stylesheet" href="stylesheets/css/Chat.css">
    <script src="stylesheets/js/sidebar.js"></script>
    <script src="stylesheets/js/chat.js"></script>
</head>
<body>

<div class="wrapper">
    <%@include file="sidebar.jsp" %>
    <div id="content">
        <div class="col-sm-3" id="left">
            <div class="row" id="left-row">
                <div id="custom-search-input">
                    <div class="input-group col-md-12">
                        <input type="text" class="search-query form-control" id="search-chat-room-key"
                               placeholder="Conversation"/>
                        <button class="btn btn-danger" type="button" id="search-chat-room">
                            <span class=" glyphicon glyphicon-search"></span>
                        </button>
                    </div>
                </div>
                <div class="dropdown all_conversation">
                    <label class="dropdown-toggle" id="dropdownMenu2" data-toggle="dropdown"
                           aria-haspopup="true">
                        <i class="fa fa-weixin" aria-hidden="true"></i>
                        All Conversations
                    </label>
                </div>
                <div class="member_list" id="member" >
                    <div id="mem-list">
                        <ul class="list-unstyled" id="list-chat">

                        </ul>
                    </div>
                </div>
            </div>


        </div>
        <div class="col-sm-9 message_section">
            <div class="row">
                <div class="new_message_head">
                    <div class="pull-left">
                        <button id="new-message"><i class="fa fa-plus-square-o" aria-hidden="true">

                        </i> New Message
                        </button>

                    </div>
                </div><!--new_message_head-->
                <div class="modal fade" id="newChatRoom" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Add friends to new chat room</h4>
                            </div>
                            <div class="modal-body">
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-success" id="create-chat-room">Create room chat
                                </button>
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="chat_area">
                    <ul class="list-unstyled" id="list-message">


                    </ul>
                </div><!--chat_area-->
                <div class="message_write">
                    <textarea class="form-control" placeholder="type a message" id="message-content"></textarea>
                    <div class="clearfix"></div>
                    <div class="chat_bottom">
                        <a href="#" class="pull-right btn btn-success" id="send-message">
                            Send</a></div>
                </div>
            </div>
        </div> <!--message_section-->
    </div>
    <div class="modal" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false" hidden>
        <div class="modal-body">
            <div id="ajax_loader">
                <img src="image/loading.gif" style="display: block;margin-left: auto;
                 margin-right: auto;
                 max-height: 5%; max-width: 5%; padding-top: 30%;">
            </div>
        </div>
    </div>
</div>

</body>
</html>
