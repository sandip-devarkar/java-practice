package com.interview;

public class Test {

	public static void main(String[] args) {
		String usBankInterview = "abcddcbr";
		
		//check a and b 
		// for first no check for a 
		// just add in buffer a 
		//  for b check its same with a in buffer 
		//   Yes   : buffer delete a we will have its index
		// move current pointer to next element
		//    again check element  in buffer if no element in buffer 
		 // add in buffer  and move to the next element
		//  check next element with 
		
		 char []usBankInterviewArray =  usBankInterview.toCharArray();
	     int arrayLength = usBankInterviewArray.length;
	     StringBuffer sb = new StringBuffer();
	     for(int i =0;i < arrayLength ; i++ ) {
	    	 
	    	  char c = usBankInterviewArray [i];
	    	  int bufferLen =  sb.length();
	    	 if( bufferLen> 0 && sb.charAt(bufferLen-1) == c)
	    	 {
	    		 sb.deleteCharAt(bufferLen-1);
	    		 
	    	 }else {
	    		 
	    		 sb.append(usBankInterviewArray[i]);
	    	 }
	    	 
	     }
	      if(sb != null )
	      {
	    	  System.out.println(" Output String " + sb.toString());
	      }
	}
}
