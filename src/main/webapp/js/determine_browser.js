var userid = "";
var packageid = "";
var select = "";
function ShowMessage() { 
    var thisURL = decodeURI(location.href);    
    var getval  = thisURL.split('?')[1];
    userid = getval.split("=")[1].split("&")[0];
    packageid = getval.split("=")[2].split("&")[0];
    select = getval.split("=")[3];
    if (isWeiXin()) {
    	alert("我是微信浏览器");
    }else{
        window.location.href="dspay.action?userid="+userid+"&packageid="+packageid+"&select="+select;
    }
} 
window.onload=ShowMessage(); 



function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}

$(function(){
	$(".browser").click(function(){
    	alert("打开浏览器");
	});
	$(".pay_finish_text").click(function(){
		window.location.href="ds_pay.html?userid="+userid;
	});
});