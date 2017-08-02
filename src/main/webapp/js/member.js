function onBridgeReady(){
	 WeixinJSBridge.invoke(
		'getBrandWCPayRequest', {
			"appId":"wx2421b1c4370ec43b",     //公众号名称，由商户传入     
			"timeStamp":"1395712654",         //时间戳，自1970年以来的秒数     
			"nonceStr":"e61463f8efa94090b1f366cccfbbb444", //随机串     
			"package":"prepay_id=u802345jgfjsdfgsdg888",     
			"signType":"MD5",         //微信签名方式：     
			"paySign":"70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名 
		},
		function(res){     
			if(res.err_msg == "get_brand_wcpay_request:ok" ) {
				alert("支付成功");
		 }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
			 else{
				 alert("支付失败");
			}
		 }
	); 
}
	
$(function(){
	$.post("personal.action",{},function(obj){
    		if (obj.status == "1") {
    			var user = obj.data;
    			$("#icon").attr('src',user.headimageurl);
    			$("#nickname").html(user.nickname);
    			if (user.integral>5999) {
    				$(".member_live").html("铂金会员");
    			}else if (user.integral>1999) {
					$(".member_live").html("黄金会员");
    			}else if (user.integral>99) {
					$(".member_live").html("白银会员");
    			}else{
    				$(".member_live").html("普通用户");
    			}
    			$(".member_integral").html(user.memberpoints);
    		}else{
    			alert(error);
    		}
    	},'json');
});