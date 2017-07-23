package com.wxgzpt.bjpygh.servlet;
/**
 * 获取所有驾校信息以及驾校套餐信息
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.DsInfoDao;
import com.wxgzpt.bjpygh.dao.DsPackageDao;
import com.wxgzpt.bjpygh.entity.DsInformation;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class SelectDsPackageServlet extends HttpServlet{

	DsInformation dsInfo;
	DsInfoDao dsInfoDao;
	DsPackageDao dsPackageDao;
	Gson gson;
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
			status.setStatus(0);
			status.setMsg("请在微信端登录");
			out.print(new Gson().toJson(status));
			System.out.println(new Gson().toJson(status));
			out.flush();
			out.close();
			return;
		}
		
        String dsname = request.getParameter("dsname");
        dsInfoDao = new DsInfoDao();
        dsPackageDao = new DsPackageDao();
        gson = new Gson();
        if(dsname==null){
        	
        	status.setStatus(1);
        	status.setDsplist(dsPackageDao.selectDsPackage(dsname));
        	out.print(gson.toJson(status));
        }else{
        	 dsInfo = dsInfoDao.selectDsInfo(dsname);
             dsInfo.setDspList(dsPackageDao.selectDsPackage(dsname));
             out.print(gson.toJson(dsInfo));
        }
        out.flush();
        out.close();
	}
	
}
