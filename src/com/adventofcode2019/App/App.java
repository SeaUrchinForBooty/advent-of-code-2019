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
		System.out.println("Day 3, part 1: " + DayThree.partOne());
		System.out.println("Day 3, part 2: " + DayThree.partTwo());
	}
}