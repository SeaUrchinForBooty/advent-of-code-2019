package com.adventofcode2019.day02;

public class DayTwo {
	
	public static int partOne() {
		String currDir = System.getProperty("user.dir");
		String pckg = "/src/com/adventofcode2019/day02/";
		String fileName = "day02.input";
		String fileLocation = currDir + pckg + fileName;
		
		IntcodeComputer ic1 = new IntcodeComputer();
		
		ic1.setFileLocation(fileLocation);
		ic1.convertFileToProgram();
		ic1.setIntcodeProgramValue(1,12);
		ic1.setIntcodeProgramValue(2,0);
		ic1.runIntcodeProgram();
		
		return ic1.getIntcodeProgramValue(0);
	}
	
	
	public static int partTwo() {
		String currDir = System.getProperty("user.dir");
		String pckg = "/src/com/adventofcode2019/day02/";
		String fileName = "day02.input";
		String fileLocation = currDir + pckg + fileName;

		int magicCode = 19690720;
		
		IntcodeComputer ic1 = new IntcodeComputer(fileLocation);
		
		for(int verb = 0; verb <= 99; verb++) {
			for(int noun = 0; noun <= 99; noun++) {
				
				ic1 = new IntcodeComputer(fileLocation);
				ic1.setIntcodeProgramValue(1, noun);
				ic1.setIntcodeProgramValue(2, verb);
				ic1.runIntcodeProgram();
				
				if(ic1.getIntcodeProgramValue(0) == magicCode)
					return 100 * noun + verb;
			}
		}
		
		return -1;
	}
}