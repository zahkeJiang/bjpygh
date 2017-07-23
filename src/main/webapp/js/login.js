/*获取用户userid*/
/*var thisURL = document.URL;    
var getval = thisURL.split('?')[1];  
var userid = getval.split("=")[1];  
function  getuserId(){  
	alert(userid);  
}  */
/*检验手机号*/
var ophone=document.getElementById("tel");
var hintText=document.getElementById("hinttext");
var code="";
var newcode = "";
var mobile = "";//获取手机验证码时所填手机号
var newnumber = /^1[3|4|5|8]\d{9}$/;
var number="";//输入框内输入的手机号
ophone.onblur=function(){
    number=this.value;
    if(newnumber.test(number)){
        hintText.innerText="";
    }
    else{
        hintText.innerText="请输入正确的手机号码！";
    }
	hintText.style.display="block";
}
/*发送验证码*/
var countdown = 60;
function settime(obj) { 
    if (countdown == 0) { 
        obj.removeAttribute("disabled");    
        obj.value="免费获取验证码"; 
        countdown = 60; 
       	return;
    } else { 
        obj.setAttribute("disabled", true); 
        obj.value="重新发送(" + countdown + ")"; 
        countdown--; 
   	} 
    setTimeout(function() { 
    settime(obj) }
    ,1000) 
}
/*发送验证码*/
function setCode(obj) { 				
	if (newnumber.test(number)) {
		code = "";
    	mobile =document.getElementById("tel").value;//获取发送验证码时所输入的电话好号码
    	var codeLength = 4;//设置验证码长度 
    	var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);//验证码候选数字
        for (var i = 0; i < codeLength; i++) {
        	var charIndex = Math.floor(Math.random() * 10);//向下取整0-10的随机数
        	code += selectChar[charIndex];
		}
//		alert("mobile="+mobile+" "+"tpl_value="+code);
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
		    console.log("onreadystatechange: "+xhr.readyState);
			if(xhr.readyState === 4){
			 	// alert("数据接受:"+xhr.status);
			 	if (xhr.status === 200) {
			 		var result = xhr.responseText;
			 		if (result!=null) {
			 			result = JSON.parse(result);
			 			if(result.status == 1){
			 				alert("验证码发送成功");
				 			settime(obj);
			 			}else{
			 				alert(result.msg);
			 			}
			 			
			 		}else{
			 			alert("请输入正确的手机号码!");
			 		}
			 	}
			}
		}
		xhr.open( "POST", "sms.action");
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		var data="mobile="+mobile+"&tpl_id=37597"+"&tpl_value="+code;
		xhr.send(data);// 发送HTTP请求
	}else{
		hintText.innerText="请输入正确的手机号码！";
	}
}

/*注册提交*/
function  bt(){
	newcode = document.getElementById("getcode").value;//获取此刻输入框验证码
	if (newnumber.test(number)) {
		if (mobile!="" && mobile==number) {
			if (code == newcode) {
				var xhr = new XMLHttpRequest();
		    	xhr.onreadystatechange = function(){
		    		console.log("onreadystatechange: "+xhr.readyState);
			 		if(xhr.readyState === 4){
			 			// alert("数据接受:"+xhr.status);
			 			if (xhr.status === 200) {
			 				/*解析json字符串*/
			 				var obj = eval('(' + xhr.responseText + ')');//json是以字符串传过来，需要对其进行转成对象。
//			 				alert(obj.status);
			 				if (obj.status == 1) {
			 					alert("绑定成功，即将跳转");
			 					location.href="user.html";
			 				}else{
			 					alert("手机号已被注册");
			 				}
			 			}
			 		}
		    	}
				xhr.open( "POST", "bond.action");
				xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				var data="phonenumber="+mobile;
				xhr.send(data);// 发送HTTP请求

			}else{
				hintText.innerText="验证码输入有误！";
			}
		}else{
			hintText.innerText="验证码输入有误！";
		}
	}else{
		hintText.innerText="请输入正确的手机号码！";
	}
}