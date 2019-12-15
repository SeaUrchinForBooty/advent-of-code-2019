package com.adventofcode2019.utils;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class IntcodeComputer {
	
	private int[] program;
	
	// Constructor
	public IntcodeComputer() {
	}
	
	public IntcodeComputer(String inputFile) {
		loadProgram(inputFile);
	}
	
	// Getters
	public int[] getProgram() {
		return this.program;
	}

	// Setters
	public void setProgram(int[] program) {
		this.program = program;
	}
	
	// creates program based on input file
	public void loadProgram(String inputFile) {
		String[] strProgram = null;
		
		BufferedReader br = null;
		String st = null;

		// Creates BufferedReader
		br = FileUtils.openBufferedReader(inputFile);
		
		// create strProgram array
		if((st = FileUtils.readBufferedReader(br,2)) != null)
			strProgram = st.split(",");
		
		// Closes BufferedReader
		FileUtils.closeBufferedReader(br);
		
		// where the magic happens
		program = new int[strProgram.length]; 
		for(int i = 0; i < program.length; i++)
			program[i] = Integer.parseInt(strProgram[i]);
	}
	
	public int getProgramValue(int adress) {
		return program[adress];
	}
	
	public void setProgramValue(int adress, int value) {
		program[adress] = value;
	}
	
	public int runProgram(int inputVar) {
		boolean run = true;
		int ptr = 0; // Program pointer
		int opCode = 0;
		int[] modes = {0,0,0,0,0}; //parameters ABCDE
		int[] parameter = {0,0,0}; // entry values
		int outputVar = 0; // output value
		
		while(run) {
			modes = readParameters(program[ptr]);
			opCode = modes[3] == 9 ? 99 : modes[4];
			
			switch(opCode) {
			case 1:
				parameter[0] = modes[2] == 0 ? 
						program[program[ptr+1]] : program[ptr+1];
						
				parameter[1] = modes[1] == 0 ? 
						program[program[ptr+2]] : program[ptr+2];
						
				program[program[ptr+3]] = parameter[0] + parameter[1];
				ptr += 4;
				break;
				
			case 2:
				parameter[0] = modes[2] == 0 ? 
						program[program[ptr+1]] : program[ptr+1];
						
				parameter[1] = modes[1] == 0 ? 
						program[program[ptr+2]] : program[ptr+2];
						
				program[program[ptr+3]] = parameter[0] * parameter[1];
				ptr += 4;
				break;
			
			case 3:
				program[program[ptr+1]] = inputVar;
				ptr += 2;
				break;
				
			case 4:
				outputVar = program[program[ptr+1]];
				ptr += 2;
				break;
			
			case 5:
				parameter[0] = modes[2] == 0 ? 
						program[program[ptr+1]] : program[ptr+1];
						
				parameter[1] = modes[1] == 0 ? 
						program[program[ptr+2]] : program[ptr+2];
						
				ptr = parameter[0] != 0 ? parameter[1] : ptr + 3;
				break;
				
			case 6:
				parameter[0] = modes[2] == 0 ? 
						program[program[ptr+1]] : program[ptr+1];
						
				parameter[1] = modes[1] == 0 ? 
						program[program[ptr+2]] : program[ptr+2];
						
				ptr = parameter[0] == 0 ? parameter[1] : ptr + 3;
				break;
				
			case 7:
				parameter[0] = modes[2] == 0 ? 
						program[program[ptr+1]] : program[ptr+1];
						
				parameter[1] = modes[1] == 0 ? 
						program[program[ptr+2]] : program[ptr+2];
				
				if(parameter[0] < parameter[1])
					program[program[ptr+3]] = 1;
				else
					program[program[ptr+3]] = 0;
				
				ptr += 4;
				break;
				
			case 8:
				parameter[0] = modes[2] == 0 ? 
						program[program[ptr+1]] : program[ptr+1];
						
				parameter[1] = modes[1] == 0 ? 
						program[program[ptr+2]] : program[ptr+2];
				
				if(parameter[0] == parameter[1])
					program[program[ptr+3]] = 1;
				else
					program[program[ptr+3]] = 0;
				
				ptr += 4;
				break;
				
			case 99: run = false;
				break;
				
				default: System.out.println("WATCHOUT!");
			}
		}
		return outputVar;
	}
	
	private int[] readParameters(int number) {
		
		List<Integer> digitsList = new ArrayList<>();
		int[] digits = new int[5];
		int i = 0, dlSize = 0;
		
		// initializing digits with zero values
		for(i = 0; i < 5; i ++)
			digits[i] = 0;

		while(number > 0) {
			digitsList.add(number % 10);
			number = number / 10;
		}
		
		dlSize = digitsList.size(); // digitsList size
		for(i = 0; i <dlSize; i++)
			digits[(5-1) - i] = digitsList.remove(0);
		
		digitsList.clear();	// Just making sure all the garbage is cleared
		return digits;
	}
}
