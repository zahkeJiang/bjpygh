package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.wxgzpt.bjpygh.config.AlipayConfig;
import com.wxgzpt.bjpygh.dao.DsInfoDao;
import com.wxgzpt.bjpygh.dao.DsOrderDao;
import com.wxgzpt.bjpygh.dao.DsPackageDao;
import com.wxgzpt.bjpygh.dao.UserCouponDao;
import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.entity.DsInformation;
import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.DsPackage;
import com.wxgzpt.bjpygh.entity.User;
import com.wxgzpt.bjpygh.entity.UserCoupon;

@SuppressWarnings("serial")
public class DsPayServlet extends HttpServlet{
	
	
	// 閸熷棙鍩涚拋銏犲礋閸欏嚖绱濋崯鍡樺煕缂冩垹鐝拋銏犲礋缁崵绮烘稉顓炴暜娑擄拷鐠併垹宕熼崣鍑ょ礉韫囧懎锝�
    // 鐠併垹宕熼崥宥囆為敍灞界箑婵夛拷
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        PrintWriter out = response.getWriter();
        String subject = "驾校报名费用";
        String  body = "驾校报名费用";
        String timeout_express="2m";
        String product_code="BJPYGH_DS_SIGNUP";
        	String userid = request.getParameter("userid");
        	DsOrderDao dsOrderDao = new DsOrderDao();
        	
            String packageid = request.getParameter("packageid");
            String select = request.getParameter("select");
            System.out.println("userid:"+userid+"packageid:"+packageid+"select:"+select);
            int couponprice = 0;
            if(select.equals("1")){
            	UserCouponDao userCouponDao = new UserCouponDao();
        		UserCoupon userCoupon = userCouponDao.selectUserCoupon(userid);
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
            String out_trade_no ="DSPYGH" + year + month + date + hour + minute + second + userid;
         
            int total_amount = dsPackage.getPrice()-couponprice;
            System.out.println("------_------");
            
            DsOrder dsOrder = new DsOrder();
            DsInfoDao dsInfoDao = new DsInfoDao();
            DsInformation DsInfo = dsInfoDao.selectDsInfo(dsPackage.getDsname());
            dsOrder.setUserid(Integer.parseInt(userid));
            dsOrder.setDsname(dsPackage.getDsname());
            dsOrder.setDstype(dsPackage.getDstype());
            dsOrder.setModels(dsPackage.getModels());
            dsOrder.setDescription(dsPackage.getDescription());
            dsOrder.setAddress(user.getAddress());
            dsOrder.setNote(user.getReamark());
            dsOrder.setRealname(user.getRealname());
            dsOrder.setOrdernumber(out_trade_no);
            dsOrder.setOrderprice(total_amount);
            dsOrder.setOriginalprice(dsPackage.getPrice());
            dsOrder.setOrderstatus(0);
            dsOrder.setPhonenumber(user.getPhonenumber());
            dsOrder.setTraintime(dsPackage.getTraintime());
            dsOrder.setImageurl(DsInfo.getDsimage());
            List<DsOrder> dso = dsOrderDao.getOrderById(userid);
        	DsOrder newOrder = null; 
            if(dso == null){
                dsOrderDao.insertOrder(dsOrder);
                System.out.println("log:DsOrderDao");
            }else{
            	for(DsOrder dsor:dso){
            		if(dsor.getOrderstatus()!=0&&dsor.getOrderstatus()!=5){
            			out.print("<!DOCTYPE html><html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no'/><title>支付失败</title><link href='css/main.css' rel='stylesheet' type='text/css'/><link href='css/submit_orders.css' rel='stylesheet' type='text/css'/></head><body style='background-color: #fff;'><div style='text-align: center; margin-top: 250px;'><img style='width: 40px;margin: 0;' src='images/alipay.png'><p>您已成功支付，请勿重复支付</p></div></body></html>");
                		out.flush();
                		out.close();
                		return;
            		}else if(dsor.getOrderstatus()==0){
            			newOrder = dsor;
            		}
            	}
            	if(newOrder!=null){
            		dsOrderDao.updateOrderByStatus(dsOrder);
            	}else{
            		dsOrderDao.insertOrder(dsOrder);
            	}
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
            model.setTotalAmount(""+total_amount);
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
//        		response.setContentType("text/html;charset=" + AlipayConfig.CHARSET); 
        	    out.write(form);//閻╁瓨甯寸亸鍡楃暚閺佸娈戠悰銊ュ礋html鏉堟挸鍤崚浼淬�夐棃锟� 
        	    out.flush();
        	    out.close();
        	} catch (AlipayApiException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        		 out.flush();
         	    out.close();
        	}       
	}
}
