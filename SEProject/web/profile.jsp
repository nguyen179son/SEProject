<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link data-require="bootstrap-css@3.1.1" data-semver="3.1.1" rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"/>
    <script data-require="bootstrap@*" data-semver="3.1.1"
            src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="stylesheets/css/profile.css">
    <link rel="stylesheet" href="./stylesheets/css/sidebar.css">
    <link rel="stylesheet" href="./stylesheets/css/Contact.css">
    <script src="stylesheets/js/sidebar.js"></script>
    <script src="stylesheets/js/profile.js"></script>


</head>

<body>

<div class="wrapper">

    <%@include file="sidebar.jsp" %>
    <div id="content">
        <input type="text" hidden id="id">
        <img src="image/profile.png" class="center img-responsive center-profile-picture" alt="profilepic" id="profile-pic">

        <div class="form-group">
            <div class="col-sm-3" style="float:left"></div>
            <div class="col-sm-3" style="float:left">
                <label class="control-label">Name: </label>
            </div>
            <div class="col-sm-4 div-info">
                <label class="view-label prevent-break-line" id="nick-name">abc</label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-3" style="float:left"></div>
            <div class="col-sm-3" style="float:left">
                <label class="control-label">Email: </label>
            </div>
            <div class="col-sm-4 div-info">
                <label class="view-label prevent-break-line" id="email">abc </label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-3" style="float:left"></div>
            <div class="col-sm-3" style="float:left">
                <label class="control-label">Phone Number: </label>
            </div>
            <div class="col-sm-4 div-info">
                <label class="view-label prevent-break-line" id="phone">abc </label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-3" style="float:left"></div>
            <div class="col-sm-3" style="float:left">
                <label class="control-label">Date of Birth: </label>
            </div>
            <div class="col-sm-4 div-info">
                <label class="view-label prevent-break-line" id="dob">abc </label>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-10" id="div-delete-friend" style="padding: 5% 45%">
                <a href="#" class="btn btn-primary" id="edit-profile" data-record-id="0">
                    Edit
                </a>
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
</div>
</body>
</html>
