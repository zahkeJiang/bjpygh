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
			
			if(dsOrder.getOrderstatus()==1){
				status.setStatus(1);
				status.setPrice(Integer.parseInt(dsOrder.getOrderprice()));
				out.print(gson.toJson(status));
				System.out.println(gson.toJson(status));
			}else{
				status.setStatus(0);
				out.print(gson.toJson(status));
				System.out.println(gson.toJson(status));
			}	 
			out.flush();
			out.close();
	}

}
