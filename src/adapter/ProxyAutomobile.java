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
import java.util.LinkedHashMap;

public abstract class ProxyAutomobile {
	private static LinkedHashMap<String, Automobile> autos = new LinkedHashMap(); // (modelName, auto) pair
	
	/* in CreateAuto API */
	/* build the auto model object */
	public void buildAuto(String filename) {
		FileIO io = new FileIO();
		boolean fileFound = false;
		Automobile a1;
		
		/* handle error when filename is wrong */
		do {
			try {
				a1 = io.buildAutoObject(filename);
				fileFound = true;
				/* put the built model to collection */
				if (a1 != null)
					autos.put(a1.getModel(), a1);
			}
			catch (WrongInputException e) {
				e.recordLog("log.txt", e);
				e.printInfo();
				filename = fix(e.getErrno());
			}
		} while (!fileFound);
		
	}
	/* print the auto model information */
	public void printAuto(String modelName) {
		if (autos.containsKey(modelName))
			autos.get(modelName).printInfo();
		else {
			System.out.println("The model doesn't exist");
		}
	}
	
	/* in UpdateAuto API*/
	/* update the OptionSet name of a specified model */
	public void updateOptionSetName(String modelname, String setName, String newName) {
		if (autos.containsKey(modelname)) {
			Automobile a1 = autos.get(modelname);
			a1.updatOptionSetName(setName, newName);
		}
		else
			System.out.println("model does not exist.");
	}
	/* update the price of an Option of specified model and OptionSet */
	public void updateOptionPrice(String modelname, String setName, String optionName, int newprice) {
		if (autos.containsKey(modelname)) {
			Automobile a1 = autos.get(modelname);
			a1.updateOptionPrice(setName, optionName, newprice);
		}
		else
			System.out.println("model deose not exist.");
	}
	/* make a choice in an OptionSet of an Auto */
	public void makeOptionChoice(String modelname, String setName, String optName) {
		Automobile a1 = autos.get(modelname);
		a1.setOptionChoice(setName, optName);
	}
	
	public String fix(int errno) {
		AutoException autoExp = new AutoException(errno);
		return autoExp.fix(errno);
	}
	
}
