/*获取userid*/
/*var thisURL = document.URL;    
var getval = thisURL.split('?')[1];  
var userid = getval.split("=")[1];  */
/*function getuserId(){  
//  alert(userid); 
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function(){
		console.log("onreadystatechange: "+xhr.readyState);
		if(xhr.readyState === 4){
		  // alert("数据接受:"+xhr.status);
			if (xhr.status === 200) {
        //解析json字符串
			 	// alert("获取信息成功，userid="+userid);
			 	var result = eval('(' + xhr.responseText + ')');//json是以字符串传过来，需要对其进行转成对象。
//			 	alert("province="+obj.province+" "+"userid="+obj.userid+" "+"openid="+obj.openid+" "+"nickname="+obj.nickname+" "+"sex="+obj.sex+" "+"city="+obj.city+" "+"mobile="+obj.phonenumber+" "+"school="+obj.school);
			 	if(result.status == 1){
			 		var obj = result.data;
			 		document.getElementById('icon').src=obj.headimageurl;
		  			document.getElementById('nickname').innerText = obj.nickname;
		  			document.getElementById('mobile').innerText = obj.phonenumber;
		  			
		  			if (obj.sex!=null) {
		  				if(obj.sex=="1"){
		  					document.getElementById('sex').innerText = "男";
		  				}else{
		  					document.getElementById('sex').innerText = "女";
		  				}
		  			}else{
		  				document.getElementById('sex').innerText = "未设置";
		  			}
		  			var integral = obj.integral;
		  			if (integral>5999) {
		  				document.getElementById('integral').innerText = "铂金会员";
	    			}else if (integral>1999) {
	    				document.getElementById('integral').innerText = "黄金会员";
	    			}else if (integral>99) {
	    				document.getElementById('integral').innerText = "白银会员";
	    			}else{
	    				document.getElementById('integral').innerText = "普通用户";
	    			}
		  			if (obj.school!=null) {
		  				document.getElementById('school').innerText = obj.school;
		  			}else{
		  				document.getElementById('school').innerText = "未设置";
		  			}
		  			if (obj.city!=null) {
		  				document.getElementById('city').innerText = obj.city;
		  			}else{
		  				document.getElementById('city').innerText = obj.province;
		  			}
			 }else{
				 alert(result.msg);
			 }
			 	
			}
		}
	}
	xhr.open( "POST", "personal.action");
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
//	var data="userid="+userid;
	xhr.send();// 发送HTTP请求 
}*/




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
		$("#icon").attr('src',user.headimageurl);
		$('#nickname').html(userobj.nickname);
		$('#mobile').html(userobj.phonenumber);
		if (userobj.sex!=null) {
			if(userobj.sex=="1"){
				$('#sex').html("男");
			}else{
				$('#sex').html("女");
			}
		}else{
			$('#sex').html("未设置");
		}
		if (userobj.school!=null) {
		  	$('#school').html(userobj.school);
		}else{
		  	$('#school').html("未设置");
		}
		if (userobj.city!=null) {
		  	$('#city').html(userobj.city);
		}else{
		  	$('#city').html("未设置");
		}
		var integral = obj.integral;
		if (integral>5999) {
		  	$('#integral').html("铂金会员");
	    }else if (integral>1999) {
	    	$('#integral').html("黄金会员");
	    }else if (integral>99) {
	    	$('#integral').html("白银会员");
	    }else{
	    	$('#integral').html("普通用户");
	    }
	}
},'json');
}
