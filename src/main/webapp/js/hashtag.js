$(this).ready(function() {
	var term = getUrlParameter('id');	
	var btn =  $("#addht");
	// console.log(term);
 

	$.ajax("/tagController", {
		type: "GET",
		dataType:'json',
		contentType: 'application/json',
		data:{term : JSON.stringify({ "term": term})},
		contentType: 'application/json; charset=utf-8',
		success: function(r) {
			console.log(r);

			// if (r.length > 0) {
			

				// var i = 0;
				// while (i < r.length) {
					// $(".hashtags").append(r[i]+"\t");	
					// i+=1;
				// }
			// }
	
		},
		error: function(r) {
			console.log("failed getting tags");
		}
	});
	btn.click(function() {
		var tag = $("#ht").val();
		console.log(tag);
		$.ajax("/tagController", {
			type: "POST",
			dataType:'json',
			contentType: 'application/json',
			data:{tag : JSON.stringify({ "tag": tag}),
			term : JSON.stringify({ "term": term})
			
			},
			contentType: 'application/json; charset=utf-8',
			success: function(resp) {
				console.log("added tag");

			},
			error: function(r) {
				alert("failed add");
			}
		});
	});
 
});
function getUrlParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == sParam) 
        {
            return sParameterName[1];
        }
    }
}