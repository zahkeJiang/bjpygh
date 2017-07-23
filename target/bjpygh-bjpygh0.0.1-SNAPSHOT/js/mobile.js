/*获取userid*/
var thisURL = document.URL;    
var getval = thisURL.split('?')[1];
var oldmobile = getval.split("=")[1];
function  getuserId(){  
  document.getElementById('oldmobile').innerText = oldmobile;
}
function change_mobile(){
	location.href="change_mobile.html?oldmobile="+oldmobile;
}