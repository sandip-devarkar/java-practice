package com.prepare.javastring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
There are N staff members in a company. Each staff member has a unique ID (from 1 to N).
Every staff member has a number called learning efficiency (X).
The total division efficiency = sum of all staff efficiencies.
The company runs Qlearning initiatives.
For each initiative:
One staff member is chosen.
Along with that person, the K staff members with the lowest efficiency in the same team must also join.
Once they join, their efficiency becomes 0 (they are considered reset).
They cannot join future initiatives again.
The task: After all initiatives are done, calculate the remaining total efficiency of the division.
*/
public class LearningEfficiency {

	public static void main(String[] args) {
		// Number of staff members
		int N = 6;
		// Index represents  Staff id (1 to N)
		// Staff efficiencies(X Values)
	    int[] efficiency = {10,20,5,15,8,12};
	    
	    //Teams: Staff group by team ID
	    Map<String,List<Integer>> teams = new HashMap<>();
	    teams.put("TeamA", Arrays.asList(0,1,2));
	    teams.put("TeamB", Arrays.asList(3,4,5));
	    
	    // Example Q-Learning initiative
	    // Select staff ID 1 (efficiency 20) from Team A
	    String selectedTeam = "TeamA";
	    int selectedStaff = 1; // staff index in efficiency array
	    int k = 2; // lowest K staff also participate
	    
	    //Step 1: Get all staffs in selected team
	    List<Integer>  teamMembers = teams.get(selectedTeam);
	    
	    //Step2: Find K staff with lowest efficiency in that team
	    List<Integer> sortByEfficiency = new ArrayList<>(teamMembers);
	    sortByEfficiency.sort(Comparator.comparingInt(i -> efficiency[i]));
	     //  If you sort by ID directly → [0, 1, 2] (no change).
	     //  If you sort by efficiency[i] → [2, 0, 1] 
	    

	    // Step3: COllects participants  (Selected staff + lowest K)
	    Set<Integer> participants = new HashSet<>();
	    participants.add(selectedStaff);
	    for(int i=0;i<k && i <sortByEfficiency.size();i++) {
	    	participants.add(sortByEfficiency.get(i));
	    }
	    
	    // step 4: Set their efficiency to 0
	    for(int id : participants) {
	    	efficiency[id] = 0;
	    }
	    
	    // Step 5 : Calculate the total division efficiency
	    int totalEfficiency = 0;
	    for(int value: efficiency) {
	    	totalEfficiency = totalEfficiency + value;
	    }
	    
	    //Output Result
	    
	    System.out.println("Remaining Division efficency "+ totalEfficiency);
	    
	    }
		
		
	}
