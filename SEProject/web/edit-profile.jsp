<!doctype html>
<html lang="en">
<head>
    <link rel="icon" href="image/favico.jpg">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <link rel="stylesheet" href="stylesheets/css/edit-profile.css">
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
    <script src="stylesheets/js/edit-profile.js"></script>
    <script src="stylesheets/js/datetimepicker/moment-with-locales.min.js"></script>
    <script src="stylesheets/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="stylesheets/css/sidebar.css">
</head>

<body>

<div class="wrapper">

    <%@include file="sidebar.jsp" %>
    <div id="content">

        <form action="editProfile" method="post">

            <input type="text" hidden id="id" value=<%=request.getAttribute("id")%>>
            <img src="image/profile.png" class="center img-responsive" alt="profilepic" id="profile">

            <div id="error" class="alert alert-danger" hidden></div>

            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-2">Update Avatar</label>
                <div class="col-sm-6 pull-right">
                    <a href="/upload-profile-picture" class="btn btn-primary">Change avatar</a>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-2">Nick Name: </label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" placeholder="Nick name" name="nick_name" id="nick-name">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-2">Phone Number: </label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" placeholder="phone" name="phone" id="phone">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-2">Date of Birth: </label>
                <div class="col-sm-6">
                    <div class='input-group date' id='birthday' name="birthday">
                        <input type="text" class="form-control datepicker" id="datepicker" placeholder="DoB"
                               name="DoB"/>
                        <span class="input-group-addon"><span
                                class="glyphicon glyphicon-calendar"></span></span>
                    </div>

                </div>

            </div>
            <br>
            <br>
            <br/>
            <div class="form-group">
                <div class="col-sm-4"></div>
                <div class="col-sm-2 padding-top-10">
                    <a href="#" id="edit-profile" class="btn btn-primary">Save</a>
                </div>
                <div class="col-sm-2 padding-top-10">
                    <a href="/get-my-profile" class="btn btn-danger" id="cancel">cancel</a>
                </div>
            </div>
        </form>
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
