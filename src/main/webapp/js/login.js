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

     
    $('#login').click(function() {
		var hash = window.location.hash;
		console.log(hash);
		if (hash) {
			hash = hash.substr(1);
		}
        var userdata = new Object();
        userdata.userName = $("#login").val();
        userdata.password = $("#password").val();
 
 
        if (!userdata.userName || !userdata.password  ) {
            alert("Fill all forms.idk this should only popup when clicking on login but it overlaps with register vms");
		}
		else {
			$.ajax("/accountLogin",{
				type:"POST",
				dataType:'json',
				data: JSON.stringify(userdata),
				contentType: 'application/json',
 
				success: function(userdata){   
			 
					console.log("gg");
					loadpage(hash);
					 
				},
				error:function(req, text) {
					console.log(req);
					console.log(text);
				}

			});
		}
 

         
    });
});