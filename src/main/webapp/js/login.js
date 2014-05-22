$(this).ready(function() {
	var loginButton = $("#loginbutton");
	var logoutButton = $("#logoutButton");
	var loginPopupButton = $("#toggleLogin");
	var loginContainer = $("#loginContainer");
	var authorizeButton = $("#authorize-button");
	var adda = $("#add");
	
	if ($("#ht").length > 0){
		var ht = $("#ht");
	}	

	$("#password").keyup(function(event){
		if(event.keyCode == 13){
			$("#loginbutton").click();
		}	
	});

	/**
	 * Sisselogimise kasti kuvamise/peitmise abifunktsioon
	 * @param {Boolean} forceVis Kas sunnime login kasti kuvamist.
	 */
	function toggleLoginContainer(forceVis) {
		if(typeof forceVis !== 'Boolean' && loginContainer.css('visibility') === 'visible' || forceVis === true) {
			loginContainer.css('visibility', 'hidden');
		} else if(typeof forceVis !== 'Boolean' && loginContainer.css('visibility') === 'hidden' || forceVis === false) {
			loginContainer.css('visibility', 'visible');
		}
	}

	/**
	 * Abifunkstioon, mis vahetab login/logout nupu kuvamist.
	 * @param {Boolean} isLoggedIn Kas tuleb kuvada olek, kus kasutaja on sisse logitud.
	 */
	function toggleLoginButtons(isLoggedIn) {
		if(isLoggedIn === true) {
			logoutButton.css('visibility', 'visible');
			loginPopupButton.css('visibility', 'hidden');
		} else {
			logoutButton.css('visibility', 'hidden');
			loginPopupButton.css('visibility', 'visible');
		}
	}

	/*
	 * Küsime lehe laadides login servletilt kasutaja õigusi ja vastavalt sellele
	 * näitame login/logout nuppu.
	 * NoScript puhul ei ole sisselogimine toetatud.
	 */
	$.ajax("/accountLogin", {
	
		type: "GET",
		dataType: 'json',
		success: function(resp) {
			if(resp.accessRights >= 1) {
				console.log("Logged in with accessrights: " + resp.accessRights);
				toggleLoginButtons(true);
				adda.css('visibility', 'visible');
				if ($("#ht").length > 0){
					adda.css('visibility', 'visible');
				}	
			}
			else if(resp.accessRights == 0) {
				console.log("Logged in with accessrights: " + resp.accessRights);
				toggleLoginButtons(true);
				if ($("#ht").length > 0){
					adda.css('visibility', 'visible');
				}	
			}
			else {
				console.log("Not logged in");
				toggleLoginButtons(false);
			}
		}
	});

	/**
	 * Kuvame/peidame sisselogimise/regamise/google-auth kasti
	 */
 
	loginPopupButton.click(function() {
		$("#userName").focus();
		toggleLoginContainer();
		
	});

	/*
	 * Saadab servletile päringu sisselogimiseks. Servlet seab sessioni parameetri
	 * LOGIN_RIGHTS väärtuseks accessRights või -1, kui kasutajat ei leita.
	 */
	$.getScript("js/Sha256.js", function() {
		loginButton.click(function() {

		
			var hash = window.location.hash;
			if(hash) {
				hash = hash.substr(1);
			}
			if(hash === "") {
				hash = "index";
			}
			var userdata = new Object();
			userdata.username = $("#userName").val();

			//Räsime parooli siin, et POST päringus paintexti ei edastataks
			userdata.password = Sha256.hash($("#password").val());


			if(!userdata.username || !userdata.password) {
				alert("Fill all forms.");
			} else {
				$.ajax("/accountLogin", {
					type: "POST",
					dataType: 'json',
					data: userdata,
					success: function(userdata) {
						if(userdata.accessRights === -1) {
							alert("Vale parool/kasutaja.");
						} 
						else {
							toggleLoginButtons(true);
							toggleLoginContainer(false);
							if(userdata.accessRights >= 1) {
								console.log("Logged in with accessrights: " + userdata.accessRights);
						
								adda.css('visibility', 'visible');
							}
							else if(userdata.accessRights == 0) {
								console.log("Logged in with accessrights: " + userdata.accessRights);
								
							}
						}
					}
				});
			}
		});
	});

	/**
	 * Väljalogimiseks saadame GET /accountLogin?action=logout.
	 * Servlet tagastab hetkel alati success, aga jätame tulevikuks kontrolli.
	 */
	logoutButton.click(function() {
		$.ajax("/accountLogin", {
			type: "GET",
			dataType: 'json',
			data: "action=logout",
			success: function(resp) {
				if(resp.response === "success") {
					toggleLoginButtons(false);
					adda.css('visibility', 'hidden');
							 
				}
			}
		});
	});

	/**
	 * Google auth. Idee poolest peaks login servletile saatma kasutaja ja vastavalt sellele
	 * regama uue kasutaja või logima olemasolevaga sisse.
	 */
	$('#authorize-button').click(function() {
		var apiKey = 'AIzaSyD7HJs0zDCJKqLcLIK5ok5uAAm33cubOGs';
		var clientId = '510213468349-6npga48p58v7rr3s50p0smnp7e6dho5m.apps.googleusercontent.com';
		var scopes = 'https://www.googleapis.com/auth/plus.me';

		checkAuth();

		function checkAuth() {
			gapi.client.setApiKey(apiKey);
			gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: true}, handleAuthResult);
		}

		function handleAuthResult(authResult) {
			if(authResult && !authResult.error) {
				toggleLoginContainer(false);
				toggleLoginButtons(true);
				createCookie("googlein", "y", 7);
				makeApiCall();
			} else {
				authorizeButton.click(handleAuthClick);
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