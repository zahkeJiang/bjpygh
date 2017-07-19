var userid = "";
var packageid = "";
var select = "";
function ShowMessage() { 
    var thisURL = decodeURI(location.href);    
    var userId = thisURL.split('?')[1];
    userid = userId.split("=")[1].split("&")[0];
    packageid = userId.split("=")[2].split("&")[0];
    select = userId.split("=")[3];
    if (isWeiXin()) {
    	alert("我是微信浏览器");
    }else{
    	/*$(".micromessenger").css({"display":"none"});
    	$.ajax({
    		type:"POST",
        	url:"dspay.action",
        	dataType:"text",
        	data:"userid="+userid+"&packageid="+packageid+"select="+select,
    	});*/
        window.location.href("dspay.action?userid="+userid+"&packageid="+packageid+"&select="+select);
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
    	$.ajax({
    		type:"POST",
        	url:"sdp.action",
        	dataType:"text",
        	data:"userid="+userid+"&packageid="+packageid+"select="+select,
    	});
	})
	$(".pay_finish_text").click(function(){
		alert("完成付款");
	});
});