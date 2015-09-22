/**
 * @author zhexinq
 * Interface for providing contracts for building an Auto object
 * and print Auto object info
 */

package adapter;

public interface CreateAuto {
	public void buildAuto(String filename);
	public void printAuto(String modelName);
	
}
