package scale;

import model.Automobile;

public class Helper {
	
	public Helper() {
		
	}
	
	public synchronized void syncUpdateOption(Automobile car, String [] args) {
		//pass in auto
		//call auto class's update option
		
		System.out.println("Update Option - sync");
		car.updateOption(args);
//		car.printCarInfo();
	}
	
	public void asyncUpdateOption(Automobile car, String [] args) {
		
		System.out.println("Update Option - async");
		car.updateOption(args);
//		car.printCarInfo();
	}
	
	public synchronized void syncUpdatePrice(Automobile car, String [] args) {
		//parse args to get new price and where to update it
		//updateOptionPrice(String optionSetName, String optionName, double newPrice)
		double newPrice = parseArgs(args[args.length-1]);
		String opSetName = args[0];
		String opName = args[1];
		
		System.out.println("Update Price - sync");
		car.updateOptionPrice(opSetName, opName, newPrice);
		
	}
	public void asyncUpdatePrice(Automobile car, String [] args) {
		
		System.out.println("Update Price - async");
		double newPrice = parseArgs(args[args.length-1]);
		String opSetName = args[0];
		String opName = args[1];
		
		car.updateOptionPrice(opSetName, opName, newPrice);
	}
	
	public double parseArgs(String arg) {
		return Double.parseDouble(arg);
	}

}
