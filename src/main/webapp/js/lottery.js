//显示中奖结果 这个函数需要自定义，默认没有
function lotteryStop(index){
    //index就是lottery.num
    switch(index){
        case 0:
            alert("恭喜获得200元优惠券");
            $(".lottery_box").empty();
            $(".lottery_box").html("<img src='images/coupon_01.png'><p>我已经抽过奖了,优惠券:200元&nbsp;<a href='index.html'>立即使用</a></p>");
            break;
        case 1:
            alert("恭喜获得250元优惠券");
            $(".lottery_box").empty();
            $(".lottery_box").html("<img src='images/coupon_02.png'><p>我已经抽过奖了,优惠券:250元&nbsp;<a href='index.html'>立即使用</a></p>");
            break;
        case 2:
            alert("恭喜获得300元优惠券");
            $(".lottery_box").empty();
            $(".lottery_box").html("<img src='images/coupon_03.png'><p>我已经抽过奖了,优惠券:300元&nbsp;<a href='index.html'>立即使用</a></p>");
            break;
        case 3:
            alert("恭喜获得400元优惠券");
            $(".lottery_box").empty();
            $(".lottery_box").html("<img src='images/coupon_04.png'><p>我已经抽过奖了,优惠券:400元&nbsp;<a href='index.html'>立即使用</a></p>");
            break;
        case 4:
            alert("恭喜获得500元优惠券");
            $(".lottery_box").empty();
            $(".lottery_box").html("<img src='images/coupon_05.png'><p>我已经抽过奖了,优惠券:500元&nbsp;<a href='index.html'>立即使用</a></p>");
            break;  
        case 5:
            alert("恭喜获得600元优惠券");
            $(".lottery_box").empty();
            $(".lottery_box").html("<img src='images/coupon_06.png'><p>我已经抽过奖了,优惠券:600元&nbsp;<a href='index.html'>立即使用</a></p>");
            break;
        case 6:
            alert("恭喜获得800元优惠券");
            $(".lottery_box").empty();
            $(".lottery_box").html("<img src='images/coupon_07.png'><p>我已经抽过奖了,优惠券:800元&nbsp;<a href='index.html'>立即使用</a></p>");
            break;
        case 7:
            alert("恭喜获得1000元优惠券");
            $(".lottery_box").empty();
            $(".lottery_box").html("<img src='images/coupon_08.png'><p>我已经抽过奖了,优惠券:1000元&nbsp;<a href='index.html'>立即使用</a></p>");
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
    $(".lottery_box").hidden();
    $.post("queryCoupon.action",{},function(obj){
        if (obj.status == "0") {
            $(".lottery_box").show();
            $(".lottery_begin").click(function(){
            	$.post("coupon.action",{},function(obj){
                    if (obj.status =="1") {
                        if (obj.price==200) {
                            lottery.num=0;
                        }else if (obj.price == 250) {
                            lottery.num=1;
                        }else if (obj.price == 300) {
                            lottery.num=2;
                        }else if (obj.price == 400) {
                            lottery.num=3;
                        }else if (obj.price == 500) {
                            lottery.num=4;
                        }else if (obj.price == 600) {
                            lottery.num=5;
                        }else if (obj.price == 800) {
                            lottery.num=6;
                        }else if (obj.price == 1000) {
                            lottery.num=7;
                        }
                    }
                },'json');
                roll();
            });
        }else{
            if (obj.price=="200") {
                var coupon_pic = "<img src='images/coupon_01.png'>";
            }else if (obj.price=="200") {
                var coupon_pic = "<img src='images/coupon_02.png'>";
            }else if (obj.price=="250") {
                var coupon_pic = "<img src='images/coupon_03.png'>";
            }else if (obj.price=="300") {
                var coupon_pic = "<img src='images/coupon_04.png'>";
            }else if (obj.price=="400") {
                var coupon_pic = "<img src='images/coupon_05.png'>";
            }else if (obj.price=="500") {
                var coupon_pic = "<img src='images/coupon_06.png'>";
            }else if (obj.price=="600") {
                var coupon_pic = "<img src='images/coupon_07.png'>";
            }else if (obj.price=="1000") {
                var coupon_pic = "<img src='images/coupon_08.png'>";
            }
            $(".lottery_box").empty();
            $(".lottery_box").html(coupon_pic+"<p>我已经抽过奖了,优惠券:"+obj.price+"元&nbsp;<a href='index.html'>立即使用</a></p>");
            $(".lottery_box").show();
        }
    },'json');
    
});