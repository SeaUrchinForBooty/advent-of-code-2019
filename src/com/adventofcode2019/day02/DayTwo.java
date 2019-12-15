package com.adventofcode2019.day02;

import com.adventofcode2019.utils.IntcodeComputer;

public class DayTwo {
	
	private final static int magicNumber = 19690720;
	
	private final static String currDir = System.getProperty("user.dir");
	private final static String pckg = "/inputFiles/";
	private final static String fileName = "day02.input";
	private final static String inputFile = currDir + pckg + fileName;
	
	
	public static int partOne() {
	
		IntcodeComputer ic = new IntcodeComputer(inputFile);
		ic.setProgramValue(1,12);
		ic.setProgramValue(2,0);
		ic.runProgram(0);
		
		return ic.getProgramValue(0);
	}
	
	
	public static int partTwo() {
		
		IntcodeComputer ic1 = null;
		for(int verb = 0; verb <= 99; verb++) {
			for(int noun = 0; noun <= 99; noun++) {
				ic1 = new IntcodeComputer(inputFile);
				ic1.setProgramValue(1, noun);
				ic1.setProgramValue(2, verb);
				ic1.runProgram(0);
				
				if(ic1.getProgramValue(0) == magicNumber)
					return 100 * noun + verb;
			}
		}
		return -1;
	}
}