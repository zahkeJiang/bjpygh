var userid = "";
var dsname = "";
var dstype = "";
var models = "";
var price = "";
var packageid = "";
var traintime = "";

function ShowMessage() { 
    var thisURL = decodeURI(location.href);    
    var userId = thisURL.split('?')[1];
    userid = userId.split("=")[1].split('&')[0];
    dsname = userId.split("=")[2].split('&')[0];
    dstype = userId.split("=")[3].split('&')[0];
    models = userId.split("=")[4].split('&')[0];
    price = userId.split("=")[5].split('&')[0];
    packageid = userId.split("=")[6].split('&')[0];
    traintime = userId.split("=")[7];
} 
window.onload=ShowMessage(); 

var realname="";
var address = "";
var note = "";
var select="0";
$(function(){
	$(".ds_type").html(dstype);
	$(".ds_name").html(dsname);
	$(".ds_models").html(models);
	$(".ds_price").html(price);
	$(".traintime").html(traintime);
	var a = "";
	$(".price").html(price-a);
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