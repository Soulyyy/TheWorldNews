/**
 * Created by kasutaja on 28.02.14.
 */

function swapPage(id, file) {
    console.log("EnterMethod"+id);
    $.ajax({
        type:"GET",
        url:'./styles/htmlData/'+ file+".html",
        data:{"id":id},
        crossDomain:true,
        success: function(data) {

            var externalHTML = document.getElementById("articleGroup");
            console.log(data);
            //$('#subCategory').html($("#"+id).html(data));
            externalHTML.innerHTML=data;
        }

    });
}

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
	var authorizeButton = document.getElementById('authorize-button');
	authorizeButton.style.visibility = '';
	$('#logoutText').hide();
 
	$('#uName').text('Not logged in.');
  }