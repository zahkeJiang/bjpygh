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

var realname="";
var address = "";
var note = "";
var select="0";

//获取优惠券金额
function get_coupons(){
	$.ajax({
        type:"POST",
        url:"queryCoupon.action",
        data:"userid="+userid,
        dataType:"text",
        success:function(data){
			var obj = eval('(' + data + ')');
			if (obj.status=="1") {
				$(".coupons span").html("右可用优惠券");
			}else{
				$(".coupons span").html("无可用优惠券");
			}
        }	
    });
}
$(function(){
	$(".ds_type").html(dstype);
	$(".ds_name").html(dsname);
	$(".ds_models").html(models);
	$(".ds_price").html(price);
	$(".traintime").html(traintime);
	get_coupons();
	var coupons_sum = "";
	$(".price").html(price-coupons_sum);
	$(".submit").click(function(){
		//获取用户姓名、联系方式、性别、地址
		realname = $("#real_name").val();
		address = $("#address").val();
		note = $("#note").val();
		if ($("#real_name").val()=="") {
			alert("请输入您的真实姓名");
		}else if ($("#address").val() == "") {
			alert("请输入您的地址");
		}else {
			$.ajax({
        		type:"POST",
        		url:"note.action",
        		data:"userid="+userid+"&realname="+realname+"&address="+address+"&note="+note,
        		dataType:"text",
        		success:function(data){
					window.location.href="determine_browser.html?userid="+userid+"&packageid="+packageid+"&select="+select;	
        		},
       			error:function(obj){
            		alert(error);
        		}
    		});
		}
	});
});