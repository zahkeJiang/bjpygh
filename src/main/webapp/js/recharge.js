
$(function(){
    userload();
    $(".amount_type_01").click(function(){
        alert("积分充值5元");
    });
    $(".amount_type_02").click(function(){
        alert("积分充值10元");
    });
    $(".amount_type_03").click(function(){
        alert("积分充值20元");
    });
    $(".amount_type_04").click(function(){
        alert("积分充值50元");
    });
    $(".amount_type_05").click(function(){
        alert("积分充值100元");
    });
    $(".amount_type_06").click(function(){
        alert("积分充值200元");
    });
});
//获取用户信息
function userload(){
    $.post("personal.action",{},function(obj){
        if (obj.status == "1") {
            var user = obj.data;
            $("#icon").attr('src',user.headimageurl);
            $("#nickname").html(user.nickname);
            $("#mobile").html(user.phonenumber);
        }else if (obj.status == "-1") {}{
             window.location.href="openWchat.html";
        }
    },'json');
}