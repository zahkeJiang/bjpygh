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

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.UserCouponDao;
import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.entity.Status;
import com.wxgzpt.bjpygh.entity.User;
import com.wxgzpt.bjpygh.entity.UserCoupon;

@SuppressWarnings("serial")
public class ActivationServlet extends HttpServlet{

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
			status.setStatus(0);
			status.setMsg("请在微信端登录");
			out.print(gson.toJson(status));
			System.out.println(new Gson().toJson(status));
			out.flush();
			out.close();
			return;
		}
		String userid = userMap.get("id");
		
		UserDao userDao = new UserDao();
		User user = userDao.getUserById(userid);
		if(user.getMemberpoints()<100){
			status.setStatus(0);
			status.setMsg("积分不足");
			out.print(gson.toJson(status));
			System.out.println(new Gson().toJson(status));
			out.flush();
			out.close();
			return;
		}
		Map<String, String> pointMap = new HashMap<String, String>();
		pointMap.put("userid", userid);
		pointMap.put("memberpoints", user.getMemberpoints()-100+"");
		userDao.changeUserPoints(pointMap);
		
			UserCouponDao userCouponDao = new UserCouponDao();
			UserCoupon userCoupon = userCouponDao.selectUserCoupon(userid);
			Map<String, String> statusMap = new HashMap<String, String>();
			statusMap.put("userid",""+userCoupon.getUserid());
			statusMap.put("couponstatus", "2");
			userCouponDao.updataCouponStatus(statusMap);
			
			
			status.setStatus(1);
			status.setMsg("激活成功");
			out.print(gson.toJson(status));
			System.out.println(gson.toJson(status));
			out.flush();
			out.close();
	}
	
}
