package com.wxgzpt.bjpygh.servlet;
/**
 * 获取所有驾校信息以及驾校套餐信息
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.DsInfoDao;
import com.wxgzpt.bjpygh.dao.DsPackageDao;
import com.wxgzpt.bjpygh.entity.DsInformation;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class SelectDsPackageServlet extends HttpServlet{

	DsInformation dsInfo;
	Status status;
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
        String dsname = request.getParameter("dsname");
        dsInfoDao = new DsInfoDao();
        dsPackageDao = new DsPackageDao();
        gson = new Gson();
        if(dsname==null){
        	status = new Status();
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
