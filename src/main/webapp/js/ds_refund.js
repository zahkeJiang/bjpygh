var userid = "";
var ordernumber="";
var orderprice = "";
function ShowMessage() 
{ 
    var thisURL = document.URL;    
    var getval = thisURL.split('?')[1];  
    userid = getval.split("=")[1].split("&")[0];  
    ordernumber = getval.split("=")[2].split("&")[0]; 
    orderprice = getval.split("=")[3]; 
} 
window.onload=ShowMessage(); 

$(function(){
	$("order_price p").html(ordernumber);
	$(".apply_refund").click(function(){
		var refund_reason = $(".refund_reason select").val()
		if (refund_reason !="") {
			$.ajax({
				type:"POST",
        		url:"selectOrder.action",
        		dataType:"text",
        		data:"userid="+userid+"&WIDout_trade_no="+ordernumber+"&WIDrefund_amount="+orderprice+"&WIDrefund_reason="+refund_reason,
				success:function(data){
					var obj = eval('(' + data + ')');
					var refund_status = obj.status;
					window.location.href="finish_refund.html?userid="+userid+"&refund_status="+refund_status;
				},
				error:function(obj){
					alert("error");
				}
			});
			
		}else{
			alert("请选择退款原因");
		}
	});
});