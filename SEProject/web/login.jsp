<!doctype html>
<html>
<head>
    <link rel="icon" href="image/HomepageIcon.jpg">
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

    <input type="hidden" name="ReturnUrl" value='${requestScope["javax.servlet.forward.request_uri"]}'/>
    <input type="text" class="form-control" id="email" placeholder="User name here" name="email" required autofocus
    />
    <br>

    <input type="password" class="form-control" id="password" placeholder="Your password here" name="password"
           required
    /> <br>
    <input type="button" id="login_button" class="btn btn-primary" name="Login" value="Log in"/> <br>
    <div id ="message" hidden>
        Wrong email or password
    </div>
    <a href="/password" id="forgot-password" class="btn btn-warning" name="forget-password"> Forgot
        password?</a> <br>
    <label class="signup">
        <a href="/signup"> Sign up if you are a new user</a>
    </label>

</div>
<div class="modal" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false" hidden>
    <div class="modal-body">
        <div id="ajax_loader" style="margin-left: 5%; padding: 2% 19%;">
            <img src="image/loading.gif" style="display: block;margin-left: auto;
                 margin-right: auto;
                 max-height: 5%; max-width: 5%; padding-top: 30%;">
        </div>
    </div>
</div>
</body>
</html>