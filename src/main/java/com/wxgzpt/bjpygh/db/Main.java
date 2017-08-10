package com.wxgzpt.bjpygh.db;

import java.util.Map;

public class Main {
	
	public static void main(String[] args) {
		String text = "<xml><appid><![CDATA[wx74d8d40a83387a3e]]></appid><bank_type><![CDATA[CFT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><device_info><![CDATA[WEB]]></device_info><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1483244352]]></mch_id><nonce_str><![CDATA[1a04b5880c7d44f1adc78a549c4c8677]]></nonce_str><openid><![CDATA[o9C-m0gWfR9WOs8DIDElxSUfDIUU]]></openid><out_trade_no><![CDATA[PYGHHY201771016161828]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[61A9FD0B6C4A99168E1DE8697FC9978F]]></sign><time_end><![CDATA[20170810161623]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[4003062001201708105501060817]]></transaction_id></xml>";
		
		XMLToMap x= new XMLToMap();
		Map<String, String> map = x.getXML(text);
		System.out.println(map);
		
	} 
	
}
