$(this).ready(function() {
	$("#searchBox").keyup(function(event){
		if(event.keyCode == 13){
			$("#searchbtn").click();
		}
	});
 
	$('#searchbtn').click(function() {	
		var jsondata = {jsondata:$('#searchBox').val()};
		console.log(jsondata);
		 $.ajax("/searchText", {
			type: "POST",
			dataType:'json',
			data: JSON.stringify(jsondata),
			contentType: 'application/json',
			success: function(result) {

				console.log(result);
			},
			error: function(result) {
				console.log("fail search");
			}
		});
	});
});
