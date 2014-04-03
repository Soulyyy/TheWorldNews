$(document).ready(function() {
    $('#reg').click(function() {
        var userdata = new Object();
        userdata.userName = $("#username").val();
        userdata.password = $("#pw").val();
        var pw2 = $("#pw2").val();  
        userdata.firstname = $("#first").val();
        userdata.surname = $("#last").val();
        userdata.email = $("#email").val();
 
        if (!userdata.userName || !userdata.password || !pw2 || !userdata.firstname || !userdata.surname  || !userdata.email) {
            alert("T2ida k6ik vormid.");
        }
        else {
            if ( userdata.password != pw2 ) {
                alert("Paroolid peavad olema samad.");
            }
            else {
                if (pw2.length <1){
                    alert("Parooli pikkus peab olema v2hemalt 5.");
                 
                }
                else {
                console.log(JSON.stringify(userdata));
                $.ajax("/accountSignup",{
                  
                    type:"POST",
					dataType:'json',  
                    data: JSON.stringify(userdata),
                    contentType: 'application/json',

                    success: function(userdata){   
					
                        console.log("gg");
						window.location.href = "../index.html";
                         
                    },
                    error:function(req, text) {
						console.log(req);
						console.log(text);
					}
                   
                });
                }
            }
        }
         
    });
});