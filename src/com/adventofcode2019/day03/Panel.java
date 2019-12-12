package com.adventofcode2019.day03;

public class Panel extends Grid{

	public Panel(int[] panelSize, int[] centerCoordinates) {
		super(panelSize, centerCoordinates);
	}

	@Override
	public String toString() {
		return "I AM A PANEL!";
	}
}