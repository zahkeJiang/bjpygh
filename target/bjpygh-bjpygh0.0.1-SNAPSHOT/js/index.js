/*    
    var data = [{
                    "name": "海淀驾校","icon":"images/phone_picture.png","a":"北京市海"
                }, {
                    "name": "老牛大讲堂2","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海"
                }, {
                    "name": "老牛大讲堂3","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂4","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂2","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂3","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂4","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂2","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂3","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂4","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂2","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂3","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂4","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                    }, {
                    "name": "老牛大讲堂2","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂3","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }, {
                    "name": "老牛大讲堂4","icon":"http://img1.skqkw.cn:888/2014/12/06/08/21ofdtyslqn-62877.jpg","a":"北京市海淀区科学院南路2号"
                }]
                $(function(){
                    var dshtml ="";
                    
                     //$.each循环实现添加  
                    $.each(data,function(commentIndex,comment){
                        
                        dshtml += "<div class='ds_list'><img src="+" ' "
                            +comment['icon']+" ' "+" height='80px' width='100px'><div class='information'><div class='ds_name_price'> <span class='ds_name'>"
                            +comment['name']+"</span><span class='ds_price'>"+'3200'+"</span></div><span class='ds_address'>"
                            +comment['a']+"</span></div></div>";
                        // dshtml+="<span class='ds_address'>"+comment.a+"</span>";

                    });
                    $('.container').html(dshtml);
                       
                    $(".ds_list").click(function(){
                        
                        var dsname = $(this).find(".ds_name").html();   
                                                          
                        window.location.href="ds_information.html?dsname="+dsname;
                    })
                })*/
                
/*for循环实现添加*/     
/*    for(var i=0;i<data.length;i++){//data.length是获取data的长度。for循环
        $(".container").append("<div class='ds_list'><img src="+" ' "+data[i].icon+" ' "+" height='80px' width='100px'><div class='information'><div class='ds_name_price'> <span class='ds_name'>"+data[i].name+"</span><span class='ds_price'>"+'3200'+"</span></div><span class='ds_address'>"+data[i].a+"</span></div></div>");//如果添加class或者id内容要加单引号例如：$("#first").append("<li class='a'>"+data[i].name+"</li>");
}*/

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
    $.ajax({
        type:"POST",
        url:"sdi.action",
        dataType:"text",
        success:function(data){
//            alert("ajax获取成功:"+data);
            $(".container").empty();
            var obj = eval('(' + data + ')');
            var html = "";
            if(obj.status=="1"){
//                alert("解析成功，staus="+obj.status);
                var ds_list = obj.dspInfolist;
                
                // $.each循环实现添加  
                $.each(ds_list,function(commentIndex,comment){
                    html += "<div class='ds_list'><img src="+" ' "
                    +comment.dsimage+" ' "+" height='80px' width='100px'><div class='information'><div class='ds_name_price'> <span class='ds_name'>"
                    +comment.dsname+"</span><span class='ds_price'>"+'3200'+"</span></div><span class='ds_address'>"
                    +comment.address+"</span></div></div>";
                });
                $(".container").html(html);

                //为驾校列表注册点击事件
                $(".ds_list").click(function(){                       
                    var dsname = $(this).find(".ds_name").html();                          
                    var myurl="ds_information.html?dsname="+dsname;
                    window.location.assign(encodeURI(myurl));
                }) 
            }else if (obj.status=="-1") {
                window.location.href="openWchat.html";
            }
            
        },
        error:function(obj){
            alert(obj);
        }
    });

    
})




