/**
 * Created by kasutaja on 28.02.14.
 */

$(this).ready(function(){
	//console.log(document.cookie);
    $.ajax({
    	type:"GET",
		url:'./html/'+ dest +".html",
		data:{"id":id},
		success:function(data) {
			var logoutButton = document.getElementById('logoutButton');
			logoutButton.style.visibility = 'visible';
			var logint = document.getElementById('toggleLogin');
			logint.style.visibility = 'hidden';
			
		}
    	
    })
	setInterval(checkHash, 100);
    $('a[menuItem]').click( function() {
		var destination = $(this).attr('menuItem');
		loadpage(destination);
	});
	$('#logoutButton').click(function() {
		
		var logoutButton = document.getElementById('logoutButton');
		var logint = document.getElementById('toggleLogin');
		if (readCookie("sessionid") != "") {
			eraseCookie("sessionid");
			logoutButton.style.visibility = 'hidden';
				
			logint.style.visibility = 'visible';
		}
		else {
			$.ajax({
				type:"GET",
				success:function() {
					myIFrame.location='https://www.google.com/accounts/Logout';

					logoutButton.style.visibility = 'hidden';
					logint.style.visibility = 'visible';
		 
				}
			});
		}
		
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
	 
});
function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires;
}
function readCookie(name) {
	var cname = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(cname) == 0) return c.substring(cname.length,c.length);
	}
	return "";
}

function eraseCookie(name) {
	createCookie(name,"",-1);
}
var loadpage = function(dest)  {
    var id=3;
	if (dest != 'index') {
		console.log("got this far");
		$.ajax({
			type:"GET",
			url:'./jsp/'+ dest +".jsp",
			data:{"id":id},
			
			crossDomain:true,
			success: function(data) {
				window.location.hash = dest;
				var externalHTML = document.getElementById("articleGroup");
				console.log("GoogleLogOutSuccess");
				externalHTML.innerHTML=data;
			}

		});    
	}
	else {
		window.location.href = "Index.jsp";
 
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

//Template for everything when shit hits the fan
//$(this).ready(function(){
//	$.ajax({
//		type:"GET",
//		url:'./html/'+ dest +".html",
//		data:{"id":id},
//		success:
//	});
//	
//});
 
 