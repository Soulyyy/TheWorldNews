$(this).ready(function() {
	var term = getUrlParameter('searchBox');	
	console.log(term);
 

	 $.ajax("/searchText", {
		type: "GET",
		dataType:'json',
<<<<<<< HEAD
		data: JSON.stringify({ term: term}),
		contentType: 'application/json',
=======
		data:{searchBox : JSON.stringify({ "term": term})},
		contentType: 'application/json; charset=utf-8',
>>>>>>> 3e332a37182bbbd465c5d3509d0623ac8ed5ee25
		success: function(result) {
			console.log("Suc");
			console.log(result);
		},
		error: function(result) {
			console.log("fail search");
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