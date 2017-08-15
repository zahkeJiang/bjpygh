//显示中奖结果 这个函数需要自定义，默认没有
function lotteryStop(index){
    //index就是lottery.num
    switch(index){
        case 0:
            var couponhtml = '<img src="images/ds_coupon_02.png" width="84%">';
            $(".coupon_price").html(couponhtml);
            change_lottery_begin();//抽奖按钮改变样式，不能再点击
            $(".layer").fadeIn("slow");
            $(".coupon").show();
            break;
        case 1:
            var couponhtml = '<img src="images/ds_coupon_03.png" width="84%">';
            $(".coupon_price").html(couponhtml);
             change_lottery_begin();//抽奖按钮改变样式，不能再点击
            $(".layer").fadeIn("slow");
            $(".coupon").fadeIn("slow");
            break;
        case 2:
            var couponhtml = '<img src="images/ds_coupon_05.png" width="84%">';
            $(".coupon_price").html(couponhtml);
             change_lottery_begin();//抽奖按钮改变样式，不能再点击
            $(".layer").fadeIn("slow");
            $(".coupon").fadeIn("slow");
            break;
        case 3:
            var couponhtml = '<img src="images/ds_coupon_08.png" width="84%">';
            $(".coupon_price").html(couponhtml);
             change_lottery_begin();//抽奖按钮改变样式，不能再点击
            $(".layer").fadeIn("slow");
            $(".coupon").fadeIn("slow");
            break;
        case 4:
           var couponhtml = '<img src="images/ds_coupon_02.png" width="84%">';
            $(".coupon_price").html(couponhtml);
             change_lottery_begin();//抽奖按钮改变样式，不能再点击
            $(".layer").fadeIn("slow");
            $(".coupon").fadeIn("slow");
            break;  
        case 5:
            var couponhtml = '<img src="images/ds_coupon_10.png" width="84%">';
            $(".coupon_price").html(couponhtml);
             change_lottery_begin();//抽奖按钮改变样式，不能再点击
            $(".layer").fadeIn("slow");
            $(".coupon").fadeIn("slow");
            break;
        case 6:
            var couponhtml = '<img src="images/ds_coupon_02.png" width="84%">';
            $(".coupon_price").html(couponhtml);
             change_lottery_begin();//抽奖按钮改变样式，不能再点击
            $(".layer").fadeIn("slow");
            $(".coupon").fadeIn("slow");
            break;
        case 7:
            var couponhtml = '<img src="images/ds_coupon_06.png" width="84%">';
            $(".coupon_price").html(couponhtml);
            change_lottery_begin();//抽奖按钮改变样式，不能再点击
            $(".layer").fadeIn("slow");
            $(".coupon").fadeIn("slow");
            break;
        default:
            alert("当前抽奖人数过多，请稍后尝试！");
    }
}
var lottery={
    index:7,    //当前转动到哪个位置，起点位置
    count:8,    //总共有多少个位置
    timer:0,    //setTimeout的ID，用clearTimeout清除
    speed:20,   //初始转动速度
    times:0,    //转动次数
    cycle:24,   //转动基本次数：即至少需要转动多少次再进入抽奖环节
    prize:0,    //中奖位置
    num:0,      //最后停下的位置
    // 当一个函数被保存为对象的一个属性时，我们称之它为该对象的一个方法，那么this被绑定到该对象上
    roll:function(){
        var index = this.index;
        var count = this.count;
        $("#lottery").find(".lottery-unit-"+index).removeClass("active");
        index += 1;
        if (index>count-1) {
            index = 0;
        };
        $("#lottery").find(".lottery-unit-"+index).addClass("active");
        this.index=index;
    }
};
//开始抽奖动画
function roll(num){
    lottery.times += 1;
    lottery.roll();
    if (lottery.times > lottery.cycle+10 && lottery.prize==lottery.index) {
        clearTimeout(lottery.timer);
        try {
            if(typeof(eval(lotteryStop)) == "function"){
                setTimeout("lotteryStop(lottery.index)",100);
            }
        } catch(e) {}
        lottery.prize=0;
        lottery.times=0;
        lottery.speed=20;
        
    }else{
        if (lottery.times==lottery.cycle) {
            lottery.speed -= 10;
        }else if(lottery.times<lottery.cycle) {
            var index = lottery.num;
            lottery.prize = index;      
        }else{
            if (lottery.times > lottery.cycle+10) {
                lottery.speed += 100;
            }else{
                lottery.speed += 20;
            }
        }
        if (lottery.speed<40) {
            lottery.speed=40;
        };
        lottery.timer = setTimeout(roll,lottery.speed);
    }
}
$(function(){
    $.post("queryCoupon.action",{},function(obj){
        if (obj.status == "0") {
            //点击抽奖按钮，开始抽奖
            $(".lottery_begin").click(function(){
            	$.post("coupon.action",{},function(obj){
                    if (obj.status =="1") {
                        if (obj.price==0) {
                            lottery.num=0;
                        }else if (obj.price == 1) {
                            lottery.num=1;
                        }else if (obj.price == 2) {
                            lottery.num=2;
                        }else if (obj.price == 3) {
                            lottery.num=3;
                        }else if (obj.price == 4) {
                            lottery.num=4;
                        }else if (obj.price == 5) {
                            lottery.num=5;
                        }else if (obj.price == 6) {
                            lottery.num=6;
                        }else if (obj.price == 7) {
                            lottery.num=7;
                        }
                    }
                },'json');
               $(".lottery_begin").prop("disabled",true);//抽奖按钮设为不可点击状态
                roll();
            });
        }else{
            change_lottery_begin();//改变抽奖按钮，并显示优惠信息
        }
    },'json');

    //关闭弹窗
    $("#closeBtn").click(function(){
        $(".coupon").fadeOut("fast");
        $(".layer").fadeOut("fast");
        $(".footer").html("<p>您已抽过奖了,<a href='index.html'>立即使用</a></p>");
    });

});

//开始抽奖按钮改变样式
function change_lottery_begin(){
    $(".lottery_begin p").empty();
    $(".lottery_begin p").html("已&nbsp;抽&nbsp;奖");
    $(".lottery_begin").css({"font-size":"18px","background-color":"gray","color":"#555"});
    $(".lottery_begin").prop("disabled",true);
    
}