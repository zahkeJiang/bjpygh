package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.UserCouponDao;
import com.wxgzpt.bjpygh.entity.Status;
import com.wxgzpt.bjpygh.entity.UserCoupon;

@SuppressWarnings("serial")
public class QueryCouponServlet extends HttpServlet{
	private UserCouponDao userCouponDao;
	private UserCoupon userCoupon;
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
		if(request.getParameter("userid")!=null){
			String userid = request.getParameter("userid");
			status = new Status();
			gson = new Gson();
			userCouponDao = new UserCouponDao();
			try {
				userCoupon = userCouponDao.selectUserCoupon(userid);
				if(userCoupon.getCouponstatus()!=1){
					status.setStatus(0);
				}else{
					status.setStatus(1);
					status.setPrice(userCoupon.getCouponprice());
				}
			} catch (NullPointerException e) {
				status.setStatus(0);
			}finally{
				op.print(gson.toJson(status));
				op.flush();
				op.close();
			}
		}
		
	}
	
}
