package com.ezzie.enoch.examination;

import java.util.Random;

public class RandomString { 
	public static String randomstring(int lo, int hi){
		int n = rand(lo,hi);
		char b[] = new char[n];
		for (int i = 0; i<n; i++)
			b[i] = (char)rand ('a', 'z');
		return new String(b);
	}
	private static int rand(int lo, int hi){
		Random rand = new Random();
		int n = hi-lo +1;
		int i = rand.nextInt(n);
		if (i<0)
			i = -i;
		return lo+i;
		
	}
	public static String randomstring(){
		return randomstring(4,20);
	}
//	public static String radomstrings(){
//		return randomstring(4,51);
//	}
}
