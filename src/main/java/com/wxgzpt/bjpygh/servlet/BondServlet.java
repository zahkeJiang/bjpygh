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
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class BondServlet extends HttpServlet{
	Status status;
	Gson gson;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        status = new Status();
        HttpSession session = request.getSession();
        Map<String, String> userMap = (Map<String, String>) session.getAttribute("user");
        System.out.println(userMap);
		UserDao userDao = new UserDao();
		String userid = userMap.get("id");
		String phonenumber = request.getParameter("phonenumber");
		String otherid = userDao.getUserIdByPN(phonenumber);
		if(otherid != null){
			status.setStatus(0);
			gson = new Gson();
			out.println(gson.toJson(status));
		}else{
			Map<String,String> map = new HashMap<String,String>();
			map.put("userid", userid);
			map.put("phonenumber", phonenumber);
			userDao.bondUser(map);
			userMap.put("phone", phonenumber);
			session.setAttribute("user", userMap);
			
			status.setStatus(1);
			gson = new Gson();
			out.println(gson.toJson(status));
		}
		out.flush();
		out.close();
	}
	
}
