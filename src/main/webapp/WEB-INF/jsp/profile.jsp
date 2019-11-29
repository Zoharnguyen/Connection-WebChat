<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title>Profile</title>
		<link href= "/css/normalize.css" rel="stylesheet" type="text/css"/>
		<link href= "http://localhost:8081/applications/ConnectionApp/profile.css" rel="stylesheet" type="text/css"/>
		<link rel= "stylesheet" href= "https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css">
		<link rel="stylesheet" href="http://localhost:8081/applications/ConnectionApp/chat.css" />
		<style>
			#context {
				display: block;
			}
		</style>
	</head>
	<body>
		<div id= "container">
			<img src="http://localhost:8081/applications/ConnectionApp/home_header.jpg"/>
			<div id= "leftPage">
				<div id= "image">
					<img src="http://localhost:8081/applications/ConnectionApp/profile_image.jpg">
				</div><!--image-->
				<div id= "content">
					<ul>
						<li><a onclick= showContent("content_profile") href= "#user" class= "border_01">Profile</a></li><br>
						<li>
							<a onclick= hideSubMenu() class= "border_01">Mangage Objects</a>
							<div id= "sub_menu_1">
								<ul class= "sub_menu">
									<li><br><a onclick= showStandardCreate() class= "border_01">Create Object</a></li><br>
									<li><a onclick= showList() class= "border_01">List Objects</a></li>
								</ul>
							</div>
						</li>
						<li><br><a href= "#gender" onclick= showMessage() class= "border_01">Messages</a></li><br>
						<li><a href= "welcome" class= "border_01">Log Out</a></li><br>
					</ul>
				</div><!--content-->
			</div><!--leftPage-->
			<div id= "rightPage">
				<div id= "title">
					<ul>
						<li><a onclick= showContent("content_home") href= "#"  class= "border_01">Home</a></li>
					</ul>
				</div><!--title-->
				<div id= "content_home">
					<ul>
						<li>
							<img src= "http://localhost:8081/applications/ConnectionApp/home_page_01.jpg"/>
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus imperdiet, nulla et dictum interdum, nisi lorem egestas vitae scel<span id="dot1">...</span><span id="more1" class="more">erisque enim ligula venenatis dolor. Maecenas nisl est, ultrices nec congue eget, auctor vitae massa. Fusce luctus vestibulum augue ut aliquet. Nunc sagittis dictum nisi, sed ullamcorper ipsum dignissim ac. In at libero sed nunc venenatis imperdiet sed ornare turpis. Donec vitae dui eget tellus gravida venenatis. Integer fringilla congue eros non fermentum. Sed dapibus pulvinar nibh tempor porta.</span></p>
							<button onclick= readMoreAndLessFunction("myBtn1","more1","dot1") id="myBtn1">Read more</button>
						</li><br><br>
						<li>
							<img src= "http://localhost:8081/applications/ConnectionApp/profile_image.jpg"/>
							<p>Our word lists are designed to help learners at any level focus on the most important words to learn.<span id="dot2">...</span><span id="more2" class="more">Do you know your vegan from your freegan? Your omnivore from your locavore? Read</span></p>
							<button onclick= readMoreAndLessFunction("myBtn2","more2","dot2") id="myBtn2">Read more</button>
						</li>
					</ul>
				</div><!--content_home-->
				<div id= "content_profile">
					<h1><Strong>Personal Information</strong></h1><br>
					<strong>Username: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDto.userName}</p>
					<strong>Password: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ********</p>
					<strong>Age: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDto.age}</p>
					<strong>Gender: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDto.gender}</p>
					<strong>Address: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDto.cityAddress}</p>
					<strong>Favorite Film Category:</strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDto.filmCategoryFavourite}</p>
					<strong>Favorite Music Category: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDto.musicCategoryFavourite}</p>
					<strong>Favorite Game Category: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDto.gameCategoryFavourite}</p>
					<strong>Favorite Sport Category: </strong></strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDto.sportCategoryFavourite}</p>
					<button onclick= showContentProfileEdit() type="button">Edit</button>	
				</div><!--content_profile-->
				<form:form method = "GET" action= "profile_content_profile_edit" modelAttribute="userDto">
                    <div id= "content_profile_edit">
                            <h1><strong>Edit Information</strong></h1><br>
                        <form>
                            <p><strong>Username: </strong></p>
                            <form:input path= "userName" type="text" name="username"/>
                            <p><strong>Password: </strong></p>
                            <form:input path= "password" type="password" name="password"/>
                            <p><strong>Age: </strong></p>
                            <form:input path= "age" type="text" name="age"/>
                            <p><strong>Gender: </strong></p>
                            <form:input path= "gender" type="text" name="gender"/>
                            <p><strong>Address: </strong></p>
                            <form:input path= "cityAddress" type="text" name="address"/>
                            <p><strong>Favorite Film Category: </strong></p>
                            <form:input path= "filmCategoryFavourite" type="text" name="f_film_c"/>
                            <p><strong>Favorite Music Category: </strong></p>
                            <form:input path= "musicCategoryFavourite" type="text" name="f_music_c"/>
                            <p><strong>Favorite Game Category: </strong></p>
                            <form:input path= "gameCategoryFavourite" type="text" name="f_game_c"/>
                            <p><strong>Favorite Sport Category: </strong></p>
                            <form:input path= "sportCategoryFavourite" type="text" name="f_sport_c"/>
                        <input type="submit" name="Submit">
                        <button onclick= showContentProfile() type= "button">back</button>
                    </div><!--content_profile_edit-->
                </form:form>
                <form:form method = "GET" action= "profile_content_standard_create" modelAttribute="userDesireDto">
                    <div id = "content_standard_create">
                        <h1><strong>Create New Object</strong></h1><br><br>
                        <p><strong>Name</strong></p>
                        <form:input path= "nameObject" type="text" name="name_o"/>
                        <p><strong>Age: </strong></p>
                        <form:input path= "age" type="text" name="age"/>
                        <p><strong>Gender: </strong></p>
                        <form:select path= "gender">
                            <c:forEach items="${genderList.list}" var="genderVar">
                                <option >${genderVar}</option>
                            </c:forEach>
                        </form:select>
                        <p><strong>Address: </strong></p>
                        <form:select path= "cityAddress">
                            <c:forEach items="${cityAddressList.list}" var="cityAddressVar">
                                <option >${cityAddressVar}</option>
                            </c:forEach>
                        </form:select>
                        <p><strong>Favorite Film Category: </strong></p>
                        <form:select path= "filmCategoryFavourite">
                            <c:forEach items="${filmCategoryFavouriteList.list}" var="filmCategoryFavouriteVar">
                                <option >${filmCategoryFavouriteVar}</option>
                            </c:forEach>
                        </form:select>
                        <p><strong>Favorite Music Category: </strong></p>
                        <form:select path= "musicCategoryFavourite">
                            <c:forEach items="${musicCategoryFavouriteList.list}" var="musicCategoryFavouriteVar">
                                <option >${musicCategoryFavouriteVar}</option>
                            </c:forEach>
                        </form:select>
                        <p><strong>Favorite Game Category: </strong></p>
                        <form:select path= "gameCategoryFavourite">
                            <c:forEach items="${gameCategoryFavouriteList.list}" var="gameCategoryFavouriteVar">
                                <option >${gameCategoryFavouriteVar}</option>
                            </c:forEach>
                        </form:select>
                        <p><strong>Favorite Sport Category: </strong></p>
                        <form:select path= "sportCategoryFavourite">
                            <c:forEach items="${sportCategoryFavouriteList.list}" var="sportCategoryFavouriteVar">
                                <option >${sportCategoryFavouriteVar}</option>
                            </c:forEach>
                        </form:select>
                        <br><br><input type="submit" name="Submit">
                    </div><!--content_standard_create-->
				</form:form>
				<div id="list_objects">
					<ul id="list">
						<li>
							<h1><Strong>Information about your expectation</strong></h1><br>
							<strong>Name: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDesireDtoShow.nameObject}</p>
							<strong>Age: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDesireDtoShow.age}</p>
							<strong>Gender: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDesireDtoShow.gender}</p>
							<strong>Address: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDesireDtoShow.cityAddress}</p>
							<strong>Favorite Film Category</strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDesireDtoShow.filmCategoryFavourite}</p>
							<strong>Favorite Music Category: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDesireDtoShow.musicCategoryFavourite}</p>
							<strong>Favorite Game Category: </strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDesireDtoShow.gameCategoryFavourite}</p>
							<strong>Favorite Sport Category: </strong></strong><p class= "border">&nbsp&nbsp&nbsp&nbsp ${userDesireDtoShow.sportCategoryFavourite}</p>
							<button onclick= showList() type="button">Back</button>	
							<button onclick= showEditObject() type="button">Edit</button>
							<form:form method = "GET" action = "show_suitableness_profiles">
							    <button type="submit">Searching</button>
							</form:form>
						<li>
					</ul>
					<div id="edit_object">
							<h1><strong>Edit Information about your expected object</strong></h1>
							<p><strong>Username: </strong></p>
							<input type="text" name="username">
							<p><strong>Password: </strong></p>	
							<input type="text" name="password">												
							<p><strong>Age: </strong></p>										
							<input type="text" name="age">												
							<p><strong>Gender: </strong></p>					
							<input type="text" name="gender">												
							<p><strong>Address: </strong></p>
							<input type="text" name="address">												
							<p><strong>Major: </strong></p>
							<input type="text" name="major">												
							<p><strong>Favorite Film Category: </strong></p>
							<input type="text" name="f_film_c">												
							<p><strong>Favorite Music Category: </strong></p>
							<input type="text" name="f_music_c">												
							<p><strong>Favorite Game Category: </strong></p>
							<input type="text" name="f_game_c">												
							<p><strong>Favorite Sport Category: </strong></p>	
							<input type="text" name="f_sport_c"><br>
							<button onclick= showContentListObjects() type="button">Back</button>	
							<button type="button">Save</button>								
					</div><!--edit_object-->
					<div id="find_object">
                            <ul>
                                <c:forEach items="${userProfileSuitablenessList}" var="userProfileSuitablenessListVar">
                                    <img src= "http://localhost:8081/applications/ConnectionApp/profile_image.jpg"/>
                                    <p> A little information about user here. </p>
                                    <p><strong>Name: </strong>&nbsp&nbsp&nbsp&nbsp ${userProfileSuitablenessListVar.fullName}<p>
                                    <p><strong>Gender: </strong>&nbsp&nbsp&nbsp&nbsp ${userProfileSuitablenessListVar.gender}<p>
                                    <p><strong>Age: </strong>&nbsp&nbsp&nbsp&nbsp ${userProfileSuitablenessListVar.age}<p>
                                    <p><strong>Point: </strong>&nbsp&nbsp&nbsp&nbsp ${userProfileSuitablenessListVar.point}<p>
                                    <div>
                                        <div class="username-page-container">
                                            <form name="usernameForm" onsubmit= "showChat()">
                                                <div class="form-group">
                                                    <input type="hidden" id="name" value="${userProfileSuitablenessListVar.fullName}"/>
                                                </div>
                                                <div class="form-group">
                                                    <button type="submit" class="accent username-submit">Start Chatting</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div><!--username-page-->
                                </c:forEach>
                                <li>
                                    <img src= "http://localhost:8081/applications/ConnectionApp/profile_image.jpg"/>
                                    <p> A little information about user here. </p>
                                    <div id="username-page" >
                                        <div class="username-page-container">
                                            <form id="usernameForm" name="usernameForm" onsubmit= "showChat()">
                                                <div class="form-group">
                                                    <input type="hidden" id="name" value="${userDto.userName}"/>
                                                </div>
                                                <div class="form-group">
                                                    <button type="submit" class="accent username-submit">Start Chatting</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div><!--username-page-->
                                </li>
                                <li>
                                    <img src= "http://localhost:8081/applications/ConnectionApp/profile_image.jpg"/>
                                    <p> A little information about user here. </p>
                                    <button type="button">Map</button><br>
                                </li>
                                <li>
                                    <img src= "http://localhost:8081/applications/ConnectionApp/profile_image.jpg"/>
                                    <p> A little information about user here. </p>
                                    <button type="button">Map</button><br>
                                </li>
                                <li>
                                    <img src= "http://localhost:8081/applications/ConnectionApp/profile_image.jpg"/>
                                    <p> A little information about user here. </p>
                                    <button type="button">Map</button>
                                </li>
                            </ul>
						<br><br><button onclick= showContentListObjects() type="button">Back</button>
					</div><!--find_object-->
					<div id= "show_list">
						<h1><strong>List your objects</h1></strong><br><br>
                        <c:forEach items="${objectList.list}" var="objectListVar">
                            <form:form method = "GET" action= "list" modelAttribute= "nameObjectUser">
                                <img src= "http://localhost:8081/applications/ConnectionApp/profile_image.jpg"/>
                                <p><strong>Name</strong>: ${objectListVar}</p>
                                <button type="submit" value= "Submit">Detail</button><br>
                                <form:input type= "hidden" path="nameObject" value= "${objectListVar}"/>
                            </form:form>
                        </c:forEach>
					</div><!--show_list-->
				</div><!--list_objects-->
				<div id= "message">
					<h1><strong>List your messages</h1></strong><br><br>
						<ul>
							<li>
								<img src= "http://localhost:8081/applications/ConnectionApp/profile_image.jpg"/>
								<p><strong>Zohar: </strong>What do you think?</p>
								<button type="button">Open</button><br>
							</li>
							<li>
								<img src= "http://localhost:8081/applications/ConnectionApp/profile_image.jpg"/>
								<p><strong>David: </strong>I don't think that it is too important</p>
								<button type="button">Open</button><br>
							</li>
							<li>
								<img src= "http://localhost:8081/applications/ConnectionApp/profile_image.jpg"/>
								<p><strong>Susan: </strong>Are you serious?</p>
								<button type="button">Open</button><br>
							</li>
							<li>
								<img src= "http://localhost:8081/applications/ConnectionApp/profile_image.jpg"/>
								<p><strong>Jack: </strong>I don't really know what does that mean?</p>
								<button type="button">Open</button><br>
							</li>
						</ul>
				</div><!--message-->
                <div id="chat-page" >
                    <div class="chat-container">
                        <ul id="messageArea">
                        </ul>
                        <form id="messageForm" name="messageForm" nameForm="messageForm">
                            <div class="form-group">
                                <div class="input-group clearfix">
                                    <input type="text" placeholder="Type a message..." autocomplete="off" class="form-control" id="message_01" />
                                    <button type="submit" class="primary">Send</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div><!-- chat-page -->
			</div><!-- right_page -->
		</div><!--container-->
 <c:set var="profile_status" scope="session" value="${profileStatus}"/>
		<script language="javascript">
		    window.onload = function beforeLoadingPage() {
                hideAllContentDivs();
                var profileStatus = "<c:out value = "${profile_status}"/>";
                console.log(profileStatus);
                var x = document.getElementById(profileStatus);
                x.style.display = "block";
                console.log('It is working!!!');
            }
			var divContents = ["content_home", "content_profile","content_profile","content_profile_edit","content_standard_create","list","edit_object","find_object","show_list","message","chat-page","username-page"];
			function showChat() {
			    hideAllContentDivs();
			    var x = document.getElementById("chat-page");
			    x.style.display = "block";
			}
			function myFunction() {
				var x = document.getElementById("context");
				if (x.style.display === "none") {
					x.style.display = "block";
					console.log("Here");
				} else {
					x.style.display = "none";
				}
			}

			function hideSubMenu() {
				var x = document.getElementById("sub_menu_1");
				if (x.style.display === "none") {
					x.style.display = "block";
				} else {
					x.style.display = "none";
				}
			}

			function showContentProfileEdit() {
				hideAllContentDivs()
				var y = document.getElementById("content_profile");
				y.style.display = "none";
				console.log("here")
				var x = document.getElementById("content_profile_edit");
				x.style.display = "block";
			}

			function showContentProfile() {
				hideAllContentDivs()
				var y = document.getElementById("content_profile_edit");
				y.style.display = "none";
				console.log("here")
				var x = document.getElementById("content_profile");
				x.style.display = "block";
			}

			function showContent(elementId) {
				for(var i=0; i<divContents.length; i++){
					if(divContents[i] == elementId) {
						var x = document.getElementById(elementId);
						x.style.display = "block";
						console.log(divContents[i]);
					} else {
						var x = document.getElementById(divContents[i]);
						x.style.display = "none";
					}
				}
			}

			function hideAllContentDivs() {
				for(var i=0; i<divContents.length; i++){
					var x = document.getElementById(divContents[i]);
					x.style.display = "none";
				}
			}

			function showStandardCreate() {
				hideAllContentDivs();
				var x = document.getElementById("content_standard_create");
				x.style.display = "block";
			}

			function addField() {
				var wrapper = $("#content_standard_create");
				$(wrapper).append('<br><div><select name="standard"><option value="example_1">Address</option><option value="example_2">Favorite Film</option><option value="example_3">Gender</option><option value="example_4">Age</option></select><input type="text" name="value"><a href="#" class="delete">Delete</a><br></div>');
				$(wrapper).on("click", ".delete", function(e) {
				e.preventDefault();
				$(this).parent('div').remove();
				})
			}
			function readMoreAndLessFunction(contentID, moreID, dotID) {
				var dots = document.getElementById(dotID);
				var moreText = document.getElementById(moreID);
				var btnText = document.getElementById(contentID);

				if (dots.style.display === "none") {
					dots.style.display = "inline";
					btnText.innerHTML = "Read more";
					moreText.style.display = "none";
				} else {
					dots.style.display = "none";
					btnText.innerHTML = "Read less";
					moreText.style.display = "inline";
				}
			}

			function showContentListObjects() {
				hideAllContentDivs()
				var y = document.getElementById("show_list");
				y.style.display = "block";
			}

			function showEditObject() {
				hideAllContentDivs()
				var y = document.getElementById("edit_object");
				y.style.display = "block";
			}

			function showFindObject() {
				hideAllContentDivs()
				var y = document.getElementById("find_object");
				y.style.display = "block";
				var x = document.getElementById("username-page");
				x.style.display = "block";
			}

			function showList() {
				hideAllContentDivs();
				var y = document.getElementById("show_list");
				y.style.display = "block";
			}

			function showMessage() {
				hideAllContentDivs();
				var y = document.getElementById("message");
				y.style.display = "block";
			}

		</script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
        <script src="http://localhost:8081/applications/ConnectionApp/chat.js"></script>
	</body>

</html>