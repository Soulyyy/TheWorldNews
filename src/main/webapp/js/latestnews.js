$(this).ready(function() {
	latestnews();
 	setInterval(latestnews, 10000); 
	// $('a[data-menuItem]').click(function() {
		// var destination = $(this).attr('data-menuItem');
		// loadpage(destination);
	// });
});

function latestnews() {
	$.ajax("/latestNews", {
			type: "GET",
			dataType:'json',
			success: function(resp) {
 
				var id = parseInt(resp[0]);
				var id2 = parseInt(resp[1]);
				var id3 = parseInt(resp[2]);
				var id4 = parseInt(resp[3]);
				var id5 = parseInt(resp[4]);
 
				$(".latestNewsDisplay").html("<div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id+"\">"+resp[5]+"</a></div><div class=\"itemodd\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id2+"\">"+resp[6]+"<div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id3+"\">"+resp[7]+"</div><div class=\"itemodd\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id4+"\">"+resp[8]+"</div><div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id5+"\">"+resp[9]+"</div>");
			}
	});
}
