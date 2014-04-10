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
	function createCookie(name,value,days) {
		if (days) {
			var date = new Date();
			date.setTime(date.getTime()+(days*24*60*60*1000));
			var expires = "; expires="+date.toGMTString();
		}
		else var expires = "";
		document.cookie = name+"="+value+expires+"; path=/";
	}

	function readCookie(name) {
		var nameEQ = name + "=";
		var ca = document.cookie.split(';');
		for(var i=0;i < ca.length;i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1,c.length);
			if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
		}
		return null;
	}

	function eraseCookie(name) {
		createCookie(name,"",-1);
	}
     
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
					if (userdata.response ==-1) {
						alert("Vale parool/user.");
					}
					else {
						createCookie(userdata.UserName,userdata.response,7);
					}
					//console.log(userdata.response);
					 
				},
				error:function(req, text) {
					console.log(req);
					console.log(text);
				}

			});
		}
 

         
    });
});