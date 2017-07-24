package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class QueryCouponServlet extends HttpServlet{
	private UserCouponDao userCouponDao;
	private UserCoupon userCoupon;
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
			out.print(new Gson().toJson(status));
			System.out.println(new Gson().toJson(status));
			out.flush();
			out.close();
			return;
		}
			String userid = userMap.get("id");
			
			userCouponDao = new UserCouponDao();
			try {
				userCoupon = userCouponDao.selectUserCoupon(userid);
				UserDao userDao = new UserDao();
				User user = userDao.getUserById(userid);
				Date date = new Date(604800000L);
				if(userCoupon.getCouponstatus()==1&&userCoupon!=null){
					if((new Date()).getTime()-userCoupon.getCoupontime().getTime()<date.getTime()||user.getMemberpoints()>99){
						status.setStatus(1);
						status.setPrice(userCoupon.getCouponprice());
					}else{
						status.setStatus(0);
					}	
				}else{
					status.setStatus(0);
					
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				status.setStatus(0);
			}finally{
				out.print(gson.toJson(status));
				System.out.println(gson.toJson(status));
				out.flush();
				out.close();
			}
		
	}
	
}
