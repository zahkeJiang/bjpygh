//var userid = "";
var ordernumber="";
//var orderprice = "";
function ShowMessage() { 
    var thisURL = document.URL;    
    var getval = thisURL.split("?")[1];  
    ordernumber = getval.split("=")[1];
} 
window.onload=ShowMessage(); 

$(function(){
	$(".order_price p").html(ordernumber);
	$(".apply_refund").click(function(){
	var refund_reason = $("#rea option:selected").val();
	$.post("refund.action",{"WIDrefund_reason":refund_reason},
			function(obj){
			if(obj.status == "1"){
				window.location.href="finish_refund.html?refund_status=1";
			}else{
				window.location.href="finish_refund.html?refund_status=0";
			}
		},'json');
	
	});
});