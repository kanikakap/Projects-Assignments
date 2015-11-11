$(document).ready(function() 
{

//http://www.webcodehelpers.com/2013/12/registration-form-validation-using-jquery-with-example.html
var alphaExp = /^[a-z0-9\\-]+$/i;
var checkUsername = $("#username").val();
var checkPassword = $("#password").val();
var checkEmail = $("#email").val();


//http://stackoverflow.com/questions/2584267/insert-span-next-to-textbox-using-jquery
$( "#username" ).after( "<span id='usernameLabel'></span>" );
$( "#password" ).after( "<span id='passwordLabel'></span>" );
$( "#email" ).after( "<span id='emailLabel'></span>" );



//http://www.jquerybyexample.net/2011/08/addclass-removeclass-hasclass-and.html
$("#username").focus(function()
{
$("#spanUsername").html("Enter username - only alphanumeric characters").removeClass().addClass("info");
});

$("#username").focusout(function()
{

if($("#username").val().match(alphaExp))
{
	//$( "#usernameLabel").removeClass("info");
	//$( "#usernameLabel").removeClass("error");

	$( "#usernameLabel").html("Correct").removeClass().addClass("ok");
	//$('#usernameLabel').append($('<img>',{id:'theImg',src:'http://1.bp.blogspot.com/-_--pHOOZbkY/VYWS0ESXCTI/AAAAAAAAIvE/l7zqac4cYVg/s1600/tick2.png'}))

}

else
{
	//$( "#usernameLabel" ).removeClass("info");
	//$( "#usernameLabel" ).removeClass("ok");
	$( "#usernameLabel" ).html("Username must contain alphaNumericCharacter").removeClass().addClass("error");

}
});


//for PASSWORD
$("#password").focus(function()
{
$( "#password" ).html("Enter password - atleast 8 characters").addClass("info");
});

$("#password").focusout(function(){
	
if($("#password").val().length < 8) 
{
	//$( "#passwordLabel" ).removeClass("info");
	//$( "#passwordLabel" ).removeClass("ok");
$( "#passwordLabel" ).html("Password must be atleast 8 characters").removeClass().addClass("error");

}
else 
{
	//$( "#passwordLabel" ).removeClass("info");
	//$( "#passwordLabel" ).removeClass("error");
$( "#passwordLabel" ).html("Correct").removeClass().addClass("ok");
//$('#passwordLabel').append($('<img>',{id:'theImg',src:'http://1.bp.blogspot.com/-_--pHOOZbkY/VYWS0ESXCTI/AAAAAAAAIvE/l7zqac4cYVg/s1600/tick2.png'}))

}
});


//for EMAIL
$("#email").focus(function()
{
$( "#email" ).addClass("info").text("Enter Email Id");
});

$("#email").focusout(function()
{
if($("#email").val().indexOf("@",0) <0) 
{
	//$( "#emailLabel" ).removeClass("info");
	//$( "#emailLabel" ).removeClass("ok");
$( "#emailLabel" ).removeClass().addClass("error").text("Email must contain @");
} 
else
{
	//$( "#emailLabel" ).removeClass("info");
	//$( "#emailLabel" ).removeClass("error");
$( "#emailLabel" ).removeClass().addClass("ok").text("Correct");
//$('#emailLabel').append($('<img>',{id:'theImg',src:'http://1.bp.blogspot.com/-_--pHOOZbkY/VYWS0ESXCTI/AAAAAAAAIvE/l7zqac4cYVg/s1600/tick2.png'}))

}
});

});
