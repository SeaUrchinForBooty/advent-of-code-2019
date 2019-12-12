package com.adventofcode2019.day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DayOne {
	
	private BufferedReader br;
	
	// Constructor
	private DayOne() {
		setBr(null);
	}

	// Getters and setters
	private BufferedReader getBr() {
		return this.br;
	}
	
	private void setBr(BufferedReader br) {
		this.br = br;
	}
	
	// Methods
	private void openFile() {
		String currDir = System.getProperty("user.dir");
		String path = "/src/com/adventofcode2019/day01/";
		String fileName = "day01.input";
		String fileLocation = currDir + path + fileName;
		File file = new File(fileLocation);
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			setBr(br);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void closeFile() {
		BufferedReader br = getBr();
		try {
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	
	// computes fuel need
	private static Integer computeFuelNeed(Integer aux) {
		
		Integer fuelNeed = 0;
		
		while( (aux = (aux/3 - 2)) > 0) {
			fuelNeed = fuelNeed + aux;
		}
		return fuelNeed;
	}
	
	public static Integer partOne() {
		
		DayOne dO = new DayOne();
		String st = null;
		int sum = -1;
		
		dO.openFile(); // Opens file and stores BufferedReader

		// Reads the files and does the math
		try {
			while((st = dO.getBr().readLine()) != null ) {
				int aux = Integer.parseInt(st);
				sum = sum + aux/3 - 2 ;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		dO.closeFile(); // Closes BufferedReader
		
		return sum;
	}
	
	
	public static Integer partTwo() {
		
		DayOne dO = new DayOne();
		String st = null;
		Integer totalFuel = 0;
		
		dO.openFile();
		// Where the magic happens
		try {
			while((st = dO.getBr().readLine()) != null) {
				Integer fuel = Integer.parseInt(st);
				totalFuel = totalFuel + computeFuelNeed(fuel);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// Tries closing BufferedReader br
		dO.closeFile();
		
		return totalFuel;
	}
}