$(document).ready(function() {
	$.getScript("js/Sha256.js", function() {
		$('#reg').click(function() {
			var userdata = new Object();
			userdata.userName = $("#username").val();
			userdata.password = $("#pw").val();
			var pw2 = $("#pw2").val();
			userdata.firstname = $("#first").val();
			userdata.surname = $("#last").val();
			userdata.email = $("#email").val();

			if(!userdata.userName || !userdata.password || !pw2 || !userdata.firstname || !userdata.surname || !userdata.email) {
				alert("Täida kõik vormid.");
			} else {
				if(userdata.password !== pw2) {
					alert("Paroolid peavad olema samad.");
				} else {
					if(pw2.length < 1) {
						alert("Parooli pikkus peab olema vähemalt 5.");
					} else {
						userdata.password = Sha256.hash(userdata.password);
						console.log(JSON.stringify(userdata));
						$.ajax("/accountSignup", {
							type: "POST",
							dataType: 'json',
							data: JSON.stringify(userdata),
							contentType: 'application/json',
							success: function() {
								console.log("gg");
								window.location.href = "../Index.jsp";
							},
							error: function(req, text) {
								console.log(req);
								console.log(text);
							}
						});
					}
				}
			}
		});
	});
});