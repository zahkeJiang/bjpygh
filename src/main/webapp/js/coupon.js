$(function(){
	$.post("queryCoupon.action",{},function(obj){
		if (obj.status=="0") {
			var coupon_hint = "<div class='coupons_hint_box'><div class='coupon_hint'><div class='nohint'><p class='nohint_no'>没有券</p><p class='nohint_look'>去优惠活动里进行看看吧</p><a href='lottery.html'>去看看</a></div></div></div>"
			$(".container").html(coupon_hint);
		}else{
			var coupon = "<div class='coupon'><img src='images/coupon.jpg' ><p>"+obj.price+"元</p><div class='coupon_hint_text'><h2>优惠提示:</h2><p>30天有效期，从领取日开始计时</p><p>过期可使用会员积分激活继续使用</p></div><div class='use_coupon'></div></div>"
			$(".container").html(coupon);
			if (obj.status == "1" || obj.status == "2") {
    			$(".use_coupon").html("立即使用");
    			$(".use_coupon").click(function(){
    				window.location.href="index.html";
    			});
    		}else if (obj.status == "3") {
    			$(".use_coupon").html("立即激活并使用");
    			$(".use_coupon").click(function(){
    				$.post("activation.action",{},function(obj){
						if (obj.status == "1") {
							alert("激活成功");
						}else{
							alert("激活失败");
						}
					});
				});	
    		}
		}
    },'json');	
});

