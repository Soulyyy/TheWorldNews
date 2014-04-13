$(this).ready(function() {
	function readCookie(name) {
		var cname = name + "=";
		var ca = document.cookie.split(';');
		for(var i=0;i < ca.length;i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1,c.length);
			if (c.indexOf(cname) == 0) return c.substring(cname.length,c.length);
		}
		return "";
	}

    $('#articlesubmit').click(function() {
    	
        var articledata = new Object();
        articledata.image = $("#Image").val();
        articledata.header = $("#titleInput").val();
		articledata.content = $("#textArea").val();
		articledata.articlegroupstring = "News;";
		articledata.sid = readCookie("sessionid");
		if(document.getElementById('Business').checked) {
			articledata.articlegroupstring += "Business;";
		} if(document.getElementById('Sports').checked) {
			articledata.articlegroupstring += "Sports;";
		} if(document.getElementById('Science').checked) {
			articledata.articlegroupstring += "Science;";
		} if(document.getElementById('Arts').checked) {
			articledata.articlegroupstring += "Arts;";
		} if(document.getElementById('Fashion').checked) {
			articledata.articlegroupstring += "Fashion;";
		}	
	
 
        // if (!articledata.image || !articledata.header  || !articledata.content ) {
            // alert("Fill all forms.");
		// }

		// else {
			// if (readCookie("sessionid") == "") {
				// alert("Must be logged in as editor");
			// }
			// else {
				$.ajax("/submitNews",{
					type:"POST",
					dataType:'json',
					data: JSON.stringify(articledata),
					contentType: 'application/json',
	 
					success: function(articledata){   
						console.log("gg");
					},
					error:function(req, text) {
						console.debug("%o", JSON.stringify(articledata));  
						console.log(req);
						console.log(text);
					}

				});
			// }
		// }
 

         
    });
});