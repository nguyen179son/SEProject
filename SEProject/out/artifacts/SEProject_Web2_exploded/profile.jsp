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
    <link rel="stylesheet" href="/stylesheets/css/sidebar.css">
</head>

<body>

<div class="wrapper">

    <%@include file="sidebar.jsp" %>
    <div id="content">
        <nav class="navbar">
            <div class="col-sm-5">

                <a class="" href="#">
                    <img src="image/HomepageIcon.jpg" class="img-responsive logo"></a>
            </div>
            <a href="#" id="header"> ICT CHAT</a>

            <%--<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">--%>
            <%--<ul class="nav navbar-nav navbar-right">--%>
            <%--<li><a href="#">Page</a></li>--%>
            <%--<li><a href="#">Page</a></li>--%>
            <%--<li><a href="#">Page</a></li>--%>
            <%--<li><a href="#">Page</a></li>--%>
            <%--</ul>--%>
            <%--</div>--%>
        </nav>
        <input type="text" hidden id="id" value=<%=request.getAttribute("id")%>>
        <img src="image/profile.png" class="center img-responsive" alt="profilepic" id="profile">

        <div class="form-group">
            <div class="col-sm-2"></div>
            <label class="control-label col-sm-4">Nick Name: </label>
            <div class="col-sm-6">
                <label class="view-label" id="nick-name"> </label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2"></div>
            <label class="control-label col-sm-4">Email: </label>
            <div class="col-sm-6">
                <label class="view-label" id="email"> </label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2"></div>
            <label class="control-label col-sm-4">Phone Number: </label>
            <div class="col-sm-6">
                <label class="view-label" id="phone"> </label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2"></div>
            <label class="control-label col-sm-4">Date of Birth: </label>
            <div class="col-sm-6">
                <label class="view-label" id="dob"> </label>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-10">
                <a href="<%="/editProfile?id="+request.getAttribute("id")%>" id="edit-profile"
                   class="<%=session.getAttribute("userID")==request.getAttribute("id")? "btn btn-primary":"hide"%>">Edit</a>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
