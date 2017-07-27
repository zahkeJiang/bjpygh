package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.google.gson.Gson;
import com.wxgzpt.bjpygh.config.AlipayConfig;
import com.wxgzpt.bjpygh.dao.DsOrderDao;
import com.wxgzpt.bjpygh.dao.UserCouponDao;
import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.Status;
import com.wxgzpt.bjpygh.entity.User;
import com.wxgzpt.bjpygh.entity.UserCoupon;

@SuppressWarnings("serial")
public class RefundServlet extends HttpServlet{

	UserCouponDao userCouponDao;	
	DsOrderDao dsOrderDao;
	UserCoupon userCoupon;
	DsOrder dsOrder;
	
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
        Status status = new Status();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Map<String, String> userMap = (Map<String, String>) session.getAttribute("user");
		if(userMap == null){
			status.setStatus(0);
			status.setMsg("请在微信端登录");
			out.print(new Gson().toJson(status));
			System.out.println(new Gson().toJson(status));
			out.flush();
			out.close();
			return;
		}
		
		dsOrderDao = new DsOrderDao();
		
			String userid = userMap.get("id");
			
//			String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
			
			
			String refund_reason=new String(request.getParameter("WIDrefund_reason").getBytes("ISO-8859-1"),"UTF-8");
			
			DsOrder dsOrder = dsOrderDao.getDsOrder(userid);
			
			if(dsOrder.getOrderstatus() == 3){
				status.setStatus(0);
				out.print(new Gson().toJson(status));
				out.flush();
				out.close();
				return;
			}
			String refund_amount=dsOrder.getOrderprice();
			/**********************/
			 AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
			AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();
			
			AlipayTradeRefundModel model=new AlipayTradeRefundModel();
			model.setOutTradeNo(dsOrder.getOrdernumber());
			model.setTradeNo("");
			model.setRefundAmount(""+(float)(Math.round((Float.parseFloat(refund_amount)*0.994)*100))/100);
			model.setRefundReason(refund_reason);
			model.setOutRequestNo("PYGH01RF001");
			alipay_request.setBizModel(model);
			
			AlipayTradeRefundResponse alipay_response;
			try {
				alipay_response = client.execute(alipay_request);
				String result = alipay_response.getBody();
				JSONObject tmp = JSONObject.fromObject(result);
				System.out.println(result);
				String data = tmp.getString("alipay_trade_refund_response");
				JSONObject obj = JSONObject.fromObject(data);
				if(obj.getString("code").equals("10000")){
					if(obj.getString("fund_change").equals("Y")){
						/*改变用户优惠券状态*/
						status.setStatus(1);
					
//					userCouponDao = new UserCouponDao();
//						userCoupon = userCouponDao.selectUserCoupon(userid);
//						if(userCoupon.getCouponstatus()==3){//判断优惠券已使用
//							Map<String, String> map = new HashMap<String, String>();
//							map.put("couponstatus", "1");
//							map.put("userid", userid);
//							userCouponDao.updataCouponStatus(map);
//							
//							UserDao userDao = new UserDao();
//							User user = userDao.getUserById(userid);
//							Map<String, String> pointMap = new HashMap<String, String>();
//							map.put("userid", userid);
//							map.put("memberpoints", user.getMemberpoints()+99+"");
//							userDao.changeUserPoints(pointMap);
//						}
						
						Map<String, String> map = new HashMap<String, String>();
						map.put("userid", userid);
						map.put("orderstatus","0");
						dsOrderDao.changeStatus(map);
						out.print(new Gson().toJson(status));
					}else{
						status.setStatus(0);
						out.print(new Gson().toJson(status));
					}
				}else{
					status.setStatus(0);
					out.print(new Gson().toJson(status));
				}
				
			} catch (AlipayApiException e) {
				status.setStatus(0);
				out.print(new Gson().toJson(status));
			}finally{
				out.flush();
				out.close();
				System.out.println(new Gson().toJson(status));
			}
		   
	}
		
}
