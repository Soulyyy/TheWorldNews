$(this).ready(function() {
	firstlatestnews();
	latestnews(); 
 
 
});

function latestnews() {
	$.ajax("/latestNews", {
			type: "GET",
			dataType:'json',
            async: true, 
            cache: false,
            timeout:60000,
			success: function(resp) {
				console.log(resp);

				// var id = parseInt(resp[0]);
				// var id2 = parseInt(resp[1]);
				// var id3 = parseInt(resp[2]);
				// var id4 = parseInt(resp[3]);
				// var id5 = parseInt(resp[4]);
 
				// $(".latestNewsDisplay").html("<div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id+"&image="+resp[10]+"\">"+resp[5]+"</a></div><div class=\"itemodd\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id2+"&image="+resp[11]+"\">"+resp[6]+"<div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id3+"&image="+resp[12]+"\">"+resp[7]+"</div><div class=\"itemodd\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id4+"&image="+resp[13]+"\">"+resp[8]+"</div><div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id5+"&image="+resp[14]+"\">"+resp[9]+"</div>");
				
				setTimeout(latestnews, 1000);
			},
			error: function(resp){
               console.log("latestnews fail");
			   setTimeout(latestnews,15000);
			}
	});
}
 

function firstlatestnews() {
	$.ajax("/FlatestNews", {
			type: "GET",
			dataType:'json',
			success: function(resp) {
 
				var id = parseInt(resp[0]);
				var id2 = parseInt(resp[1]);
				var id3 = parseInt(resp[2]);
				var id4 = parseInt(resp[3]);
				var id5 = parseInt(resp[4]);
 
				$(".latestNewsDisplay").html("<div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id+"&image="+resp[10]+"\">"+resp[5]+"</a></div><div class=\"itemodd\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id2+"&image="+resp[11]+"\">"+resp[6]+"<div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id3+"&image="+resp[12]+"\">"+resp[7]+"</div><div class=\"itemodd\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id4+"&image="+resp[13]+"\">"+resp[8]+"</div><div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id5+"&image="+resp[14]+"\">"+resp[9]+"</div>");
			}
	});
}
