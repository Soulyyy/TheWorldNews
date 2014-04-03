/**
 * Created by kasutaja on 28.02.14.
 */

$(this).ready(function(){
	setInterval(checkHash, 100);
	if (location.pathname != "/index.html") {
		window.location.href = "index.html";
	}
 
    $('a[menuItem]').click( function() {
		var destination = $(this).attr('menuItem');
		loadpage(destination);
	});
	$('#logoutButton').click(function() {
	 
		$.ajax({
			type:"GET",
			success:function() {
				myIFrame.location='https://www.google.com/accounts/Logout';
				var logoutButton = document.getElementById('logoutButton');
				logoutButton.style.visibility = 'hidden';
				var logint = document.getElementById('toggleLogin');
				logint.style.visibility = 'visible';
	 
				
				
	 
			}
		});
	});
	$('#toggleLogin').click(function() {
		var authorizeButton = document.getElementById('authorize-button');
		var container = document.getElementById('loginContainer');
		var reg = document.getElementById('regi');
		if (authorizeButton.style.visibility == 'visible') {
		
			authorizeButton.style.visibility = 'hidden';
			regi.style.visibility = 'hidden';
			container.style.visibility = 'hidden';
 
		}
		else {
			authorizeButton.style.visibility = 'visible';
			regi.style.visibility = 'visible';
			container.style.visibility = 'visible';
			
		}
	
	});
	$('#regi').click( function() {
		var destination = 'registerUser';
		var id=3;
		var authorizeButton = document.getElementById('authorize-button');
		var container = document.getElementById('loginContainer');
		var reg = document.getElementById('regi');
		authorizeButton.style.visibility = 'hidden';
		regi.style.visibility = 'hidden';
		container.style.visibility = 'hidden';
		$.ajax({
			type:"GET",
			url:'./html/'+ destination +".html",
			data:{"id":id},
			
			crossDomain:true,
			success: function(data) {
				window.location.hash = destination;
				var externalHTML = document.getElementById("articleGroup");

				externalHTML.innerHTML=data;
				$('#reg').click(function() {
					var userdata = new Object();
					userdata.userName = $("#username").val();
					userdata.password = $("#pw").val();
					var pw2 = $("#pw2").val();  
					userdata.firstname = $("#first").val();
					userdata.surname = $("#last").val();
					userdata.email = $("#email").val();
			 
					if (!userdata.userName || !userdata.password || !pw2 || !userdata.firstname || !userdata.surname  || !userdata.email) {
						alert("T2ida k6ik vormid.");
					}
					else {
						if ( userdata.password != pw2 ) {
							alert("Paroolid peavad olema samad.");
						}
						else {
							if (pw2.length <1){
								alert("Parooli pikkus peab olema v2hemalt 5.");
							 
							}
							else {
							console.log(JSON.stringify(userdata));
							$.ajax("/accountSignup",{
							  
								type:"POST",
								dataType:'json',  
								data: JSON.stringify(userdata),
								contentType: 'application/json',

								success: function(userdata){   
								
									console.log("gg");
									window.location.href = "../index.html";
									 
								},
								error:function(req, text) {
									console.log(req);
									console.log(text);
								}
							   
							});
							}
						}
					}
					 
				});
			}

		});    
	 
	});
});
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
		window.location.href = "index.html";
 
	}
};

var recentHash = "";

var checkHash = function() {
	var hash = window.location.hash;

	if (hash) {
		hash = hash.substr(1);
    if (hash == recentHash) {
		return;
    }
    recentHash = hash;

	loadpage(hash);
  }
};


function handleClientLoad() {
	var apiKey = 'AIzaSyD7HJs0zDCJKqLcLIK5ok5uAAm33cubOGs';
	gapi.client.setApiKey(apiKey);
	window.setTimeout(checkAuth,1);
}

function checkAuth() {
	var clientId = '510213468349-6npga48p58v7rr3s50p0smnp7e6dho5m.apps.googleusercontent.com';
	var scopes = 'https://www.googleapis.com/auth/plus.me';
	gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: true}, handleAuthResult);
}

function handleAuthResult(authResult) {
 
	var authorizeButton = document.getElementById('authorize-button');
	var logoutButton = document.getElementById('logoutButton');
	var logint = document.getElementById('toggleLogin');
	var container = document.getElementById('loginContainer');
	var reg = document.getElementById('regi');
			
	if (authResult && !authResult.error) {
		authorizeButton.style.visibility = 'hidden';
		regi.style.visibility = 'hidden';
		logoutButton.style.visibility = 'visible';
		logint.style.visibility = 'hidden';
		container.style.visibility = 'hidden';

		makeApiCall();
	  
	} else {
	  
	  //authorizeButton.style.visibility = 'visible';
	  authorizeButton.onclick = handleAuthClick;
	}
}

function handleAuthClick(event) {
	var clientId = '510213468349-6npga48p58v7rr3s50p0smnp7e6dho5m.apps.googleusercontent.com';
	var scopes = 'https://www.googleapis.com/auth/plus.me';
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
  
		$('#uName').text('Logged in as ' + user.displayName);
	  });
	});
}
 
 