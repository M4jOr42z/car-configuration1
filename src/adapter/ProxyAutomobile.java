/**
 * @author zhexinq
 * abtract class to contain all implementations of any methods
 * declared in the interface
 */

package adapter;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

public abstract class ProxyAutomobile {
	private static Automobile a1;
	
	/* build the auto model object */
	public void buildAuto(String filename) {
		FileIO io = new FileIO();
		try {
			a1 = io.buildAutoObject(filename);
		}
		catch (AutoException e) {
			e.fix(e.getErrno());
		}
	}
	/* print the auto model information */
	public void printAuto(String modelName) {
		if (a1 == null)
			System.out.println("auto model not initiailized yet.");
		else
			a1.printInfo();
	}
	/* update the OptionSet name of a specified model */
	public void updateOptionSetName(String Modelname, String setName, String newName) {
		if (a1 != null && a1.getName().equals(Modelname))
			a1.updatOptionSetName(setName, newName);
		else
			System.out.println("The specified model does not exist.");
	}
	/* update the price of an Option of specified model and OptionSet */
	public void updateOptionPrice(String Modelname, String setName, String optionName, int newprice) {
		if (a1 != null && a1.getName().equals(Modelname))
			a1.updateOption(setName, optionName, newprice);
		else
			System.out.println("The specified model deose not exist.");
	}
	
}
