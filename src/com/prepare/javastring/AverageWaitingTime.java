package com.prepare.javastring;

public class AverageWaitingTime {

	public static void main(String[] args) {
		// Step 0: Print the problem statement
	     System.out.println("Problem: Calculate average waiting time given request times and duration times.");

	   // Context specific Variables : request times and duration times
	   int[] requestTime = {0,2,4,5};  // When each process arrives

	   int[] durationTime = {7,4,1,4}; // How long each process takes
       int n = requestTime.length;     // Number of processes
       int currentTime = 0;            // Tracks when CPU is free
       int totalWaitingTime = 0;       // Sum of waiting times

       
       //Step1 :Loop though each process in order
       for(int i=0; i<n; i++) {
    	   //step2 If CPU is idle move currentTime to requestTime
    	   if(currentTime < requestTime[i]) {
    		   currentTime = requestTime[i];
    	   }
    	   // Step 3: Waiting time = currentTime - requestTime[i]
    	   int waitingTime = currentTime - requestTime[i];
    	   totalWaitingTime = totalWaitingTime + waitingTime;
    	   
    	   //step4 Update current transaction when request completes
    	   currentTime = currentTime + durationTime[i];

       }
       double averageWaitingTime = (double) totalWaitingTime / n;
       System.out.println("Average Waiting Time = " + averageWaitingTime);
       System.out.println("Total Waiting Time: " + totalWaitingTime);

	}
}
	