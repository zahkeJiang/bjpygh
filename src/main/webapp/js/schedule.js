$(function(){
	$(".nohint").hide();
	$(".header").hide();
	$.post("schedule.action",{},function(obj){
		if (obj.status == "1") {
			var data = obj.data;
			if (data.orderstatus == "1") {
				$(".header").show();
				$(".recive").hide();
				$(".nohint").hide();
				$(".pro1").css("background-color","#00c800");
				$(".pro2").css("background-color",grey);
				$(".pro3").css("background-color",grey);
				$(".progress_bar").css("width","27%");
				$(".descrip").html("订单支付完成，请准备好报名所需材料，等待专员接收.....");
			}else if (data.orderstatus == "2") {
				$(".header").show();
				$(".recive").hide();
				$(".nohint").hide();
				$(".pro1").css("background-color","#00c800");
				$(".pro2").css("background-color","#00c800");
				$(".pro3").css("background-color",grey);
				$(".progress_bar").css("width","59%");
				$(".descrip").html("材料接收完成，正在报名中.....");
			}else if (data.orderstatus == "3") {
				$(".header").show();
				$(".recive").show();
				$(".nohint").hide();
				$(".pro1").css("background-color","#00c800");
				$(".pro2").css("background-color","#00c800");
				$(".pro3").css("background-color","#00c800");
				$(".progress_bar").css("width","90%");
				$(".descrip").html("报名完成，返还材料中，收到材料请点确认收到.....");
			}else if(data.orderstatus == "4"){
				$(".header").show();
				$(".recive").hide();
				$(".nohint").hide();
				$(".pro1").css("background-color","#00c800");
				$(".pro2").css("background-color","#00c800");
				$(".pro3").css("background-color","#00c800");
				$(".progress_bar").css("width","90%");
				$(".descrip").html("报名成功了，可以进行网上理论知识学习了！:)");
			}else{
				$(".nohint").show();
				$(".header").hide();
			}
		}else{
			$(".nohint").show();
			$(".header").hide();
		}
	},'json');
	$(".recive").click(function(){
		$.post("changeStatus.action",{},function(obj){
			if(obj.status == "1"){
				alert("提交成功");
				$(".recive").hide();
			}else{
				alert("提交失败");
			}
		},'json');
	});
});