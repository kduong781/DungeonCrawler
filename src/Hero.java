import java.awt.*;
/**
 * Methods and data members for the Hero Class
 */
import java.io.*;
import java.util.*;
public class Hero extends Character implements Serializable{

	/*
	 * List of items the hero currenty has
	 */
	private ArrayList<Item> item = new ArrayList<Item>();
	/*
	 *location of the hero
	 */
	private Point location;
	/*
	 * Heros starting hp 
	 */
	private static int h = 30;
	/**
	 * constructor for the hero
	 * @param n name 
	 * @param q quip
	 * @param start 
	 */
	Hero(String n, String q, Point start){
		super(n,q, h, 1, 25);
		location = start;
		
	}
	/**
	 *returns maximum hp hero can have at each level( used for health potions)
	 * @return hp scaled according to level
	 */
	public int maxHp(){
		return 30*getLevel();
	}
	/**
	 * Checks if potion is in itemList
	 * @return true if health potion is in inventory/ false if not
	 */
	public boolean checkPotion(){
		boolean check = false;
		for(Item i: item){ // for each loop goes through each item in heroes inventory
			if(i.getName().equals("Health Potion"));{ // if there is ahealth potion return true
				check = true;
			}
		}
		return check;
	}
	/**
	 * displays the items that the hero currently has
	 */
	public void displayInv(){
		int x = 1;
		for(Item i: item){ // goes through item arrayList
			System.out.println(x + ". "+i.getName());
			x++;
		}
	}
	/**
	 * Displays items at the element specified by the user
	 * @param answer // element
	 * @return // returns the item at the element
	 */
	public Item displayItem(int answer){
		return item.get(answer);
	}

	/**
	 * gets item when hero kills a minion or when he walks into a room with an item
	 * @param i item recieved
	 * @return returns the item recieved into the arrayList
	 */
	public ArrayList<Item> getItem(Item i){
		if(item.size()<5){ // limits maximum item to 5
		item.add(i);
		}else{
			System.out.println("Your inventory is full!");
		}
		return item;
	
	}
	/**
	 * Used to check if user wants to pick up item
	 * @param i
	 * @return
	 */
	public boolean pickUpItem(Item i){
		return true;
	}
	/**
	 * removes item specified by user 
	 * @param i (specified by Type: Item)
	 */
	public void removeItem(Item i){
		item.remove(i);
	}
	/**
	 * removes item specfied by user
	 * @param index element where item is in the arrayList
	 */
	public void removeItem(int index){
		item.remove(index);
	}
	/**
	 * returns location of the hero
	 * @param p Point p
	 * @return returns location
	 */
	public Point getLocation(Point p){
		return location;
	}
	/**
	 * set location of hero on the map
	 * @param p
	 */
	public void setLocation(Point p){
		location = p;
	}
	/**
	 * moves the hero north
	 * @param l Level
	 * @return returns temporary char
	 */
	public char goNorth(Level l){
		Point p = location;
		char temp = l.level[(int)p.getX()-1][(int)p.getY()];
		l.level[(int)p.getX()-1][(int)p.getY()] = '*';
		location.setLocation(p.getX()-1, p.getY());
		return temp;
	}	
	/**
	 * moves the hero south
	 * @param l Level
	 * @return returns temporary char
	 */
	public char goSouth(Level l){
		Point p = location;
		char temp = l.level[(int)p.getX()+1][(int)p.getY()];
		l.level[(int)p.getX()+1][(int)p.getY()] = '*';
		location.setLocation(p.getX()+1, p.getY());
		return temp;
	}
	/**
	 * moves the hero east
	 * @param l Level
	 * @return returns temporary char
	 */
	public char goEast(Level l){
		Point p = location;
		char temp = l.level[(int)p.getX()][(int)p.getY()+1];
		l.level[(int)p.getX()][(int)p.getY()+1] = '*';
		location.setLocation(p.getX(), p.getY()+1);
		return temp;
	}
	
	/**
	 * moves the hero west
	 * @param l Level
	 * @return returns temporary char
	 */
	public char goWest(Level l){
		Point p = location;
		char temp = l.level[(int)p.getX()][(int)p.getY()-1];
		l.level[(int)p.getX()][(int)p.getY()-1] = '*';
		location.setLocation(p.getX(), p.getY()-1);
		return temp;
	}
	
	/**
	 * Attacks the character specified
	 *@param c character being attacked
	 */
	@Override
	public void attack(Character c){
		// TODO Auto-generated method stub
		Random r = new Random();
		int random = r.nextInt(3)+1;
		Item item = new Item("",0);
		System.out.println(getName() +" hits " + c.getName() +" for " + random + " damage");
		c.takeDamage(random);
		if(c.getHp()<=0){
			System.out.println(getName() + " has killed an " + c.getName());
			System.out.println(getName() + " has recieved " + c.getGold() + " gold");
			System.out.println(c.getName() +" had been defeated and says " + c.getQuip());
				
			collectGold(c.getGold());

		}
		
	}

}
