package com.adventofcode2019.day01;

import com.adventofcode2019.utils.FileUtils;

import java.io.BufferedReader;

public class DayOne {
	
	private static final String currDir = System.getProperty("user.dir");
	private static final String path = "/src/com/adventofcode2019/day01/";
	private static final String fileName = "day01.input";
	private static final String inputFile = currDir + path + fileName;
	
	
	//Methods
	private static Integer computeFuelNeed(Integer aux) {
		
		int fuelNeed = 0;
		
		while( (aux = (aux/3 - 2)) > 0) {
			fuelNeed = fuelNeed + aux;
		}
		return fuelNeed;
	}
	
	
	public static Integer partOne() {
		
		BufferedReader br = null;
		String st = null;
		int sum = 0;

		// Opens a BufferedReader br
		br = FileUtils.openBufferedReader(inputFile);
		
		// Where the magic happens
		while((st = FileUtils.readBufferedReader(br, 2)) != null ) {
			int aux = Integer.parseInt(st);
			sum = sum + aux/3 - 2 ;
		}
		
		// Closes BufferedReader br
		FileUtils.closeBufferedReader(br);
		
		return sum;
	}
	
	
	public static Integer partTwo() {
		BufferedReader br = null;
		String st = null;
		Integer totalFuel = 0;

		br = FileUtils.openBufferedReader(inputFile);
		
		// Where the magic happens
		while((st = FileUtils.readBufferedReader(br, 2)) != null) {
			Integer fuel = Integer.parseInt(st);
			totalFuel = totalFuel + computeFuelNeed(fuel);
		}
		FileUtils.closeBufferedReader(br);
		
		return totalFuel;
	}
}