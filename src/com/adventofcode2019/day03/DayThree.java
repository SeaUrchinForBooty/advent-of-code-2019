package com.adventofcode2019.day03;


public class DayThree {
	
	private final static String currDir = System.getProperty("user.dir");
	private final static String pckg = "/src/com/adventofcode2019/day03/";
	private final static String inputFileName = "day03.input";
	private final static String outputFileName = "day03.output";
	private final static String inputFile = currDir + pckg + inputFileName;
	private final static String outputFile = currDir + pckg + outputFileName;
	
	
	public static int partOne() {
		FuelManagementSystem fms = 
				new FuelManagementSystem(inputFile, outputFile);
		fms.printPanel();
		return fms.computeClosestIntersection();
	}
	
	public static int partTwo() {
		FuelManagementSystem fms = 
				new FuelManagementSystem(inputFile, outputFile);
		return fms.computeSteps();
	}
}
