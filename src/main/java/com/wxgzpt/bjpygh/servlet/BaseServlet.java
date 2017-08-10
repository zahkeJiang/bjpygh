package com.wxgzpt.bjpygh.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.wxgzpt.bjpygh.dao.UserDao;
import com.wxgzpt.bjpygh.entity.Global;
import com.wxgzpt.bjpygh.entity.User;

@SuppressWarnings("serial")
abstract class BaseServlet extends HttpServlet {

	UserDao userDao;
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
		Map<String, String> userMap = (Map<String, String>) session.getAttribute("user");
        System.out.println(userMap);
        userDao = new UserDao();
        Map<String, String> map = new HashMap<String, String>();
        if(userMap == null){
        	userMap = new HashMap<String, String>();
            map = getMap(request);
            String openid =map.get("openid");
            String access_token = map.get("access_token");
            try{
            	
            	String userId = userDao.getUserIdByOpenid(openid);
            	String phone = userDao.getPhoneNumber(openid);
            	if(userId == null){		//判断用户是否存在
    				//用户不存在则插入用户
    				User user = getUser(getUserInfo(openid, access_token));
    				userDao.InsertUserFromWx(user);
    				userId = userDao.getUserIdByOpenid(openid);
    				
    			}
            	
            	userMap.put("id",userId);
				userMap.put("openid", openid);
				userMap.put("phone", phone);
				session.setAttribute("user", userMap );            	
            	
            } catch (NullPointerException e) {
//    			userDao.InsertUserFromWx(user);
//    			userId = userDao.getUserIdByOpenid(openId);
//    			request.getRequestDispatcher("/login.html").forward(request, response);
    		}
        }
        System.out.println("base:"+userMap);
        String phonenumber = userMap.get("phone");
		if(phonenumber != null){	//判断用户是否绑定
			this.getExec(map,request, response);
		}else{
			
			response.sendRedirect("/login.html");      
		}
        
      	
	}

	private String getUserInfo(String openid,String access_token) {
		
		String userInfo = getInfo(Global.WXURL+Global.WXURLUF+"?access_token="+access_token+"&openid="+openid+"&lang=zh_CN");
		return userInfo;
	}

	private User getUser(String userInfo) {
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

	private Map<String, String> getMap(HttpServletRequest request) {
		String code = request.getParameter("code");

		String sb1 = getInfo(Global.WXURL+Global.WXURLAT+"?appid=wx74d8d40a83387a3e&secret=0f84386999305a8cd8464fc32efb01f3&code="+code+"&grant_type=authorization_code");
        JSONObject tmp = JSONObject.fromObject(sb1);
        String access_token = tmp.getString("access_token");
        String openid = tmp.getString("openid");
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", access_token);
        map.put("openid", openid);
		return map;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
		this.postExec(request, response);	
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
	
	
	abstract void getExec(Map<String, String> map,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
	
	abstract void postExec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
}
