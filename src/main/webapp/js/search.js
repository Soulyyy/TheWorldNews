$(this).ready(function() {
	$("#searchBox").keyup(function(event){
		if(event.keyCode == 13){
			$("#searchbtn").click();
		}
	});
 
	$('#searchbtn').click(function() {	
		var term =   $('#searchBox').val();
		console.log(term);
		 $.ajax("/searchText", {
			type: "POST",
			data:{'fragment':term},
			success: function(result) {
				// console.log("suc");
				console.log(result);
				},
			error: function(result) {
				console.log("fail search");
			}
		});
	});
});
