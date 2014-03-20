
 
$(this).ready(function() {
 
	$('#reg').click(function() {
		var userdata={ 
		"username" : $("#username").val() ,
		"pw" : $("#pw").val() ,
		"fname" : $("#first").val() ,
		"lname" : $("#last").val() ,
		"email" : $("#other-email").val() 
		
			
		};
		getdata(userdata);
		
	});
});

 function getdata(d) {
		var info = d;
 
};