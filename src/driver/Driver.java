/**
 * @author zhexinq
 * Driver class for testing the model and util packages
 * 				- populate an Automotive object using .txt file
 * 				- serialize the object to .ser file
 * 				- deserialize the object and read it to memory
 * 				- print new attributes
 */

package driver;

import model.Automobile;
import util.FileIO;
import exception.AutoException;

public class Driver {

	public static void main(String[] args) {
		// Build Automotive Object
		FileIO io = new FileIO();
		Automobile FordZTW = null;
		try {
			FordZTW = io.buildAutoObject("ford.txt");
			// Print attributes before serialization
			System.out.println("before serialization: ");
			FordZTW.printInfo();
			// Serialize the object
			io.serialzeAutoObject(FordZTW, "fordztw.ser");
			// Deserialize the object and read it into memory
			Automobile newFordZTW = io.deserializeAutoObject("fordztw.ser");
			// Print new attributes
			System.out.println("\nafter serialization: ");
			newFordZTW.printInfo();
		}
		catch (AutoException e) {
			e.fix(e.getErrno());
		}
	}

}
