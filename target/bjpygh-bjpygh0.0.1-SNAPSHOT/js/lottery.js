
$(function(){
	$.post("queryCoupon.action",{},function(obj){
    		if (obj.status == "1") {
				$(".couponPrice").html(obj.price+"元");
    		}else{
    			$(".couponPrice").html("试试手气-.-");
    		}
    	},'json');

	$(".getCoupon").click(function(){
    	$.post("coupon.action",{},function(obj){
    		if (obj.status == "1"){
    			alert("恭喜获得"+obj.price+"元优惠券");
    			$(".couponPrice").html(obj.price+"元");
    		}else{
    			alert("您已有优惠券");
    		}
    	},'json');
	});
});