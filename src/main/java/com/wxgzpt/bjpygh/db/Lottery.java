package com.wxgzpt.bjpygh.db;

public class Lottery {

	double s;
	int price = 100;;
	public int getPrice(){
		s = Math.random();
			if(s<=0.95){
			 price = 200;
		 }else if(s<=0.965){
			 price = 300;
		 }else if(s<=0.975){
			 price = 400;
		 }else if(s<=0.995){
			 price = 500;
		 }else if(s<=0.9994){
			 price = 600;
		 }else if(s<=0.9997){
			 price = 700;
		 }else if(s<=0.9999){
			 price = 800;
		 }else if(s<=1){
			 price = 1000;
		 }
		 return price;
	}
	
}
