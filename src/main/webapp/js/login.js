$(this).ready(function() {
	
var loadpage = function(dest)  {
	var id=3;
	if (dest != 'index') {
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
	}
	else {
		window.location.href = "Index.jsp";
 
	}
};
function createCookie(name,value,days) {
	if (days) {
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires;
}

	
$('#loginbutton').click(function() {
	var hash = window.location.hash;
	if (hash) {
		hash = hash.substr(1);
	}
	if (hash == "") {
		hash = "index";
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
				console.log(userdata.response);
				if (userdata.response ==-1) {
					alert("Vale parool/user.");
				}
				else {
					createCookie("sessionid",userdata.response,7);
					loadpage(hash);
					location.reload();
					var authorizeButton = document.getElementById('authorize-button');
					var container = document.getElementById('loginContainer');
					var reg = document.getElementById('regi');
 
					authorizeButton.style.visibility = 'hidden';
					regi.style.visibility = 'hidden';
					container.style.visibility = 'hidden';
			 
	 
					
				}
				 
			},
			error:function(req, text) {
				console.log("login failed");
			}

		});
	}


	 
});
});