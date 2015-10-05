import java.util.*;
import java.io.*;
/**
 * Methods and Data Members of the Enemy Class
 * @author KevinD
 *
 */
public class Enemy extends Character{
	/**
	 *Random Item enemy has
	 */
	private Item item;
	/**
	 * name of the enemy
	 */
	private String name;
	/**
	 * Constructor in the Enemy class
	 * @param n name
	 * @param q quip
	 * @param h hp
	 * @param l level
	 * @param g gold
	 * @param i item
	 */
	Enemy(String n, String q, int h, int l, int g, Item i){
		super(n,q,h,l,g);
		item = i;
		name = n;
		h = h*l;
	}
	/**
	 * returns the item that the enemy has
	 * @return item
	 */
	public Item getItem(){
		return item;
	}
	@Override
	/**
	 * attacks the character specified by the enemy
	 */
	public void attack(Character c) {
		Random r = new Random();
		int random = r.nextInt(2)+1;
		System.out.println(name +" hits " + c.getName() +" for " + random + " damage");
		c.takeDamage(random);
		if(c.getHp()<=0){
			System.out.println("You Lose! Restart the program to load your previous checkpoint!");
			
		}
		
		// TODO Auto-generated method stub
		
	}
}
