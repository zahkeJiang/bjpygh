package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.DsPackageDao;
import com.wxgzpt.bjpygh.entity.DsPackage;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class QueryPackageServlet extends HttpServlet{

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
        PrintWriter out = response.getWriter();
        Status status = new Status();
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
		
        String packageid = request.getParameter("packageid");
        DsPackageDao dsPackageDao = new DsPackageDao();
        Gson gson = new Gson();
        DsPackage dsPackage = dsPackageDao.selectDsPackageById(packageid);
        if(dsPackage!=null){
        	 status.setStatus(1);
             status.setData(dsPackage);
        }else{
        	status.setStatus(0);
        }
        out.print(gson.toJson(status));
        System.out.println(gson.toJson(status));
        out.flush();
        out.close();
	}
	
}
