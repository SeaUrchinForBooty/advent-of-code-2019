package com.adventofcode2019.day02;

public class DayTwo {
	
	private final static int magicNumber = 19690720;
	
	private final static String currDir = System.getProperty("user.dir");
	private final static String pckg = "/src/com/adventofcode2019/day02/";
	private final static String fileName = "day02.input";
	private final static String inputFile = currDir + pckg + fileName;
	
	
	public static int partOne() {
	
		IntcodeComputer ic1 = new IntcodeComputer();		
		ic1.setFileLocation(inputFile);
		ic1.convertFileToProgram();
		ic1.setIntcodeProgramValue(1,12);
		ic1.setIntcodeProgramValue(2,0);
		ic1.runIntcodeProgram();
		
		return ic1.getIntcodeProgramValue(0);
	}
	
	
	public static int partTwo() {
		
		IntcodeComputer ic1 = new IntcodeComputer(inputFile);
		for(int verb = 0; verb <= 99; verb++) {
			for(int noun = 0; noun <= 99; noun++) {
				ic1 = new IntcodeComputer(inputFile);
				ic1.setIntcodeProgramValue(1, noun);
				ic1.setIntcodeProgramValue(2, verb);
				ic1.runIntcodeProgram();
				
				if(ic1.getIntcodeProgramValue(0) == magicNumber)
					return 100 * noun + verb;
			}
		}
		return -1;
	}
}