<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<!-- Code By Webdevtrick ( https://webdevtrick.com )-->
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Sign-Up</title>
  <link href="https://fonts.googleapis.com/css?family=Manjari&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  <link rel="stylesheet" href="http://localhost:8081/applications/ConnectionApp/signup.css">
</head>
<body>

	<div id="container">
		<div class="form">
			<div class="tab-content">
			<form:form action= "signup-check" modelAttribute="userDto">
				<div id="signup">
				  <div class="top-row">
					<div class="label-field">
					  <label>
						Username<span class="req">*</span>
					  </label>
					  <form:input type="text" path="userName"/>
					</div>
					<div class="label-field">
					  <label>
						Password<span class="req">*</span>
					  </label>
					  <form:input path="password" type="password"/>
					</div>
				  </div><!--top-row-->
				  <div class="label-field">
					<label>
					  Age<span class="req">*</span>
					</label>
					<form:input type="text" path="age"/>
				  </div>
				  <div class="label-field">
					<label>
					  Gender<span class="req">*</span>
					</label>
					<form:input type="text" path="gender"/>
				  </div>
				<div class="label-field">
					<label>
					  Address<span class="req">*</span>
					</label>
					<form:input type="text" path="cityAddress"/>
				  </div>
				<div class="label-field">
					<label>
					  Favorite Film Category<span class="req">*</span>
					</label>
					<form:input type="text" path="filmCategoryFavourite"/>
				  </div>
				<div class="label-field">
					<label>
					  Favorite Music Category<span class="req">*</span>
					</label>
					<form:input type="text" path="musicCategoryFavourite"/>
				  </div>
				<div class="label-field">
					<label>
					  Favorite Game Category<span class="req">*</span>
					</label>
					<form:input type="text" path="gameCategoryFavourite"/>
				    </div>
				<div class="label-field">
					<label>
					  Favorite Sport Category<span class="req">*</span>
					</label>
					<form:input type="text" path="sportCategoryFavourite"/>
				</div>
				<div id= "footer">
					<div id= "submit"><input type= "submit" name= "Submit"></div>
					<div id= "back"><a href= "welcome">Back</a></div>
				</div><!--footer-->
				</div><!--signup-->
              </form:form>
			  </div><!--tab-content-->
		</div><!--form-->
	</div><!--container-->
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script  src="http://localhost:8081/applications/ConnectionApp/signup.js"></script>
</body>
</html>