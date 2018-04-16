<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="stylesheets/css/profile.css">
    <script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script src="stylesheets/js/profile.js" type="text/javascript"></script>
</head>

<body>
<%
    if (session.getAttribute("userID") == null) {
        session.setAttribute("returnUrl", request.getAttribute("currentUrl"));
        response.sendRedirect("/SEProject_war_exploded/home");
    }
%>
<nav class="navbar navbar-inverse">
    <div class="container">

        <div clas="navbar-header">
            <a class="navbar-brand" href="#">
                <img src="image/HomepageIcon.jpg" class="img-responsive logo">
            </a>
        </div>

        <p class="navbar-text text" style="color:black;"> Chat Application </p>

    </div>
</nav>

<input type="text" hidden id="id" value=<%=request.getAttribute("id")%>>
<input type="text" hidden id="id" value=<%=session.getAttribute("userID")%>>
<img src="image/profile.png" class="center img-responsive" alt="profilepic" id="profile">

<div class="form-group">
    <div class="col-sm-4"></div>
    <div class="col-sm-2">
        <label class="control-label">Nick Name: </label>
    </div>
    <div class="col-sm-6">
        <label class="view-label" id="nick-name"> </label>
    </div>
</div>

<div class="form-group">
    <div class="col-sm-4"></div>
    <div class="col-sm-2">
        <label class="control-label">Email: </label>
    </div>
    <div class="col-sm-6">
        <label class="view-label" id="email"> </label>
    </div>
</div>

<div class="form-group">
    <div class="col-sm-4"></div>
    <div class="col-sm-2">
        <label class="control-label">Phone Number: </label>
    </div>
    <div class="col-sm-6">
        <label class="view-label" id="phone"> </label>
    </div>
</div>

<div class="form-group">
    <div class="col-sm-4"></div>
    <div class="col-sm-2">
        <label class="control-label">Date of Birth: </label>
    </div>
    <div class="col-sm-6">
        <label class="view-label" id="dob"> </label>
    </div>
</div>

<div class="form-group">
    <div class="">
        <a href="#" id="edit-profile" class="<%=session.getAttribute("userID")==request.getAttribute("id")? "btn btn-primary":"hide"%>">Edit</a>
    </div>
</div>
</body>
</html>
