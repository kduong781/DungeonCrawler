import java.io.*;
/**
 * Methods and data members of the Character class
 * @author KevinD
 *
 */
public abstract class Character implements Serializable {
	/**
	 * name and catchprhase
	 */
	private String name, quip;
	/**
	 * level of the character
	 */
	private int level;
	/**
	 * hp of the character
	 */
	private int hp;
	/**
	 * how much gold the user has
	 */
	private int gold;
	/**
	 * Constructor of the CHARACTER class
	 * @param n name
	 * @param q quip
	 * @param h hp
	 * @param l level
	 * @param g gold
	 */
	public Character(String n, String q, int h, int l, int g){
		name = n;
		quip = q;
		gold = g;
		level = l;
		hp = h;
	}
	/**
	 * abstract void class which forces all subclasses to create this method
	 * @param c
	 */
	public abstract void attack(Character c);
	/**
	 * returns the name of the character
	 * @return
	 */
	public String getName(){
		return name;
		
	}
	/**
	 * returns the catchprhase of the character
	 * @return
	 */
	public String getQuip(){
		return quip;
	}
	/**
	 * returns the hp of the user
	 * @return
	 */
	public int getHp(){
		return hp;
	}
	/**
	 * returns the level of the user
	 * @return level
	 */
	public int getLevel(){
		return level;
	}
	/**
	 * returns how much gold the character has
	 * @return gold
	 */
	public int getGold(){
		return gold;
	}
	/**
	 * increases the level of the hero
	 */
	public void increaseLevel(){
		level++;
	}
	/**
	 * adds h to the current hp of the character
	 * @param h the amount of hp wanted to be added
	 */
	public void heal(int h){
		hp = hp +h;
	}
	/**
	 * decreases h to the current hp of the character
	 * @param h the amount of hp wanted to be subtracted
	 */
	public void takeDamage(int h){
		hp = hp - h;
		if(hp<=0){
			hp = 0;
		}
	}
	/**
	 * adds gold to the users wallet
	 * @param g gold
	 */
	public void collectGold(int g){
		gold = gold + g;
	}
	/**
	 * does nothing
	 */
	public void display(){
		
	}
}
