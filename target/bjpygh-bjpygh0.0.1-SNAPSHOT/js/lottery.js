var userid = "";
function ShowMessage() 
{ 
    var thisURL = document.URL;    
    var userId = thisURL.split('?')[1];  
    userid = userId.split("=")[1];  
    alert(userid);
} 
window.onload=ShowMessage(); 

$(function(){
	$(".dsimage").click(function(){
    	window.location.href="";
	})
});