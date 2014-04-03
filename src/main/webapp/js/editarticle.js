$(this).ready(function() {
 
     
    $('#articleedit').click(function() {
        var articledata = new Object();
        articledata.Title = $("#titleInput").val();
        articledata.imgURL = $("#Image").val();
		articledata.text = $("#textArea").val();
		articledata.articleGroup = $("#articleGroup").val();
 
 
        if (!articledata.Title || !articledata.imgURL  || !articledata.text || !articledata.articleGroup) {
            alert("Fill all forms.");
		}
		else {
			$.ajax("/editnews",{
				type:"POST",
				dataType:'json',
				data: JSON.stringify(articledata),
				contentType: 'application/json',
 
				success: function(articledata){  
					console.log("gg");
				},
				error:function(req, text) {
					console.log(req);
					console.log(text);
				}

			});
		}
 

         
    });
});