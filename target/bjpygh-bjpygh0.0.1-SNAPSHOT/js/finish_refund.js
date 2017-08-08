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
		var refund_success = "<p>退款成功</p><img src='images/refund_success.png'>"
		$(".refund_infor").html(refund_success);
	}else{
		var refund_fail = "<p>退款失败</p><img src='images/refund_fail.png'>"
		$(".refund_infor").html(refund_fail);
	}
});