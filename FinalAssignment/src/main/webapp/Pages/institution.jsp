<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Institution</title>
<link rel="stylesheet" type="text/css"
	href="../Bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="../Bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="../Bootstrap/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css"
	href="../Bootstrap/css/bootstrap-theme.min.css">
</head>
<body style="background-color: antiquewhite;height: 500px">

	<h1 align="center">
		<b>Institution Information</b>
	</h1>

	<form action="Connector" method="post">
		<div class="form-group">
			<label for="Title">Title :</label> <input type="text" id="title"
				placeholder="title" name="title">
		</div>
		<div class="form-group">
			<label for="desc">Description Of the Institution :</label> <input
				type="text" id="desc" placeholder="Description" name="desr">
		</div>
		<div class="form-group">
			<label for="location">Location :</label> <input type="text" id="loc"
				placeholder="Location" name="loc">
		</div>
		<div class="form-group">
			<label for="branches">Branches :</label> <input type="text" id="loc"
				placeholder="Branches" name="branches">
		</div>
		<div class="form-group">
			<label for="fileName">Select Image: </label> <input id="fileName"
				type="file" name="fileName" /><br />
		</div>
		<button type="submit" class="btn btn-default">ADD</button>


	</form>
</body>
</html>
