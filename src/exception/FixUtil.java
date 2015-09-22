/**
 * @author zhexinq
 * helper class to fix all exceptions raised in util package
 */

package exception;

import java.io.Console;

public class FixUtil {
	
	/* fix error when the input file cannot be found or opened */
	// ask the user to retype a correct filename
	public String fixFileNotFoundException() {
		return null;
	}
	/* fix error when input file header is malformed */
	// e.g. not in the form "description: model, optionset size, base price"
	public void fixMalformedHeader() {
		System.out.println("Load input file failed, please check the input header is in right form");
	}
	/* fix when no base price provided */
	// give a deault price when no provided
	public String fixMissingBasePrice() {
		System.out.println("missing base price in the input file, please enter one");
		return null;
	}
	/* fix when no OptionSets size provided */
	public String fixMissingOptionSetsSize() {
		System.out.println("missing OptionSets size, please enter one");
		return null;
	}
	/* fix error when OptionSet data is malformed */
	// not in the form "xxxx:" or number of ',' is even
	// skip to next entry
	public void fixMalformedOptionSetEntry() {
		System.out.println("malformed OptionSet entry, skip to the next");
	}
	/* fix error when Option price is missing */
	public String fixMissingOptionPrice() {
		return null;
	}

}
