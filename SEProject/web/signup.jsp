<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign up Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="stylesheets/css/signup.css">

</head>

<body>
<form method="post" action="/SEProject_war_exploded/signup">
    <div class="container text-center">
        <h1> Chat Application </h1>
        <img src="image/HomepageIcon.jpg" class="img-rounded" alt="logo">
    </div>

    <div class="container text-center">
        <h1>Sign Up</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>
    </div>
    <div class="form-group">

        <div id="error" class=<%= request.getAttribute("error_message") != null ? "show" : "hide"%>
        >
            <span id="123">
                <%=request.getAttribute("error_message")%>
            </span>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-4">
            <label class="control-label col-sm-offset-3">Name: </label>
        </div>
        <div class="col-sm-6">
            <input type="text" class="form-control" placeholder="Enter your name here" name="name" autofocus value="<%=
                request.getAttribute("nickname")!=null?request.getAttribute("nickname"):""
            %>"> <br>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-4">
            <label class="control-label col-sm-offset-3">Email: </label>
        </div>
        <div class="col-sm-6">
            <input type="text" class="form-control" placeholder="Enter your email here" name="email" autofocus value="<%=
                request.getAttribute("email")!=null?request.getAttribute("email"):""
            %>"> <br>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-4">
            <label class="control-label col-sm-offset-3">Password: </label>
        </div>
        <div class="col-sm-6">
            <input type="password" class="form-control" placeholder="Enter your password here" name="password" autofocus
                   value="<%=
                request.getAttribute("password")!=null?request.getAttribute("password"):""
            %>">
            <br>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-4">
            <label class="control-label col-sm-offset-3">Confirm Password: </label>
        </div>
        <div class="col-sm-6">
            <input type="password" class="form-control" placeholder="Enter your password again" name="confirmpassword"
                   autofocus value="<%=
                request.getAttribute("confirm_password")!=null?request.getAttribute("confirm_password"):""
            %>"> <br>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-4"></div>
        <div class="col-sm-6">
            <input type="submit" class="btn btn-primary btn-lg"></input>
            <a href="/SEProject_war_exploded/home" class="btn btn-danger btn-lg">Cancel</a>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-7" style="text-align:right">
            Already Registered?
            <a href="/SEProject_war_exploded/home"> Sign In</a>
            <br> <br>
        </div>
    </div>

</form>

</body>
</html>
