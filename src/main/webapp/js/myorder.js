//var userid = "";
//function ShowMessage() 
//{ 
//    var thisURL = document.URL;    
//    var getval = thisURL.split('?')[1];  
//    userid = getval.split("=")[1];  
//} 
//window.onload=ShowMessage(); 


var container = "<div class='orders_bg'><div class='bg_hint'><img src='images/image_icon.jpg'><p>您当前没有相关订单</p></div></div>"
$(function(){
    all_orders();
	$(".all_orders").click(function(){     
		all_orders();
	});
	$(".orders_success").click(function(){ 
		orders_success();
	});
	$(".orders_finished").click(function(){
		orders_finished();
	});

});

//定义查询全部订单方法
function all_orders(){
    $(".orders_finished").css({"color":"black","border-bottom":"0"});
    $(".orders_success").css({"color":"black","border-bottom":"0"});
	$(".all_orders").css({"color":"red","border-bottom":"2px solid red"});

    $.post("selectOrder.action",{},function(obj){
        $(".container").empty();
        if (obj.status=="1") {   
            var dsorderh_tml = "";
            var dsorder_list = obj.dsOrder;
            // alert("dsorder_list.userid="+dsorder_list[0].userid+"dsorder_list.dstype="+dsorder_list[0].dstype+"dsorder_list.dsname="+dsorder_list[0].dsname+"dsorder_list.traintime="+dsorder_list[0].traintime+"dsorder_list.orderid="+dsorder_list[0].orderid);
            // $.each循环实现添加订单列表  
            $.each(dsorder_list,function(commentIndex,comment){
                dsorderh_tml += "<div class='dsorder_list'><div class='dsorder_titie'><p class='ds_name'>"
                                +comment.dsname+"</p></div><div class='dsoder_container'><img src='"
                                +obj.imageurl+"' height='48px' width='64px'><p class='dsorder_information'>"
                                +comment.dstype+"&nbsp;/&nbsp;"+comment.models+"&nbsp;/&nbsp;"+comment.traintime+"</p></div><div class='dsorder_footer'><span class='dsorder_pay'>实付款：</span><span class='order_price'>¥"
                                +comment.orderprice+"</span></div></div>";
            });
            $(".container").html(dsorderh_tml);

            // 为订单列表设置点击事件
             $(".dsoder_container").click(function(){    
                window.location.href="order_information.html";
            });
        }else{
            $(".container").html(container);
        }
    },"json");
}

//定义查询成功报名订单方法
function orders_success(){
    $(".all_orders").css({"color":"black","border-bottom":"0"});
    $(".orders_finished").css({"color":"black","border-bottom":"0"});
    $(".orders_success").css({"color":"red","border-bottom":"2px solid red"});
    $.post("selectOrder.action",{},function(obj){
    	$(".container").empty();
//    	var obj = eval('(' + data + ')');
    	if (obj.status=="1") {
        	var dsorder_list = obj.dsOrder;
//            alert("dsorder_list[0].orderstatus="+dsorder_list[0].orderstatus);
        	if (dsorder_list[0].orderstatus=="1"||dsorder_list[0].orderstatus=="2") {  
        		var dsorderh_tml = "";
         		// $.each循环实现添加订单列表  
        		$.each(dsorder_list,function(commentIndex,comment){
            		dsorderh_tml += "<div class='dsorder_list'><div class='dsorder_titie'><p class='ds_name'>"
            					+comment.dsname+"</p><p class='refund' obnumber='"+comment.ordernumber+"' obprice='"+comment.orderprice+"'>取消订单</p></div><div class='dsoder_container'><img src='"
            					+obj.imageurl+"' height='48px' width='64px'><p class='dsorder_information'>"
            					+comment.dstype+"&nbsp;/&nbsp;"+comment.models+"&nbsp;/&nbsp;"+comment.traintime+"</p></div><div class='dsorder_footer'><span class='dsorder_pay'>实付款：</span><span class='order_price'>¥"
            					+comment.orderprice+"</span></div></div>";
        		});
        		$(".container").html(dsorderh_tml);

        		// 为订单列表设置点击事件
		 		$(".dsoder_container").click(function(){    
        			 window.location.href="order_information.html";
        		});
                $(".refund").click(function(){
                	var ordernumber = $(this).attr("obnumber");
                	var orderprice = $(this).attr("obprice");
                    window.location.href="ds_refund.html?ordernumber="+ordernumber;
                });
        	}else{
                $(".container").html(container);
            }
    	}else{
            $(".container").html(container);
    	}
    },'json');
}

//定义查询已完成订单方法
function orders_finished(){
    $(".all_orders").css({"color":"black","border-bottom":"0"});
    $(".orders_success").css({"color":"black","border-bottom":"0"});
    $(".orders_finished").css({"color":"red","border-bottom":"2px solid red"});

    $.post("selectOrder.action",{},function(obj){
        $(".container").empty();
        if (obj.status=="1") {
            var dsorder_list = obj.dsOrder;
            if (dsorder_list[0].orderstatus=="4"||dsorder_list[0].orderstatus=="4") {
                var dsorderh_tml = "";
                // $.each循环实现添加订单列表  
                $.each(dsorder_list,function(commentIndex,comment){
                    dsorderh_tml += "<div class='dsorder_list'><div class='dsorder_titie'><p class='ds_name'>"
                                    +comment.dsname+"</p><p class='orders_finish'>已完成</p></div><div class='dsoder_container'><img src='"
                                    +obj.imageurl+"' height='48px' width='64px'><p class='dsorder_information'>"
                                    +comment.dstype+"&nbsp;/&nbsp;"+comment.models+"&nbsp;/&nbsp;"+comment.traintime+"</p></div><div class='dsorder_footer'><span class='dsorder_pay'>实付款：</span><span class='order_price'>¥"
                                    +comment.orderprice+"</span></div></div>";
                });
                $(".container").html(dsorderh_tml);

                // 为订单列表设置点击事件
                $(".dsoder_container").click(function(){    
                     window.location.href="order_information.html";
                });
            }else{
                $(".container").html(container);
            }
        }else{
            $(".container").html(container);
        }
    },'json');
}
