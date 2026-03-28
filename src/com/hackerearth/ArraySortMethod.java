package com.hackerearth;

import java.util.Arrays;
import java.util.Comparator;

public class ArraySortMethod {

	public static void main(String[] args) {

		String[] inputStrings1 = { "ABC", "DEV", "ZXY", "YZX" };
		String[] inputStrings2 = { "ABC", "DEV", "ZXY", "YZX" };
		Arrays.sort(inputStrings1, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {

				return o1.length() - o2.length();
			}
		});
		
		System.out.println("inputStrings1");
		for (String string : inputStrings2) {
			
			System.out.print(string+" ");
		}
		
		
		Arrays.sort(inputStrings2 , (o1,o2) -> o1.length()-o2.length());
		System.out.println("\ninputStrings2 ");
		for (String string : inputStrings2) {
			
			System.out.print(string + " ");
		}
		
		
	}
}
