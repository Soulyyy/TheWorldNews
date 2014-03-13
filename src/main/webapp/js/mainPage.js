/**
 * Created by kasutaja on 28.02.14.
 */

$(this).ready(function(){

	$('#logoutText').hide();
 
	setInterval(checkHash, 100);
    $('a[menuItem]').click(  function() {
		
		var destination = $(this).attr('menuItem');
		loadpage(destination);
 
	});
 
});
var loadpage = function(dest)  {
    var id=3;

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
}


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
	if (authResult && !authResult.error) {
	  authorizeButton.style.visibility = 'hidden';
	  makeApiCall();
	} else {
	  
	  authorizeButton.style.visibility = '';
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
		$('#logoutText').show();
		$('#uName').text('Logged in as ' + user.displayName);
	  });
	});
}

function startLogoutPolling() {
		$('#logoutText').hide();
        console.log("Enter");
	
		//myw = window.open('https://www.google.com/accounts/logout','logout_from_google','width=500,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=no,top=20,left=200');
  
		var authorizeButton = document.getElementById('authorize-button');
		authorizeButton.style.visibility = '';
 
		$('#uName').text('Not logged in.');

}


$(this).ready(function() {
    $('#logoutText').click(function() {
 
		myIFrame.location='https://www.google.com/accounts/Logout';

        startLogoutPolling();
		return false;
 
    });
});

