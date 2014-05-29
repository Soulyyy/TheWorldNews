$(this).ready(function() {
	var term = getUrlParameter('q');	
	 $.ajax("/searchText", {
		type: "GET",
		dataType:'json',
		contentType: 'application/json',
		data:{searchBox : JSON.stringify({ "term": term})},
		contentType: 'application/json; charset=utf-8',
		success: function(r) {
			if (r.length == 0) {
				$(".sresults").html("No results found");
			}
			else {
				var i = 0;
				while (i < r.length) {
					$(".sresults").append("<a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+r[i]+"&image="+r[i+3]+"\">"+r[i+1]+"</a><br/>"+r[i+2]+"<br/>");
		
					i+=4;
				}
			}
		},
		error: function(r) {
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