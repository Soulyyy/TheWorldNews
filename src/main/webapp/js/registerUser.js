
 
$(this).ready(function() {
	console.log("a");
	$('#reg').click(function() {
	 
		var data={ "firstName" : "Ray" };
		alert(data.firstName);
	});
});

 