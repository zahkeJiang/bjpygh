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
    $.post("queryOrder.action",{},function(data){
        if(data.status == "1"){              
            alert("支付成功");
            var order_number = data.out_trade_no;
            var ds_price = data.price;
            $(".order_number").html(order_number);
            $(".ds_price").html(ds_price);
            $(".pay_success").show();
        }else if (data.status=="0") {
            alert("支付失败");
            $(".pay_fail").show();
        }
    },'json');
});
