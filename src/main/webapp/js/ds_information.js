

var dsname = "";
function ShowMessage() { 
    var thisURL = decodeURI(location.href);    
    var getval = thisURL.split('?')[1];
    dsname = getval.split("=")[1];
} 
window.onload=ShowMessage(); 
				
$(function(){
	// 打开弹窗
    $(".ds_information").click(function(){
        $(".ds_type").hide();
    	$(".dsintro").fadeIn(10);
    })
    
    // 关闭弹窗
    $(".dsintro img").click(function(){
    	$(".dsintro").fadeOut(10);
         $(".ds_type").show();
    })

    //发送post请求，获取班型列表
    $.post("sdp.action",{"dsname":dsname},function(obj){
        $(".container").empty();
        // if (obj.status=="1") {
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
                +comment.traintime+"</span><span class='description'>"
                +comment.description+"</span><span class='packageid'>"
                +comment.packageid+"</span></div><div><span  class='price_symbol'>¥</span><span class='price'>"
                +comment.price+"</span><span class='pointzero'>.00</span></div></div>";
            });
            $(".container").html(dshtml);

            // 为班型列表设置点击事件
             $(".dsp_list").click(function(){    
                var dstype = $(this).find(".dstype").html(); 
                var models = $(this).find(".models").html(); 
                var price = $(this).find(".price").html(); 
                var traintime = $(this).find(".traintime").html();  
                var packageid = $(this).find(".packageid").html();   
                var description = $(this).find(".description").html();
                var myurl="ds_apply.html?dsname="+dsname+"&dstype="+dstype+"&models="+models+"&price="+price+"&packageid="+packageid+"&traintime="+traintime+"&description="+description;                                      
                window.location.assign(encodeURI(myurl));
            });
        // }
    },'json');
});
