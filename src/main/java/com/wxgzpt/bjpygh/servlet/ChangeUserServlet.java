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
import com.wxgzpt.bjpygh.entity.User;

@SuppressWarnings("serial")
public class ChangeUserServlet extends HttpServlet{

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
			status.setStatus(-1);
			status.setMsg("请在微信端登录");
			out.print(gson.toJson(status));
			System.out.println(gson.toJson(status));
			out.flush();
			out.close();
			return;
		}
		
		String userid = userMap.get("id");
		UserDao userDao = new UserDao();
		User user = userDao.getUserById(userid);
		
		String sex = request.getParameter("sex");
		String nickname = request.getParameter("nickname");
		String school = request.getParameter("school");
		String city = request.getParameter("city");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		if(sex!=null){
			map.put("sex", sex);
		}else{
			map.put("sex", user.getSex()+"");
		}
		if(nickname!=null){
			map.put("nickname", nickname);
		}else{
			map.put("nickname", user.getNickname());
		}
		if(school!=null){
			map.put("school", school);
		}else{
			map.put("school", user.getSchool());
		}
		if(city!=null){
			map.put("city", city);
		}else{
			map.put("city", user.getCity());
		}	
		
		userDao.changeUserInfo(map);
		status.setStatus(1);
		out.print(gson.toJson(status));
		out.flush();
		out.close();
	}
	
}
