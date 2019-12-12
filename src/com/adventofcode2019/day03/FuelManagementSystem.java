package com.adventofcode2019.day03;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.adventofcode2019.day03.Panel;
import com.adventofcode2019.day03.Wire;
import com.adventofcode2019.utils.FileUtils;


// TOP LEVEL CLASS
public class FuelManagementSystem {
	
	// Following alphanumeric order
	private Map<Integer[], Integer> intersections;
	private Panel panel;
	private String inputFile;
	private String outputFile;
	private Wire wire1;
	private Wire wire2;

	// CONSTRUCTORS
	public FuelManagementSystem(String inputFile, String outputFile) {
		BufferedReader br = FileUtils.openBufferedReader(inputFile);
		String st = null;

		// sets inputFile
		setInputFile(inputFile);
		
		// set outputFile
		setOutputFile(outputFile);
		
		// sets wire 1
		st = FileUtils.readBufferedReader(br,2);
		setWire1(new Wire(st));
		
		// sets wire 2
		st = FileUtils.readBufferedReader(br, 2);
		setWire2(new Wire(st));
		FileUtils.closeBufferedReader(br);
		
		// set Panel
		setPanel(new Panel(getPanelDimensions(),getPanelCenter()));
		
		// set Intersections
		setIntersections(new HashMap<>());
		intersections = drawWire(wire1, '1');
		intersections = drawWire(wire2, '2');
	}
	
	// Getters and Setters
	// GETTERS
	public Map<Integer[], Integer> getIntersections() {
		return this.intersections;
	}
	
	public Panel getPanel() {
		return this.panel;
	}
	
	public String getInputFile() {
		return this.inputFile;
	}
	
	public String getOutputFile() {
		return this.outputFile;
	}
	
	public Wire getWire1() {
		return wire1;
	}
	
	public Wire getWire2() {
		return wire2;
	}
	
	// SETTERS
	public void setIntersections(Map<Integer[], Integer> intersections) {
		this.intersections = intersections;
	}
	
	public void setPanel(Panel panel) {
		this.panel = panel;
	}
	
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
	
	public void setWire1(Wire wire1) {
		this.wire1 = wire1;
	}
	
	public void setWire2(Wire wire2) {
		this.wire2 = wire2;
	}
	
	// METHODS
	public void printPanel() {
		panel.printGrid(outputFile);
	}
	
	public int computeSteps() {
		int[] itrPos = {0,0};
		int wire1Steps = 0;
		int wire2Steps = 0;
		int totalSteps = 0;
		int auxTotalSteps = 0;
		boolean firstIteration = true;
		
		Set<Integer[]> keys = intersections.keySet();
		
		for(Integer[] pos : keys) {
			itrPos[0] = Integer.valueOf(pos[0]);
			itrPos[1] = Integer.valueOf(pos[1]);
			wire1Steps = countStepsUntilIntersection(wire1, itrPos);
			wire2Steps = countStepsUntilIntersection(wire2, itrPos);
			
			if(firstIteration) {
				totalSteps = wire1Steps + wire2Steps;
				firstIteration = false;
			}
			else {
				auxTotalSteps = wire1Steps + wire2Steps;
				if(auxTotalSteps < totalSteps) {
					totalSteps = auxTotalSteps;
				}
			}
		}
		return totalSteps;
	}
	
	public int countStepsUntilIntersection(Wire wire, int[] intersection) {
		int totalSteps = 0;
		int[] center = getPanelCenter();
		int[] pos = {center[0],center[1]};
		int pathLength = wire.getPathLength();
		int currSteps = 0;
		int i = 0, j = 0;
		
		for(i = 0; i < pathLength; i++) {
			currSteps = wire.getSteps(i);
			for(j = 0; j < currSteps; j++) {
				if(pos[0] == intersection[0] && pos[1] == intersection[1]) {
					i = pathLength;
					j = currSteps;
				}
				else {
					switch(wire.getDirections(i)) {
					case 'U': pos[1]++;
						break;
					case 'D': pos[1]--;
						break;
					case 'L': pos[0]--;
						break;
					case 'R': pos[0]++;
						break;
						default: System.out.println("I SHOULD NOT BE HERE!");
					}
					totalSteps++;
				}
			}
		}
		return totalSteps;
	}
	
