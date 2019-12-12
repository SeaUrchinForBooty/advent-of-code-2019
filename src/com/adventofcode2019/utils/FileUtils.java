package com.adventofcode2019.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
	
	public static BufferedWriter openBufferedWriter(String fileLocation) {
		File file = new File(fileLocation);
		BufferedWriter bw = null;
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}
			
			bw = new BufferedWriter(new FileWriter(file));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return bw;
	}
	
	public static void writeBufferedWriter(BufferedWriter bw, String str) {
		try {
			bw.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void closeBufferedWriter(BufferedWriter bw) {
		try {
			if(bw != null)
				bw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void closeBufferedReader(BufferedReader br) {
		try {
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedReader openBufferedReader(String fileLocation) {
		File file = new File(fileLocation);
		BufferedReader br = null;
	
		try {
			br = new BufferedReader(new FileReader(file));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;
	}
	
	public static String readBufferedReader(BufferedReader br, int option) {
		String st = null;
		
		try {
			switch(option) {
			case 1: //TODO: Implement reading only one character
				break;
			case 2: st = br.readLine();
				break;
				default: System.out.println("I SHOULD NOT BE HERE!");
			}
			
			if(st != null) {
				return st;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return st;
	}
}