
//var userid = "";
//function ShowMessage() 
//{ 
//    var thisURL = document.URL;    
//    var userId = thisURL.split('?')[1];  
////    userid = userId.split("&")[0];  
////    var openid = userId.split("&")[1];
//    alert(userId);
//} 
//window.onload=ShowMessage(); 


$(function(){
    $(".address").click(function(){
        alert("正在开拓其他城市");
    });
    //获取驾校列表
    $.post("sdi.action",{},function(obj){
        $(".container").empty();
        var html = "";
        if(obj.status=="1"){
            //alert("解析成功，staus="+obj.status);
            var ds_list = obj.dspInfolist;
            // $.each循环实现添加  
            $.each(ds_list,function(commentIndex,comment){
                html += "<div class='ds_list'><img src="+" ' "
                    +comment.dsimage+" ' "+" height='80px' width='100px'><div class='information'><div class='ds_name_price'> <span class='ds_name'>"
                    +comment.dsname+"</span><span class='ds_price'></span></div><span class='ds_address'>"
                    +comment.address+"</span></div></div>";
            });
            $(".container").html(html);

            //为驾校列表注册点击事件
            $(".ds_list").click(function(){                       
                var dsname = $(this).find(".ds_name").html();                          
                var myurl="ds_information.html?dsname="+dsname;
                window.location.assign(encodeURI(myurl));
            }); 
        }
    },'json');
});




