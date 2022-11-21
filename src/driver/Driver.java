/*
Nicholas Carrig	
CIS D035B
Assignment 4 
 */
package driver;

import adapter.BuildAuto;
import exception.FileNotFoundException;
import model.Automobile;
import util.FileIO;

public class Driver {
	public static void main(String [] args) {
		
		//FileIO/model driver
		
		String fileName = "test-car-3.txt";
		FileIO newCar = new FileIO(fileName);
		
		Automobile car1 = null;
		try {
			car1 = newCar.readFile();
		}
		catch (FileNotFoundException e) {
			//if the second file name is bad create a generic exception
			try {
				fileName = e.getNewFileName();
				car1 = new FileIO(fileName).readFile();
			} catch (Exception e1) {
				e1.printStackTrace();
				return;
			} 
		}
		catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		car1.printCarInfo();
		
		newCar.serializeVehicle(car1);
		Automobile car2 = newCar.deserializeVehicle("Ford Focus Wagon");
		System.out.println();
		System.out.println("Deserialized File:");
		car2.printCarInfo();
		

	}
}
