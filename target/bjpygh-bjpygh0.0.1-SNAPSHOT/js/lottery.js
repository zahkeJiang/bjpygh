
$(function(){
	$(".Price").hide();
	$(".couponPrice").hide();
	$.post("queryCoupon.action",{},function(obj){
    		if (obj.status != "0") {
				$(".Price").html(obj.price+"元");
				$(".Price").show();
    		}else{
    			$(".couponPrice").show();
    			$(".couponPrice").html("试试手气-.-");
    			$(".Price").hide();
    		}
    	},'json');

	$(".couponPrice").click(function(){
    	$.post("coupon.action",{},function(obj){
    		if (obj.status == "1"){
    			alert("恭喜获得"+obj.price+"元优惠券");
    			$(".couponPrice").hide();
    			$(".Price").show();
    			$(".Price").html(obj.price+"元");
    		}
    	},'json');
	});
});