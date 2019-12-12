package com.adventofcode2019.day03;


public class DayThree {
	
	public static int partOne(String inputFile, String outputFile) {
		FuelManagementSystem fms = new FuelManagementSystem(inputFile, outputFile);
		fms.printPanel();
		return fms.computeClosestIntersection();
	}
	
	
	public static int partTwo(String inputFile, String outputFile) {
		FuelManagementSystem fms = new FuelManagementSystem(inputFile, outputFile);
		return fms.computeSteps();
	}
}
