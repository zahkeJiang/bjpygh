//var userid = "";
//function ShowMessage() 
//{ 
//    var thisURL = document.URL;    
//    var getval = thisURL.split('?')[1];  
//    userid = getval.split("=")[1];  
//} 
//window.onload=ShowMessage(); 

$(function(){
    //发送请求判断支付完成与否
    $.ajax({
        type:"POST",
        url:"queryOrder.action",
        dataType:"text",
        success:function(data){         
            var obj = eval('(' + data + ')');
            if(obj.status=="1"){              
                alert("支付成功");
                var order_number = obj.out_trade_no;
                var ds_price = obj.price;
                $(".order_number").html(order_number);
                $(".ds_price").html(ds_price);
                $(".pay_success").css({"display":"inherit"});
            }else if (obj.status=="0") {
                alert("支付失败");
            	$(".pay_fail").css({"display":"inherit"});
            }
        }
    });
    
})
