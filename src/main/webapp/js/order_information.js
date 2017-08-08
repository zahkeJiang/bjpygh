
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
						if(obj.status == "1"){
							alert("已送达");
						}else{
							alert("提交失败");
						}
					},'json');
				});
				var result = "<div class='result'><p>您已报名完成。</p><p>欢迎您再次使用。</p><p>北京漂洋过海，一切因你而在！</p></div>";
				$(".footer").html(result);
			}else if (userorder.orderstatus=="4") {
				$(".chedule_content").empty();
				$(".chedule_content").html("交易完成");
				$(".delivery").html("已送达");
				$(".delivery").css({"background-color":"#d2e9ff","display":"inline"});
				var result = "<div class='result'><p>您已报名完成。</p><p>欢迎您再次使用。</p><p>北京漂洋过海，一切因你而在！</p></div>";
				$(".footer").html(result);
			}
		}
	},"json");
	
});
