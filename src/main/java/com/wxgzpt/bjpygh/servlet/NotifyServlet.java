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
import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.db.XMLToMap;
import com.wxgzpt.bjpygh.entity.Status;
import com.wxgzpt.bjpygh.entity.User;

@SuppressWarnings("serial")
public class NotifyServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
		
        String inputLine;
        String notityXml = "";

        try {
        while ((inputLine = request.getReader().readLine()) != null) {
        notityXml += inputLine;
        }
        request.getReader().close();
        } catch (Exception e) {
        e.printStackTrace();
        }
        XMLToMap x= new XMLToMap();
		Map<String, String> map = x.getXML(notityXml);
		if(map.get("result_code").equals("SUCCESS")){
			UserDao userDao = new UserDao();
			String total_fee = map.get("total_fee");
			Map<String, String> uMap = new HashMap<String, String>();
			uMap.put("userid", userid);
			switch(total_fee){
			case "1":
				uMap.put("memberpoints", "100");
				userDao.changeUserPoints(uMap);
				break;
			case "2":
				uMap.put("memberpoints", "100");
				userDao.changeUserPoints(uMap);
				break;
			case "3":
				uMap.put("memberpoints", "100");
				userDao.changeUserPoints(uMap);
				break;
			case "4":
				uMap.put("memberpoints", "100");
				userDao.changeUserPoints(uMap);
				break;
			case "5":
				uMap.put("memberpoints", "100");
				userDao.changeUserPoints(uMap);
				break;
			case "6":
				uMap.put("memberpoints", "100");
				userDao.changeUserPoints(uMap);
				break;
			default:
					
				break;
			}
		}
        out.flush();
        out.close();
	}
	
}
