
 
$(this).ready(function() {
	var userdata;
	console.log("WE NEED POST");
	$('#reg').click(function() {
		console.log("MAYBE HERE?");
		
		$.ajax({
			console.log("MADE IT HERE");
			type:"POST",
			success: function(){	
				userdata={ 
						"username" : $("#username").val() ,
						"pw" : $("#pw").val() ,
						"fname" : $("#first").val() ,
						"lname" : $("#last").val() ,
						"email" : $("#other-email").val(),
				};
				var jsonString = JSON.stringify(userdata);
				console.log(jsonString);
			}
			
			});
	});
		getdata(userdata);
		//document.forms[0].submit();
		var jsonString = JSON.stringify(userdata);
		console.log(jsonString);
		console.log(userdata);
});

 function getdata(d) {
		var info = d;
 
};