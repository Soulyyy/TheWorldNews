$(this).ready(function() {
	var url = window.location.href;
	var params = url.split('?id=');
 
    $('#edit').click(function() {
    	
        var articledata = new Object();
        articledata.image = $("#Image").val();
        articledata.header = $("#titleInput").val();
		articledata.content = $("#textArea").val();
		articledata.articlegroup = "News;";
		articledata.id = params[1];
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
			$.ajax("/editArticle",{
					type:"POST",
					dataType:'json',
					data: JSON.stringify(articledata),
					contentType: 'application/json',
	 
					success: function(resp){   
						window.location.href = "http://gold-experience.herokuapp.com/Index.jsp";
							console.log(resp);
					},
					error:function(resp) {
						alert("edit fail");
						// console.debug("%o", JSON.stringify(articledata));  
 
						console.log(resp);
					}

				});
		}
 

         
    });
});