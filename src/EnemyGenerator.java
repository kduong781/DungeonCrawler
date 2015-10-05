import java.io.File;

import java.io.FileNotFoundException;
import java.util.*;
/**
 * Methods and Data members of the ItemGenerator class
 * @author KevinD
 *
 */
public class EnemyGenerator {
	/**
	 * list of items that the user currently has
	 */
	private ArrayList<Enemy> enemyList;
	/**
	 * constructor of EnemyGenerator that generates items into the arrayList: enemyList
	 * @throws FileNotFoundException
	 */
	EnemyGenerator() throws FileNotFoundException{
		
		enemyList = new ArrayList<Enemy>();
		Scanner s = new Scanner(new File("EnemyList.txt"));
		Random r = new Random();
		int random = r.nextInt(6);
		String item = "";
		for(int x = 0; x<=6; x++){
			item = s.nextLine();
		Scanner s1 = new Scanner(item).useDelimiter(",");
		try{
		String name = s1.next();
		String quip = s1.next();
		int hp = Integer.parseInt(s1.next());
		Item v = new Item("324",2);
		Enemy enemyFinal = new Enemy(name,quip,hp,1,5,v);
		enemyList.add(enemyFinal);
		}catch(Exception e){
			
		}
		}
		
}
	/**
	 * Randomly picks a item from the itemList
	 * @return a random item
	 * @throws FileNotFoundException
	 */
	Enemy generateEnemy(int level) throws FileNotFoundException{
		Random r = new Random();
		int random = r.nextInt(6);
		try{
		return enemyList.get(random);
		}catch(Exception e){
			return generateEnemy(level);
		}
	/*	Scanner s = new Scanner(new File("EnemyList.txt"));
		Random r = new Random();
		int random = r.nextInt(6);
		String item = "";
		for(int x = 0; x<=random; x++){
			item = s.nextLine();
		}
		Scanner s1 = new Scanner(item).useDelimiter(",");
		try{
		String name = s1.next();
		String quip = s1.next();
		int hp = Integer.parseInt(s1.next());
		Item x = new Item("324",2);
		Enemy enemyFinal = new Enemy(name,quip,hp,1,5,x);
		return enemyFinal;
		}catch(Exception e){
			Enemy enemyFinal = generateEnemy(level);
			return enemyFinal;
		}

	}
	*/
}
}

