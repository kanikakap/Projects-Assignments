$(document).ready(function() {
// your js code goes here...
$( "#username" ).after( "<span id='username_span'></span>" );
$( "#password" ).after( "<span id='password_span'></span>" );
$( "#email" ).after( "<span id='email_span'></span>" );


$("#username").focusin(function(){
$( "#username_span" ).addClass("info").text("Enter username - only alphabetical or numeric characters");
});

$("#username").focusout(function(){
var alphaNumericChar = "^[a-zA-Z0-9]+$";
if($("#username").val().match(alphaNumericChar) == null) {
$( "#username_span" ).addClass("error").text("Error");
} else{
$( "#username_span" ).addClass("ok").text("Ok");
}
});

$("#password").focusin(function(){
$( "#password" ).addClass("info").text("Enter password - atleast 8 characters");
});

$("#password").focusout(function(){
if($("#password").val().length >= "8") {
$( "#password_span" ).addClass("ok").text("Ok");
} else {
$( "#username_span" ).addClass("error").text("Error");
}

});

$("#email").focusin(function(){
$( "#email" ).addClass("info").text("Enter Email Id");
});

$("#email").focusout(function(){
if($("#email").val().search("@") != -1) {
$( "#email_span" ).addClass("error").text("Error");
} else {
$( "#email_span" ).addClass("ok").text("Ok");

}
});

});