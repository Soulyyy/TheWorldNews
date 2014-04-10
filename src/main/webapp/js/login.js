$(this).ready(function() {
	 var loadpage = function(dest)  {
		var id=3;

		$.ajax({
			type:"GET",
			url:'./html/'+ dest +".html",
			data:{"id":id},
			
			crossDomain:true,
			success: function(data) {
				window.location.hash = dest;
				var externalHTML = document.getElementById("articleGroup");

				externalHTML.innerHTML=data;
			}

		});    
	};

     
    $('#loginbutton').click(function() {
		var hash = window.location.hash;
		console.log(hash);
		if (hash) {
			hash = hash.substr(1);
		}
        var userdata = new Object();
        userdata.userName = $("#userName").val();
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
					if (userdata.response == 0) {
						alert("Vale parool/user.");
					}
					console.log(userdata.response);
					 
				},
				error:function(req, text) {
					console.log(req);
					console.log(text);
				}

			});
		}
 

         
    });
});