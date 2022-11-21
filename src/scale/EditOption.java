package scale;

import model.Automobile;

//public class EditOption extends ProxyAutomobile implements Runnable {
public class EditOption implements Runnable {

	private Thread t;
	private Automobile auto;
	private boolean DEBUG = true;
//	private String modelName;
	private String [] args;		//[0+] contains name of thing(s) that needs to be updated
								//[length - 1] is updated value
	private int operation;
	
	public EditOption(Automobile auto, int operation, String [] args)
	{
		this.auto = auto;
		this.operation = operation;
		this.args = args;
		this.t = new Thread(this);
		t.start();
	}

	public void run() {
		ops();
	}

	public void ops() {
					
		Helper h = new Helper(); //add synchronized/non synchronized methods here
	
	    switch(operation) {
			case 0:
				//Call method in helper class 
				//Update the option name from blue to dark blue - non synchronized operation
				h.asyncUpdateOption(auto, args);
				break;
			case 1:
				//Call method in helper class 
				//Update the option name from blue to Red	- non synchronized operation
//				try {
//					t.sleep(500);
//				}
//				catch(Exception e) {
//					System.out.println(e.toString());
//				}
				h.asyncUpdateOption(auto, args);
				break;	
			case 2:
				//Call method in helper class 
				//Update the option name from blue to dark blue - synchronized operation
				h.syncUpdateOption(auto, args);
				break;
			case 3:
				//Call method in helper class 
				//Update the option name from blue to red - synchronized operation
							
				h.syncUpdateOption(auto, args);
				break;	
			case 4:
				//update option price: non-sync
				h.asyncUpdatePrice(auto, args);
				break;
			case 5:
				//update option price: second non-sync
//				try {
//					t.sleep(500);
//				}
//				catch(Exception e) {
//					System.out.println(e.toString());
//				}
				h.asyncUpdatePrice(auto, args);
				break;	
			case 6:
				h.syncUpdatePrice(auto, args);
				break;
			case 7:
				h.syncUpdatePrice(auto, args);
				break;
			default:
				//allow current threads to finish
				System.out.println("Allowing current threads to finish");
				try {
					t.sleep(3000);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
		}
	
	}
	

}
