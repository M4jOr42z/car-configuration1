/**
 * @author zhexinq
 * 
 * driver class for demonstrating 
 * two threads modifying the same value at the same time
 * in synchronized or not synchronized case
 */

package driver;

import adapter.BuildAuto;

public class Driver {
	
	public static void main(String[] args) {
		/* instantiate an BuildAuto object  and build a model */
		BuildAuto autoFactory = new BuildAuto();
		autoFactory.buildAuto("ford.txt");
		
		/* edit on the same model for multiple times with multiple threads */
		for (int i = 0; i < 5; i++) {
			autoFactory.editOptionNameSync("Focus Wagon ZTW", "Transmission", "manual", "standard");
			autoFactory.editOptionNameSync("Focus Wagon ZTW", "Transmission", "manual", "sporty");
		}
	}

}
