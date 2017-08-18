package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.RecordDao;
import com.wxgzpt.bjpygh.entity.IntegralRecord;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class SelectRecordServlet extends HttpServlet{

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
			out.print(new Gson().toJson(status));
			System.out.println(new Gson().toJson(status));
			out.flush();
			out.close();
			return;
		}
		
		String userid = userMap.get("id");	
		
		RecordDao recordDao = new RecordDao();
		List<IntegralRecord> records = recordDao.getRecordById(userid);
		if(records.size()>0){
			status.setStatus(1);
			status.setData(records);
			status.setMsg("获取成功");
			out.print(gson.toJson(status));
			System.out.println(gson.toJson(status));
		}else{
			status.setStatus(0);
			status.setMsg("没有记录");
			out.print(gson.toJson(status));
		}
		out.flush();
		out.close();
	}
	
}
