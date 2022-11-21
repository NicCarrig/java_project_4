package driver;

import adapter.BuildAuto;

public class Driver2 {

	public static void main(String[] args) {
		
		//Interface/BuildAuto Driver
		
		String fileName = "test-car-3.txt";
		
		System.out.println("Build Auto Testing:");
//		String key = "Ford Focus Wagon";
		String key = "Focus Wagon";
		BuildAuto a1 = new BuildAuto();
		a1.buildAuto(fileName);
		a1.printAuto(key);
		a1.updateOptionSetName(key, "Power Moonroof", "Updated Power Moonroof");
		a1.printAuto(key);
		a1.updateOptionPrice(key, "Updated Power Moonroof", "not present", 0.99);
		a1.printAuto(key);

	}

}
