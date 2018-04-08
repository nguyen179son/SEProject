<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Log in page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="stylesheets/css/login.css">
</head>

<body>
<div class="container-fluid text-center">
    <h1> Chat Application </h1>
    <img src="image/HomepageIcon.jpg" class="img-rounded" alt="logo">
</div>


<div class="container-fluid text-center">
    <form action="login" method="get">


        <input type="text" class="form-control" placeholder="User name here" name="user_name" required autofocus
               value="<%=request.getAttribute("user_name")!=null?request.getAttribute("user_name"):""%>"/>
        <br>

        <input type="password" class="form-control" placeholder="Your password here" name="password" required
               value="<%=request.getAttribute("password")!=null?request.getAttribute("password"):""%>"/> <br>
        <input type="submit" class="btn btn-primary" name="Login" value="Log in"/> <br>
    </form>
    <div id="message" class=<%= request.getAttribute("error_message") != null ? "show" : "hide"%>>
        Wrong email or password
    </div>
    <label class="signup">
        <a href="/SEProject_war_exploded/signup"> Sign up if you are a new user</a>
    </label>

</div>
</body>
</html>