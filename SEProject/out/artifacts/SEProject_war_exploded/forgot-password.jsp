<!doctype html>
<html>
<head>
    <link rel="icon" href="image/HomepageIcon.jpg">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Forgot password</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="stylesheets/css/forgot-password.css">
    <script src="stylesheets/js/forgot-password.js"></script>

</head>

<body>
<div class="container text-center">
    <h1> Chat Application </h1>
    <img src="image/HomepageIcon.jpg" class="img-rounded" alt="logo">
</div>

<div class="container text-center">
    <h1>Forgot Password</h1>
    <p>Please fill in this form to reset you password</p>
    <hr>
</div>
<div class="form-group">

    <div id="error" class="alert alert-danger" hidden>
    </div>
</div>

<div class="form-group">
    <div class="col-sm-4">
        <label class="control-label col-sm-offset-3">Email: </label>
    </div>
    <div class="col-sm-6">
        <input type="text" class="form-control" name="email" id="email" placeholder="Enter your email here">
        <%--<label class="control-label" id="email"> EMAIL</label>--%>
        <br>
        <%--<p>avc</p>--%>
    </div>
</div>

<div class="form-group">
    <div class="col-sm-4">
        <label class="control-label col-sm-offset-3">Confirm code: </label>
    </div>
    <div class="col-sm-6">
        <input type="text" class="form-control" placeholder="Enter the confirm code here" name="confirm_code" autofocus>
        <a href="#" id="send-confirm-code">Send the confirm code</a>
        <label id="sent-code" hidden>SENT</label>
        <br>
        <p> </p>
    </div>
</div>

<div class="form-group">
    <div class="col-sm-4">
        <label class="control-label col-sm-offset-3">Password: </label>
    </div>
    <div class="col-sm-6">
        <input type="password" class="form-control" placeholder="Enter your password here" name="password"
               autofocus>
        <br>
    </div>
</div>

<div class="form-group">
    <div class="col-sm-4">
        <label class="control-label col-sm-offset-3">Confirm Password: </label>
    </div>
    <div class="col-sm-6">
        <input type="password" class="form-control" placeholder="Enter your password again" name="confirm_password"
               autofocus> <br>
    </div>
</div>

<div class="form-group">
    <div class="col-sm-4"></div>
    <div class="col-sm-6">
        <button class="btn btn-primary btn-lg" id="reset-password"> Reset Password</button>
        <a href="/login" class="btn btn-danger btn-lg" id="cancel">Cancel</a>
    </div>
</div>

<div class="form-group">
    <div class="col-sm-7" style="text-align:right">
        Remember it?
        <a href="/login"> Sign In</a>
        <br> <br>
    </div>
</div>


</body>
</html>