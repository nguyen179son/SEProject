<!doctype html>
<html>
<head>
    <title>Friend requests</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link data-require="bootstrap-css@3.1.1" data-semver="3.1.1" rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"/>
    <script data-require="bootstrap@*" data-semver="3.1.1"
            src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="./stylesheets/css/sidebar.css">
    <link rel="stylesheet" href="./stylesheets/css/Request.css">
    <script src="stylesheets/js/Request.js"></script>
</head>
<body>
<div class="wrapper">
    <%@include file="sidebar.jsp" %>
    <div id="content">
        <div class="col-sm-4" id="left">
            <div class="row" id="left-row">
                <div id="custom-search-input">
                    <div class="input-group col-md-12">
                        <input type="text" class="  search-query form-control" placeholder="Search Users"
                               id="search_user_name"/>
                        <button class="btn btn-danger" type="button" id="searchUsers">
                            <span class=" glyphicon glyphicon-search"></span>
                        </button>
                    </div>
                </div>
                <div class="member_list" id="member" style="overflow-x: auto">

                    <ul class="list-unstyled" id="listFriend">
                    </ul>
                </div>
            </div>


        </div>
        <div class="col-sm-8 message_section">
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
            </div>
        </div> <!--message_section-->
        <div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
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
            <div id="ajax_loader">
                <img src="image/loading.gif" style="display: block;margin-left: auto;
                 margin-right: auto;
                 max-height: 5%; max-width: 5%; padding-top: 30%;">
            </div>
        </div>
    </div>
</div>
<div>


</div>
</body>
</html>
