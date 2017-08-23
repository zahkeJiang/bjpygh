package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class ActCodeServlet extends HttpServlet{

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
		Status status = new Status();
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		
		String ActCode = request.getParameter("code");
		if(ActCode.equals("PYGH521")){
			status.setStatus(1);
			status.setMsg("验证成功");
			out.print(gson.toJson(status));
		}else{
			status.setStatus(0);
			status.setMsg("验证失败");
			out.print(gson.toJson(status));
		}
		
		out.flush();
		out.close();
	}
	
}
