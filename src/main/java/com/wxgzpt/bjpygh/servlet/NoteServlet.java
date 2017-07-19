package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class NoteServlet extends HttpServlet{
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
        PrintWriter out = response.getWriter();
        UserDao userDao = new UserDao();
        Map<String, String> map = new HashMap<String, String>();
        	String userid = request.getParameter("userid");
            String realname = request.getParameter("realname");
            String address = request.getParameter("address");
            String note = request.getParameter("note");
            
            map.put("userid", userid);
            map.put("realname", realname);
            map.put("address", address);
            map.put("reamark", note);
            
            userDao.bondRealName(map);
            System.out.println("finish");
            status = new Status();
            status.setStatus(1);
            gson = new Gson();
            out.print(gson.toJson(status));
            out.flush();
            out.close();
	}
}
