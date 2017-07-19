var data = [{
                    "dstype": "直通车","models":"爱丽舍C1","traintime":"1-5","price":"3400","packageid":"1","dsimage":"http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"
                }, {
                    "dstype": "直通车","models":"爱丽舍C1","traintime":"1-7","price":"3400","packageid":"2","dsimage":"http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"
                }, {
                    "dstype": "定制班","models":"爱丽舍C1","traintime":"1-5","price":"3400","packageid":"3","dsimage":"http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"
                }, {
                    "dstype": "定制班","models":"爱丽舍C1","traintime":"1-7","price":"3400","packageid":"4","dsimage":"http://pic78.huitu.com/res/20160604/1029007_20160604114552332126_1.jpg"
                }]

var dsname="海淀驾校";
var packageid = "";
var userid = "2";
$(function(){
    var dshtml ="";
    var ds_image = "<img src="+" ' "+data.dsimage+" ' height='200px' width='100%'"+"><p class='ds_name'></p>";
    $(".ds_image").html(ds_image);
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
    // 	$(".dsintro").fadeIn("slow");
    // })
    
    // 关闭弹窗
    $(".dsintro img").click(function(){
    	$(".dsintro").fadeOut("100");
    })
})