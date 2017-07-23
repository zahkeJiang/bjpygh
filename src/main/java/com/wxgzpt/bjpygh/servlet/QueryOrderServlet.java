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
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.google.gson.Gson;
import com.wxgzpt.bjpygh.config.AlipayConfig;
import com.wxgzpt.bjpygh.dao.DsOrderDao;
import com.wxgzpt.bjpygh.dao.UserCouponDao;
import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.Status;
import com.wxgzpt.bjpygh.entity.UserCoupon;

@SuppressWarnings("serial")
public class QueryOrderServlet extends HttpServlet{
	UserCouponDao userCouponDao;
	DsOrderDao dsOrderDao;
	UserCoupon userCoupon;
	DsOrder dsOrder;
	Status status;
	Gson gson;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        status = new Status();
		gson = new Gson();
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
		
			String userid = userMap.get("id");
			dsOrderDao = new DsOrderDao();
			
			dsOrder = dsOrderDao.getDsOrder(userid);
			if(dsOrder==null){
				status.setStatus(0);
				out.print(gson.toJson(status));
				System.out.println(gson.toJson(status));
				out.flush();
				out.close();
				return;
			}
			
			if(dsOrder.getOrderstatus()==0){
				status.setStatus(0);
				out.print(gson.toJson(status));
				System.out.println(gson.toJson(status));
				out.flush();
				out.close();
				return;
			}
			/*判断是否为空*/
			String out_trade_no = dsOrder.getOrdernumber();
			/**********************/
			 // SDK ���������࣬������������������Լ���װ��ǩ������ǩ�������������עǩ������ǩ     
			 AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
			 AlipayTradeQueryRequest alipay_request = new AlipayTradeQueryRequest();
			 
			 AlipayTradeQueryModel model=new AlipayTradeQueryModel();
		     model.setOutTradeNo(out_trade_no);
		     model.setTradeNo("");
		     alipay_request.setBizModel(model);
		     
		     AlipayTradeQueryResponse alipay_response;
			try {
				alipay_response = client.execute(alipay_request);
				String result = alipay_response.getBody();
				
				JSONObject tmp = JSONObject.fromObject(result);
				String data = tmp.getString("alipay_trade_query_response");
				System.out.println(data);
				JSONObject obj = JSONObject.fromObject(data);
				if(obj.getString("trade_status").equals("TRADE_SUCCESS")){
					/*改变用户优惠券状态*/
					userCouponDao = new UserCouponDao();
					try {
						userCoupon = userCouponDao.selectUserCoupon(userid);
						if(userCoupon.getCouponstatus()==1){
							Map<String, String> map = new HashMap<String, String>();
							map.put("couponstatus", "2");
							map.put("userid", userid);
							userCouponDao.updataCouponStatus(map);
						}else{
							status.setStatus(1);
							status.setPrice(userCoupon.getCouponprice());
						}
					} catch (NullPointerException e) {
						e.printStackTrace();
					}
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("userid", userid);
					map.put("orderstatus", "1");
					dsOrderDao.changeStatus(map);
					status.setStatus(1);
					status.setOrderNumber(obj.getString("out_trade_no"));
					status.setPrice(Integer.parseInt(dsOrder.getOrderprice()));
					out.print(gson.toJson(status));
					System.out.println(gson.toJson(status));
				}else{
					status.setStatus(0);
					out.print(gson.toJson(status));
					System.out.println(gson.toJson(status));
				}
			} catch (AlipayApiException e) {
				status.setStatus(1);
				out.print(gson.toJson(status));
			}finally{
				out.flush();
				out.close();
			}		     
	}

}
