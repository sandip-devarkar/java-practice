package com.prepare.javastring;

/*
An organization is evaluating the performance of its team members based on their monthly evaluation scores. Each team member has a unique ID numbered from 0 to N-1.
The organization wants to identify team members whose scores are prime numbers.
👉 Task: Develop a program to determine the number of team members with prime number scores.
*/

public class PrimeScores {

	public static void main(String[] args) {
		// Step 0: Print the problem statement
		System.out.println("Problem: Identify team members whose scores are prime numbers.");

		// Context-specific variable: scores of team members
		int[] scores = { 10, 7, 15, 23, 8, 11 };

		System.out.println("scores of team members");
		for (int i = 0; i < scores.length; i++) {

			if (findPrime(scores[i])) {
				System.out.printf(" " + scores[i] + " ");
			}
		}
	}

	private static boolean findPrime(int score) {

		if (score == 0 || score == 1) {
			System.out.println("O or 1 is not prime ");
			return false;
		}
		int sqrtNumber = (int) Math.sqrt(score);
		for (int j = 2; j <= sqrtNumber; j++) {
			if (score % j == 0) {
				return false;
			}
		}

		return true;

	}
}
