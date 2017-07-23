var userid = "";
var refund_status="";
function ShowMessage() 
{ 
    var thisURL = document.URL;    
    var getval = thisURL.split('?')[1];  
    userid = getval.split("=")[1].split("&")[0];  
    refund_status = getval.split("=")[2]; 
} 
window.onload=ShowMessage(); 


$(function(){
	if (refund_status =="1") {
		alert("退款成功");
	}else{
		alert("退款失败");
	}
});