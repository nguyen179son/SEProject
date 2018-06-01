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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link data-require="bootstrap-css@3.1.1" data-semver="3.1.1" rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"/>
    <script data-require="bootstrap@*" data-semver="3.1.1"
            src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="stylesheets/css/sidebar.css">
    <link rel="stylesheet" href="stylesheets/css/Chat.css">
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
                        <input type="text" class="  search-query form-control" placeholder="Conversation"/>
                        <button class="btn btn-danger" type="button">
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
                <div class="member_list" id="member">
                    <ul class="list-unstyled" id="list-chat">
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    09:45AM</strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong> <span
                                        class="badge pull-right">3</span>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right ">
                                    09:45AM</strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong> <span
                                        class="badge pull-right">3</span>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    09:45AM</strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong> <span
                                        class="badge pull-right">3</span>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    09:45AM</strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong> <span
                                        class="badge pull-right">3</span>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    09:45AM</strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong> <span
                                        class="badge pull-right">3</span>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    09:45AM</strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong> <span
                                        class="badge pull-right">3</span>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    09:45AM</strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong> <span
                                        class="badge pull-right">3</span>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>


        </div>
        <div class="col-sm-9 message_section">
            <div class="row">
                <div class="new_message_head">
                    <div class="pull-left">
                        <button><i class="fa fa-plus-square-o" aria-hidden="true"></i> New Message</button>
                    </div>
                </div><!--new_message_head-->

                <div class="chat_area">
                    <ul class="list-unstyled">
                        <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body1 clearfix">
                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a
                                    piece of classical Latin literature from 45 BC, making it over 2000 years old.
                                    Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia.</p>
                                <div class="chat_time pull-right">09:40PM</div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body1 clearfix">
                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a
                                    piece of classical Latin literature from 45 BC, making it over 2000 years old.
                                    Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia.</p>
                                <div class="chat_time pull-right">09:40PM</div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body1 clearfix">
                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a
                                    piece of classical Latin literature from 45 BC, making it over 2000 years old.
                                    Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia.</p>
                                <div class="chat_time pull-right">09:40PM</div>
                            </div>
                        </li>
                        <li class="left clearfix admin_chat">
                     <span class="chat-img1 pull-right">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body1 clearfix">
                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a
                                    piece of classical Latin literature from 45 BC, making it over 2000 years old.
                                    Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia.</p>
                                <div class="chat_time pull-left">09:40PM</div>
                            </div>
                        </li>
                        <li class="left clearfix admin_chat">
                     <span class="chat-img1 pull-right">
                     <img src="image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body1 clearfix">
                                <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a
                                    piece of classical Latin literature from 45 BC, making it over 2000 years old.
                                    Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia.</p>
                                <div class="chat_time pull-left">09:40PM</div>
                            </div>
                        </li>


                    </ul>
                </div><!--chat_area-->
                <div class="message_write">
                    <textarea class="form-control" placeholder="type a message"></textarea>
                    <div class="clearfix"></div>
                    <div class="chat_bottom">
                        <a href="#" class="pull-right btn btn-success">
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
