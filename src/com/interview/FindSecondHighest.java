package com.interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FindSecondHighest {

	public static void main(String[] args) {
		
		List<Integer> integerArray = Arrays.asList(1,23,44,23,12);
		Optional<Integer>  secondHigehstNumber  = integerArray.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
		System.out.println("Congratulation Your Output is " +secondHigehstNumber);
		
	}
}
