$(function(){
	$.post("queryCoupon.action",{},function(obj){
    		if (obj.status == "1") {
    			$(".coupons_hint_box").empty();
				$(".price").html(obj.price);
    		}else if (obj.status == "2") {
    			$(".coupons_hint_box").empty();
    			$(".price").html(obj.price);
    		}else if (obj.status == "3") {
    			$(".coupons_hint_box").empty();
				$(".price").html(obj.price);
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