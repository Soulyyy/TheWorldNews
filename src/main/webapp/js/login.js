$(this).ready(function() {
	function createCookie(name,value,days) {
		if (days) {
			var date = new Date();
			date.setTime(date.getTime()+(days*24*60*60*1000));
			var expires = "; expires="+date.toGMTString();
		}
		else var expires = "";
		document.cookie = name+"="+value+expires;
	}
		
	var loadpage = function(dest)  {

		var id=3;
		if (dest != 'index') {
			$.ajax({
				type:"GET",
				url:'./html/'+ dest +".html",
				data:{"id":id},
				
				crossDomain:true,
				success: function(data) {
					window.location.hash = dest;
					var externalHTML = document.getElementById("articleGroup");

					externalHTML.innerHTML=data;
				}

		});    
		}
		else {
			window.location.href = "Index.jsp";
	 
		}
	};
	function createCookie(name,value,days) {
		if (days) {
			var date = new Date();
			date.setTime(date.getTime()+(days*24*60*60*1000));
			var expires = "; expires="+date.toGMTString();
		}
		else var expires = "";
		document.cookie = name+"="+value+expires;
	}

	
	$('#loginbutton').click(function() {
		var hash = window.location.hash;
		if (hash) {
			hash = hash.substr(1);
		}
		if (hash == "") {
			hash = "index";
		}
		var userdata = new Object();
		userdata.userName = $("#userName").val();
		userdata.password = $("#password").val();
		var u = userdata.userName;

		if (!userdata.userName || !userdata.password  ) {
			alert("Fill all forms.");
		}
		else {
			$.ajax("/accountLogin",{
				type:"POST",
				dataType:'json',
				data: JSON.stringify(userdata),
				contentType: 'application/json',

				success: function(userdata){   
					// console.log(userdata.response);
					if (userdata.response ==-1) {
						alert("Vale parool/user.");
					}
					else {
						createCookie("sessionid",userdata.response,7);
						createCookie("currentuser",u,7);
						// loadpage(hash);
						// location.reload();
						window.location.href = "Index.jsp";
	 
		 
						
					}
					 
				},
				error:function(req, text) {
					console.log("login failed");
				}

			});
		}


		 
	});
	$('#authorize-button').click(function() {
		var authorizeButton = document.getElementById('authorize-button');
		var logoutButton = document.getElementById('logoutButton');
		var logint = document.getElementById('toggleLogin');
		var container = document.getElementById('loginContainer');
		var reg = document.getElementById('regi');
		var apiKey = 'AIzaSyD7HJs0zDCJKqLcLIK5ok5uAAm33cubOGs';
		var clientId = '510213468349-6npga48p58v7rr3s50p0smnp7e6dho5m.apps.googleusercontent.com';
		var scopes = 'https://www.googleapis.com/auth/plus.me';
		
		
		checkAuth();

		function checkAuth() {
			gapi.client.setApiKey(apiKey);
			gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: true}, handleAuthResult);
		}

		function handleAuthResult(authResult) {
 
			if (authResult && !authResult.error) {
				authorizeButton.style.visibility = 'hidden';
				regi.style.visibility = 'hidden';
				logoutButton.style.visibility = 'visible';
				logint.style.visibility = 'hidden';
				container.style.visibility = 'hidden';
				createCookie("googlein","y",7);
				makeApiCall();
				
			  
			} else {
			
			  authorizeButton.onclick = handleAuthClick;
			}
		}

		function handleAuthClick(event) {
			gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: false}, handleAuthResult);
			return false;
		}

		function makeApiCall() {
			gapi.client.load('plus', 'v1', function() {
			  var request = gapi.client.plus.people.get({
				'userId': 'me'
			  });
			  request.execute(function(resp) {
				user = resp;
				console.log(user);

			  });
			});
		}

 
	
	
	
	
	});

	
});