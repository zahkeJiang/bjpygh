package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.google.gson.Gson;
import com.wxgzpt.bjpygh.config.AlipayConfig;
import com.wxgzpt.bjpygh.dao.DsOrderDao;
import com.wxgzpt.bjpygh.dao.DsPackageDao;
import com.wxgzpt.bjpygh.dao.UserCouponDao;
import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.DsPackage;
import com.wxgzpt.bjpygh.entity.Status;
import com.wxgzpt.bjpygh.entity.User;
import com.wxgzpt.bjpygh.entity.UserCoupon;

@SuppressWarnings("serial")
public class DsPayServlet extends HttpServlet{
	
	
	int couponprice = 0;
	// 閸熷棙鍩涚拋銏犲礋閸欏嚖绱濋崯鍡樺煕缂冩垹鐝拋銏犲礋缁崵绮烘稉顓炴暜娑擄拷鐠併垹宕熼崣鍑ょ礉韫囧懎锝�
    String out_trade_no;
    // 鐠併垹宕熼崥宥囆為敍灞界箑婵夛拷
    String subject = "驾校报名费用";
    // 娴犳ɑ顑欓柌鎴︻杺閿涘苯绻�婵夛拷
    String total_amount;
    // 閸熷棗鎼ч幓蹇氬牚閿涘苯褰茬粚锟�
    String body = "杩欐槸涓�棣栫畝鍗曠殑灏忔儏姝�";
    // 鐡掑懏妞傞弮鍫曟？ 閸欘垳鈹�
    String timeout_express="2m";
    // 闁匡拷閸烆喕楠囬崫浣虹垳 韫囧懎锝�
    String product_code="QUICK_WAP_PAY";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        	String userid = request.getParameter("userid");
        	DsOrderDao dsOrderDao = new DsOrderDao();
        	DsOrder dso = dsOrderDao.getDsOrder(userid);
//        	if(dso.getOrderstatus()==1){
//        		out.print("您已支付成功，勿重复支付。");
//        		out.flush();
//        		out.close();
//        		return;
//        	}
            String packageid = request.getParameter("packageid");
            String select = request.getParameter("select");
            System.out.println("userid:"+userid+"packageid:"+packageid+"select:"+select);
            if(select.equals("1")){
            	UserCouponDao userCouponDao = new UserCouponDao();
        		UserCoupon userCoupon = userCouponDao.selectUserCoupon(userid);
        		UserDao userDao = new UserDao();
				User user = userDao.getUserById(userid);
        		Date date = new Date(604800000L);
				if(userCoupon.getCouponstatus()==1&&userCoupon!=null){
					if((new Date()).getTime()-userCoupon.getCoupontime().getTime()<date.getTime()){
						couponprice = userCoupon.getCouponprice();
					}else if(userCoupon.getCouponstatus()==2){
						couponprice = userCoupon.getCouponprice();
					}else{
						couponprice = 0;
					}
				}else{
					couponprice = 0;
				}
            }else{
            	couponprice = 0;
            }
            System.out.println("log:UserCouponDao");
            UserDao userDao = new UserDao();
            User user = userDao.getUserById(userid);
            System.out.println("log:UserDao");
            DsPackageDao dsPackageDao = new DsPackageDao();
            DsPackage dsPackage = dsPackageDao.selectDsPackageById(packageid);
            System.out.println("log:DsPackageDao");
            /*閼惧嘲褰囪ぐ鎾冲閺冨爼妫块敍宀�鏁撻幋鎰吂閸楋拷*/
            Calendar c = Calendar.getInstance();//閸欘垯浜掔�佃鐦℃稉顏呮闂傛潙鐓欓崡鏇犲娣囶喗鏁�
            int year = c.get(Calendar.YEAR); 
            int month = c.get(Calendar.MONTH); 
            int date = c.get(Calendar.DATE); 
            int hour = c.get(Calendar.HOUR_OF_DAY); 
            int minute = c.get(Calendar.MINUTE); 
            int second = c.get(Calendar.SECOND);
            int milliseconds = c.get(Calendar.MILLISECOND);
            out_trade_no ="PYGHDS" + year + month + date + hour + minute + second + milliseconds+userid;
         
            total_amount = ""+(dsPackage.getPrice()-couponprice);
            System.out.println("------_------");
            
            DsOrder dsOrder = new DsOrder();
            
            dsOrder.setUserid(Integer.parseInt(userid));
            dsOrder.setDsname(dsPackage.getDsname());
            dsOrder.setDstype(dsPackage.getDstype());
            dsOrder.setModels(dsPackage.getModels());
            dsOrder.setAddress(user.getAddress());
            dsOrder.setNote(user.getReamark());
            dsOrder.setRealname(user.getRealname());
            dsOrder.setOrdernumber(out_trade_no);
            dsOrder.setOrderprice(total_amount);
            dsOrder.setOrderstatus(0);
            dsOrder.setPhonenumber(user.getPhonenumber());
            dsOrder.setTraintime(dsPackage.getTraintime());
            if(dso == null){
                dsOrderDao.insertOrder(dsOrder);
                System.out.println("log:DsOrderDao");
            }else if(dso.getOrderstatus() == 0||dso.getOrderstatus()==4){
            	dsOrderDao.updateOrder(dsOrder);
            }else{
            	out.print("您已报名成功，若要更换套餐，请先退单。");
            	out.flush();
            	out.close();
            	return;
            }
            
            /**********************/
            // SDK 閸忣剙鍙＄拠閿嬬湴缁紮绱濋崠鍛儓閸忣剙鍙＄拠閿嬬湴閸欏倹鏆熼敍灞间簰閸欏﹤鐨濈憗鍛啊缁涙儳鎮曟稉搴ㄧ崣缁涙拝绱濆锟介崣鎴ｏ拷鍛￥闂囷拷閸忚櫕鏁炵粵鎯ф倳娑撳酣鐛欑粵锟�     
            //鐠嬪啰鏁SA缁涙儳鎮曢弬鐟扮础
            AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
            AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();
            
            // 鐏忎浇顥婄拠閿嬬湴閺�顖欑帛娣団剝浼�
            AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
            model.setOutTradeNo(out_trade_no);
            model.setSubject(subject);
            model.setTotalAmount(total_amount);
            model.setBody(body);
            model.setTimeoutExpress(timeout_express);
            model.setProductCode(product_code);
            alipay_request.setBizModel(model);
            // 鐠佸墽鐤嗗鍌涱劄闁氨鐓￠崷鏉挎絻
            alipay_request.setNotifyUrl(AlipayConfig.notify_url);
            // 鐠佸墽鐤嗛崥灞绢劄閸︽澘娼�
            alipay_request.setReturnUrl(AlipayConfig.return_url);   
            
            // form鐞涖劌宕熼悽鐔堕獓
            String form = "";
        	try {
        		// 鐠嬪啰鏁DK閻㈢喐鍨氱悰銊ュ礋
        		form = client.pageExecute(alipay_request).getBody();
        		response.setContentType("text/html;charset=" + AlipayConfig.CHARSET); 
        	    response.getWriter().write(form);//閻╁瓨甯寸亸鍡楃暚閺佸娈戠悰銊ュ礋html鏉堟挸鍤崚浼淬�夐棃锟� 
        	    response.getWriter().flush();
        	    response.getWriter().close();
        	} catch (AlipayApiException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}       
	}
}