	// Computes closest intersection coordinates
	public int computeClosestIntersection() {
		int[] panelCenter = getPanelCenter();
		boolean firstIteration = true;
		int sum = 0; // [coordinate x, coordinate y, sum];
		int auxSum = 0;
		Set<Integer[]> keys = null;
		
		if(!intersections.isEmpty()) {
			keys = intersections.keySet();

			for(Integer[] pos : keys) {
				auxSum = Math.abs(pos[0] - panelCenter[0]) +
						Math.abs(pos[1] - panelCenter[1]);
				
				if(firstIteration) {
					firstIteration = false;
					sum = auxSum;
				}
				else {
					if(auxSum < sum)
						sum = auxSum;
				}
			}
		}
		return sum;
	}
	
	public int[] getPanelCenter() {
		int[] panelCenter = {0,0};
		int input1 = getWire1().getReach()[0];
		int input2 = getWire2().getReach()[0];
		panelCenter[0] = input1 > input2 ? input1 : input2;
		
		input1 = getWire1().getReach()[1];
		input2 = getWire2().getReach()[1];
		panelCenter[1] = input1 > input2 ? input1 : input2;
		
		return panelCenter;
	}
	
	public int[] getPanelDimensions() {
		int[] panelDimensions = {0,0};
		int[] panelCenter = getPanelCenter();

		panelDimensions[0] = panelCenter[0] * 2 + 1;
		panelDimensions[1] = panelCenter[1] * 2 + 1;
		
		return panelDimensions;
	}
	
	public Map<Integer[], Integer> drawWire(Wire wire, char figure) {
		
		Map<Integer[], Integer> intersections = new HashMap<>();
		int[] center = getPanelCenter();
		int[] pos = {center[0], center[1]};
		int value = 0;
		int currSteps = 0;
		Integer[] aux = null;
		
		for(int i = 0; i < wire.getPathLength(); i++) {
			switch(wire.getDirections(i)) {
			case 'U':
				currSteps = wire.getSteps(i);
				for(int steps = 1; steps <= currSteps; steps++) {
					pos[1] += 1;
					value = panel.getGridValue(pos);
					if(value != (int) '.' && value != (int) figure
							&& value != (int) 'x') {
						panel.setGridValue(pos, (int) 'x');
						
						aux = new Integer[]{0,0};
						aux[0] = Integer.valueOf(pos[0]);
						aux[1] = Integer.valueOf(pos[1]);
						if(!intersections.containsKey(aux))
							intersections.put(aux, Integer.valueOf(currSteps));
					}
					else
						panel.setGridValue(pos, figure);
				}
				break;
			case 'D':
				for(int steps = 1; steps <= wire.getSteps(i); steps++) {
					pos[1] -= 1; 
					value = panel.getGridValue(pos);
					if(value != (int) '.' && value != (int) figure
							&& value != (int) 'x') {
						panel.setGridValue(pos, 'x');
						
						aux = new Integer[]{0,0};
						aux[0] = Integer.valueOf(pos[0]);
						aux[1] = Integer.valueOf(pos[1]);
						if(!intersections.containsKey(aux))
							intersections.put(aux, Integer.valueOf(currSteps));
					}
					else
						panel.setGridValue(pos, figure);
				}
				break;
			case 'L':
				for(int steps = 1; steps <= wire.getSteps(i); steps++) {
					pos[0] -= 1; 
					value = panel.getGridValue(pos);
					if(value != (int) '.' && value != (int) figure
							&& value != (int) 'x') {
						panel.setGridValue(pos, (int) 'x');
						
						aux = new Integer[]{0,0};
						aux[0] = Integer.valueOf(pos[0]);
						aux[1] = Integer.valueOf(pos[1]);
						if(!intersections.containsKey(aux))
							intersections.put(aux, Integer.valueOf(currSteps));
					}
					else
						panel.setGridValue(pos, figure);
				}
				break;
			case 'R':
				for(int steps = 1; steps <= wire.getSteps(i); steps++) {
					pos[0] += 1; 
					value = panel.getGridValue(pos);
					if(value != (int) '.' && value != (int) figure
							&& value != (int) 'x') {
						panel.setGridValue(pos, 'x');
						
						aux = new Integer[]{0,0};
						aux[0] = Integer.valueOf(pos[0]);
						aux[1] = Integer.valueOf(pos[1]);
						if(!intersections.containsKey(aux))
							intersections.put(aux, Integer.valueOf(currSteps));
					}
					else
						panel.setGridValue(pos, figure);
				}
				break;
				default: System.out.println("I SHOULD NOT BE HERE!"); 
			}
		}
		return intersections;
	}
}
