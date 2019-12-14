package com.adventofcode2019.utils;

import java.lang.Math;

public class Wire {
	
	// NESTED CLASSES
	private static class Directions {
		private char direction; //possible direction (U,D,L,R)
		private int steps; // steps is an integer number
		
		// Constructors
		Directions(char direction, int steps) {
			setDirection(direction);
			setSteps(steps);
		}
		
		// Getters and setters
		private char getDirection() {
			return this.direction;
		}
		
		private int getSteps() {
			return steps;
		}
		
		private void setDirection(char direction) {
			this.direction = direction;
		}
		
		private void setSteps(int steps) {
			this.steps = steps;
		}
	}
	
	
	// VARIABLES
	private Directions[] path;
	private int[] reach; // counting from the center it indicates it's reach
	
	// Constructor
	public Wire() {
	}
	
	public Wire(String path) {
		setPath(parseWire(path));
		setReach(computeReach());
	}
	
	// Getters
	public Directions[] getPath() {
		return this.path;
	}
	
	public int[] getReach() {
		return this.reach;
	}
	
	// Setters	
	public void setPath(Directions[] path) {
		this.path = path;
	}
	
	public void setReach(int[] reach) {
		this.reach = reach;
	}
	
	
	// Methods
	public Directions[] parseWire(String pathString) {
		String[] aux = pathString.split(",");
		Directions[] path = new Directions[aux.length];
		
		char direction = '.'; // Possible directions are U,D,L,R
		int steps = 0; // steps are a positive integer
		
		for(int i = 0; i < path.length; i++) {
			direction = aux[i].charAt(0);
			steps = Integer.parseInt(
					aux[i].substring(1,aux[i].length()));
			path[i] = new Directions(direction, steps);
		}
		return path;
	}
	
	public int[] computeReach() {
		Directions[] path = getPath();
		int x = 0, y = 0;
		int[] reach = new int[]{0,0};
		
		for(int i = 0; i < path.length; i++) {
			switch(path[i].getDirection()) {
				case 'U': 
					y = y + path[i].getSteps();
					if(reach[1] < Math.abs(y))
						reach[1] = Math.abs(y);
						setReach(reach);
					break;
				case 'D':
					y = y - path[i].getSteps();
					if(reach[1] < Math.abs(y))
						reach[1] = Math.abs(y);
						setReach(reach);
					break;
				case 'L':
					x = x - path[i].getSteps();
					if(reach[0] < Math.abs(x))
						reach[0] = Math.abs(x);
						setReach(reach);
					break;
				case 'R':
					x = x + path[i].getSteps();
					if(reach[0] < Math.abs(x))
						reach[0] = Math.abs(x);
						setReach(reach);
					break;
			}
		}
		return reach;
	}
	
	public int getPathLength() {
		return getPath().length;
	}
	
	public char getDirections(int index) {
		return getPath()[index].getDirection();
	}
	
	public int getSteps(int index) {
		return getPath()[index].getSteps();
	}
}