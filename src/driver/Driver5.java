package driver;

import java.io.IOException;

import adapter.BuildAuto;
import adapter.EditAuto;
import exception.AutoException;
import exception.FileNotFoundException;
import model.Automobile;
import util.FileIO;

public class Driver5 {

	public static void main(String[] args) {
		
		//DRIVER FOR UNSYNCED METHODS
		
		String fileName = "test-car-3.txt";
//		String fileName2 = "test-car-3-sync.txt";
		
		BuildAuto build1 = new BuildAuto();
		build1.buildAuto(fileName);
//		build1.buildAuto(fileName2);
//		build1.printAuto("Focus Wagon");
		
		//inputs for updating options
		String [] x = { "blue", "dark blue"};	//input for operation 0 and 2
		String [] y = {"dark blue", "red"};		//input for operation 1 and 3
		//String [] for updating price should be formatted as ["optionSetName", "optionName", "newPrice"]
		String [] p1 = {"Color","price update color", "1.1"};	//input for operation 4 and 6
		String [] p2 = {"Color","price update color", "2.2"};	//input for operation 5 and 7
		
		//UPDATING OPTIONS WITHOUT SYNCHRONIZATION
		//update an option - async
		EditAuto a1 = new BuildAuto();		
		a1.editThread("Focus Wagon", 0, x);
		a1.editThread("Focus Wagon", 1, y);		
		//update a price - async
		a1.editThread("Focus Wagon", 4, p1);
		a1.editThread("Focus Wagon", 5, p2);
		
//		//UPDATING OPTIONS USING SYNCHRONIZATION
//		//update an option - synced
//		EditAuto a2 = new BuildAuto();
//		a2.editThread("Focus Wagon Synced Example", 2, x);
//		a2.editThread("Focus Wagon Synced Example", 3, y);
//		//update a price - synced
//		a2.editThread("Focus Wagon Synced Example", 6, p1);
//		a2.editThread("Focus Wagon Synced Example", 7, p2);
		
	}
}
