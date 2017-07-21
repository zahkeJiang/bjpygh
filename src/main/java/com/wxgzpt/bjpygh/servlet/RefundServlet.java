package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class RefundServlet extends HttpServlet{

	DsOrderDao dsOrderDao;
	DsOrder dsOrder;
	Status status;
	Gson gson;
	
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
        
		PrintWriter op = response.getWriter();
		status = new Status();
		dsOrderDao = new DsOrderDao();
		gson =new Gson();
		if(request.getParameter("userid")!=null){
			String userid = request.getParameter("userid");
			
			String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
			String refund_amount=new String(request.getParameter("WIDrefund_amount").getBytes("ISO-8859-1"),"UTF-8");
			
			String refund_reason=new String(request.getParameter("WIDrefund_reason").getBytes("ISO-8859-1"),"UTF-8");
			
			/**********************/
			 AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
			AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();
			
			AlipayTradeRefundModel model=new AlipayTradeRefundModel();
			model.setOutTradeNo(out_trade_no);
			model.setTradeNo("");
			model.setRefundAmount(refund_amount);
			model.setRefundReason(refund_reason);
			model.setOutRequestNo("");
			alipay_request.setBizModel(model);
			
			AlipayTradeRefundResponse alipay_response;
			try {
				alipay_response = client.execute(alipay_request);
				String result = alipay_response.getBody();
				JSONObject tmp = JSONObject.fromObject(result);
				String data = tmp.getString("alipay_trade_refund_response");
				JSONObject obj = JSONObject.fromObject(data);
				if(obj.getString("sub_code").equals("ACQ.TRADE_HAS_SUCCESS")){
					Map<String, String> map = new HashMap<String, String>();
					map.put("userid", userid);
					map.put("orderstatus","4");
					dsOrderDao.changeStatus(map);
					
					status.setStatus(1);
				}else{
					status.setStatus(0);
				}
				op.print(status);
				 System.out.println(result);
			} catch (AlipayApiException e) {
				status.setStatus(0);
				op.print(status);
			}finally{
				op.flush();
				op.close();
			}
		   
		  }
	}
		
}
