package com.adventofcode2019.day05;

import com.adventofcode2019.utils.TEST;

public class DayFive {
	
	private final static String currDir = System.getProperty("user.dir");
	private final static String path = "/inputFiles/";
	private final static String inputName = "day05.input";
	private final static String inputFile = currDir + path + inputName;
	
	
	public static int partOne() {
		int input = 1;
		return TEST.runIntcodeComputer(inputFile, input);
	}
	
	public static int partTwo() {
		int input = 5;
		return TEST.runIntcodeComputer(inputFile, input);
	}
}