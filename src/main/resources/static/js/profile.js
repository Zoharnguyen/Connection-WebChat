
		    window.onload = function beforeLoadingPage() {
                hideAllContentDivs();
                var profileStatus = "<c:out value = "${profile_status}"/>";
                console.log(profileStatus);
                var x = document.getElementById(profileStatus);
                x.style.display = "block";
                console.log('It is working!!!');
            }
			var divContents = ["content_home", "content_profile","content_profile","content_profile_edit","content_standard_create","list","edit_object","find_object","show_list","message"];
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
				var y = document.getElementById("list");
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