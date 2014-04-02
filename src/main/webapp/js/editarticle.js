$(this).ready(function() {
 
     
    $('#articlesubmit').click(function() {
        var articledata = new Object();
        articledata.Title = $("#titleInput").val();
        articledata.imgURL = $("#Image").val();
		articledata.text = $("#textArea").val();
 
 
        if (!articledata.Title || !articledata.imgURL  || !articledata.text ) {
            alert("Fill all forms.");
		}
		else {
			$.ajax("/editarticle",{
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