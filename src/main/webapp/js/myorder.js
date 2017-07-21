var userid = "";
function ShowMessage() 
{ 
    var thisURL = document.URL;    
    var getval = thisURL.split('?')[1];  
    userid = getval.split("=")[1];  
} 
window.onload=ShowMessage(); 

$(function(){
    var container = "<div class='orders_bg'><div><img src='../images/image_icon.jpg'><p>您当前没有相关订单</p></div></div>"
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
	$(".all_orders").css({"color":"red","border-bottom":"2px solid red"});
	$.ajax({
		type:"POST",
        url:"selectOrder.action",
        dataType:"text",
        data:"userid="+userid,
        success:function(data){
        	var obj = eval('(' + data + ')'); 
        	alert("obj.status="+obj.status);
        	if (obj.status=="1") {   
        		$(".container").empty();
            	var dsorderh_tml = "";
            	var dsorder_list = obj.dsOrder;
                // alert("dsorder_list.userid="+dsorder_list[0].userid+"dsorder_list.dstype="+dsorder_list[0].dstype+"dsorder_list.dsname="+dsorder_list[0].dsname+"dsorder_list.traintime="+dsorder_list[0].traintime+"dsorder_list.orderid="+dsorder_list[0].orderid);
             	// $.each循环实现添加订单列表  
            	$.each(dsorder_list,function(commentIndex,comment){
                	dsorderh_tml += "<div class='dsorder_list'><div class='dsorder_titie'><p class='ds_name'>"
                				+comment.dsname+"</p></div><div class='dsoder_container'><img src='"
                				+"' height='40px' width='50px'><p class='dsorder_information'>"
                				+comment.dstype+comment.models+comment.traintime+"</p></div><div class='dsorder_footer'><span class='dsorder_pay'>实付款</span><span class='price_symbol'>￥<span class='order_price'>"
                				+comment.orderprice+"</span></span></div></div>";
            	});
            	$(".container").html(dsorderh_tml);

            	// 为订单列表设置点击事件
    		 	$(".dsorder_list").click(function(){    
            		alert("我点击了这个订单");
            	});
        	}else{
        		alert("目前没有订单");
                $(".container").html(container);
        	}

        },
        error:function(obj){
        	alert("error");
        }

	});
}

//定义查询成功报名订单方法
function orders_success(){
    $(".all_orders").css({"color":"black","border-bottom":"0"});
    $(".orders_finished").css({"color":"black","border-bottom":"0"});
    $(".orders_success").css({"color":"red","border-bottom":"2px solid red"});
	$.ajax({
		type:"POST",
        url:"selectOrder.action",
        dataType:"text",
        data:"userid="+userid,
        success:function(data){
        	var obj = eval('(' + data + ')');
        	if (obj.status=="1") {
            	var dsorder_list = obj.dsOrder;
                alert("dsorder_list[0].orderstatus="+dsorder_list[0].orderstatus);
            	if (dsorder_list[0].orderstatus=="1") {  
            		$(".container").empty();
            		var dsorderh_tml = "";
             		// $.each循环实现添加订单列表  
            		$.each(dsorder_list,function(commentIndex,comment){
                		dsorderh_tml += "<div class='dsorder_list'><div class='dsorder_titie'><p class='ds_name'>"
                					+comment.dsname+"</p><p class='refund'>退款</p></div><div class='dsoder_container'><img src='"
                					+"' height='40px' width='50px'><p class='dsorder_information'>"
                					+comment.dstype+comment.models+comment.traintime+"</p></div><div class='dsorder_footer'><span class='dsorder_pay'>实付款</span><span class='price_symbol'>￥<span class='order_price'>"
                					+comment.orderprice+"</span></span></div></div>";
            		});
            		$(".container").html(dsorderh_tml);

            		// 为订单列表设置点击事件
    		 		$(".dsorder_list").click(function(){    
            			alert("我点击了这个订单");
            		});
            	}else{
                    alert("当前没有相关订单");
                    $(".container").html(container);
                }
        	}else{
        		alert("目前没有订单");
                $(".container").html(container);
        	}
        },
        error:function(obj){
            alert("error");
        }

	});
}

//定义查询已完成订单方法
function orders_finished(){
    $(".all_orders").css({"color":"black","border-bottom":"0"});
    $(".orders_success").css({"color":"black","border-bottom":"0"});
    $(".orders_finished").css({"color":"red","border-bottom":"2px solid red"});
    $.ajax({
        type:"POST",
        url:"selectOrder.action",
        dataType:"text",
        data:"userid="+userid,
        success:function(data){
            var obj = eval('(' + data + ')');
            if (obj.status=="1") {
                var dsorder_list = obj.dsOrder;
                alert("dsorder_list[0].orderstatus="+dsorder_list[0].orderstatus);
                if (dsorder_list[0].orderstatus=="3") {
                    $(".container").empty();
                    var dsorderh_tml = "";
                    // $.each循环实现添加订单列表  
                    $.each(dsorder_list,function(commentIndex,comment){
                        dsorderh_tml += "<div class='dsorder_list'><div class='dsorder_titie'><p class='ds_name'>"
                                    +comment.dsname+"</p><p class='orders_finish'>已完成</p></div><div class='dsoder_container'><img src='"
                                    +"' height='40px' width='50px'><p class='dsorder_information'>"
                                    +comment.dstype+comment.models+comment.traintime+"</p></div><div class='dsorder_footer'><span class='dsorder_pay'>实付款</span><span class='price_symbol'>￥<span class='order_price'>"
                                    +comment.orderprice+"</span></span></div></div>";
                    });
                    $(".container").html(dsorderh_tml);

                    // 为订单列表设置点击事件
                    $(".dsorder_list").click(function(){    
                        alert("我点击了这个订单");
                    });
                }else{
                    alert("当前没有相关订单");
                    $(".container").html(container);
                }
            }else{
                alert("当前没有相关订单");
                $(".container").html(container);
            }
        },
        error:function(obj){
            alert("error");
        }

    });
}
