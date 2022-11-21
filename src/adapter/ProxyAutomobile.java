package adapter;

import java.util.Scanner;
import java.util.Set;

import exception.FileNotFoundException;
import model.Automobile;
import model.LHMAutomobile;
import scale.EditOption;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static LHMAutomobile<Automobile> a1 = new LHMAutomobile<Automobile>();
	
	//CreateAuto Interface methods
	public void buildAuto(String fileName) {
		FileIO carFile = new FileIO(fileName);
		Automobile car = null;
		try {
			car = carFile.readFile();
		} catch (FileNotFoundException e) {
			try {
				car = new FileIO(e.getNewFileName()).readFile();
			} catch (Exception e1) {
				e1.printStackTrace();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		a1.create(car);
	}
	public void printAuto(String modelName) {
		String key = findKeyFromModelName(modelName);
		Automobile car = a1.read(key);
		car.printCarInfo();
	}
	
	//UpdateAuto Interface methods
	public void updateOptionSetName(String modelName, String optionSetName, String newName) {
		String key = findKeyFromModelName(modelName);
		a1.updateOptionSetName(key, optionSetName, newName);
	}
	public void updateOptionPrice(String modelName, String optionSetName, String optionName, double newPrice) {
		String key = findKeyFromModelName(modelName);
		a1.updateOptionPrice(key, optionSetName, optionName, newPrice);
	}
	
	//FixAuto Interface methods
	public void fixErr(int errNo) {
		
	}
	
	//user choice methods
	public void getUserChoices(String modelName) {
		String key = findKeyFromModelName(modelName);
		Scanner in = new Scanner(System.in);
		for(int i = 0; i < a1.read(key).getNumOptionSets(); i++ ) {
			a1.read(key).printOneOptionSetChoices(i);
			System.out.printf("Please enter the number of your choice: ");
			int choiceNum = in.nextInt();
			a1.read(key).addChoice(a1.read(key).getSingleOption(i, choiceNum - 1));
			a1.read(key).setOptionChoice(i, choiceNum - 1);
			
		}
		in.close();
	}
	//public void getChoicesFromFile(String fileName){}
	public void printUserChoices(String modelName) {
		String key = findKeyFromModelName(modelName);
		a1.read(key).printUserChoices();
		a1.read(key).printTotalCost();
	}
	
	//LMH search and helper methods
	public String findKeyFromModelName(String modelName) {
		//used to find the hash map key from either just a model name or  make and model names
		//ex: should be able to get the key for either "Ford Focus Wagon" or "Focus Wagon"
		Set<String> keys = a1.setKeys();
		for(String key : keys) {
			if( modelName.equals(a1.read(key).getModel()) || modelName.equals(a1.read(key).getMakeAndModel()) ) {
				return a1.read(key).generateKey();
			}
		}
		
		return "";
	}
	
	public void editThread(String modelName, int operation, String [] args) {
		//get auto object out of the hash map using model name
		//create an EditOption object and update based on operation number
		String key = findKeyFromModelName(modelName);
		Automobile car = a1.read(key);
		EditOption edit = new EditOption(car, operation, args);
		
	}
}
