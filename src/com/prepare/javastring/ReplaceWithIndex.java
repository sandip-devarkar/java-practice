package com.prepare.javastring;

import java.util.Arrays;

public class ReplaceWithIndex {

	public static void main(String[] args) {
		
		int[] originalArray = {5,4,2,1,0,3};
		    //index        //  0 1 2 3 4 5
		   //  0 iteration
		   // finalArray[originalArray[k]]= k;
           // 5 element of original array needs to be used as index so k = zero needs to be placed at 5		
		 
		  //  1 iteration
		    // value 4  of original array needs to be used as index so k = 1 needs to be placed at 4		
		  //  2 iteration
		 // value 2  of original array needs to be used as index so k = 2 needs to be placed at 2		
		  //  3 iteration
		  // value 1  of original array needs to be used as index so k = 3 needs to be placed at 1		
		  //  4 iteration
		   //  value 0  of original array needs to be used as index so k = 4 needs to be placed at 0
		 //  5 iteration
		   //  value 3  of original array needs to be used as index so k = 5 needs to be placed at 3
		//output array      4 3 2 5 1 0

		System.out.println("Original Array " + Arrays.toString(originalArray));
		int[] finalArray = new int[originalArray.length];
		for(int k =0;k< originalArray.length;k++) {
			finalArray[originalArray[k]]= k;
		}
		
		System.out.println("Final Array " + Arrays.toString(finalArray));
	}
}
