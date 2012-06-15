package com.ezzie.enoch.examination;

import java.util.Random;

public class RandomInteger {

	
	
	public static int randomInteger(){
		Random rand = new Random();
		int n = rand.nextInt(100);
		if(n<0)
			 n = -n;
		if(n==0)
			  n= n+1;
		System.out.println(n);
		return new Integer(n);
		
	}
//	public static void main(String[] args){
//		Randominteger a = new Randominteger();
//	
//		System.out.println("this is " +  a.randomInteger());
//	}
}
