import java.io.*;
 
import java.util.*;
/**
 * Methods and Data members of the ItemGenerator class
 * @author KevinD
 *
 */
public class ItemGenerator {
	/**
	 * list of items that the user currently has
	 */
	private ArrayList<Item> itemList;
	/**
	 * constructor of ItemGenerator that generates items into the arrayList: itemList
	 * @throws FileNotFoundException
	 */
	public ItemGenerator() throws FileNotFoundException{
		
		Scanner s = new Scanner(new File("ItemList.txt"));
		itemList = new ArrayList<Item>();
		Random r = new Random();
		//int random = r.nextInt(8);
		String item = "";
		for(int x = 0; x<=8; x++){
			item = s.nextLine();
	
		Scanner s1 = new Scanner(item).useDelimiter(",");
		try{
		String name = s1.next();
		int value = Integer.parseInt(s1.next());
		Item itemFinal = new Item(name,value);
		itemList.add(itemFinal);
		}catch(Exception e){
		}
		}
		}

	
	/**
	 * Randomly picks a item from the itemList
	 * @return a random item
	 * @throws FileNotFoundException
	 */
	Item generateItem() throws FileNotFoundException{
		Random r = new Random();
		int random = r.nextInt(8);
		try{
		return itemList.get(random);
		}catch(Exception e){
			return generateItem();
		}
		
		/*Scanner s = new Scanner(new File("ItemList.txt"));
		Random r = new Random();
		int random = r.nextInt(8);
		String item = "";
		for(int x = 0; x<=random; x++){
			item = s.nextLine();
		}
	
		Scanner s1 = new Scanner(item).useDelimiter(",");
		try{
		String name = s1.next();
		int value = Integer.parseInt(s1.next());
		Item itemFinal = new Item(name,value);
		return itemFinal;
		}catch(Exception e){
			Item itemFinal = generateItem();
			return itemFinal;
		}*///
	}
}
