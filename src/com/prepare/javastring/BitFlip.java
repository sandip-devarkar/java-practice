package com.prepare.javastring;

public class BitFlip {

	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		int bitCount = Integer.bitCount(a ^ b);
		System.out.println("BitCount "+ bitCount);
	}
}
