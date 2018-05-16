<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Log in page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="stylesheets/js/login.js"></script>
    <link rel="stylesheet" type="text/css" href="stylesheets/css/login.css">
</head>

<body>
<div class="container-fluid text-center">
    <h1> Chat Application </h1>
    <img src="image/HomepageIcon.jpg" class="img-rounded" alt="logo">
</div>


<div class="container-fluid text-center">
    <form action="home" method="post">

        <input type="hidden" name="ReturnUrl" value='${requestScope["javax.servlet.forward.request_uri"]}' />
        <input type="text" class="form-control" id="email" placeholder="User name here" name="email" required autofocus
               />
        <br>

        <input type="password" class="form-control" id="password" placeholder="Your password here" name="password" required
        /> <br>
        <input type="button" id="login_button" class="btn btn-primary" name="Login" value="Log in"/> <br>
    </form>
    <div id="message" hidden>
        Wrong email or password
    </div>
    <a href="/get-new-password" id="forgot-password" class="btn btn-light" name="forget-password"> Forgot password?</a> <br>
    <label class="signup">
        <a href="/signup"> Sign up if you are a new user</a>
    </label>

</div>
</body>
</html>