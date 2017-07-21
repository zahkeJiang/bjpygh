/*获取userid*/
var thisURL = document.URL;    
var getval = thisURL.split('?')[1];  
var userid = getval.split("=")[1].split("&")[0];  
var oldmobile = getval.split("=")[2];
function  getuserId(){  
  document.getElementById('oldmobile').innerText = oldmobile;
}
function change_mobile(){
	location.href="change_mobile.html?userid="+userid+"&oldmobile="+oldmobile;
}