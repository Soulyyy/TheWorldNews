$(this).ready(function() {
	var term = getUrlParameter('id');	
	// console.log(term);
 

	$.ajax("/tagController", {
		type: "GET",
		dataType:'json',
		contentType: 'application/json',
		data:{searchBox : JSON.stringify({ "term": term})},
		contentType: 'application/json; charset=utf-8',
		success: function(r) {
			if (r.length > 0) {
				var i = 0;
				while (i < r.length) {
					$("#hashtags").append(r[i].tagname+"("+r[i].count+")\t");	
					i+=1;
				}
			}
	
		},
		error: function(r) {
			// console.log(r);
			console.log("failed getting tags");
		}
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