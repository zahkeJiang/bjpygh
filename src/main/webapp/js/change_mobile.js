/*获取userid*/
		var thisURL = document.URL;    
		var userId = thisURL.split('?')[1];  
		var userid = userId.split("=")[1].split("&")[0];  
		var oldmobile = userId.split("=")[2];
		function  getuserId(){  
  			alert("mobile接收到的userid为"+userid); 
  			document.getElementById('oldmobile').innerText = oldmobile;
  		}
		var newnumber = /^1[3|4|5|8]\d{9}$/;
		var number = "";
		var mobile = "";
		var code = "";
  		function setCode(){
	 		mobile=document.getElementById("tel").value;//获取发送验证码时输入框内手机号
	 		if (newnumber.test(mobile)) {
	 			code = "";
	 			var codeLength = 4;//设置验证码长度 
	 			var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);//验证码候选数字
	 			for (var i = 0; i < codeLength; i++) {
	 				var charIndex = Math.floor(Math.random() * 10);//向下取整0-10的随机数
	 				code += selectChar[charIndex];
	 			}
	 			alert("mobile="+mobile+" "+"tpl_value="+code);
	 			//创建线程，将验证码与手机号发送后端
	 			var xhr = new XMLHttpRequest();
		    	xhr.onreadystatechange = function(){
		    		console.log("onreadystatechange: "+xhr.readyState);
			 		if(xhr.readyState === 4){
			 			// alert("数据接受:"+xhr.status);
			 			if (xhr.status === 200) {

			 				// alert("数据:"+xhr.responseText);
			 				// document.getElementById("p").innerHTML = xhr.responseText;
			 					
			 				/*点击发送验证码后更改样式*/
							// var countdown = 60;
    			// 			function time(obj){
    			// 				if (countdown == 0) { 
       //  							obj.removeAttribute("disabled");    
       //  							obj.value="免费获取验证码"; 
       //  							return;
    			// 				} else { 
       //  							obj.setAttribute("disabled", true); 
       //  							obj.value="重新发送(" + countdown + ")"; 
       //  							countdown--; 
       //  							setTimeout(function() { 
    			// 						setCode(obj)
    			// 					},1000) 
    			// 				} 
    			// 			}
			 			}
			 		}
		    	}
				xhr.open( "POST", "sms.action");
				xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				var data="mobile="+mobile+"&tpl_id=37597"+"&tpl_value="+code;
				xhr.send(data);// 发送HTTP请求
	 		}else{
	 			alert("请输入正确的手机号码！");
	 		}   
  		}

  		/*确认更改手机号并提交*/
		function  bt(){
			newcode = document.getElementById("getcode").value;//获取此刻输入框验证码
			number = document.getElementById("tel").value;//获取此刻输入框手机号
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
			 						alert(obj.status);
			 						if (obj.status == 1) {
			 							alert("成功更换手机号，即将跳转到user页面");
			 							location.href="user.html?userid="+userid;
			 						}else{
			 							alert("手机号已被注册");
			 						}
			 					}
			 				}
		    			}
						xhr.open( "POST", "bond.action");
						xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
						var data="phonenumber="+mobile+"&userid="+userid;
						xhr.send(data);// 发送HTTP请求
						
					}else{
						alert("验证码输入错误1");
					}
				}else{
					alert("验证码输入错误2");
				}
			}else{
				alert("请输入正确的手机号码！");
			}
		}