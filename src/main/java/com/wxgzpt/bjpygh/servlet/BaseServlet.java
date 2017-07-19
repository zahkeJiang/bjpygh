package com.wxgzpt.bjpygh.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.entity.Global;
import com.wxgzpt.bjpygh.entity.User;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
abstract class BaseServlet extends HttpServlet {

	String openId;
	String userId;
	User user;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        UserDao userDao = new UserDao();
        user = this.getUser(request, response);
		try {
			userId = userDao.getUserIdByOpenid(openId);
			if(userId != null){		//判断用户是否存在
				String phonenumber = userDao.getPhoneNumber(openId);
				if(phonenumber != null){	//判断用户是否绑定
					this.getExec(userId,request, response);
				}else{
					response.sendRedirect("login.html?userid="+userId);
				}
			}else{
				//用户不存在则插入用户并绑定
				userDao.InsertUserFromWx(user);
				userId = userDao.getUserIdByOpenid(openId);
				response.sendRedirect("login.html?userid="+userId);
			}
		} catch (NullPointerException e) {
//			userDao.InsertUserFromWx(user);
//			userId = userDao.getUserIdByOpenid(openId);
//			request.getRequestDispatcher("/login.html").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
		this.postExec(request, response);
	}

	//获取公众号用户信息
	private User getUser(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");

			 String sb1 = getInfo(Global.WXURL+Global.WXURLAT+"?appid=wx74d8d40a83387a3e&secret=0f84386999305a8cd8464fc32efb01f3&code="+code+"&grant_type=authorization_code");
	         JSONObject tmp = JSONObject.fromObject(sb1);
	         String access_token = tmp.getString("access_token");
	         String openid = tmp.getString("openid");
	         openId = openid;
	         
	         String userInfo = getInfo(Global.WXURL+Global.WXURLUF+"?access_token="+access_token+"&openid="+openid+"&lang=zh_CN");
	         User user = new User();
	         JSONObject user1 = JSONObject.fromObject(userInfo);
	         user.setCity(user1.getString("city"));
	         user.setOpenid(user1.getString("openid"));
	         user.setCountry(user1.getString("country"));
	         user.setHeadimageurl(user1.getString("headimgurl"));
	         user.setNickname(user1.getString("nickname"));
	         user.setProvince(user1.getString("province"));
	         user.setSex(Integer.parseInt(user1.getString("sex")));
	         return user;
	}
	
	//get请求方式访问数据
	private String getInfo(String url){
		URL httpUrl;
		String s = null;
		try {
			httpUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5000);
			conn.setDoOutput(true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String str;
			while((str = reader.readLine())!=null){
				sb.append(str);
			}       
				s = sb.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return s; 
	}
	
	
	abstract void getExec(String userid,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
	
	abstract void postExec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
}
