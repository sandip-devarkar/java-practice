package com.prepare.javastring;

/*
A team member needs to transmit a confidential message, represented by a code S, to their supervisor.
To secure the message, they use two key values: N and M.
The encryption formula is defined as:
Encrypted=(((S\% N)\% M)\% 1000000007)
Task:
Develop an algorithm (and program) that takes inputs S, N, and M, and outputs the encrypted message.
*/
public class EncryptMessage {

	public static void main(String[] args) {
		long S = 12345;
		long N = 100;
		long M = 50;
		long encyptedMessage  = encypted(S,N,M);
		System.out.println("Encrypted" + encyptedMessage);
	}

	private static long encypted(long s, long n, long m) {
		long mod = 1000000007L;
		long encrypted  = ((s%n)%m)%mod ;
		return encrypted;
	}
}
