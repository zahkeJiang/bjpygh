package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.DsOrderDao;
import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.entity.DsOrder;
import com.wxgzpt.bjpygh.entity.Status;

@SuppressWarnings("serial")
public class NoteServlet extends HttpServlet{
	Status status;
	Gson gson;
	DsOrderDao dsOrderDao;
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
        dsOrderDao = new DsOrderDao();
        status = new Status();
        gson = new Gson();
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
        Map<String, String> map = new HashMap<String, String>();
        	String userid = userMap.get("id");
            String realname = request.getParameter("realname");
            String address = request.getParameter("address");
            String note = request.getParameter("note");
            List<DsOrder> dsOrder = dsOrderDao.getOrderById(userid);
            for(DsOrder dso : dsOrder){
            	if(dso!=null&&dso.getOrderstatus()!=0&&dso.getOrderstatus()!=5){
                	status.setStatus(0);
                    out.print(gson.toJson(status));
                    System.out.println(gson.toJson(status));
                    out.flush();
                    out.close();
                    return;
                }
            }
            
            map.put("userid", userid);
            map.put("realname", realname);
            map.put("address", address);
            map.put("reamark", note);
            
            userDao.bondRealName(map);
            System.out.println("finish");
            
            status.setStatus(1);
            out.print(gson.toJson(status));
            System.out.println(gson.toJson(status));
            out.flush();
            out.close();
	}
}
