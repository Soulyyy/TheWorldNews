$(this).ready(function() {
 
     
    $('#reg').click(function() {
        var userdata = new Object();
        userdata.userName = $("#username").val();
        userdata.password = $("#pw").val();
        var pw2 = $("#pw2").val(); // seda pole vaja userdata'ga kaasa saata
        userdata.firstname = $("#first").val();
        userdata.surname = $("#last").val();
        userdata.email = $("#email").val();
 
        if (!userdata.userName || !userdata.password || !pw2 || !userdata.firstname || !userdata.surname  || !userdata.email) {
            alert("Fill all forms.");
        }
        else {
            if ( userdata.password != pw2 ) {
                alert("Paroolid peavad olema samad.");
            }
            else {
                if (pw2.length == 0){
                    alert("Parooli pikkus peab olema v?�hemalt 5.");
                 
                }
                else {
                console.log(JSON.stringify(userdata));
                $.ajax("/accountSignup",{
                  
                    type:"POST",
  
                    data: JSON.stringify(userdata),
                    contentType: 'application/json',
                    //mimeType: 'application/json',
                    success: function(userdata){   
                 
                        console.log("gg");
                         
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