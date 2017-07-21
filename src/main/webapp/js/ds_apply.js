
var userid = "";
var dsname = "";
var dstype = "";
var models = "";
var price = "";
var packageid = "";
var traintime = "";

function ShowMessage() { 
    var thisURL = decodeURI(location.href);    
    var getval = thisURL.split('?')[1];
    userid = getval.split("=")[1].split('&')[0];
    dsname = getval.split("=")[2].split('&')[0];
    dstype = getval.split("=")[3].split('&')[0];
    models = getval.split("=")[4].split('&')[0];
    price = getval.split("=")[5].split('&')[0];
    packageid = getval.split("=")[6].split('&')[0];
    traintime = getval.split("=")[7];
} 
window.onload=ShowMessage(); 

$(function(){
	$(".ds_type").html(dstype);
	$(".ds_name").html(dsname);
	$(".ds_models").html(models);
	$(".ds_price").html(price);
	$("#submit").click(function(){
		var myurl="submit_orders.html?userid="+userid+"&dsname="+dsname+"&dstype="+dstype+"&models="+models+"&price="+price+"&packageid="+packageid+"&traintime="+traintime;                                      
        window.location.assign(encodeURI(myurl));
	});
});