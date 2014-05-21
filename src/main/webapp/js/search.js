$(this).ready(function() {
	var term = getUrlParameter('searchBox');	
	console.log(term);
	$('#searchbtn').click(function() {	

		 $.ajax("/searchText", {
			type: "GET",
			dataType:'json',
			data: JSON.stringify({ "term": term}),
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
}?