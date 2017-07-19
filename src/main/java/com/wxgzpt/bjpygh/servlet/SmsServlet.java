package com.wxgzpt.bjpygh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wxgzpt.bjpygh.dao.JuheSMS;
import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.entity.Status;
/**
 * 验证码请求接口
 * @author 蒋圆
 *
 */

@SuppressWarnings("serial")
public class SmsServlet extends HttpServlet{

	Status status;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");       
        PrintWriter out = response.getWriter();
        UserDao userDao = new UserDao();
        String mobile = request.getParameter("mobile");//获取手机号
        String userid = userDao.getUserIdByPN(mobile);
        if(userid != null){
        	status = new Status();
        	status.setStatus(0);
        	Gson gson = new Gson();
        	out.println(gson.toJson(status));
        	out.close();
        	return;
        }
        String tpl_id = request.getParameter("tpl_id");//获取聚合模板id
        String tpl_value = request.getParameter("tpl_value");//获取验证码的值
        
        JuheSMS.getRequest2(out,mobile, tpl_id, tpl_value);
        out.flush();
        out.close();
	}
}
