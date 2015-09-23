/**
 * @author zhexinq
 * Driver class for testing the model and util packages
 * 				- populate an Automotive object using .txt file
 * 				- serialize the object to .ser file
 * 				- deserialize the object and read it to memory
 * 				- print new attributes
 */

package driver;

import adapter.BuildAuto;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Driver {
	
	/* exercise API and test exeception handler */
	public static void main(String[] args) {
		/* exercise API */
		System.out.println("Test API functions");
		System.out.println("build a ford auto:");
		String modelName = "Focus Wagon ZTW";
		BuildAuto auto1 = new BuildAuto();
		auto1.buildAuto("ford.txt");
		auto1.printAuto(modelName);
		auto1.updateOptionSetName(modelName, "Color", "Colour");
		auto1.updateOptionPrice(modelName, "Colour", "CD Silver Clearcoat Metallic", 
								100);
		auto1.printAuto(modelName);

		/* select options */
		System.out.println("\ntest the selection&show price");
		auto1.makeOptionChoice(modelName, "Colour", "Liquid Grey Clearcoat Metallic");
		auto1.makeOptionChoice(modelName, "Transmission", "manual");
		auto1.makeOptionChoice(modelName, "Brakes/Traction Control", "ABS with Advance Trac");
		auto1.makeOptionChoice(modelName, "Side Impact Air Bags", "present");
		auto1.makeOptionChoice(modelName, "Power Moonroof", "present");
		auto1.printAuto(modelName);
		
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
		
		/* test missing missing option price */
		System.out.println("\ntest missing option price exception");
		auto2.buildAuto("missing_option_price.txt");
		auto2.printAuto("Focus Wagon ZTW");
	}

}
