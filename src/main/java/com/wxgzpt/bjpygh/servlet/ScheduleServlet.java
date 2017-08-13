package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.DsInfoDao;
import com.wxgzpt.bjpygh.dao.DsOrderDao;
import com.wxgzpt.bjpygh.dao.UserCouponDao;
import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.Status;
import com.wxgzpt.bjpygh.entity.UserCoupon;

@SuppressWarnings("serial")
public class ScheduleServlet extends BaseServlet{

	@Override
	void getExec(Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/schedule.html");
	}

	@Override
	void postExec(HttpServletRequest request, HttpServletResponse response)
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
		
			String userid = userMap.get("id");	
			String ordernumber = request.getParameter("ordernumber");
			DsOrderDao dsOrderDao = new DsOrderDao();
			DsOrder dsOrder = dsOrderDao.getDsOrderByNumber(ordernumber);
			Gson gson = new Gson();
			UserCouponDao userCouponDao = new UserCouponDao();
			UserCoupon userCoupon = userCouponDao.selectUserCoupon(userid);
			status.setData(dsOrder);
			status.setStatus(1);
			status.setPrice(userCoupon.getCouponprice());
			out.print(gson.toJson(status));
			System.out.println(gson.toJson(status));
			out.flush();
			out.close();
	}

}
