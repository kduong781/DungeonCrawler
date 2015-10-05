import java.io.Serializable;

/**
 * Methods and data members of the Item class
 * @author KevinD
 *
 */
public class Item implements Serializable{
	/**
	 * name of the item
	 */
	String name;
	/**
	 * value of the item in gold
	 */
	int goldValue;
	/**
	 * Constructor of the Item class
	 * @param n name 
	 * @param v value
	 */
	public Item(String n, int v){
		name = n;
		goldValue = v;
	}
	/**
	 * gets the name of the item
	 * @return name
	 */
	public String getName(){
		return name;
	}
	/**
	 * gets the value of the item
	 * @return goldValue
	 */
	public int getValue(){
		return goldValue;
	}
}
