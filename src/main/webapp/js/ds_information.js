/*var data = [{
                    "dstype": "直通车","models":"爱丽舍C1","traintime":"1-5","price":"3400","packageid":"1"
                }, {
                    "dstype": "直通车","models":"爱丽舍C1","traintime":"1-7","price":"3400","packageid":"2"
                }, {
                    "dstype": "定制班","models":"爱丽舍C1","traintime":"1-5","price":"3400","packageid":"3"
                }, {
                    "dstype": "定制班","models":"爱丽舍C1","traintime":"1-7","price":"3400","packageid":"4"
                }]

var dsname="海淀驾校";
var packageid = "";
var userid = "2";
$(function(){
    var dshtml ="";
    var ds_image = "<img src="+" ' "+data.dsimage+" ' "+">";
    $("#ds_image").html(ds_image);
     //$.each循环实现添加  
    $.each(data,function(commentIndex,comment){   
        dshtml += "<div class='dsp_list'><div class='line'></div><h2 class='dstype'>"
                +comment.dstype+"</h2><div class='dsp_infor'><span class='models'>"
                +comment.models+"</span><span class='traintime'>"
                +comment.traintime+"</span><span>一人一车，班车接送</span><span class='packageid'>"
                +comment.packageid+"</span></div><div><span  class='price_symbol'>￥</span><span class='price'>"
                +comment.price+"</span></div></div>";
    });
    
    $('.container').html(dshtml);
    //为班型列表设置点击事件

    $(".dsp_list").click(function(){    
                dstype = $(this).find(".dstype").html(); 
                models = $(this).find(".models").html(); 
                price = $(this).find(".price").html(); 
                traintime = $(this).find(".traintime").html();  
                packageid = $(this).find(".packageid").html();   
               
                var myurl="ds_apply.html?userid="+userid+"&dsname="+dsname+"&dstype="+dstype+"&models="+models+"&price="+price+"&packageid="+packageid+"&traintime="+traintime;                                      
                window.location.assign(encodeURI(myurl));
            })

    // 打开弹窗
    $(".ds_information").click(function(){
            $(".dsintro").fadeIn("100");
    })
    // $(".ds_information").on('click',function(){
    //  $(".dsintro").fadeIn("slow");
    // })
    
    // 关闭弹窗
    $(".dsintro img").click(function(){
        $(".dsintro").fadeOut("100");
    })
})*/



var userid = "";
var dsname = "";
function ShowMessage() { 
    var thisURL = decodeURI(location.href);    
    var userId = thisURL.split('?')[1];
    var dsName = thisURL.split('&')[1];
    dsname = dsName.split("=")[1];
    userid = userId.split("=")[1].split('&')[0];
} 
window.onload=ShowMessage(); 
				
$(function(){
	// 打开弹窗
    $(".ds_information").click(function(){
    		$(".dsintro").fadeIn("10");
    })
    
    // 关闭弹窗
    $(".dsintro img").click(function(){
    		$(".dsintro").fadeOut("10");
    })

	$.ajax({
		type:"POST",
        url:"sdp.action",
        dataType:"text",
        data:"dsname="+dsname,
        success:function(data){
        	
        	$(".container").empty();
        	var obj = eval('(' + data + ')');
            var dshtml = "";
            var dsp_list = obj.dspList;


            //添加驾校图片/名称/简介/地址
            var ds_image = "<img id='ds_image' src="+" ' "+obj.dsimage+" ' "+"><span class='ds_name'></span>";
            $(".ds_image").html(ds_image);
            $(".ds_name").html(obj.dsname);
            $(".dsintro_name").html(obj.dsname);
            $(".dsintro p").html(obj.dsintro);
            $(".address").html(obj.address);


             // $.each循环实现添加  
            $.each(dsp_list,function(commentIndex,comment){
                dshtml += "<div class='dsp_list'><div class='line'></div><h2 class='dstype'>"
                +comment.dstype+"</h2><div class='dsp_infor'><span class='models'>"
                +comment.models+"</span><span class='traintime'>"
                +comment.traintime+"</span><span>一人一车，班车接送</span><span class='packageid'>"
                +comment.packageid+"</span></div><div><span  class='price_symbol'>￥</span><span class='price'>"
                +comment.price+"</span></div></div>";
            });
            $(".container").html(dshtml);

            // 为班型列表设置点击事件
    		 $(".dsp_list").click(function(){    
                dstype = $(this).find(".dstype").html(); 
                models = $(this).find(".models").html(); 
                price = $(this).find(".price").html(); 
                traintime = $(this).find(".traintime").html();  
                packageid = $(this).find(".packageid").html();   
                
                var myurl="ds_apply.html?userid="+userid+"&dsname="+dsname+"&dstype="+dstype+"&models="+models+"&price="+price+"&packageid="+packageid+"&traintime="+traintime;                                      
                window.location.assign(encodeURI(myurl));
            })

        }

	});
});
