var nickname = "";
function ShowMessage() 
{ 
   var thisURL = decodeURI(location.href);  
   var getval = thisURL.split('?')[1];  
   nickname = getval.split("=")[1];  
} 
window.onload=ShowMessage(); 

$(function(){
	$("input[name='nickname']").val(nickname);
	$(".clear").click(function(){
		$("input[name='nickname']").val("").focus(); // 清空并获得焦点
	});

	$(".save").click(function(){
		nickname = $("#nickname").val();
		window.location.href="user.html";
//		$.post("学校接口",{"nickname":nickname},function(obj){	
//		},'json');
	});
});