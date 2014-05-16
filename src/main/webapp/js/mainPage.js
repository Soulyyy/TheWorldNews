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
				console.log(resp);
				console.log(resp[0]);
				var id = parseInt(resp[0]);
				console.log(id);
				$(".latestNewsDisplay").html("<div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id+"\">"+resp[1]+"</a></div><div class=\"itemodd\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id+"\">"+resp[2]+"<div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id+"\">"+resp[3]+"</div><div class=\"itemodd\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id+"\">"+resp[4]+"</div><div class=\"itemeven\"><a href=\"http://gold-experience.herokuapp.com/jsp/ArticleView.jsp?id="+id+"\">"+resp[5]+"</div>");
			}
	});

}

function createCookie(name, value, days) {
	var expires = "";

	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
		expires = "; expires=" + date.toGMTString();
	}

	document.cookie = name + "=" + value + expires;
}

function readCookie(name) {
	var cname = name + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) === ' ')
			c = c.substring(1, c.length);
		if (c.indexOf(cname) === 0)
			return c.substring(cname.length, c.length);
	}
	return "";
}

function eraseCookie(name) {
	createCookie(name, "", -1);
}

function loadpage(dest) {
	var id = 3;

	$.ajax({
		type: "GET",
		url: '../jsp/' + dest + ".jsp",
		data: {"id": id},
		crossDomain: true,
		success: function(data) {
			window.location.hash = dest;
			var externalHTML = document.getElementById("articleGroup");

			externalHTML.innerHTML = data;
		}
	});
}
 

var recentHash = "";

var checkHash = function() {
	var hash = window.location.hash;

	if (hash) {
		hash = hash.substr(1);
		if (hash === recentHash) {
			return;
		}
		recentHash = hash;

		loadpage(hash);
	}
};