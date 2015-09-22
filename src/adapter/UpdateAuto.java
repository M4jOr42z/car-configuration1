/**
 * @author zhexinq
 * Interface provides contracts for updating Auto object's OptionSet name,
 * and Option price.
 */

package adapter;

public interface UpdateAuto {
	public void updateOptionSetName(String Modelname, String setName, String newName);
	public void updateOptionPrice(String Modelname, String setName, String optionName, int newprice);
}
