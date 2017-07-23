package com.wxgzpt.bjpygh.servlet;
/**
 * 获取所有驾校信息
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.DsInfoDao;
import com.wxgzpt.bjpygh.entity.DsInformation;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class SelectDsInfoServlet extends BaseServlet{
	
	Status status;
	@Override
	void getExec(Map<String, String> map, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/index.html");

	}

	@Override
	void postExec(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
        
        DsInfoDao dsInfoDao = new DsInfoDao();
        
        status = new Status();
        status.setStatus(1);
        List<DsInformation> dspInfolist = dsInfoDao.selectDsInfoList();
		status.setDspInfolist(dspInfolist);
        
		Gson gson = new Gson();
		out.print(gson.toJson(status));
		out.flush();
		out.close();
	}
	
}
