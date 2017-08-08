package com.wxgzpt.bjpygh.db;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
	
	public static void main(String[] args) {
//		UserDao userDao = new UserDao();
//		 User user = new User();
//         user.setCity("city");
//         user.setOpenid("openid");
//         user.setCountry("country");
//         user.setHeadimageurl("headimgurl");
//         user.setNickname("nickname");
//         user.setProvince("province");
//         user.setSex(Integer.parseInt("1"));
//         user = userDao.getUserById("1");
//         Gson gson = new Gson();
//         System.out.println(gson.toJson(user));
		
//		DsInfoDao dsInfoDao = new DsInfoDao();
//		DsInformation dsInfo = new DsInformation();
//		dsInfo.setAddress("鍖椾含甯傛捣娣�鍖轰竾娉夋渤璺传閲戝簞鍥�5鍙锋ゼ");
//		dsInfo.setDsimage("http://t.pshpsh.top/bjpygh/dsImage/yuanhang.jpg");
//		dsInfo.setDsintro("杩滆埅椹炬牎杩滆埅椹炬牎杩滆埅椹炬牎杩滆埅椹炬牎杩滆埅椹炬牎杩滆埅椹炬牎杩滆埅椹炬牎杩滆埅椹炬牎杩滆埅椹炬牎杩滆埅椹炬牎");
//		dsInfo.setDsname("杩滆埅椹炬牎");
//		dsInfoDao.insertDsInfo(dsInfo);
		
//		DsInfoDao dsInfoDao = new DsInfoDao();
//		Status status = new Status();
//		status.setDspInfolist(dsInfoDao.selectDsInfoList());
//		System.out.println(new Gson().toJson(status));
		
//		DsPackageDao dsPackageDao = new DsPackageDao();
//		DsPackage  dsPackage = new DsPackage();
//		dsPackage.setDsname("杩滆埅椹炬牎");
//		dsPackage.setDstype("瀹氬埗鐝�");
//		dsPackage.setModels("鐖变附鑸岰2");
//		dsPackage.setPrice(3600);
//		dsPackage.setTraintime("鍏ㄥ懆");
//		dsPackage.setDescription("鍛ㄤ竴鑷冲懆鏃ワ紝鏃堕棿鑷繁绾︼紝鑷姩鎸�");
//		dsPackageDao.insertDsPackage(dsPackage);
		
//        DsInfoDao dsInfoDao = new DsInfoDao();
//        DsInformation dsInfo = dsInfoDao.selectDsInfo("杩滆埅椹炬牎");
//        
//        DsPackageDao dsPackageDao = new DsPackageDao();
//        
//        dsInfo.setDspList(dsPackageDao.selectDsPackage("杩滆埅椹炬牎"));
//        
//        Gson gson = new Gson();
//		System.out.println(gson.toJson(dsInfo));
//		Map<String, String> map;
//		UserDao userDao = new UserDao();
//		map = new HashMap<String, String>();
//        map.put("userid", "1");
//        map.put("realname", "蒋圆");
//        map.put("address", "北京市海淀区");
//        map.put("reamark", "嘿嘿嘿");
//        
//        userDao.bondRealName(map);
		
//		String dsname = "";
//		Status status;
//		DsPackageDao dsPackageDao;
//		Gson gson;
//		gson = new Gson();
//        dsPackageDao = new DsPackageDao();
//        status = new Status();
//		status.setDsplist(dsPackageDao.selectDsPackage(dsname));
//    	System.out.println(gson.toJson(status));
		
//		double s;
//		int index1000=0;
//		int index800=0;
//		int index600=0;
//		int index500=0;
//		int index450=0;
//		int index400=0;
//		int index350=0;
//		int index300=0;
//		int index250=0;
//		int index200=0;
//		int index150=0;
//		int index100=0;
//		for(int i=0;i<10000;i++){
//			 s = Math.random();
//			 if(s<=0.4){
//				 index100++;
//			 }else if(s<=0.6){
//				 index150++;
//			 }else if(s<=0.9){
//				 index200++;
//			 }else if(s<=0.95){
//				 index250++;
//			 }else if(s<=0.97){
//				 index300++;
//			 }else if(s<=0.98){
//				 index350++;
//			 }else if(s<=0.99){
//				 index400++;
//			 }else if(s<=0.999){
//				 index450++;
//			 }else if(s<=0.9994){
//				 index500++;
//			 }else if(s<=0.9997){
//				 index600++;
//			 }else if(s<=0.9999){
//				 index800++;
//			 }else if(s<=1){
//				 index1000++;
//			 }
//			 System.out.println(s);
//		}
//		System.out.println("index100="+index100);
//		System.out.println("index150="+index150);
//		System.out.println("index200="+index200);
//		System.out.println("index250="+index250);
//		System.out.println("index300="+index300);
//		System.out.println("index350="+index350);
//		System.out.println("index400="+index400);
//		System.out.println("index450="+index450);
//		System.out.println("index500="+index500);
//		System.out.println("index600="+index600);
//		System.out.println("index800="+index800);
//		System.out.println("index1000="+index1000);
//		int sum = 100*index100+150*index150+200*index200+index250*250+index300*300+index400*400+index500*500+
//				350*index350+450*index450+600*index600+800*index800+1000*index1000;
//	System.out.println(sum);
//	int sss=10000*200;
//	System.out.println("yunajihua:"+sss);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//		try {
//			Date dt1 = formatter.parse("1970-01-08 08:00:00");
//			Date dt2 = formatter.parse("2017-7-5 00:00:00");
//			Date dt3 = formatter.parse("2017-7-12 00:00:00");
////			Date date = new Date();
//		    long times1 = dt1.getTime();
//		    long times2 = dt2.getTime();
//		    long times3 = dt3.getTime();
//		    Date date = new Date(604800000L);
//		    System.out.println(times1);
//		    System.out.println(times2);
//		    System.out.println(times3-times2);
//		    System.out.println(date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 float a = 123.2334f;;
//		float  b   =  (float)(Math.round(a*100))/100;
//		System.out.println(b);
		 Calendar c = Calendar.getInstance();//閸欘垯浜掔�佃鐦℃稉顏呮闂傛潙鐓欓崡鏇犲娣囶喗鏁�
         int year = c.get(Calendar.YEAR); 
         int month = c.get(Calendar.MONTH); 
         int date = c.get(Calendar.DATE); 
         int hour = c.get(Calendar.HOUR_OF_DAY); 
         int minute = c.get(Calendar.MINUTE); 
         int second = c.get(Calendar.SECOND);
         int milliseconds = c.get(Calendar.MILLISECOND);
		Timestamp timestape = new Timestamp(year, month, date, hour, minute, second, milliseconds);
		System.out.println(timestape);
	}

}
