$(function(){
	$.post("personal.action",{},function(obj){
		if (obj.status=="1") {
			var integral = obj.data;
			$(".my_integral").html(integral.memberpoints);
		}
	},"json");
	$(".recharge").click(function(){
		window.location.href="recharge.html";
	});
	$(".integral_type").click(function(){
		alert("正在建设中..");
	});
});
		