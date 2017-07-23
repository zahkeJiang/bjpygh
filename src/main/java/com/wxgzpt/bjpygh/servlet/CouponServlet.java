package com.wxgzpt.bjpygh.servlet;
/**
 * 抽奖接口
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
	void getExec(Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("/user.html");
	}

	@Override
	void postExec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
