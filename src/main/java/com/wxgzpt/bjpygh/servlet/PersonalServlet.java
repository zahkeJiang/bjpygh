package com.wxgzpt.bjpygh.servlet;
/**
 * 个人中心接口
 * 获取用户个人信息
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.entity.User;

@SuppressWarnings("serial")
public class PersonalServlet extends BaseServlet{

	@Override
	void getExec(String userid,HttpServletRequest request,HttpServletResponse response) 
			throws ServletException, IOException {
//			request.getRequestDispatcher("user.html?userid="+userid).forward(request, response);
			response.sendRedirect("/user.html?userid="+userid);
	}

	@Override
	void postExec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		UserDao userDao = new UserDao();
		String userid = request.getParameter("userid");
		User user = userDao.getUserById(userid);
		Gson gson = new Gson();
		
		out.println(gson.toJson(user));
		out.flush();
		out.close();
	}

}
