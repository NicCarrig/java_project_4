package driver;

import java.io.IOException;

import adapter.BuildAuto;
import exception.AutoException;
import exception.FileNotFoundException;
import model.Automobile;
import util.FileIO;

public class Driver4 {
	public static void main(String[] args) {
		
		//LHM and choice driver
		
		String fileName = "test-car-3.txt";
//		String key = "Ford Focus Wagon";
		String key = "Focus Wagon";
		
		//hard coded choices for testing
		FileIO newCar = new FileIO(fileName);
		Automobile car1 = null;
		try {
			car1 = newCar.readFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (AutoException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		car1.setOptionChoice("Color", "French Blue Clearcoat Metallic");
		car1.setOptionChoice("Power Moonroof", "present");
		car1.printUserChoices();
		System.out.println(car1.getOptionChoice("Color"));
		System.out.println(car1.getOptionChoicePrice("Power Moonroof"));
		
		//USER INPUT CHOICES
		BuildAuto a1 = new BuildAuto();
		a1.buildAuto(fileName);		
		System.out.println("User Choices:");
		a1.getUserChoices(key);
		a1.printUserChoices(key);
		
	}

}
