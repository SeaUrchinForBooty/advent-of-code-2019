package com.adventofcode2019.day03;

import java.io.BufferedWriter;

import com.adventofcode2019.utils.*;

public class Grid {
	
	private int[][] grid;
	private int[] dimensions;
	private int[] center;
	
	// Constructors
	public Grid() {
		
	}
	
	public Grid(int[] dimensions, int[] center) {
		int[][] grid = null;
		int xSize = dimensions[0];
		int ySize = dimensions[1];
		
		// creating the grid
		grid = new int[xSize][ySize];
		for(int y = 0; y < ySize; y++)
			for(int x = 0; x < xSize; x++)
				grid[x][y] = (int) '.';
		
		setGrid(grid);
		setDimensions(dimensions);
		setCenter(center);
	}
	
	// Getters and setters
	// GETTERS
	public int[][] getGrid() {
		return this.grid;
	}
	
	public int[] getDimensions() {
		return this.dimensions;
	}
	
	public int[] getCenter() {
		return this.center;
	}
	
	// SETTERS
	public void setGrid (int[][] grid) {
		this.grid = grid;
	}
	
	public void setDimensions (int[] gridMeasures) {
		this.dimensions = gridMeasures;
	}
	
	public void setCenter (int[] gridCenter) {
		this.center = gridCenter;
	}

	// METHODS
	public void resetGrid() {
		int[] coord = getDimensions();
		int x = coord[0];
		int y = coord[1];
		
		grid = new int[x][y];
		
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				grid[j][i] = (int) '.';
			}
		}
	}
	
	public int getGridValue(int[] coord) {
		int x = coord[0], y = coord[1];
		return getGrid()[x][y];
	}
	
	public void setGridValue(int[] coord, int value) {
		int[][] grid = getGrid();
		int x = coord[0], y = coord[1];
		grid[x][y] = value;
	}
	
	public void printGrid(String fileLocation) {
		BufferedWriter bw = FileUtils.openBufferedWriter(fileLocation);
		int[][] grid = getGrid();
		int[] dimensions = getDimensions();
		String strAux = null;
		char charAux;
		
		for(int y = 0; y < getDimensions()[1]; y++) {
			for(int x = 0; x < getDimensions()[0]; x++) {
				charAux = (char) grid[x][(dimensions[1]-1) - y];
				strAux = "" + charAux;
				FileUtils.writeBufferedWriter(bw, strAux);
			}
			FileUtils.writeBufferedWriter(bw, "\n");
		}
		
		FileUtils.closeBufferedWriter(bw);
	}
	
	public String toString() {
		return "Board measures are (" + getDimensions()[0] 
				+ ", " + getDimensions()[1] + ")";
	}
}