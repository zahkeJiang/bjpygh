var userid = "";
function ShowMessage() 
{ 
    var thisURL = document.URL;    
    var getval = thisURL.split('?')[1];  
    userid = getval.split("=")[1];  
} 
window.onload=ShowMessage(); 

$(function(){
	$(".all_orders").click(function(){
		all_orders();
	});
	$(".orders_success").click(function(){
		orders_success();
	});
	$(".orders_finished").click(function(){
		orders_success();
	});

});

function all_orders(){
	alert("点击了全部");
	$.ajax({
		type:"POST",
        url:"selectOrder.action",
        dataType:"text",
        data:"userid="+userid,
        success:function(data){
        	var obj = eval('(' + data + ')'); 
        	alert(obj.stadus);
        	alert(obj.dsOrder.userid);
        	if (obj.status=="1") {
        		$(".container").empty();
            	var dsorderh_tml = "";
            	var dsorder_list = obj.dsOrder;
            	alert(dsorder_list);
             	// $.each循环实现添加订单列表  
            	$.each(dsorder_list,function(commentIndex,comment){
                	dsorderh_tml += "<div class='dsorder_list'><div class='dsorder_titie'><p class='ds_name'>"
                				+comment.dsname+"</p></div><div class='dsoder_container'><img src='"
                				+"'><p class='dsorder_information'>"
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
        	}

        },
        error:function(obj){
        	alert("error");
        }

	});
}
function orders_success(){
	$.ajax({
		type:"POST",
        url:"selectOrder.action",
        dataType:"text",
        data:"userid="+userid,
        success:function(data){
        	var obj = eval('(' + data + ')');
        	if (obj.status=="1") {
            	var dsorder_list = obj.dsOrder;
            	if (dsorder_list.orderstatus=="1") {
            		$(".container").empty();
            		var dsorderh_tml = "";
             		// $.each循环实现添加订单列表  
            		$.each(dsorder_list,function(commentIndex,comment){
                		dsorderh_tml += "<div class='dsorder_list'><div class='dsorder_titie'><p class='ds_name'>"
                					+comment.dsname+"</p><p class='refund'>退款</p></div><div class='dsoder_container'><img src='"
                					+comment.image+"' height='80px' width='100px'><p class='dsorder_information'>"
                					+comment.dstype+comment.models+comment.traintime+"</p></div><div class='dsorder_footer'><span class='dsorder_pay'>实付款</span><span class='price_symbol'>￥<span class='order_price'>"
                					+comment.orderprice+"</span></span></div></div>";
            		});
            		$(".container").html(dsorderh_tml);

            		// 为订单列表设置点击事件
    		 		$(".dsorder_list").click(function(){    
            			alert("我点击了这个订单");
            		});
            	}else if (dsorder_list.orderstatus=="3") {
            		$(".container").empty();
            		var dsorderh_tml = "";
             		// $.each循环实现添加订单列表  
            		$.each(dsorder_list,function(commentIndex,comment){
                		dsorderh_tml += "<div class='dsorder_list'><div class='dsorder_titie'><p class='ds_name'>"
                					+comment.dsname+"</p><p class='orders_finish'>已完成</p></div><div class='dsoder_container'><img src='"
                					+comment.image+"'><p class='dsorder_information'>"
                					+comment.dstype+comment.models+comment.traintime+"</p></div><div class='dsorder_footer'><span class='dsorder_pay'>实付款</span><span class='price_symbol'>￥<span class='order_price'>"
                					+comment.orderprice+"</span></span></div></div>";
            		});
            		$(".container").html(dsorderh_tml);

            		// 为订单列表设置点击事件
    		 		$(".dsorder_list").click(function(){    
            			alert("我点击了这个订单");
            		});
            	}
        	}else{
        		alert("目前没有订单");
        	}

        }

	});
}
