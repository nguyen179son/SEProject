<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Confirmation Code</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="stylesheets/css/confirmcode.css">
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
			
			<form class="navbar-form navbar-right">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="User Name">
					<input type="text" class="form-control" placeholder="Password">
				</div>
				<button type="submit" class="btn btn-danger">Sign In</button>
			</form> 
		
		</div>
		
	</nav>
	
	<div class="row">
		<p id="confirmcode">
			Enter your confirmation code....
		</p>
	</div>
	
	
	<div class="container-fluid text-center" style="padding-left: 43%">
		<input type="text" class="form-control" name="code" id="txtcode" placeholder="Enter code here">
	</div>
	
	<div class="container-fluid text-center">
		<button type="submit" class="btn btn-primary" name="btncode">Enter Code</button>
	</div>
	
	<div class="row">
		<p id="resend">
			If you dont get the confirmation code, click 
			<a href="#"> here</a> 
		</p>
	</div>
	
	
</body>
</html>
