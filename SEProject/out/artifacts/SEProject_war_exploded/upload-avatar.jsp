<%--
  Created by IntelliJ IDEA.
  User: chihiro
  Date: 08/06/2018
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload avatar</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <link data-require="bootstrap-css@3.1.1" data-semver="3.1.1" rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"/>
    <script data-require="bootstrap@*" data-semver="3.1.1"
            src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="./stylesheets/css/sidebar.css">
    <link rel="stylesheet" href="stylesheets/css/avatar.css">
    <script src="stylesheets/js/avatar.js"></script>
</head>
<body>
<div class="wrapper">
    <%@include file="sidebar.jsp" %>
    <div id="content" style="padding-top: 10%">
        <div class="container">
            <div class="col-md-6">
                <div class="form-group">
                    <label>Upload Image</label>
                    <div class="input-group">
            <span class="input-group-btn">
                <span class="btn btn-default btn-file">
                    Browse… <input type="file" id="imgInp">
                </span>
            </span>
                        <input type="text" class="form-control" readonly>
                    </div>
                    <img id='img-upload'/>
                    <button class="pull-right" id="upload">Upload</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
