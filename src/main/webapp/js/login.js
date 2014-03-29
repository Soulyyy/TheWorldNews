$(this).ready(function() {
 
     
    $('#log').click(function() {
        var userdata = new Object();
        userdata.userName = $("#login").val();
        userdata.password = $("#password").val();
 
 
        if (!userdata.userName || !userdata.password  ) {
            alert("Fill all forms.");
		}
		else {
			$.ajax("/accountLogin",{
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