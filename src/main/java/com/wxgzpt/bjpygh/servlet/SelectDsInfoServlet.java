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
import javax.servlet.http.HttpSession;

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
        status = new Status();
        status.setStatus(1);
        List<DsInformation> dspInfolist = dsInfoDao.selectDsInfoList();
		status.setDspInfolist(dspInfolist);
        
		Gson gson = new Gson();
		out.print(gson.toJson(status));
		System.out.println(gson.toJson(status));
		out.flush();
		out.close();
	}
	
}
