/**
 * @author zhexinq
 * Automotive class for definition of car model
 * 
 * each car model has a set of properties: 
 * color, transmission, brakes/traction control, airbags, moonroof, AND a base price.
 * each property has a set of options, AND a name
 * each option has a price, AND a name.
 * 
 * users of Automotive objects can read the property values using print method,
 * and modify the properties using update/delete methods
 */
package model;

import java.io.*;

public class Automobile implements Serializable {
	private static final long serialVersionUID = -6602495310727640916L;
	private String name;
	private int basePrice;
	private OptionSet[] opsets;
	
	/* constructor */
	// in default a Focus Wagon ZTW model, there are 5 OptionSets, base price $18445
	public Automobile() {
		this.name = "Focus Wagon ZTW";
		this.basePrice = 18445;
		this.opsets = new OptionSet[5];
		for (int i = 0; i < 5; i++)
			this.opsets[i] = new OptionSet();
	}
	public Automobile(String name, int size, int price) {
		this.name = name;
		this.basePrice = price;
		this.opsets = new OptionSet[size];
		for (int i = 0; i < size; i++)
			this.opsets[i] = new OptionSet();
	}
	
	/* getters */
	public String getName() {
		return this.name;
	}
	public int getBasePrice() {
		return this.basePrice;
	}
	public OptionSet[] getOptionSets() {
		int numOfSets = this.opsets.length;
		OptionSet[] result = new OptionSet[numOfSets];
		for (int i = 0; i < numOfSets; i++) {
			result[i] = new OptionSet(this.opsets[i]);
		}	
		return result;
	}
	/* get OptionSet by index */
	// return null if n is not within array range
	public OptionSet getOptionSet(int n) {
		if (n >= 0 && n < opsets.length)
			return new OptionSet(this.opsets[n]);
		return null;
	}
	
	/* find methods */
	// find an OptionSet reference in Auto by name if exist, else return null
	public OptionSet findOptionSet(String name) {
		int N = this.opsets.length;
		for (int i = 0; i < N; i++) {
			if (this.opsets[i].getName().equals(name))
				return opsets[i];
		}
		return null;
	}
	// find an OptionSet index by name if exist, else return -1
	public int findOptionSetIndex(String name) {
		for (int i = 0; i < this.opsets.length; i++) {
			if (this.opsets[i].getName().equals(name))
				return i;
		}
		return -1;
	}
	// find an Option reference in Auto by name in context of an OptionSet
	// if not exist, return null
	public OptionSet.Option findOption(String setname, String optname) {
		OptionSet opset = findOptionSet(setname);
		if (opset != null) {
			OptionSet.Option[] options = opset.getOptions();
			int N = options.length;
			for (int i = 0; i < N; i++) {
				if (options[i].getName().equals(optname))
					return options[i];
			}
		}
		return null;
	}
		
	/* setter */
	public void setName(String name) {
		this.name = name;
	}
	public void setBasePrice(int p) {
		this.basePrice = p;
	}
	public void setOptionSet(int n, OptionSet set) {
		this.opsets[n] = set;
	}
	public void setOptionSets(OptionSet[] sets) {
		this.opsets = sets;
	}
	public void setOption(int setid, int optid, OptionSet.Option opt) {
		OptionSet set = getOptionSet(setid);
		if (set != null) {
			OptionSet.Option[] opts = set.getOptions();
			if (optid >= 0 && optid < opts.length) 
				set.setOption(optid, opt);
		}
	}
	
	/* updaters */
	// update name and Options of OptionSet by index
	public void updateOptionSet(int n, String setName, String[] setOptions, int[] setPrices) {
		assert(n >= 0 && n < this.opsets.length);
		assert((setOptions.length == setPrices.length) && setOptions.length >= 0);
		
		// number of options
		int N = setOptions.length;
		// construct the new OptionSet object for update
		OptionSet optset = new OptionSet(N, setName);
		OptionSet.Option[] options = new OptionSet.Option[N];
		for (int i = 0; i < N; i++)
			options[i] = new OptionSet.Option(setOptions[i], setPrices[i]);
		optset.setOptions(options);
		// put the constructed set into Auto object's field
		setOptionSet(n, optset);
	}
	// update Options of OptionSet by name
	public void updateOptionSet(String setName, String[] setOptions, int[] setPrices) {
		assert(findOptionSet(setName) != null);
		assert((setOptions.length == setPrices.length) && setOptions.length >= 0 
				&& setOptions.length < this.opsets.length);
		
		// reference to the original set in Auto
		OptionSet optset = findOptionSet(setName);
		// number of options
		int N = setOptions.length;
		// construct the new Option array for update
		OptionSet.Option[] options = new OptionSet.Option[N];
		for (int i = 0; i < N; i++)
			options[i] = new OptionSet.Option(setOptions[i], setPrices[i]);
		optset.setOptions(options);
	}
	// update OptionSet name
	public void updatOptionSetName(String setName, String newName) {
		OptionSet optset = findOptionSet(setName);
		if (optset != null)
			optset.setName(newName);
	}
	// update name and price of Option by OptionSetName and index
	public void updateOption(String setName, int optIndex, String optName, int optPrice) {
		assert(findOptionSet(setName) != null);
		assert(optIndex >= 0 && optIndex < findOptionSet(setName).getOptions().length);
		
		OptionSet optset = findOptionSet(setName);
		OptionSet.Option opt = new OptionSet.Option(optName, optPrice);
		optset.setOption(optIndex, opt);
	}
	// update price of Option by name
	public void updateOption(String setName, String optName, int optPrice) {
		assert(findOption(setName, optName) != null);
		
		OptionSet.Option opt = findOption(setName, optName);
		opt.setName(optName);
		opt.setPrice(optPrice);		
	}
	
