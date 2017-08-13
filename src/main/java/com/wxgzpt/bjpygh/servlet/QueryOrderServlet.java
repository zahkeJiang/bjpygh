package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.DsInfoDao;
import com.wxgzpt.bjpygh.dao.DsOrderDao;
import com.wxgzpt.bjpygh.dao.UserCouponDao;
import com.wxgzpt.bjpygh.entity.DsInformation;
import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.Status;
import com.wxgzpt.bjpygh.entity.UserCoupon;

@SuppressWarnings("serial")
public class QueryOrderServlet extends HttpServlet{
	UserCouponDao userCouponDao;
	DsOrderDao dsOrderDao;
	UserCoupon userCoupon;

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
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
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
		
			String userid = userMap.get("id");
			dsOrderDao = new DsOrderDao();
			DsInfoDao dsInfoDao = new DsInfoDao();
			
			List<DsOrder> dsOrder = dsOrderDao.getOrderById(userid);
			
			if(dsOrder==null){
				status.setStatus(0);
				out.print(gson.toJson(status));
				System.out.println(gson.toJson(status));
				out.flush();
				out.close();
				return;
			}
			for (DsOrder dso:dsOrder){
				DsInformation DsInfo = dsInfoDao.selectDsInfo(dso.getDsname());
				if(dso.getOrderstatus()==1){
					status.setStatus(1);
					status.setImageurl(DsInfo.getDsimage());
					status.setPrice(dso.getOrderprice());
					status.setOrderNumber(dso.getOrdernumber());
					out.print(gson.toJson(status));
					System.out.println(gson.toJson(status));
					out.flush();
					out.close();
					return;
				}else{
					status.setStatus(0);
					out.print(gson.toJson(status));
					System.out.println(gson.toJson(status));
				}	 
			}
			
			out.flush();
			out.close();
	}


}
