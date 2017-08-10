$(function(){
    getLocation();
});
function getLocation()  {  
    if (navigator.geolocation)  {  //浏览器支持geolocation
        navigator.geolocation.getCurrentPosition(showPosition,showError);  
    }else{
        alert("浏览器不支持定位");
    }  
}  
  
  function showPosition(position)
{
  //返回用户位置
  //经度
   var longitude =position.coords.longitude;
  //纬度
  var latitude = position.coords.latitude;
  alert('经度'+longitude+'，纬度'+latitude);
  //根据经纬度获取地理位置，不太准确，获取城市区域还是可以的
  var map = new BMap.Map("allmap");
  var point = new BMap.Point(longitude,latitude);
  var gc = new BMap.Geocoder();
  gc.getLocation(point, function(rs){
    var addComp = rs.addressComponents;
    var location = "<img src='images/address.png' width='20px' height='20px'>"+"&nbsp;"+addComp.country+"&nbsp;"+addComp.province + "&nbsp;" + addComp.city+"&nbsp;"+addComp.district;
    $(".getlocation").html(location);
  });   

}
  
function showError(error){  
  switch(error.code){  
    case error.PERMISSION_DENIED:  //Permission denied - 用户不允许地理定位
       alert("定位失败,用户拒绝请求地理定位"); 
      break;  
    case error.POSITION_UNAVAILABLE:  //Position unavailable - 无法获取当前位置
      alert("定位失败,位置信息是不可用"); 
      break;  
    case error.TIMEOUT:  //Timeout - 操作超时
     alert("定位失败,请求获取用户位置超时"); 
      break;  
    case error.UNKNOWN_ERROR:  
      alert("定位失败,定位系统失效"); 
      break;  
    }  
  }  