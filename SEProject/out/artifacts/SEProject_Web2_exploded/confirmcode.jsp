<!doctype html>
<html lang="en">
<head>
	<link rel="icon" href="image/HomepageIcon.jpg">
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Confirmation Code</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="stylesheets/css/confirmcode.css">
	<script src="stylesheets/js/confirmcode.js"></script>
</head>

<body>
<nav class="navbar navbar-inverse">
	<div class="container">

		<div clas="navbar-header">
			<a class="navbar-brand" href="#">
				<img src="image/HomepageIcon.jpg" class="img-responsive logo">
			</a>
		</div>
		<p class="navbar-text text" style="color: black;"> Chat Application </p>

	</div>

</nav>

<div class="row">
	<p id="confirmcode">
		Enter your confirmation code
	</p>
</div>

<input type="text" id="email" name="email" value="<%=request.getAttribute("email")%>" hidden>
<div class="container-fluid col-sm-3" style="float: none">

	<input type="text" class="form-control center" name="confirmation_code" id="confirmation_code" placeholder="Enter code here">

</div>

<div class="container-fluid text-center">
	<button type="submit" id="confirm_button" class="btn btn-primary" name="btncode">Enter Code</button>
</div>
<div id="error" class="alert alert-danger" hidden>

</div>
<div class="row">
	<p id="resend">
		If you dont get the confirmation code, click
		<a href="#"> here</a>
	</p>
</div>


</body>
</html>
