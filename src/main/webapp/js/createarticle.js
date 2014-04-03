$(this).ready(function() {
	console.log("a");
    $('#articlesubmit').click(function() {
        var articledata = new Object();
        articledata.Title = $("#titleInput").val();
        articledata.imgURL = $("#Image").val();
		articledata.text = $("#textArea").val();
		articledata.type = "News";
		
		if(document.getElementById('Business').checked) {
			articledata.type = "Business";

		}else if(document.getElementById('Sports').checked) {
			articledata.type = "Sports";
		}else if(document.getElementById('Science').checked) {
			articledata.type = "Science";
		}else if(document.getElementById('Arts').checked) {
			articledata.type = "Arts";
		}else if(document.getElementById('Fashion').checked) {
			articledata.type = "Fashion";
		

		
		
		
		
		
		}
 
        if (!articledata.Title || !articledata.imgURL  || !articledata.text ) {
            alert("Fill all forms.");
		}
		else {
			$.ajax("/submitNews",{
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