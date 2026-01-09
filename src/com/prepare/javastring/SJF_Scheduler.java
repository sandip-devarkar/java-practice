package com.prepare.javastring;

/* Java program to calculate Average Waiting Time using Shortest Job First (SJF)
In a computer system, tasks are scheduled using the Shortest Job First (SJF) algorithm.
Each task has:
- Request time (when it arrives)
- Duration (time needed to finish)
The system always picks the task with the shortest duration.
If multiple tasks have the same duration, the one with the earliest request time is chosen.
The waiting time for a task = (Start time – Request time).
Assume tasks arrive frequently enough that the system is always busy.
Goal: Given a list of tasks (request time, duration), calculate the average waiting time using SJF scheduling.
*/
import java.util.*;

class Task {
    int requestTime;  // when task arrives
    int duration;     // how long task takes
    Task(int requestTime, int duration) {
        this.requestTime = requestTime;
        this.duration = duration;
    }
}

public class SJF_Scheduler {
    public static void main(String[] args) {
        // Step 1: Define tasks
        List<Task> tasks = Arrays.asList(
            new Task(0, 7),
            new Task(2, 4),
            new Task(4, 1),
            new Task(5, 4)
        );

        // Step 2: Sort tasks by request time
        tasks.sort(Comparator.comparingInt(t -> t.requestTime));

        // Step 3: Priority Queue for shortest job
        PriorityQueue<Task> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.duration));

        int time = 0;              // current system time
        int index = 0;             // index to track tasks
        int totalWaitingTime = 0;  // sum of waiting times

        // Step 4: Process until all tasks are handled
       // - Keep looping until all tasks are added (index < tasks.size()) AND the priority queue is empty.
      //  - This ensures we don’t stop until every task has been processed.

        while (index < tasks.size() || !pq.isEmpty()) {
            // Add tasks that have arrived by current time
        	// Add all tasks that have arrived by the current time into the priority queue.
        	//pq stores tasks waiting to be executed, sorted by shortest duration.
           // index++ moves to the next task in the list.

            while (index < tasks.size() && tasks.get(index).requestTime <= time) {
                pq.add(tasks.get(index));
                index++;
            }

            if (!pq.isEmpty()) {
                // Pick shortest job
            	//If the queue is not empty, pick the shortest job (poll() removes and returns the smallest duration task).

                Task current = pq.poll();

                // Waiting time = start time - request time
                //Calculate waiting time = (start time – request time).Add it to the total waiting time.

                int waitingTime = time - current.requestTime;
                totalWaitingTime += waitingTime;

                // Move time forward by duration
               // Move system time forward by the task’s duration (simulating execution).
                time += current.duration;
            } else {
                // If no task has arrived yet, move time forward
            	//- If no task has arrived yet, just increment time by 1 unit until something arrives.

                time++;
            }
        }

        // Step 5: Calculate average waiting time
        double averageWaitingTime = (double) totalWaitingTime / tasks.size();
        System.out.println("Average Waiting Time = " + averageWaitingTime);
        System.out.println("Total Waiting Time: " + totalWaitingTime);
    }
}