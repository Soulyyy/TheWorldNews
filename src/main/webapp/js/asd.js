$(this).ready(function() {

   $.ajax("/displayArticle",{
					type:"GET",
				 
					success: function(resp){   
						console.log(resp);
					}, 
				});
});