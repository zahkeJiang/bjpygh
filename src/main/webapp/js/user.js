

$(function(){
	//获取用户信息
	userload();
	//跳转到用户昵称页面
	$(".nickname").click(function(){
		var nickname = $("#nickname").html();
  		var nicknameurl="nickname.html?nickname="+nickname;   
        window.location.assign(encodeURI(nicknameurl));   
	});
	//跳转到更换手机号页面
	$(".mobile").click(function(){
		var oldmobile = $("#mobile").html();
  		window.location.href="mobile.html?mobile="+oldmobile;   
	});
	//跳转我的订单
  	$(".orders").click(function(){
    	window.location.href="myorder.html";
  	});
  //跳转到会员页面
	$(".member").click(function(){
  		window.location.href="member.html";  
  	})
  	//跳转到更换学校页面
	$(".school").click(function(){
		var school = $("#school").html();
		var schoolurl="school.html?school="+school;   
        window.location.assign(encodeURI(schoolurl));
	});
	  	//跳转到更换学校页面
	$(".city").click(function(){
		var city = $("#city").html();
		window.location.href="address.html";   
	});
  	 //跳转到性别页面
	$(".sex").click(function(){
		var usersex = $("#sex").html();
		var sex = "";
		if (usersex=="男") {
			sex = 1;
		}else if (usersex == "女") {
			sex = 0;
		}else{
			sex = "";
		}
  		window.location.href="sex.html?sex="+sex;  
  	})
});


function userload(){
$.post("personal.action",{},function(obj){

	if (obj.status=="1") {
		var userobj = obj.data;
		// document.getElementById('icon').src=obj.headimageurl;
		$("#icon").attr('src',userobj.headimageurl);
		$('#nickname').html(userobj.nickname);
		$('#mobile').html(userobj.phonenumber);
		if (userobj.sex!=null) {
			if(userobj.sex=="1"){
				$('#sex').html("男");
			}else{
				$('#sex').html("女");
			}
		}else{
			$('#sex').html("");
		}
		if (userobj.school!=null) {
		  	$('#school').html(userobj.school);
		}else{
		  	$('#school').html("");
		}
		if (userobj.city!=null) {
		  	$('#city').html(userobj.city);
		}else{
		  	$('#city').html("");
		}
		var integral = userobj.integral;
		if (integral>4999) {
		  	$('#integral').html("铂金会员");
	    }else if (integral>499) {
	    	$('#integral').html("黄金会员");
	    }else if (integral>14) {
	    	$('#integral').html("白银会员");
	    }else{
	    	$('#integral').html("普通用户");
	    }
	}
},'json');
}
