/*获取userid*/
var thisURL = document.URL;    
var userId = thisURL.split('?')[1];  
var userid = userId.split("=")[1].split("&")[0];  
var oldmobile = userId.split("=")[2];
function  getuserId(){  
  alert("mobile接收到的userid为"+userid); 
  alert("mobile接收到的oldmobile为"+oldmobile); 
  document.getElementById('oldmobile').innerText = oldmobile;
}
function change_mobile(){
	location.href="change_mobile.html?userid="+userid+"&oldmobile="+oldmobile;
}