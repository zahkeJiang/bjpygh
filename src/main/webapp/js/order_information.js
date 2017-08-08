
$(function(){
	$.post("schedule.action",{},function(obj){
		if (obj.status=="1") {
			var userorder = obj.data;
			$(".realname").html(userorder.realname);
			$(".tel").html(userorder.phonenumber);
			$(".address").html(userorder.address);
			$(".ds_training_mode").html(userorder.note);
			$(".ds_name").html(userorder.dsname);
			alert(userorder.dstype);
			alert(userorder.dstype+"/1");
			alert(userorder.dstype"/");
			$(".ds_type").html(userorder.dstype+"/"+userorder.models+"/"+traintime);
			$(".description").html(userorder.description);
			$(".order_number").html(userorder.ordernumber);
			$(".order_time").html(userorder.paytime);
			$(".ds_pay").html("¥"+userorder.orderprice+".00");
			$(".ds_price").html("¥"+(userorder.orderprice+obj.price)+".00");
			$(".ds_coupon").html("¥"+obj.price+".00");

			if (userorder.orderstatus=="1"||userorder.orderstatus=="2") {
				var refund = "<div class='refund'>取消订单</div>";
				$(".footer").html(refund);
				$(".refund").click(function(){
					window.location.href="ds_refund.html?ordernumber="+userorder.ordernumber;
				});
				if (userorder.orderstatus=="2") {
					$(".submit_order p").css({"color":"#67c0ee"});
					$(".material_audit p").css({"color":"#67c0ee"});
					$("#cartoon2").attr('src',"images/alipay.png");
					$(".hint").html("您的材料正在接受审核，24小时之内审核完成。");
				}else{
					$(".submit_order p").css({"color":"#67c0ee"});
					// var cartton = "<img src='images/alipay.png' height="30px">"
					$("#cartoon").attr('src',"images/alipay.png");
					$(".hint").html("您的订单已经提交成功。请准备好所需材料，24小时之内将会有工作人员与您联系上门提取，请保持电话畅通。");
				}
			}else if (userorder.orderstatus=="3"||userorder.orderstatus=="4") {
				var result = "<div class='result'><p>您已报名完成。</p><p>欢迎您再次使用。</p><p>北京漂洋过海，一切因你而在！</p></div>";
				$(".footer").html(result);
				if (userorder.orderstatus=="4") {
					$(".submit_order p").css({"color":"#67c0ee"});
					$(".material_audit p").css({"color":"#67c0ee"});
					$(".finish_order p").css({"color":"#67c0ee"});
					$(".material_return p").css({"color":"#67c0ee"});
					$(".hint").html("您已完成报名，材料正在返还。");
				}else{
					$(".submit_order p").css({"color":"#67c0ee"});
					$(".material_audit p").css({"color":"#67c0ee"});
					$(".finish_order p").css({"color":"#67c0ee"});
					$(".hint").html("您已报名完成，北京漂洋过海，一切因你而在！欢迎您再次使用！");
				}
			}

		}
	},"json");
	
});
