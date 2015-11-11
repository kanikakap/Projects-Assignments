$(document).ready(function(){

  		$("#submitButton").click(function(e)
  		{
			e.preventDefault();
			var inputYear=$("#chooseYear").val();
				
			$(".displayYear,.maleDiv,.femaleDiv").show();		
			
			
			$.ajax({
					url: "http://localhost:8888/kxk140230_Assg-3/babynames.php",
					type: 'post',
					dataType: 'json',
					data: {'inputYear': inputYear},
					success: function(data) 
					{

					$(".year").text(inputYear);

					$(".femaleDiv").text(" "+"Females ");
					$(".femaleDiv").append("<br><br>");
					$(".femaleDiv").css("background-image","url(http://thegirlybaby.com/images/il_570xN_561230465_smo6.jpg)");
					

					$(".maleDiv").text(" "+"Males ");
					$(".maleDiv").append("<br><br>");
					$(".maleDiv").css("background-image","url(http://cdn.sheknows.com/articles/infant-baby-boy-in-blue.jpg)");
					
					
					for (var r=1;r<=5;r++)
						{
							$(".femaleDiv").append(r+". "+data[r]+"<br>");
						}
					for (var r=6;r<data.length;r++)
						{
							$(".maleDiv").append(r-5+". "+data[r]+"<br>");
						}
				},
				error: function(err) 
				{
				alert("Error loading JS File" + err);
				}
			}); 
			});

});