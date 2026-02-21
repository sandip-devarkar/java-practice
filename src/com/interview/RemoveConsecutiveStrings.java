package com.interview;

public class RemoveConsecutiveStrings {

	public static void main(String[] args) {
		String inputString = "ababbabdad";
		findConsecutiveCharacterAndRemove(inputString);
	}

	private static void findConsecutiveCharacterAndRemove(String inputString) {

		char[] charArray = inputString.toCharArray();
		int lengthOfCharArray = charArray.length;

		StringBuffer stringbBuffer = new StringBuffer(charArray[0]);
		for (int i = 1; i < lengthOfCharArray; i++) {

			char ch = charArray[i];
			 int  lengthofBuffer = stringbBuffer.length();
			if (lengthofBuffer >0 && stringbBuffer.charAt(lengthofBuffer - 1) == ch) {
				stringbBuffer.deleteCharAt(lengthofBuffer-1);
			} else {
				stringbBuffer.append(ch);
			}
		}
		
		System.out.println("stringbBuffer "+ stringbBuffer);
	}
}
