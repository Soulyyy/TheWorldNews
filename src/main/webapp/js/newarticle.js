$(this).ready(function() {
 
     //html does not exist 
    $('#articlesubmit').click(function() {
        var articledata = new Object();
        articledata.Title = $("#titleInput").val();
        articledata.imgURL = $("#Image").val();
		articledata.text = $("#textArea").val();
 
 
        if (!articledata.Title || !articledata.imgURL  || !articledata.text ) {
            alert("Fill all forms.");
		}
		else {
			$.ajax("/newarticle",{
				type:"POST",
				dataType:'json',
				data: JSON.stringify(userdata),
				contentType: 'application/json',
 
				success: function(userdata){   
			 
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