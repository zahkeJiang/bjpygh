var total_fee = "";
$(function(){
    userload();
    $(".amount_type_01").click(function(){
        total_fee = 0.01;
        recharge();
    });
    $(".amount_type_02").click(function(){
        total_fee = 0.02;
        recharge();
    });
    $(".amount_type_03").click(function(){
        total_fee = 0.03;
        recharge();
    });
    $(".amount_type_04").click(function(){
        total_fee = 0.04;
        recharge();
    });
    $(".amount_type_05").click(function(){
        total_fee = 0.05;
        recharge();
    });
    $(".amount_type_06").click(function(){
        total_fee = 0.06;
        recharge();
    });
});
//获取用户信息
function userload(){
    $.post("personal.action",{},function(obj){
        if (obj.status == "1") {
            var user = obj.data;
            $("#icon").attr('src',user.headimageurl);
            $("#nickname").html(user.nickname);
            $("#mobile").html(user.phonenumber);
        }
    },'json');
}


//付款
function recharge(){
    $.post("wxpay.action",{"total_fee":total_fee},function(obj){
        var payurl = obj.data;
        determine();
    });
}
//付款判断
function determine(){
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady();
    }
}

 //微信支付
function onBridgeReady(){
    WeixinJSBridge.invoke(
        'getBrandWCPayRequest', {
            "appId":payurl.appid,     //公众号名称，由商户传入     
            "timeStamp":obj.timeStamp,         //时间戳，自1970年以来的秒数     
            "nonceStr":payurl.nonce_str, //随机串     
            "package":"prepay_id="+payurl.prepay_id,     
            "signType":"MD5",         //微信签名方式：     
            "paySign":payurl.sign //微信签名 
        },
        function(res){     
            if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                alert("充值成功");
            }// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。              
            else if(res.err_msg == "get_brand_wcpay_request:cancer"){
                alert("用户取消支付"+res.err_msg);
            }
            else{
                alert("支付失败："+res.err_msg);
            }
            }
         
    ); 
}




