package driver;

import exception.FileNotFoundException;
import model.Automobile;
import util.FileIO;

public class Driver3 {

	public static void main(String[] args) {
		// Exception/error driver
		
		//Exception logging test
//		AutoException err1 = new AutoException(101);
//		System.out.println(err1.getErrMsg());
//		AutoException err2 = new AutoException(101, "test message");
//		System.out.println(err2.getErrMsg());
//		AutoException err3 = new AutoException();
		
		String fileName = "test-car-.txt";
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

	}

}
