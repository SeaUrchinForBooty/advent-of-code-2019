package com.adventofcode2019.day04;

import java.util.ArrayList;
import java.util.List;

public class DayFour {
	
	private final static int lowerLimit = 206938;
	private final static int upperLimit = 679128;
	
	
	// NESTED CLASS
	static class Password {
		
		private int[] digits;
		
		// Constructor
		Password(int password) {
			setDigits(parsePassword(password));
		}
		
		// Getter
		int[] getDigits() {
			return digits;
		}
		
		// Setter
		void setDigits(int[] digits) {
			this.digits = digits;
		}
		
		// Methods
		int digit(int index) {
			return getDigits()[index];
		}
		
		int getTotalOfDigits() {
			return getDigits().length;
		}
		
		int[] parsePassword(int password) {
			int i = 0;
			int totDigits = 0;
			int[] digits = null;
			List<Integer> digitsList = new ArrayList<>();
			
			// gets each digit from password in inverted form
			while(password > 0) {
				digitsList.add(password % 10);
				password = password / 10;
			}
			
			// 
			totDigits = digitsList.size();
			digits = new int[totDigits];
			for(i = 0; i < totDigits; i++) {
				digits[(totDigits - 1) - i] = digitsList.remove(0);
			}
			return digits;
		}
	}
	
	public static int partOne() {
		
		Password password = null;
		int totDigits = 0;
		int i = 0, j = 0;
		boolean adjacent = false;
		boolean growing = true;
		int tot = 0;
		
		for(i = lowerLimit; i <= upperLimit; i++) {
			password = new Password(i);
			totDigits = password.getTotalOfDigits();
			
			for(j = 0; j < totDigits - 1; j++) {
				if(password.digit(j) == password.digit(j+1))
					adjacent = true;
				
				if(password.digit(j) > password.digit(j+1))
					growing = false;
			}
			
			if(adjacent && growing)
				tot++;
			
			adjacent = false;
			growing = true;
		}
		return tot;
	}
	
	public static int partTwo() {
		
		Password password = null;
		int totDigits = 0;
		int i = 0, j = 0;
		boolean adjacent = false;
		boolean growing = true;
		int tot = 0;
		
		for(i = lowerLimit; i <= upperLimit; i++) {
			password = new Password(i);
			totDigits = password.getTotalOfDigits();
			
			for(j = 0; j < totDigits; j++) {
				if(j == 0) {
					if(password.digit(j) == password.digit(j+1))
						if(password.digit(j) != password.digit(j+2))
							adjacent = true;
					
					if(password.digit(j) > password.digit(j+1))
						growing = false;
				}
				
				if(j > 0 && j < totDigits - 2) {
					if(password.digit(j) == password.digit(j+1))
						if(password.digit(j) != password.digit(j-1))
							if(password.digit(j) != password.digit(j+2))
								adjacent = true;
					
					if(password.digit(j) > password.digit(j+1))
						growing = false;
				}
				
				if(j == totDigits - 2) {
					if(password.digit(j) == password.digit(j+1))
						if(password.digit(j) != password.digit(j-1))
							adjacent = true;
					
					if(password.digit(j) > password.digit(j+1))
						growing = false;
				}
			}
		
			if(adjacent && growing)
				tot++;
			
			adjacent = false;
			growing = true;
		}
		return tot;
	}
}