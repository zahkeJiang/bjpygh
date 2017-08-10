package com.wxgzpt.bjpygh.servlet;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.db.XMLToMap;

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
		System.out.println(map);
		if(map.get("result_code").equals("SUCCESS")){
			UserDao userDao = new UserDao();
			String userid = userDao.getUserIdByOpenid(map.get("openid"));
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
		}else{
			System.out.println("result_code:fail");
		}
	}
	
}
