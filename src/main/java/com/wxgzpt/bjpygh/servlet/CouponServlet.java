package com.wxgzpt.bjpygh.servlet;
/**
 * 抽奖接口
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.UserCouponDao;
import com.wxgzpt.bjpygh.db.Lottery;
import com.wxgzpt.bjpygh.entity.Status;
import com.wxgzpt.bjpygh.entity.UserCoupon;

@SuppressWarnings("serial")
public class CouponServlet extends BaseServlet{

	UserCouponDao userCouponDao;
	UserCoupon userCoupon;
	Status status;
	Gson gson;
	@Override
	void getExec(String userid, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("/coupon.html?userid="+userid);
	}

	@Override
	void postExec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userid = request.getParameter("userId");
		userCouponDao = new UserCouponDao();
		userCoupon = userCouponDao.selectUserCoupon(userid);
		status = new Status();
		
		if(userCoupon == null){
			userCoupon.setUserid(Integer.parseInt(userid));
			userCoupon.setCouponprice(0);
			userCoupon.setCouponstatus(0);
			userCoupon.setCoupontype(2);
			userCouponDao.insertUserCoupon(userCoupon);
			status.setStatus(3);
			out.print(gson.toJson(status));
		}else if(userCoupon.getCouponstatus() == 0){
			status.setStatus(3);
			out.print(gson.toJson(status));
		}else{
			status.setStatus(1);
			Lottery lottery = new Lottery();
			status.setPrice(lottery.getPrice());
			out.print(gson.toJson(status));
		}
		
		out.flush();
		out.close();
	}
	
}
