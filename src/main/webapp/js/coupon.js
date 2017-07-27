$(function(){
	$(".coupon").hide();
	$(".a").hide();
	$.post("queryCoupon.action",{},function(obj){
    		if (obj.status == "1") {
    			$(".activation").hide();
				$(".price").html(obj.price);
				$(".coupon").show();
				$(".effective").hide();
				$(".a").show();
				$(".nohint").hide();
    		}else if (obj.status == "2") {
    			$(".activation").show();
    			$(".price").html(obj.price);
				$(".effective").html("已失效");
				$(".coupon").show();
				$(".nohint").hide();
    		}else if (obj.status == "3") {
    			$(".activation").hide();
				$(".price").html(obj.price);
				$(".coupon").show();
				$(".effective").html("已使用");
				$(".nohint").hide();
    		}else {
    			$(".coupon").hide();
    			$(".nohint").show();
    		}
    	},'json');
	$(".activation").click(function(){
		$.post("activation.action",{},function(obj){
			if (obj.status == "1") {
				$(".activation").hide();
				$(".effective").html("可用");
			}else{
				alert("激活失败");
			}
		});
	});
});