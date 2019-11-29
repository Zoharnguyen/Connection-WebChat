<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
  <head>
    <title>Login</title>
    <link href= "/css/login.css" rel="stylesheet" type="text/css"/>
	<link href="https://fonts.googleapis.com/css?family=Manjari&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
  </head>
  <body>
    <div id= "container">      
      <div id= "formlogin">
        <div id= "header">
          <h2>Form Login</h2>
        </div><!--header-->
            <form:form method = "GET" action= "profile" modelAttribute="userDto">
              <div id= "body">
			    <div id= "username">UserName: <form:input path= "userName" name= "userName"/><br><br></div>
			    <div id= "password">Password: <form:input path= "password" name= "password" type= "password"/></div>
              </div><!--body-->
              <div id= "footer">
                  <div id= "submit"><input type="submit" name="Submit"></div>
                  <div id= "back"><a href= "welcome">Back</a></div>
              </div><!--footer-->
		  </form:form>
      </div><!--formlogin-->
      <img src="http://localhost:8081/applications/ConnectionApp/welcome.jpg"/>
    </div><!--container-->
  </body>
</html>