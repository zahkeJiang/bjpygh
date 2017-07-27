//var userid = "";
var refund_status="";
function ShowMessage() 
{ 
    var thisURL = document.URL;    
    var getval = thisURL.split('?')[1];  
    refund_status = getval.split("=")[1];  
//    refund_status = getval.split("=")[2]; 
} 
window.onload=ShowMessage(); 


$(function(){
	if (refund_status =="1") {
		$(".p").html("退款成功");
		$(".img").src(images/refund_success.png);
	}else{
		$(".p").html("退款失败");
		$(".img").src(images/refund_fail.png);
	}
});