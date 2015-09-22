/**
 * @author zhexinq
 * Empty class that just deals with interfaces
 */
package adapter;

public class BuildAuto extends ProxyAutomobile implements CreateAuto, UpdateAuto {
	
	/* driver for testing BuildAuto API's methods */
	public static void main(String[] args) {
		BuildAuto auto1 = new BuildAuto();
		auto1.buildAuto("ford.txt");
		auto1.printAuto("Focus Wagon ZTW");
		System.out.println();
		auto1.updateOptionSetName("Focus Wagon ZTW", "Color", "Colorrrr");
		auto1.printAuto("ocus Wagon ZTW");
		System.out.println();
		BuildAuto auto2 = new BuildAuto();	
		auto2.updateOptionPrice("Focus Wagon ZTW", "Power Moonroof", "present", 789);
		auto2.printAuto("Focus Wagon ZTW");
	}
}
