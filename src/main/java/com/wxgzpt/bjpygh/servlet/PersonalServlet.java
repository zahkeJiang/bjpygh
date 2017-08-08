package com.wxgzpt.bjpygh.servlet;
/**
 * 个人中心接口
 * 获取用户个人信息
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
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
public class PersonalServlet extends BaseServlet{

	@Override
	void getExec(Map<String, String> map,HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
//			request.getRequestDispatcher("user.html?userid="+userid).forward(request, response);
			response.sendRedirect("/user.html");
	}

	@Override
	void postExec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		UserDao userDao = new UserDao();
		String userid = userMap.get("id");
		User user = userDao.getUserById(userid);
		UserCouponDao userCouponDao = new UserCouponDao();
		UserCoupon userCoupon = userCouponDao.selectUserCoupon(userid);
		if(userCoupon !=null){
			if(userCoupon.getCouponstatus() == 1||userCoupon.getCouponstatus() == 2){
				status.setCount(1);
			}else{
				status.setCount(0);
			}
		}else{
			status.setCount(0);
		}
		
		status.setStatus(1);
		status.setMsg("获取成功");
		status.setData(user);
		out.print(new Gson().toJson(status));
		System.out.println(new Gson().toJson(status));
		out.flush();
		out.close();
	}

}
