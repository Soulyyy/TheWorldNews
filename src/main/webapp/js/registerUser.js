$(this).ready(function() {

	
	$('#reg').click(function() {
		var userdata = new Object();
		userdata.username = $("#username").val();
		userdata.pw = $("#pw").val();
		userdata.pw2 = $("#pw2").val();
		userdata.fname = $("#first").val();
		userdata.lname = $("#last").val();
		userdata.email = $("#email").val();

		if (!userdata.username || !userdata.pw || !userdata.pw2 || !userdata.fname || !userdata.lname  || !userdata.email) {
			alert("Fill all forms.");
		}
		else {
			if ( userdata.pw != userdata.pw2 ) {
				alert("Paroolid peavad olema samad.");
			}
			else {
				if (userdata.pw.length < 0){
					alert("Parooli pikkus peab olema vähemalt 5.");
				
				}
				else {
				
				$.ajax({
				 
					url: "AccountController" ,
					type:"POST",
 
					data: JSON.stringify(userdata),
					contentType: 'application/json',
					mimeType: 'application/json',
					success: function(data){	
				
						console.log("gg");
						
					},
					error:function(data,status,er) {
						console.log("error");
				//		alert("error: "+JSON.stringify(data)+" status: "+status+" er:"+er);
					}
					
				});
				}
			}
		}
		
	});
});