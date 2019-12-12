package com.adventofcode2019.App;

import com.adventofcode2019.day01.*;
import com.adventofcode2019.day02.*;
import com.adventofcode2019.day03.*;

public class App{
	
	public static void main(String[] args) {
		// Day 1
		System.out.println("Day 1, part 1: " + DayOne.partOne());
		System.out.println("Day 1, part 2: " + DayOne.partTwo());
		
		// Day 2
		System.out.println("Day 2, part 1: " + DayTwo.partOne());
		System.out.println("Day 2, part 2: " + DayTwo.partTwo());
		
		// Day 3
		
		String currDir = System.getProperty("user.dir");
		String pckg = "/src/com/adventofcode2019/day03/";
		String inputFileName = "day03.input";
		String outputFileName = "day03.output";
		String inputFile = currDir + pckg + inputFileName;
		String outputFile = currDir + pckg + outputFileName;
		
		int result = DayThree.partOne(inputFile, outputFile);
		System.out.println("Day 3, part 1: " + result);
		result = DayThree.partTwo(inputFile, outputFile);
		System.out.println("Day 3, part 2: " + result);
	}
}