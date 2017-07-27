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
import com.wxgzpt.bjpygh.dao.DsOrderDao;
import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class SelectOrderServlet extends HttpServlet{
	DsOrderDao dsOrderDao;
	List<DsOrder> dsOrder;

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
	Status status = new Status();
	HttpSession session = request.getSession();
	Map<String, String> userMap = (Map<String, String>) session.getAttribute("user");

	if(userMap == null){
		status.setStatus(0);
		status.setMsg("请在微信端登录");
		out.print(new Gson().toJson(status));
		System.out.println(new Gson().toJson(status));
		out.flush();
		out.close();
		return;
	}
	
		String userid = userMap.get("id");	
		dsOrderDao = new DsOrderDao();
		Gson gson = new Gson();
		dsOrder = dsOrderDao.getOrderById(userid);
		System.out.println(dsOrder);
		status.setStatus(0);
		for(DsOrder dso:dsOrder){
			status.setDsOrder(dsOrder);
			if(dso.getOrderstatus() != 0){
				status.setStatus(1);
			}
		}
		
		out.print(gson.toJson(status));
		System.out.println(gson.toJson(status));
		out.flush();
		out.close();
	}

	
}
