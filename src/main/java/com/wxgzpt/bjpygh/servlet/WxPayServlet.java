package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.wxpay.sdk.WXPay;
import com.google.gson.Gson;
import com.wxgzpt.bjpygh.config.MyConfig;
import com.wxgzpt.bjpygh.db.MD5;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class WxPayServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        
        Status status = new Status();
		HttpSession session = request.getSession();
        Map<String, String> userMap = (Map<String, String>) session.getAttribute("user");
		if(userMap == null){
			status.setStatus(-1);
			status.setMsg("请在微信端登录");
			out.print(new Gson().toJson(status));
			System.out.println(new Gson().toJson(status));
			out.flush();
			out.close();
			return;
		}
        MyConfig config = null;
		try {
			config = new MyConfig();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String userid = userMap.get("id");
		String openid = userMap.get("openid");
		
        WXPay wxpay = new WXPay(config);
        /*閼惧嘲褰囪ぐ鎾冲閺冨爼妫块敍宀�鏁撻幋鎰吂閸楋拷*/
        Calendar c = Calendar.getInstance();//閸欘垯浜掔�佃鐦℃稉顏呮闂傛潙鐓欓崡鏇犲娣囶喗鏁�
        int year = c.get(Calendar.YEAR); 
        int month = c.get(Calendar.MONTH); 
        int date = c.get(Calendar.DATE); 
        int hour = c.get(Calendar.HOUR_OF_DAY); 
        int minute = c.get(Calendar.MINUTE); 
        int second = c.get(Calendar.SECOND);
        String out_trade_no ="PYGHHY" + year + month + date + hour + minute + second + userid;
       
        String total_fee = request.getParameter("total_fee");
        String key = config.getKey();
        String appid=config.getAppID();
        String mch_id = config.getMchID();
        String body = "北京漂洋过海-会员积分充值";
        String device_info = "WEB";
        String nonce_str = getRandomString(32);
        String stringA="appid="+appid+"&body="+body+"&device_info="+device_info+"&mch_id="+mch_id+"&nonce_str="+nonce_str;
        String stringSignTemp=stringA+"&key="+key; //注：key为商户平台设置的密钥key
        String sign=MD5.string2MD5(stringSignTemp); //注：MD5签名方式
//        sign=hash_hmac("sha256",stringSignTemp,key); //注：HMAC-SHA256签名方式
        
        Map<String, String> data = new HashMap<String, String>();
        data.put("appid", appid);
        data.put("mch_id", mch_id);
        data.put("body", body);
        data.put("out_trade_no", out_trade_no);
        data.put("device_info", device_info);
        data.put("fee_type", "CNY");
        data.put("total_fee", total_fee);
        data.put("nonce_str", nonce_str);
//        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://gzpt.bjpygh.com/notify.action");
        data.put("trade_type", "JSAPI");  // 此处指定为微信公众号支付
        data.put("openid", openid);
        data.put("sign", sign);
//        data.put("product_id", "12");

        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            if(resp.get("return_code").equals("SUCCESS")){
            	status.setStatus(1);
            	String timeStamp = ""+(new Date().getTime())/1000;
                status.setTimeStamp(timeStamp);
                String prepay_id = "prepay_id="+resp.get("prepay_id");
                String sA="appId="+appid+"&nonceStr="+resp.get("nonce_str")+"&package="+prepay_id+"&signType=MD5"+"&timeStamp="+timeStamp;
                String sSignTemp=sA+"&key="+key; //注：key为商户平台设置的密钥key
                String paySign=MD5.string2MD5(sSignTemp); //注：MD5签名方式
                status.setPaySign(paySign);
                status.setData(resp);
            }
            
            System.out.println(new Gson().toJson(status));
            out.print(new Gson().toJson(status));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	//获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
		public static String getRandomString(int length) {
		    //随机字符串的随机字符库
		    String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		    StringBuffer sb = new StringBuffer();
		    int len = KeyString.length();
		    for (int i = 0; i < length; i++) {
		       sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
		    }
		    return sb.toString();
		} 
		
}
