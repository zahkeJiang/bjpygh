var city = "";
function ShowMessage() 
{ 
   var thisURL = decodeURI(location.href);  
   var getval = thisURL.split('?')[1];  
   city = getval.split("=")[1];  
   alert(city);
} 
window.onload=ShowMessage(); 

// var data ={"福建":{"山东","上海"},"北京":{"广东","重庆"}}
// $(function(){
// 	alert(data[0]);
// });