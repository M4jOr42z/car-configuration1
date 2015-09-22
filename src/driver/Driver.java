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
import adapter.BuildAuto;
import exception.AutoException;

public class Driver {
	
	/* exercise API and test exeception handler */
	public static void main(String[] args) {
		/* exercise API */
		System.out.println("Test API functions");
		System.out.println("build a ford auto:");
		BuildAuto auto1 = new BuildAuto();
		auto1.buildAuto("ford.txt");
		System.out.println("Update OptionSet Name.");
		auto1.updateOptionSetName("Focus Wagon ZTW", "Color", "Colour");
		System.out.println("Update Option Price.");
		auto1.updateOptionPrice("Focus Wagon ZTW", "Power Moonroof", "present", 789);
		auto1.printAuto("Focus Wagon ZTW");
		
		/* test wrong input filename exception */
		System.out.println("\ntest wrong input filename exception");
		BuildAuto auto2 = new BuildAuto();
		auto2.buildAuto("xyz.txt");
		auto2.printAuto("Focus Wagon ZTW");
		
		/* test malformed header */
		System.out.println("\ntest malformed header exception");
		auto2.buildAuto("malfomred_hdr.txt");
		auto2.printAuto("Focus Wagon ZTW");
		
		/* test missing base price */
		System.out.println("\ntest missing base price exception");
		auto2.buildAuto("missing_base_price.txt");
		auto2.printAuto("Focus Wagon ZTW");
		
		/* test missing optionset size */
		System.out.println("\ntest missing optioset size exception");
		auto2.buildAuto("missing_optonset_size.txt");
		auto2.printAuto("Focus Wagon ZTW");
		
		/* test missing missing option price */
		System.out.println("\ntest missing option price exception");
		auto2.buildAuto("missing_option_price.txt");
		auto2.printAuto("Focus Wagon ZTW");
		
	}

}
