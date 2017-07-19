var userid = "";
function ShowMessage() 
{ 
    var thisURL = document.URL;    
    var userId = thisURL.split('?')[1];  
    userid = userId.split("=")[1];  
    alert(userid);
} 
window.onload=ShowMessage(); 
$(function(){
    $.ajax({
        type:"POST",
        url:"sdi.action",
        dataType:"text",
        success:function(data){
            alert("ajax获取成功:"+data);
            $(".city").empty();
            var obj = eval('(' + data + ')');
            var html = "";
            if(obj.status=="1"){
                alert("解析成功，staus="+obj.status);
                var ds_list = obj.dspInfolist;
                alert("ds_list:"+ds_list[1].dsname);
                 /*$.each循环实现添加*/  
                $.each(ds_list,function(commentIndex,comment){
                    html += "<div class='ds_list'><img src="+" ' "
                    +comment.dsimage+" ' "+" height='80px' width='100px'><div class='information'><div class='ds_name_price'> <span class='ds_name'>"
                    +comment.dsname+"</span><span class='ds_price'>"+'3200'+"</span></div><span class='ds_address'>"
                    +comment.address+"</span></div></div>";
                });
                $(".container").html(html);

                /*为驾校列表注册点击事件*/
                $(".ds_list").click(function(){                       
                    var dsname = $(this).find(".ds_name").html();   
                    $(this).css({"background-color":"red"});                       
                    window.location.href="ds_information.html?userid="+userid+"&dsname="+dsname;
                }) 
            }
            return true;   
        },
        error:function(obj){
            alert("erroe");
        }
    });

    
})