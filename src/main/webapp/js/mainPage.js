/**
 * Created by kasutaja on 28.02.14.
 */

$(this).ready(function(){
    $('a[menuItem]').click(function() {
        //console.log("FOUND");
        var id=3;
        //console.log("EnterMethod"+id);
        //console.log(this);
        $.ajax({
            type:"GET",
            url:'./html/'+ $(this).attr('menuItem')+".html",
            data:{"id":id},
            crossDomain:true,
            success: function(data) {

                var externalHTML = document.getElementById("articleGroup");
                //console.log(data);
                //$('#subCategory').html($("#"+id).html(data));
                externalHTML.innerHTML=data;
            }

        });
    });
});

    function handleClientLoad() {
	gapi.client.setApiKey(apiKey);
	window.setTimeout(checkAuth,1);
  }

  function checkAuth() {
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
        console.log("Enter");
		console.log(this);
		console.log($(this).attr('curSite'));
		document.location.href = "https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://gold-experience.herokuapp.com/";
		var authorizeButton = document.getElementById('authorize-button');
		authorizeButton.style.visibility = '';
		$('#logoutText').hide();
 
		$('#uName').text('Not logged in.');
}

/*We need to wrap everything...
Also, this is the page, it works
*/
$(this).ready(function() {
    $('#logoutText').click(function() {
        console.log('Loaded');
        //myIFrame.location='https://www.google.com/accounts/Logout';
        startLogoutPolling();
        console.log(this);
    });
});

