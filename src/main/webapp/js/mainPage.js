/**
 * Created by kasutaja on 28.02.14.
 */

$("a").click(function swapPage(target, source) {
    var myUrl = $(this).attr("href") + "source"
    $(target).load(myUrl);
})
 

function login() {
	var win         =   window.open(_url, "windowname1", 'width=800, height=600'); 

	var pollTimer   =   window.setInterval(function() { 
		try {
			console.log(win.document.URL);
			if (win.document.URL.indexOf(REDIRECT) != -1) {
				window.clearInterval(pollTimer);
				var url =   win.document.URL;
				acToken =   gup(url, 'access_token');
				tokenType = gup(url, 'token_type');
				expiresIn = gup(url, 'expires_in');
				win.close();

				validateToken(acToken);
			}
		} catch(e) {
		}
	}, 500);
}

function validateToken(token) {
	$.ajax({
		url: VALIDURL + token,
		data: null,
		success: function(responseText){  
			getUserInfo();
			loggedIn = true;
			$('#loginText').hide();
			$('#logoutText').show();
		},  
		dataType: "jsonp"  
	});
}

function getUserInfo() {
	$.ajax({
		url: 'https://www.googleapis.com/oauth2/v1/userinfo?access_token=' + acToken,
		data: null,
		success: function(resp) {
			user    =   resp;
			console.log(user);
			$('#uName').text('Logged in as ' + user.name);
		},
		dataType: "jsonp"
	});
}

function gup(url, name) {
	name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	var regexS = "[\\#&]"+name+"=([^&#]*)";
	var regex = new RegExp( regexS );
	var results = regex.exec( url );
	if( results == null )
		return "";
	else
		return results[1];
}

function startLogoutPolling() {
	$('#loginText').show();
	$('#logoutText').hide();
	loggedIn = false;
	$('#uName').text('Not logged in.');
}
