/**
 * @author zhexinq
 * OptionSet class that has a name and a set of Options
 */
package model;

import java.io.*;

class OptionSet implements Serializable {
	private static final long serialVersionUID = 5319384422974160381L;
	private String name;
	private Option[] options;

	/* constructor */
	protected OptionSet() {
		this.name = "";
		this.options = new Option[0];
	}
	protected OptionSet(int optsize, String name) {
		this.name = name;
		this.options = new Option[optsize];
		for (int i = 0; i < optsize; i++) 
			this.options[i] = new Option();
	}
	protected OptionSet(OptionSet opset) {
		this.name = opset.getName();
		int N = opset.getOptions().length;
		this.options = new Option[N];
		for (int i = 0; i < N; i++) {
			String name = opset.getOptions()[i].getName();
			int price =   opset.getOptions()[i].getPrice();
			this.options[i] = new Option(name, price);
		}
	}
	protected OptionSet(Option[] options, String name) {
		this.name = name;
		this.options = new Option[options.length];
		for (int i = 0; i < options.length; i++)
			this.options[i] = new Option(options[i]);
	}
	
	/* getters */
	protected String getName() {
		return this.name;
	}
	protected Option[] getOptions(){
		return this.options;
	}
	protected Option getOption(int n) {
		if (n >= 0 || n <= this.options.length)
			return this.options[n];
		return null;
	}
	
	/* setters */
	protected void setName(String n) {
		this.name = n;
	}
	protected void setOptions(Option[] opts) {
		if (opts.length == this.options.length) {
			for (int i = 0; i < opts.length; i++) {
				this.options[i].setName(opts[i].getName());
				this.options[i].setPrice(opts[i].getPrice());
			}
		}
	}
	protected void setOption(int n, Option opt) {
		if (n >= 0 && n < options.length) {
			if (options[n] == null)
				options[n] = new Option();
			options[n].setName(opt.getName());
			options[n].setPrice(opt.getPrice());
		}
	}
	
	/* print method */
	protected void printInfo() {
		System.out.printf("The OptionSet %s:\n", this.name);
		for (Option opt:this.options) {
			if (opt == null)
				System.out.println("null");
			else
				opt.printInfo();
		}
	}
	
	/*** inner class ***/
	protected static class Option implements Serializable {
		private static final long serialVersionUID = -4721102974730100161L;
		private String name;
		private int price;
		
		/* constructors */
		protected Option() {
			this.name = "";
			this.price = 0;
		}
		protected Option(String name, int price) {
			this.name = name;
			this.price = price;
		}
		protected Option(Option opt) {
			this.name = opt.getName();
			this.price = opt.getPrice();
		}
		
		/* getters */
		protected String getName() {
			return this.name;
		}
		protected int getPrice() {
			return this.price;
		}
		
		/* setters */
		protected void setName(String name) {
			this.name = name;
		}
		protected void setPrice(int price) {
			this.price = price;
		}
		
		/* print method for properties info */
		protected void printInfo() {
			System.out.printf("Option %s, $%d\n", this.name, this.price);
		}
	}
	
	/* unit test */
	public static void main(String[] args) {
		// test OptionSet methods
		// constructors
		OptionSet opset = new OptionSet(2, "Transmission");
		System.out.println("original OptionSet:");
		opset.printInfo();
		System.out.println("copy OptionSet using OptionSet(OptionSet)...");
		OptionSet opsetCopy = new OptionSet(opset);
		System.out.println("new copy:");
		opsetCopy.printInfo();
		System.out.println("modify the new copy using set methods...");
		opsetCopy.setName("Power Moonroof");
		Option[] copyOpts = new Option[2];
		copyOpts[0] = new Option("present", 595);
		copyOpts[1] = new Option("not present", 0);
		opsetCopy.setOptions(copyOpts);
		System.out.println("the new copy after modification:");
		opsetCopy.printInfo();
		System.out.println("the old copy after modification:");
		opset.printInfo();
		
		// getters
		Option[] options = opsetCopy.getOptions();
		System.out.println();
		System.out.println("get all info of new copy's options:");
		for (Option opt:options)
			opt.printInfo();
		System.out.println("modify the options from get method");
		options[0] = new Option("Standard", 99);
		for (Option opt:options)
			opt.printInfo();
		System.out.println("get all info of original copy's options:");
		for (Option opt:opsetCopy.getOptions())
			opt.printInfo();
		System.out.println("get Option by index:");
		Option opt = opsetCopy.getOption(1);
		opt.printInfo();
		
		// setters
		System.out.println();
		System.out.println("old opset:");
		opset.printInfo();
		System.out.println("set old opset using new copy opsetCopy...");
		opset.setOptions(copyOpts);
		System.out.println("setted old copyset: ");
		opset.printInfo();
		System.out.println("set old opset using set by index and Option");
		Option setOpt = new Option("random", 102);
		opset.setOption(123, setOpt);
		System.out.println("after set by index out of bound:");
		opset.printInfo();
		System.out.println("after set by index in bound: (random, 102)");
		opset.setOption(1, setOpt);
		opset.printInfo();
	}
}
