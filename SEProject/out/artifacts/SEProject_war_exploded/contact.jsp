<!doctype html>
<html>
<head>
    <link rel="icon" href="image/HomepageIcon.jpg">
    <title>Contact</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link data-require="bootstrap-css@3.1.1" data-semver="3.1.1" rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"/>
    <script data-require="bootstrap@*" data-semver="3.1.1"
            src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="./stylesheets/css/sidebar.css">
    <link rel="stylesheet" href="./stylesheets/css/Contact.css">
    <script src="stylesheets/js/sidebar.js"></script>
    <script src="stylesheets/js/contact.js"></script>
</head>
<body>
<div class="wrapper">
    <%@include file="sidebar.jsp" %>
    <div id="content">
        <div class="col-sm-4" id="left">
            <div class="row" id="left-row">
                <div id="custom-search-input">
                    <div class="input-group col-md-12">
                        <input type="text" class="  search-query form-control" placeholder="Search friends"
                               id="search_friend_name"/>
                        <button class="btn btn-danger" type="button" id="searchFriends">
                            <span class=" glyphicon glyphicon-search"></span>
                        </button>
                    </div>
                </div>
                <div class="member_list" id="member">
                    <div id="mem-list">

                        <ul class="list-unstyled" id="listFriend">
                        </ul>
                    </div>
                </div>
            </div>


        </div>
        <div class="col-sm-8 message_section">
            <div class="row" id="profile-div" hidden>
                <input type="text" hidden id="id">
                <img src="image/profile.png" class="center img-responsive" alt="profilepic" id="profile-picture">

                <div class="form-group">
                    <div class="col-sm-3"></div>
                    <label class="control-label col-sm-4 primary-font">Name: </label>
                    <div class="col-sm-4 div-info">
                        <label class="view-label prevent-break-line primary-font" style="font-weight: 500" id="nick-name">abc</label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-3"></div>
                    <label class="control-label col-sm-4 primary-font">Email: </label>
                    <div class="col-sm-4 div-info">
                        <label class="view-label prevent-break-line primary-font" style="font-weight: 500" id="email">abc </label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-3"></div>
                    <label class="control-label col-sm-4 primary-font">Phone Number: </label>
                    <div class="col-sm-4 div-info">
                        <label class="view-label prevent-break-line primary-font" style="font-weight: 500" id="phone">abc </label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-3"></div>
                    <label class="control-label col-sm-4 primary-font">Date of Birth: </label>
                    <div class="col-sm-4 div-info">
                        <label class="view-label prevent-break-line primary-font" style="font-weight: 500" id="dob">abc </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10" id="div-delete-friend">
                        <button class="btn btn-danger" id="delete-friend" data-record-id="0"
                                data-record-title="Delete Friend" data-toggle="modal" data-target="#confirm-delete">
                            Delete
                        </button>
                    </div>
                </div>
            </div>
        </div> <!--message_section-->
        <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                        <h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
                    </div>
                    <div class="modal-body">
                        <p>You are about to delete <b><i class="title"></i></b> record, this procedure is irreversible.
                        </p>
                        <p>Do you want to proceed?</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <button type="button" class="btn btn-danger btn-ok">Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false" hidden>
        <div class="modal-body">
            <div id="ajax_loader" style="margin-left: 5%; padding: 2% 19%;">
                <img src="image/loading.gif" style="display: block;margin-left: auto;
                 margin-right: auto;
                 max-height: 5%; max-width: 5%; padding-top: 30%;">
            </div>
        </div>
    </div>
</div>

</body>
</html>
