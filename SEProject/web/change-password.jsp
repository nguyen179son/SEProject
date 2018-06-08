<%--
  Created by IntelliJ IDEA.
  User: chihiro
  Date: 08/06/2018
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link data-require="bootstrap-css@3.1.1" data-semver="3.1.1" rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"/>
    <script data-require="bootstrap@*" data-semver="3.1.1"
            src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="./stylesheets/css/sidebar.css">
    <link rel="stylesheet" href="./stylesheets/css/change-password.css">
    <script src="stylesheets/js/change-password.js"></script>
</head>
<body>
<div class="wrapper">
    <%@include file="sidebar.jsp" %>
    <div id="content" style="padding-top: 10%">

        <div class="form-group">

            <div id="error" class="alert alert-danger" hidden>
            </div>
            <div id='alert' class='alert alert-info' hidden>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-4">
                <label class="control-label col-sm-offset-3">Old password: </label>
            </div>
            <div class="col-sm-6">
                <input type="password" class="form-control" name="old_password" id="old-password" placeholder="Enter your old password here">
                <br>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-4">
                <label class="control-label col-sm-offset-3">New password: </label>
            </div>
            <div class="col-sm-6">
                <input type="password" class="form-control" placeholder="Enter your new password here" name="password" id="new-password"
                       autofocus>
                <br>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-4">
                <label class="control-label col-sm-offset-3">Confirm new password: </label>
            </div>
            <div class="col-sm-6">
                <input type="password" class="form-control" placeholder="Enter your new password again"
                       name="confirm_password" id="confirm-new-password"
                       autofocus> <br>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-5"></div>
            <div class="col-sm-6">
                <button class="btn btn-primary btn-lg" id="update-password">Update Password</button>
            </div>
        </div>


    </div>
</div>


</body>
</html>
