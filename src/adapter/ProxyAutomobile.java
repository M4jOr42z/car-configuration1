/**
 * @author zhexinq
 * abtract class to contain all implementations of any methods
 * declared in the interface
 */

package adapter;

import exception.WrongInputException;

import model.Automobile;
import util.FileIO;
import exception.AutoException;

public abstract class ProxyAutomobile {
	private static Automobile a1;
	
	/* build the auto model object */
	public void buildAuto(String filename) {
		FileIO io = new FileIO();
		boolean autoLoaded = false;
		
		/* handle error when filename is wrong */
		do {
			try {
				a1 = io.buildAutoObject(filename);
				autoLoaded = true;
			}
			catch (WrongInputException e) {
				e.recordLog("log.txt", e);
				e.printInfo();
				filename = fix(e.getErrno());
			}
		} while (!autoLoaded);
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
	
	public String fix(int errno) {
		AutoException autoExp = new AutoException(errno);
		return autoExp.fix(errno);
	}
	
}
