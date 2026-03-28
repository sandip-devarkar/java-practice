package com.hackerearth;

import java.io.File;
import java.io.IOException;

public class FileCreation {

	public static void main(String[] args) {
		
		try {
			var xyFile = File.createTempFile("sample", ".txt");
			if(xyFile.isDirectory()) {
			
				xyFile.delete();
				System.out.println("Directory is deleted");
			}else {
				xyFile.delete();
				System.out.println("File is deleted");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
