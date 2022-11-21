/*
Nicholas Carrig	
CIS D035B
Assignment 4 
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import model.OptionSet.Option;

public class Automobile implements Serializable{
	private String make;
	private String model;
	private double basePrice;
	private ArrayList<OptionSet> opset;
	private ArrayList<Option> choice;
	
	//Constructor
	public Automobile(String make, String model, double basePrice, ArrayList<OptionSet> optionSets) {
		opset = new ArrayList<OptionSet>();
		choice = new ArrayList<Option>();
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
		
		setOpset(optionSets);	
	}

	//Getter/Setters
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMakeAndModel() {
		return make + " " + model;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public ArrayList<OptionSet> getOpset() {
		return opset;
	}
	public void setOpset(ArrayList<OptionSet> optionSets) {
		Iterator<OptionSet> iterator = null;
		iterator = optionSets.iterator();
		while(iterator.hasNext()) {
			OptionSet optionSetEl = iterator.next();
			opset.add(optionSetEl);
		}
	}
	public OptionSet getSingleOpset(int x) {
		return opset.get(x);
	}
	public String getOpsetName(int x) {
		return opset.get(x).getOpsetName();
	}
	public Option getSingleOption(int x, int y) {
		return opset.get(x).getSingleOption(y);
	}
	public int getNumOptionSets() {
		return opset.size();
	}
	public int getNumOptionsInSet(int x) {
		return opset.get(x).getNumOptions();
	}
	
	
	//print methods
	public void printMake() {
		System.out.println("Make: " + make);
	}
	public void printModel() {
		System.out.println("Model: " + model);
	}
	public void printMakeAndModel() {
		System.out.println(make + " " + model);
	}
	public void printBasePrice() {
		System.out.printf("Base Price: $%.2f", basePrice);
		System.out.println();
	}
	public void printAllOptionSets() {
		Iterator<OptionSet> iterator = null;
		iterator = opset.iterator();
		while(iterator.hasNext()) {
			OptionSet opSetEl = iterator.next();
			opSetEl.printOptionSetInfo();
		}
	}
	public void printOneOptionSet(int x) {
		opset.get(x).printOptionSetInfo();
	}
	public void printOneOptionSetChoices(int x) {
		opset.get(x).printOptionSetChoices();
	}
	public void printCarInfo() {
		printMakeAndModel();
		printBasePrice();
		printAllOptionSets();
	}
	
	//methods for user choice
	public void setChoice(ArrayList<Option> userChoices) {
		choice.addAll(userChoices);
	}
	public void setSingleChoice(Option userChoice, int x) {
		choice.set(x, userChoice);
	}
	public ArrayList<Option> getChoice(){
		return choice;
	}
	public Option getSingleChoice(int x) {
		return choice.get(x);
	}
	public void addChoice(Option opt) {
		choice.add(opt);
	}
	public void printUserChoices() {
		Iterator<Option> iterator = null;
		iterator = choice.iterator();
		while(iterator.hasNext()) {
			Option choiceEl = iterator.next();
			choiceEl.printOptionInfo();
			System.out.println();
		}
	}
	public String getOptionChoice(String opsetName){
		//given option set name, return the name of the chosen option in that set
		String chosenOpName = null;
		Iterator<OptionSet> iterator = null;
		iterator = opset.iterator();
		//search and compare all OptionSets for given name
		while(iterator.hasNext()) {
			OptionSet element = iterator.next();
			if(element.getOpsetName().equals(opsetName)) {
				chosenOpName = element.getOptionChoice().getName();
			}
		}
		return chosenOpName;
	}
	public double getOptionChoicePrice(String opsetName) {
		//given option set name, return the price of the chosen option in that set
		double chosenOpPrice = -0.001;
		Iterator<OptionSet> iterator = null;
		iterator = opset.iterator();
		//search and compare all OptionSets for given name
		while(iterator.hasNext()) {
			OptionSet element = iterator.next();
			if(element.getOpsetName().equals(opsetName)) {
				chosenOpPrice = element.getOptionChoice().getPrice();
			}
		}
		//need a way to indicate if the option was not found and the default price is being returned
		return chosenOpPrice;
	}
	public void setOptionChoice(String opsetName, String optionName) {
		//given option set name and name of option in the set, add that option to the choice array list
		//search each option set for matching name
		for(int i = 0; i < opset.size(); i++) {
			if(opset.get(i).getOpsetName().equals(opsetName)) {
				//if opset name is found, search options in that set for matching option name
				for(int j = 0; j < opset.get(i).getOptionSize(); j++) {
					if(opset.get(i).getSingleOption(j).getName().equals(optionName)) {
						opset.get(i).setOptionChoice(j);
						choice.add(opset.get(i).getSingleOption(j));
						return;
					}
				}
			}
		}
	}
	public void setOptionChoice(int x, int y) {
		opset.get(x).setOptionChoice(y);
	}
	
	public double calcTotalCost() {
		//iterates through choice array list and adds option price to the base price
		double totalCost = basePrice;
		
		Iterator<Option> iterator = null;
		iterator = choice.iterator();
		while(iterator.hasNext()) {
			Option choiceEl = iterator.next();
			totalCost += choiceEl.getPrice();
		}
		return totalCost;
	}
	public void printTotalCost() {
		System.out.printf("Total Cost: $%.2f", calcTotalCost());
	}

	
	//helpers for LHM
	public String generateKey() {
		return make + " " + model;
	}

	public void updateOptionSetName(String oldName, String newName) {

		for(int i = 0; i < opset.size(); i++) {
			if(oldName.equals( opset.get(i).getOpsetName() )) {
				opset.get(i).setOpsetName(newName);
				return;
			}
		}
		//returns once the name get updated, otherwise will hit the "not found" message 
		System.out.println("No option set with name " + oldName + " found.");
	}
	public void updateOptionPrice(String optionSetName, String optionName, double newPrice) {
		for(int i = 0; i < opset.size(); i++) {
			//find matching option set name
			if(optionSetName.equals( opset.get(i).getOpsetName() )) {
				for(int j = 0; j< opset.get(i).getOptionSize(); j++) {
					//find matching option name
					if(optionName.equals( opset.get(i).getSingleOption(j).getName() )) {
						opset.get(i).getSingleOption(j).setPrice(newPrice);
						System.out.printf("Updating %s: %s price updated to $%.2f\n", optionSetName, optionName, newPrice);
						return;
					}
				}
			}
		}
		System.out.println("Unable to update price. Please verify that the option set name and option name are correct.");
	}
	public void updateOption(String [] vals) {
		//vals should be a string array where [0] is the value/name of the option that needs to get changed
		//vals[1] should be the value that the option will be updated to
		//no option set name was given in the example driver
		//probably need to search through all of the optionSets and options until the correct value is found
		String oldVal = vals[0];
		String newVal = vals[1];
		
		for(int i = 0; i < opset.size(); i++) {
			for(int j = 0; j < opset.get(i).getOptionSize(); j++) {
				//iterate through each option in each option set to search for the value to update
				if(oldVal.equals( opset.get(i).getSingleOption(j).getName()) ) {
					opset.get(i).getSingleOption(j).setName(newVal);
					System.out.println("Updating " + opset.get(i).getOpsetName() + ": " + oldVal + " to " + newVal );
					return;
				}
			}
		}
		System.out.println("Unable to update option. Please verify that the option you wish to update is correct.");
	}
	
}
