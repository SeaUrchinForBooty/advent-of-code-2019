package com.adventofcode2019.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class IntcodeComputer {
	
	String fileLocation;
	int[] intcodeProgram;
	
	// Constructor
	public IntcodeComputer() {
		this.fileLocation = null;
		this.intcodeProgram = null;
	}
	
	public IntcodeComputer(String fileLocation) {
		setFileLocation(fileLocation);
		convertFileToProgram();
	}
	
	// getters and setters
	public String getFileLocation() {
		return this.fileLocation;
	}
	
	public int[] getIntcodeProgram() {
		return this.intcodeProgram;
	}
	
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	public void setIntcodeProgram(int[] program) {
		this.intcodeProgram = program;
	}
	
	
	// creates program based on input file
	public void convertFileToProgram() {
		
		String fileLocation = getFileLocation();
		int[] intcodeProgram = null;
		
		File file = new File(fileLocation);
		BufferedReader br = null;
		String st = null;
		String[] values = null;
		
		// Creates BufferedReader
		try{
			br = new BufferedReader(new FileReader(file));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Creates value
		try {
			if((st = br.readLine()) != null)
				// reads the only line from the file
				// splits the values and stores in values
				values = st.split(","); 
		} catch(IOException e) {
			e.printStackTrace();
		}
		

		
		//Tries to close the BufferedReader
		try {
			br.close();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		
		// where the magic happens
		//creates the program based on values size
		intcodeProgram = new int[values.length]; 
		
		// Stores the values in the program by parsing
		for(int i = 0; i < values.length; i++)
			intcodeProgram[i] = Integer.parseInt(values[i]);
		
		setIntcodeProgram(intcodeProgram); // stores the program in class variable
	}
	
	public int getIntcodeProgramValue(int position) {
		int[] intcodeProgram = getIntcodeProgram();
		return intcodeProgram[position];
	}
	
	
	public void runIntcodeProgram() {
		int[] intcodeProgram = getIntcodeProgram();
		
		boolean run = true;
		int iPointer = 0; //instruction pointer initialized with adress 0
		int opCode = 0;
		int par1Value = 0; //initialize parameter 1 value with 0
		int par2Value = 0; //initialize parameter 2 value with 0
		int par3Value = 0; //initialize parameter 3 value with 0
		
		while(run) {
			opCode = intcodeProgram[iPointer];
			par1Value = intcodeProgram[iPointer+1];
			par2Value = intcodeProgram[iPointer+2];
			par3Value = intcodeProgram[iPointer+3];
			
			switch(opCode) {
			case 1:
				intcodeProgram[par3Value] = intcodeProgram[par1Value] +
						intcodeProgram[par2Value];
				iPointer+=4;
				break;
				
			case 2:
				intcodeProgram[par3Value] = intcodeProgram[par1Value] *
				intcodeProgram[par2Value];
				iPointer+=4;
				break;
				
			case 99: run = false;
				break;
				
				default: System.out.println("WATCHOUT!");
			}
		}
		
		setIntcodeProgram(intcodeProgram);
	}
	
	
	public void setIntcodeProgramValue(int position, int value) {
		int[] intcodeProgram = getIntcodeProgram();
		
		intcodeProgram[position] = value;
		setIntcodeProgram(intcodeProgram);
	}
}


