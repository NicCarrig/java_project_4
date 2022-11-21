package exception;

import java.util.Scanner;

public class FileNotFoundException extends AutoException{
	
	public FileNotFoundException() {
		super();
	}
	public FileNotFoundException(int errNo) {
		super(errNo);
	}
	public FileNotFoundException(String errMsg) {
		super(errMsg);
	}
	public FileNotFoundException(int errNo, String errMsg) {
		super(errNo, errMsg);		
	}
	
	public String getNewFileName() {
		Scanner in = new Scanner(System.in);
		System.out.println("File Name Not Found");
		System.out.printf("Please enter a valid file name: ");
		String newFileName = in.nextLine();
		
		return newFileName;
	}
	
}
