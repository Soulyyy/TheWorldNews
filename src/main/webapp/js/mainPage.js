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
            url:'./styles/htmlData/'+ $(this).attr('menuItem')+".html",
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
  
$(this).ready(function(){
	console.log("Enter Method");
	$('a[curSite]').click(function() {
		console.log(this);
		console.log($(this).attr('curSite'));
		//myIFrame.location='https://www.google.com/accounts/Logout';
		document.location.href = "https://www.google.com/accounts/Logout?continue="+$(this).attr('curSite');
		var authorizeButton = document.getElementById('authorize-button');
		authorizeButton.style.visibility = '';
		$('#logoutText').hide();
 
		$('#uName').text('Not logged in.');
	});
});