package com.wxgzpt.bjpygh.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	
	public static void main(String[] args) {
//		String text = "<xml><appid><![CDATA[wx74d8d40a83387a3e]]></appid><bank_type><![CDATA[CFT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><device_info><![CDATA[WEB]]></device_info><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1483244352]]></mch_id><nonce_str><![CDATA[1a04b5880c7d44f1adc78a549c4c8677]]></nonce_str><openid><![CDATA[o9C-m0gWfR9WOs8DIDElxSUfDIUU]]></openid><out_trade_no><![CDATA[PYGHHY201771016161828]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[61A9FD0B6C4A99168E1DE8697FC9978F]]></sign><time_end><![CDATA[20170810161623]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[4003062001201708105501060817]]></transaction_id></xml>";
//		
//		XMLToMap x= new XMLToMap();
//		Map<String, String> map = x.getXML(text);
//		System.out.println(map);
//		int sum = 0;
//		Lottery l = new Lottery();
//		for(int i=0;i<10000;i++){
//			int price = l.getPrice();
//			sum+=price;
//		}
//		System.out.println("10000个="+sum);
		
//		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date;
//		try {
//			date = format.parse("2017-08-13 13:12:39");
//			System.out.println(date.getTime());
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		
		try {
			Date date1 = sdf.parse("1995-02-01");
			Date date2= sdf.parse("1995-03-01");
			System.out.println(date2.getTime()-date1.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} 
	
}
