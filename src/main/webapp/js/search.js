$(this).ready(function() {
	$("#searchBox").keyup(function(event){
		if(event.keyCode == 13){
			$("#searchbtn").click();
		}
	});
 
	$('#searchbtn').click(function() {	
		var term = $('#searchBox').val();
		console.log(term);
		 $.ajax("/searchText", {
			type: "POST",
			dataType:'json',
			contentType: 'application/json',
			data:{'term':term},
			success: function(result) {

				console.log(result);
			},
			error: function(result) {
				console.log("fail search");
			}
		});
	});
});
