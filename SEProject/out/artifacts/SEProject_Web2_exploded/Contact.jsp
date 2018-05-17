<!doctype html>
<html>
<head>
    <title>Contact</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" href="./stylesheets/css/sidebar.css">
    <link rel="stylesheet" href="./stylesheets/css/Contact.css">
    <script src="stylesheets/js/contact.js"></script>
</head>
<body>
<div class="wrapper">
    <%@include file="sidebar.jsp" %>
    <div id="content">
        <div class="col-sm-3" id="left">
            <div class="row" id="left-row">
                <div id="custom-search-input">
                    <div class="input-group col-md-12">
                        <input type="text" class="  search-query form-control" placeholder="Search friends" id="search_friend_name"/>
                        <button class="btn btn-danger" type="button" id="searchFriends">
                            <span class=" glyphicon glyphicon-search"></span>
                        </button>
                    </div>
                </div>
                <div class="member_list" id="member">

                    <ul class="list-unstyled" id="listFriend">
                        <li class="left clearfix" data-id="abc">
                     <span class="chat-img pull-left">
                     <img src="./image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong>
                                    <strong class="pull-right">
                                        <i class="fa fa-star" style="color: yellow;"></i></strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="./image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right ">
                                    <i class="fa fa-star"></i></strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="./image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    <i class="fa fa-star"></i></strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="./image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    <i class="fa fa-star"></i></strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="./image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    <i class="fa fa-star"></i></strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="./image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    <i class="fa fa-star"></i></strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong>
                                </div>
                            </div>
                        </li>
                        <li class="left clearfix">
                     <span class="chat-img pull-left">
                     <img src="./image/profile.png"
                          alt="User Avatar" class="img-circle">
                     </span>
                            <div class="chat-body clearfix">
                                <div class="header_sec">
                                    <strong class="primary-font">Jack Sparrow</strong> <strong class="pull-right">
                                    <i class="fa fa-star"></i></strong>
                                </div>
                                <div class="contact_sec">
                                    <strong class="primary-font">(123) 123-456</strong>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>


        </div>
        <div class="col-sm-9 message_section" >
            <div class="row" id="profile-div" hidden>
                <input type="text" hidden id="id">
                <img src="image/profile.png" class="center img-responsive" alt="profilepic" id="profile">

                <div class="form-group">
                    <div class="col-sm-3"></div>
                    <label class="control-label col-sm-4">Name: </label>
                    <div class="col-sm-4 div-info">
                        <label class="view-label prevent-break-line" id="nick-name">abc</label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-3"></div>
                    <label class="control-label col-sm-4">Email: </label>
                    <div class="col-sm-4 div-info">
                        <label class="view-label prevent-break-line" id="email">abc </label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-3"></div>
                    <label class="control-label col-sm-4">Phone Number: </label>
                    <div class="col-sm-4 div-info">
                        <label class="view-label prevent-break-line" id="phone">abc </label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-3"></div>
                    <label class="control-label col-sm-4">Date of Birth: </label>
                    <div class="col-sm-4 div-info">
                        <label class="view-label prevent-break-line" id="dob">abc </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10" id="div-delete-friend">
                        <a href="#" class="btn btn-danger" id="delete-friend">Delete</a>
                    </div>
                </div>
            </div>
        </div> <!--message_section-->
    </div>
</div>
</body>
</html>
