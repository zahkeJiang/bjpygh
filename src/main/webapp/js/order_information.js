var orderschedule = '<div class="progress_pic"><div class="progress_pic_style"><div class="progress_bg"></div><img id="cartoon1" src="images/alipay.png" height="30px" width="30px"><div class="submit_bg"></div></div><div class="progress_pic_style"><div class="audit_bg"></div><img id="cartoon2" src="images/close.png" height="30px" width="30px"><div class="audit_bg"></div></div><div class="progress_pic_style"><div class="finish_bg"></div><img id="cartoon3" src="images/close.png" height="30px" width="30px"><div class="finish_bg"></div></div><div class="progress_pic_style"><div class="return_bg"></div><img id="cartoon4" src="images/close.png" height="30px" width="30px"><div class="progress_bg"></div></div></div><div class="orderprogress"><div class="submit_order"><div class="progress_style"><h2 >提交报名</h2><p class="submit_data">&nbsp;</p><p class="submit_time">&nbsp;</p></div></div><div class="material_audit"><div class="progress_style"><h2>材料审核</h2><p class="audit_data">&nbsp;</p><p class="audit_time">&nbsp;</p></div></div><div class="finish_order"><div class="progress_style"><h2>完成报名</h2><p class="finish_data">&nbsp;</p><p class="finish_time">&nbsp;</p></div></div><div class="material_return"><div class="progress_style"><h2>材料返还</h2><p class="return_data">&nbsp;</p><p class="return_time">&nbsp;</p></div></div></div>';
$(function(){
	$.post("schedule.action",{},function(obj){
		if (obj.status=="1") {
			var userorder = obj.data;
			$(".realname").html(userorder.realname);
			$(".tel").html(userorder.phonenumber);
			$(".address").html(userorder.address);
			$(".ds_training_mode").html(userorder.note);
			$(".ds_name").html(userorder.dsname);
			$(".ds_type").html(userorder.dstype+"&nbsp;&frasl;&nbsp;"+userorder.models+"&nbsp;&frasl;&nbsp"+userorder.traintime);
			$(".description").html(userorder.description);
			$(".order_number").html(userorder.ordernumber);
			$(".order_time").html(userorder.paytime);
			$(".ds_pay").html("¥&nbsp;"+userorder.orderprice+".00");
			$(".ds_price").html("¥&nbsp;"+(userorder.orderprice+obj.price)+".00");
			$(".ds_coupon").html("¥&nbsp;"+obj.price+".00");
			
			if (userorder.orderstatus=="1"||userorder.orderstatus=="2") {
				$(".shedule").html(orderschedule);
				var refund = "<div class='refund'>取消订单</div>";
				$(".footer").html(refund);
				$(".refund").click(function(){
					window.location.href="ds_refund.html?ordernumber="+userorder.ordernumber;
				});
				if (userorder.orderstatus=="2") {
					var submitTime = userorder.paytime;
					var submit_data = submitTime.split(' ')[0];
					var submit_time = submitTime.split(" ")[1];
					$(".submit_data").html(submit_data);
					$(".submit_time").html(submit_time);

					var audittime = userorder.submittime;
					var audit_data = audittime.split(' ')[0];
					var audit_time = audittime.split(" ")[1];
					$(".audit_data").html(audit_data);
					$(".audit_time").html(audit_time);

					$(".material_audit p").css({"color":"#67c0ee"});
					$(".material_audit h2").css({"color":"#67c0ee"});
					$("#cartoon2").attr('src',"images/alipay.png");
					$(".audit_bg").css({"background-color":"#67c0ee"});
					$(".hint").html("您的材料正在接受审核，24小时之内审核完成。");
				}else{
					var submitTime = userorder.paytime;
					var submit_data = submitTime.split(' ')[0];
					var submit_time = submitTime.split(" ")[1];
					$(".submit_data").html(submit_data);
					$(".submit_time").html(submit_time);

					$(".hint").html("您的订单已经提交成功。请准备好所需材料，24小时之内将会有工作人员与您联系上门提取，请保持电话畅通。");
				}
			}else if (userorder.orderstatus=="3") {
				$(".shedule").html(orderschedule);
				var submitTime = userorder.paytime;
				var submit_data = submitTime.split(' ')[0];
				var submit_time = submitTime.split(" ")[1];
				$(".submit_data").html(submit_data);
				$(".submit_time").html(submit_time);

				var audittime = userorder.submittime;
				var audit_data = audittime.split(' ')[0];
				var audit_time = audittime.split(" ")[1];
				$(".audit_data").html(audit_data);
				$(".audit_time").html(audit_time);

				var finishtime = userorder.signtime;
				var finish_data = finishtime.split(' ')[0];
				var finish_time = finishtime.split(" ")[1];
				$(".finish_data").html(finish_data);
				$(".finish_time").html(finish_time);

				$(".material_audit p").css({"color":"#67c0ee"});
				$(".material_audit h2").css({"color":"#67c0ee"});
				$("#cartoon2").attr('src',"images/alipay.png");
				$(".audit_bg").css({"background-color":"#67c0ee"});

				$(".finish_order p").css({"color":"#67c0ee"});
				$(".finish_order h2").css({"color":"#67c0ee"});
				$("#cartoon3").attr('src',"images/alipay.png");
				$(".finish_bg").css({"background-color":"#67c0ee"});
				$(".hint").html("您已完成报名，材料正在返还的路上，请注意查收。");

				$(".delivery").css({"display":"inline"});
				//已送达接口
				$(".delivery").click(function(){
					$.post("changeStatus.action",{},function(obj){
						$(".chedule_content").empty();
						$(".chedule_content").html("交易完成");
						$(".chedule_content").css({"height":"60px","padding":"0 16px","line-height":"60px","font-size":"20px","color":"white","background-color":"#eac100"});
						$(".hint").empty();
						$(".hint").html("材料已送达，欢迎您再次使用！");
						$(".delivery").html("已送达");
						$(".delivery").css({"background-color":"#d2e9ff","display":"inline","text-align":"center","line-height":"28px"});
					},'json');
				});
				$('delivery').unbind("click"); //移除点击事件
				var result = "<div class='result'><p>您已报名完成。</p><p>欢迎您再次使用。</p><p>北京漂洋过海，一切因你而在！</p></div>";
				$(".footer").html(result);
			}else if (userorder.orderstatus=="4") {
				$(".chedule_content").empty();
				$(".chedule_content").html("交易完成");
				$(".chedule_content").css({"height":"60px","padding":"0 16px","line-height":"60px","font-size":"20px","color":"white","background-color":"#eac100"});
				$(".hint").empty();
				$(".hint").html("材料已送达，欢迎您再次使用！");
				$(".delivery").html("已送达");
				$(".delivery").css({"background-color":"#d2e9ff","display":"inline","text-align":"center","line-height":"28px"});
				var result = "<div class='result'><p>您已报名完成。</p><p>欢迎您再次使用。</p><p>北京漂洋过海，一切因你而在！</p></div>";
				$(".footer").html(result);
			}
		}
	},"json");
	
});
