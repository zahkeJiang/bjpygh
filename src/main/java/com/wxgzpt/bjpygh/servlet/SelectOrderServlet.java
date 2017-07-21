package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.DsOrderDao;
import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class SelectOrderServlet extends HttpServlet{
	DsOrderDao dsOrderDao;
	List<DsOrder> dsOrder;
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
        
		PrintWriter op = response.getWriter();
		if(request.getParameter("userid")!=null){
			String userid = request.getParameter("userid");
			
			dsOrderDao = new DsOrderDao();
			status = new Status();
			gson = new Gson();
			
			dsOrder = dsOrderDao.getOrderById(userid);
			status.setStatus(0);
			for(DsOrder dso:dsOrder){
				status.setDsOrder(dsOrder);
				if(dso.getOrderstatus() == 1){
					status.setStatus(1);
				}
			}
			
			
			op.print(gson.toJson(status));
			System.out.println(gson.toJson(status));
			op.flush();
			op.close();
		}
	}
	
}
