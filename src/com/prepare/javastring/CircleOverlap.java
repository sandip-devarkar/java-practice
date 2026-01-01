/* A researcher often needs to calculate how much two circles overlap.
- Imagine two round plates placed on a table.
- Sometimes they overlap partly, sometimes fully, sometimes not at all.
- The researcher wants a program that can quickly tell the area of overlap between any two circles.
Each circle is defined by:
- Its center position (x, y coordinates).
- Its radius (size).
The task: Write a program that calculates the overlapping area of two circles.
*/
package com.prepare.javastring;

public class CircleOverlap {
	
	public static void main(String[] args) {
		// Circle 1 details
		double x1 = 0; //x-coordinate of center 
		double y1 = 0; //y-coordinate of center
		double r1 = 5; //radius
		
		//Circle 2 details
		double x2 = 4; //x-coordinate of center 
		double y2 = 0; //y-coordinate of center
		double r2 = 5; //radius
	
		// step 1: Calculate the distance between centers
		//d=\/sqrt{(x2-x1)^2+(y2-y1)^2}

		double d = Math.sqrt(Math.pow(x2-x1,2) +Math.pow(y2-y1,2));
		
		double overlapArea = 0;
		
		//Step2 :Decide Overlap type
		if(d >= r1+r2) {
			// circles are too far apart
		}else if(d <= Math.abs(r1-r2)) {
			//Once circle inside the other
			 double smaller = Math.min(r1, r2);
			 overlapArea =  Math.PI * smaller* smaller;
		}else {
			double part1 = circleSegment(r1,r2,d);
			double part2 = circleSegment(r2,r1,d);
			double part3 = triangleArea(r1,r2,d);
			overlapArea  =  part1 + part2 -part3;
		}
		
		System.out.println("overlapping area of two circles " + overlapArea);
	}

	private static double circleSegment(double firstRadius, double secondRadius, double d) {
		return firstRadius*firstRadius* Math.acos((d*d+firstRadius*firstRadius-secondRadius*secondRadius)/(2*d*firstRadius));
	}private static double triangleArea(double r1, double r2, double d) {
		return  0.5 * Math.sqrt((-d+r1+r2)*(d +r1-r2)*(d-r1+r2)*(r1+r2+d));

	}
}