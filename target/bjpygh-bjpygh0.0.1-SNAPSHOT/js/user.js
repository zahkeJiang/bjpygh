/*获取userid*/
var thisURL = document.URL;    
var getval = thisURL.split('?')[1];  
var userid = getval.split("=")[1];  
function getuserId(){  
  alert(userid); 
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function(){
		console.log("onreadystatechange: "+xhr.readyState);
		if(xhr.readyState === 4){
		  // alert("数据接受:"+xhr.status);
			if (xhr.status === 200) {
        /*解析json字符串*/
			 	// alert("获取信息成功，userid="+userid);
			 	var obj = eval('(' + xhr.responseText + ')');//json是以字符串传过来，需要对其进行转成对象。
//			 	alert("province="+obj.province+" "+"userid="+obj.userid+" "+"openid="+obj.openid+" "+"nickname="+obj.nickname+" "+"sex="+obj.sex+" "+"city="+obj.city+" "+"mobile="+obj.phonenumber+" "+"school="+obj.school);
			 	document.getElementById('icon').src=obj.headimageurl;
  			document.getElementById('nickname').innerText = obj.nickname;
  			document.getElementById('mobile').innerText = obj.phonenumber;
  			if (obj.sex!=null) {
  				document.getElementById('sex').innerText = obj.sex;
  			}else{
  				document.getElementById('sex').innerText = "未设置";
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
			}
		}
	}
	xhr.open( "POST", "personal.action");
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var data="userid="+userid;
	xhr.send(data);// 发送HTTP请求 
}
/*跳转到更换手机号页面*/
function change_mobile(){
  var oldmobile = document.getElementById("mobile").innerText;
  location.href="mobile.html?userid="+userid+"&mobile="+oldmobile;   
}

$(function(){
  $(".orders").click(function(){
    window.location.href="myorder.html?userid="+userid;
  });
});