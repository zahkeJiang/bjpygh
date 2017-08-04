
	
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
                $(".coupons_sum").html(obj.count);
    			$(".member_integral").html(user.memberpoints);
    		}else{
    			alert(error);
    		}
    	},'json');
});