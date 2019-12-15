package com.adventofcode2019.utils;

// ThermalEnvironmentSupervisionTerminal
public class TEST {
	
	public static int runIntcodeComputer(String inputProgram, int input) {
		IntcodeComputer ic = new IntcodeComputer();
		ic.loadProgram(inputProgram);
		return ic.runProgram(input);
	}
}