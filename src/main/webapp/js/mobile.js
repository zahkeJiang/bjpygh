var oldmobile = "";
function ShowMessage() { 
	var thisURL = decodeURI(location.href);     
	var getval = thisURL.split('?')[1];
	oldmobile = getval.split("=")[1];
} 
window.onload=ShowMessage(); 

$(function(){
	$("#oldmobile").html(oldmobile);
	$(".change_mobile").click(function(){
		window.location.href="change_mobile.html?oldmobile="+oldmobile;
	});
});
