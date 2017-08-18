package com.wxgzpt.bjpygh.servlet;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxgzpt.bjpygh.dao.RecordDao;
import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.db.XMLToMap;
import com.wxgzpt.bjpygh.entity.IntegralRecord;
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
			String total_fee = map.get("total_fee");
			UserDao userDao = new UserDao();
			User user = userDao.getUserByOpenid(map.get("openid"));
			Map<String, String> uMap = new HashMap<String, String>();
			
			//插入充值记录
			IntegralRecord record = new IntegralRecord();
			record.setValue(Integer.parseInt(total_fee)/100);
			record.setNote("微信支付充值");
			record.setUserid(user.getUserid());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			record.setTime(formatter.format(new Date()));
			RecordDao recordDao = new RecordDao();
			recordDao.insertRecord(record);
			
			uMap.put("userid", ""+user.getUserid());
			switch(total_fee){
			case "1":
				uMap.put("memberpoints", (user.getMemberpoints()+5)+"");
				uMap.put("integral", (user.getIntegral()+5)+"");
				userDao.changeUserPoints(uMap);
				break;
			case "2":
				uMap.put("memberpoints", (user.getMemberpoints()+10)+"");
				uMap.put("integral", (user.getIntegral()+10)+"");
				userDao.changeUserPoints(uMap);
				break;
			case "3":
				uMap.put("memberpoints", (user.getMemberpoints()+15)+"");
				uMap.put("integral", (user.getIntegral()+15)+"");
				userDao.changeUserPoints(uMap);
				break;
			case "4":
				uMap.put("memberpoints", (user.getMemberpoints()+20)+"");
				uMap.put("integral", (user.getIntegral()+20)+"");
				userDao.changeUserPoints(uMap);
				break;
			case "5":
				uMap.put("memberpoints", (user.getMemberpoints()+50)+"");
				uMap.put("integral", (user.getIntegral()+50)+"");
				userDao.changeUserPoints(uMap);
				break;
			case "6":
				uMap.put("memberpoints", (user.getMemberpoints()+100)+"");
				uMap.put("integral", (user.getIntegral()+100)+"");
				userDao.changeUserPoints(uMap);
				break;
			default:
					
				break;
			}
		}else{
			System.out.println("result_code:fail");
		}
		response.getWriter().print("<xml>  <return_code><![CDATA[SUCCESS]]></return_code>  <return_msg><![CDATA[OK]]></return_msg></xml>");
	}
	
}