	/* deleters */
	// delete OptionSet by index
	public void deleteOptionSet(int n) {
		assert(n >= 0 && n < this.opsets.length);
		this.opsets[n] = null;
	}
	// delete OptionSet by name
	public void deleteOptionSet(String setName) {
		int index = findOptionSetIndex(setName);
		if (index >= 0)
			deleteOptionSet(index);
	}
	// delete an Option in the context of an OptionSet by index
	public void delteOption(String setName, int n) {
		OptionSet optset = findOptionSet(setName);
		if (optset != null) {
			OptionSet.Option[] options = optset.getOptions();
			options[n] = null;
		}
	}
	// delete an Option in the context of an OptionSet by name
	public void deleteOption(String setName, String optName) {
		OptionSet optset = findOptionSet(setName);
		if (optset != null) {
			OptionSet.Option[] options = optset.getOptions();
			for (int i = 0; i < options.length; i++) {
				if (options[i].getName().equals(optName))
					options[i] = null;
			}	
		}
	}
	
	/* print */
	public void printInfo() {
		System.out.printf("Automotive: %s\n", this.name);
		for (OptionSet set:this.opsets) {
			if (set == null)
				System.out.println("null");
			else
				set.printInfo();
		}
	}
	/* unit test */
	public static void main(String[] args) {
		// test Automotive methods
		System.out.println("constructors: ");
		Automobile auto = new Automobile();
		auto.printInfo();
		Automobile auto2 = new Automobile("Honda Accord", 2, 19000);
		auto2.printInfo();
		
		// setters
		OptionSet[] optsets = new OptionSet[2];
		OptionSet.Option red = new OptionSet.Option("red", 0);
		OptionSet.Option green = new OptionSet.Option("green", 0);
		OptionSet.Option blue = new OptionSet.Option("blue", 0);
		OptionSet.Option[] colors = new OptionSet.Option[3];
		colors[0] = red;
		colors[1] = green;
		colors[2] = blue;
		OptionSet.Option brakeStd = new OptionSet.Option("standard", 0);
		OptionSet.Option abs = new OptionSet.Option("abs", 700);
		OptionSet.Option[] brakes = new OptionSet.Option[2];
		brakes[0] = brakeStd;
		brakes[1] = abs;
		OptionSet aset = new OptionSet(colors, "color");
		OptionSet bset = new OptionSet(brakes, "brake");
		optsets[0] = aset;
		optsets[1] = bset;
		
		System.out.println("setters set OptionSets by sets");
		auto2.setOptionSets(optsets);
		auto2.printInfo();
		System.out.println("setters set Optionset by index");
		OptionSet.Option[] moonroof = new OptionSet.Option[2];
		moonroof[0] = new OptionSet.Option("present", 100);
		moonroof[1] =  new OptionSet.Option("present", 0);
		auto2.setOptionSet(1, new OptionSet(moonroof, "moonroof"));
		auto2.printInfo();
		System.out.println("setters set Option by setid, optid");
		auto2.setOption(0, 2, new OptionSet.Option("black", 0));
		auto2.printInfo();
		
		// getters
		System.out.println("getters: ");
		System.out.println("auto name - " + auto2.getName());
		System.out.println("auto base price - " + auto2.getBasePrice());
		System.out.println("get all OptionSets:");
		optsets = auto2.getOptionSets();
		for (int i = 0; i < optsets.length; i++)
			optsets[i].printInfo();
		System.out.println("get OptionSet by index");
		OptionSet cset = auto2.getOptionSet(1);
		cset.printInfo();
		
		// finders
		System.out.println("find an OptionSet reference by name");
		cset = auto2.findOptionSet("color");
		cset.printInfo();
		System.out.println("find a OptionSet index by name");
		int setId = auto2.findOptionSetIndex("moonroof");
		System.out.println("moonroof is in " + setId);
		
		// updaters
		System.out.println("update the OptionSet's Options using name");
		String[] mrOptions = {"full", "half"};
		int[] mrp = {1000, 500};
		auto2.updateOptionSet("moonroof", mrOptions, mrp);
		auto2.printInfo();
		System.out.println("update an Option in an OptionSet by index");
		auto2.updateOption("color", 1, "gray", 11);
		auto2.printInfo();
		System.out.println("update an Option's price by name");
		auto2.updateOption("moonroof", "full", 900);
		auto2.printInfo();
		
		// deleters
		System.out.println("delete an OptionSet by index");
		auto2.deleteOptionSet(1);
		auto2.printInfo();
		auto2.updateOptionSet(1, "moonroof", mrOptions, mrp);
		System.out.println("delete an OptionSet by name");
		auto2.deleteOptionSet("moonroof");
		auto2.printInfo();
		auto2.updateOptionSet(1, "moonroof", mrOptions, mrp);
		System.out.println("delete an Option by index");
		auto2.delteOption("moonroof", 1);
		auto2.printInfo();
		auto2.updateOption("moonroof", 1, "half", 200);
		auto2.printInfo();
		System.out.println("delete an Option by name");
		auto2.deleteOption("moonroof", "full");
		auto2.printInfo();
	}
}
