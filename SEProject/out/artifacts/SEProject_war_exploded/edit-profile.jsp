<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="stylesheets/css/edit-profile.css">
    <link rel="stylesheet" href="stylesheets/css/datetimepicker/bootstrap-datetimepicker.min.css">
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="stylesheets/js/edit-profile.js" type="text/javascript"></script>
    <script src="stylesheets/js/datetimepicker/moment-with-locales.min.js"></script>
    <script src="stylesheets/js/datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="stylesheets/css/sidebar.css">
</head>

<body>
<%
    if (session.getAttribute("userID") == null) {
        session.setAttribute("returnUrl", request.getAttribute("currentUrl"));
        response.sendRedirect("/SEProject_war_exploded/home");
    }

    if (session.getAttribute("userID") != request.getAttribute("id")) {
        response.sendRedirect("/SEProject_war_exploded/error");
    }

%>
<div class="wrapper">

    <%@include file="sidebar.jsp" %>
    <div id="content">

        <form action="editProfile" method="post">

            <input type="text" hidden id="id" value=<%=request.getAttribute("id")%>>
            <img src="image/profile.png" class="center img-responsive" alt="profilepic" id="profile">


            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-2">Update Avatar</label>
                <div class="col-sm-6">
                    <input type="file" class="form-control" name="avatar">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-2">Nick Name: </label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" placeholder="Nick name" name="nick_name" id="nick-name">
                </div>
            </div>
            <br>
            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-2">Email: </label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" placeholder="email" name="email" id="email">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-2">Old Password: </label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" placeholder="Old password" name="oldPassword"
                           id="oldPassword">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-2">New Password: </label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" placeholder="New password" name="newPassword"
                           id="newPassword">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-2"></div>
                <label class="control-label col-sm-2">Confirm New Password: </label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" placeholder="Confirm New password"
                           name="confirmNewPassword" id="confirmNewPassword">
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
                        <input type='text' class="form-control" id="datepicker" placeholder="DoB" name="DoB"/>
                        <span class="input-group-addon"><span
                                class="glyphicon glyphicon-calendar"></span></span>
                    </div>

                </div>

            </div>
            <br>
            <div class="form-group">
                <div class="col-sm-4"></div>
                <div class="col-sm-2">
                    <input type="submit" id="edit-profile" value="Save" class="btn btn-primary padding-top-10">
                    <%--<a href="#" id="edit-profile" class="btn btn-primary">Save</a>--%>
                </div>
                <div class="col-sm-2">
                    <a href="" class="btn btn-danger padding-top-10" id="cancel">cancel</a>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
