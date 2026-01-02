package com.prepare.javastring;
/*
a system design style problem where we have:
- systemStateSize → number of states (like ON/OFF for machines).
- distSize → number of distances (like cable lengths between machines).
- systemStateArray → array holding machine states (ON=1, OFF=0).
- distanceArray → array holding cable lengths between machines.
- Goal → As a technical lead, we want to process machine ON/OFF states and compute cable length usage.
 Problem Restatement
We have multiple machines connected by cables.
- Each machine can be ON (1) or OFF (0).
- We are given cable lengths between machines.
- We want to calculate the total cable length used by machines that are ON.

*/
//Program: Calculate total cable length for machines that are ON

import java.util.*;

public class CableLengthCalculator {
 public static void main(String[] args) {
     // Step 1: Define system size
     int systemStateSize = 5; // number of machines
     int distSize = 5;        // number of cables

     // Step 2: Define machine states (1 = ON, 0 = OFF)
     int[] systemStateArray = {1, 0, 1, 1, 0};

     // Step 3: Define cable lengths (distance between machines)
     int[] distanceArray = {10, 20, 15, 25, 30};

     // Step 4: Calculate total cable length for ON machines
     int totalCableLength = 0;

     for (int i = 0; i < systemStateSize; i++) {
         // If machine is ON, add its cable length
         if (systemStateArray[i] == 1) {
             totalCableLength += distanceArray[i];
         }
     }

     // Step 5: Print result
     System.out.println("Total Cable Length used by ON machines = " + totalCableLength);
 }
}