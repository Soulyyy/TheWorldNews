$(this).ready(function() {

    $('#articlesubmit').click(function() {
    	
        var articledata = new Object();
        articledata.image = $("#Image").val();
        articledata.header = $("#titleInput").val();
		articledata.content = $("#textArea").val();
		articledata.articlegroup = "News;";
		if(document.getElementById('Business').checked) {
			articledata.articlegroup += "Business;";
		} if(document.getElementById('Sports').checked) {
			articledata.articlegroup += "Sports;";
		} if(document.getElementById('Science').checked) {
			articledata.articlegroup += "Science;";
		} if(document.getElementById('Arts').checked) {
			articledata.articlegroup += "Arts;";
		} if(document.getElementById('Fashion').checked) {
			articledata.articlegroup += "Fashion;";
		}	
	
 
        if (!articledata.image || !articledata.header  || !articledata.content ) {
            alert("Fill all forms.");
		}

		else {
			console.log(articledata.articlegroup);
			$.ajax("/submitArticle",{
					type:"POST",
					dataType:'json',
					data: JSON.stringify(articledata),
					contentType: 'application/json',
	 
					success: function(articledata){   
						window.location.href = "http://gold-experience.herokuapp.com/Index.jsp";
					},
					error:function(req, text) {
						alert("Ei lisatud");
						console.debug("%o", JSON.stringify(articledata));  
						console.log(req);
						console.log(text);
					}

				});
		}
 

         
    });
});