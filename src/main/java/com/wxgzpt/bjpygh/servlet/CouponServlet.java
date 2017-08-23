package com.wxgzpt.bjpygh.servlet;
/**
 * 抽奖接口
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
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
		response.sendRedirect("/lottery.html");
	}

	@Override
	void postExec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		status = new Status();
		gson = new Gson();
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
		try {
			String userid = userMap.get("id");
			String isActive = userMap.get("active");
			userCouponDao = new UserCouponDao();
			userCoupon = new UserCoupon();

			if(userCouponDao.selectUserCoupon(userid)==null){
				if(isActive!=null&&isActive.equals("true")){
					Lottery lottery = new Lottery();
					Map<String, Integer> map = lottery.getPrice();
					int price = map.get("price");
					int num = map.get("num");
					status.setPrice(num);
					userCoupon.setUserid(Long.parseLong(userid));
					userCoupon.setCouponprice(price);
					userCoupon.setCouponstatus(1);
					userCoupon.setCoupontype(2);
					userCoupon.setCoupontime(new Date());
					userCouponDao.insertUserCoupon(userCoupon);
					status.setStatus(1);
					
					out.print(gson.toJson(status));
					System.out.println(gson.toJson(status));
				}else{
					status.setStatus(2);
					status.setMsg("请先激活");
					System.out.println(gson.toJson(status));
					out.print(gson.toJson(status));
				}
				
			}else{
				status.setStatus(0);
				status.setMsg("优惠券已存在");
				System.out.println(gson.toJson(status));
				out.print(gson.toJson(status));
			}
			out.flush();
			out.close();
		} catch (NullPointerException e) {
			
		}
		
	}
	
}
