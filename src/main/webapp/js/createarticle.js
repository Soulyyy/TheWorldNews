$(this).ready(function() {
    $('#articlesubmit').click(function() {
    	
        var articledata = new Object();
        articledata.image = $("#Image").val();
        articledata.header = $("#titleInput").val();
		articledata.content = $("#textArea").val();
		articledata.articlegroupstring = "News;";
		
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
	
 
        if (!articledata.image || !articledata.header  || !articledata.content ) {
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
					console.debug("%o", data);  
					console.log(req);
					console.log(text);
				}

			});
		}
 

         
    });
});