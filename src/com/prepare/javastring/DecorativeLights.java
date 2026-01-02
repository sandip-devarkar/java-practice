package com.prepare.javastring;

/*
In preparation for a large outdoor party, a series of eight decorative lights have been set up along a pathway.
Due to a wiring issue, these lights behave unusually:
- A light will turn OFF if both of its neighboring lights were either ON (1) or OFF (0) the previous night.
- Otherwise, the light will remain ON (1).
- The lights at the ends of the row have only one neighbor, so they are considered to be always OFF.
The state of each light on a given day affects its state the following day.
Goal: Given the initial state of the lights and a number of days M, determine the state of the lights after M days.
*/
//Program to simulate decorative lights behavior for M days

import java.util.*;

public class DecorativeLights {
 public static void main(String[] args) {
     // Step 1: Context-specific variables
     int numLights = 8; // total lights
     int days = 3;      // number of days to simulate

     // Initial state of lights (1 = ON, 0 = OFF)
     int[] lights = {1, 0, 1, 1, 0, 1, 0, 1};

     // Step 2: Simulation for M days
     for (int d = 0; d < days; d++) {
         int[] nextState = new int[numLights]; // store next day's state

         // Step 3: Process each light
         for (int i = 0; i < numLights; i++) {
             if (i == 0 || i == numLights - 1) {
                 // End lights are always OFF
                 nextState[i] = 0;
             } else {
                 // Check neighbors
                 if (lights[i - 1] == lights[i + 1]) {
                     // Both neighbors same → turn OFF
                     nextState[i] = 0;
                 } else {
                     // Neighbors different → remain ON
                     nextState[i] = 1;
                 }
             }
         }

         // Update lights for next day
         lights = nextState;
     }

     // Step 4: Print final state
     System.out.println("Final state after " + days + " days:");
     for (int state : lights) {
         System.out.print(state + " ");
     }
 }
}