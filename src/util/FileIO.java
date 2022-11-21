/*
Nicholas Carrig	
CIS D035B
Assignment 3 
 */
package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import exception.AutoException;
import exception.FileNotFoundException;
import model.Automobile;
import model.OptionSet;

public class FileIO {
	private String fName;
	
	//Constructor
	public FileIO(String fName) {
		this.fName = fName;
	}
	
	//Read plain text file and create the automobile and option sets
	public Automobile readFile() throws FileNotFoundException, AutoException, IOException{
		//assuming one car per text file
		String make = "";
		String model = "";
		double basePrice = -0.1;
		String opSetName = "";
		ArrayList<String> setData = new ArrayList<String>();
		ArrayList<OptionSet> opSet = new ArrayList<OptionSet>();
		FileReader file = null;
		try {
			file = new FileReader(fName);
		}
		catch(Exception e) {
			//errNo 101
			throw new FileNotFoundException(101);
		}
		BufferedReader buff = new BufferedReader(file);
		boolean eof = false;
		
		while(!eof) {
			String line = buff.readLine();
			if(line == null) {
				eof = true;
			}
			else if(line.equals("Make:")) {
				//readLine() used to move to next line in order to store that value
				make = buff.readLine();
				if( make.equals(null) || make.equals("") ) {
					new AutoException(102);
					make = "DEFAULT MAKE VALUE";
				}
			}
			else if(line.equals("Model:")) {
				model = buff.readLine();
				if( model.equals(null) || model.equals("") ) {
					new AutoException(102);
					model = "DEFAULT MODEL VALUE";
				}
			}
			else if(line.equals("Base Price:")) {
				try {						
					basePrice = Double.parseDouble(buff.readLine());
				}
				catch(Exception e) {
					throw new AutoException(104);
				}
			}
			else if(line.substring(line.length() - 1).equals(":")) {
				//if the last character in the string is ":" and not one of the above cases, it should be a name of an option set
				//if there are any options in the setData array list, it means that the file has read in the name of the next option set
				//we want to create an option set out of the current info and add it to the opSet array list
				if (setData.size() > 0) {
					opSet.add(new OptionSet(opSetName, setData));
					
					//clear setData array list
					setData.clear();
				}
				//substring is used to cut off the colon at the end of the string
				opSetName = line.substring(0, line.length()-1);
			}
			else {
				setData.add(line);
			}
		}
		//add the remaining data to one more option set once the end of the file is reached
		opSet.add(new OptionSet(opSetName, setData));
		
		buff.close();
		
		Automobile car = new Automobile(make, model, basePrice, opSet);
		
		return car;
	}
	
	
	public void serializeVehicle(Automobile car) {
		String fileName = car.getMakeAndModel();
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(car);
			
			out.close();
		}
		catch (Exception e) {
			new AutoException(e.toString());
		}
		
	}
	public Automobile deserializeVehicle(String fileName) {
		Automobile car = null;
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			car = (Automobile)in.readObject();
			
			in.close();
		}
		catch(Exception e) {
			new AutoException(e.toString());
		}
		return car;
	}

}
