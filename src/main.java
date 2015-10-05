import java.util.*;
import java.awt.*;
import java.io.*;
/**
 * Methods that run the main of the program
 * @author KevinD
 *
 */
public class main{
	/**
	 * global variable in the main class
	 */
	private static char temp = 'x';
	/**
	 * Main Method that runs the program
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InvalidClassException
	 */
	public static void main(String[]args) throws IOException, ClassNotFoundException, InvalidClassException{
		Scanner s = new Scanner(System.in);
		Point p = new Point(3,0);
		Level l = new Level();
		int answer1 = getAnswer("1. Start a new game\n2. Load Previous Checkpoint",1,2);
		if(answer1==1){
		System.out.println("What is your name, traveler?");
		String name = s.next();
		Hero h = new Hero(name, "Hello", p);
		l.generateLevel(1);
	
		System.out.println(h.getName() +" enters the Dungeon of Despair " + h.getLevel());
		temp = l.getRoom(p);
		l.displayMap(p);
		while(temp!='f'){
		temp = move(h,l,temp,p);
		action(temp,h,l,p);
		l.displayMap(p);
		}
		}else if(answer1 == 2){
			try{
			ObjectInputStream read = new ObjectInputStream(new FileInputStream("hero.dat"));
			Hero h = (Hero) read.readObject();
			read.close();
			System.out.println("Loading file..");
			run(h);
			}catch(IOException e){
				System.out.println("Error Processing File");
				System.out.println("Restart the Program!");
			}
			
		}
	}
	/**
	 * Error Checking for all inputs user has inputted
	 * @param st String or Question program wants to ask
	 * @param x Minimum Option
	 * @param y Maximum Option
	 * @return
	 */
	public static int getAnswer(String st,int x,int y){
		System.out.println(st);
		Scanner s = new Scanner(System.in);
		try{
		int ans = s.nextInt();
		if(ans<x && ans>y){
			ans = s.nextInt();
		}
		return ans;
		}catch(Exception e){
			int ans = getAnswer(st,x,y);
			return ans;
		}
	}
	
	/**
	 * Runs the program after file is loaded
	 * @param h Hero
	 * @throws IOException
	 */
public static void run(Hero h) throws IOException{
	Point p = new Point(3,0);
	h.setLocation(p);
	System.out.println("Level " +h.getLevel());
	System.out.println("Hp: " + h.getHp());
	Level l = new Level();
	l.generateLevel(h.getLevel());
	System.out.println(h.getName() +" enters the Dungeon of Despair " + h.getLevel());
	char temp = l.getRoom(p);
	l.displayMap(p);
	while(temp!='/'){
	temp = move(h,l,temp,p);
	action(temp,h,l,p);
	l.displayMap(p);
	}
	
}
/**
 * Does specified action depending on what direction user wants to go
 * @param h Hero
 * @param l Level
 * @param temp temp
 * @param p Point
 * @return
 */
public static char move(Hero h, Level l, char temp, Point p){
	Scanner s = new Scanner(System.in);
	int answer = getAnswer("Choose A Direction\n1. North\n2.South\n3. East\n4. West", 1,4);
	if(p.getX() == 0 && p.getY() == 3){
		p.setLocation(3,0);
		l.level[(int)p.getX()][(int) p.getY()] = 'f';
	}
	while(inBounds(p,answer)== false){
		System.out.println("You have run into a wall.");
		System.out.println("Choose another direction");
		answer = s.nextInt();
	//	move = answer;
	}
	switch (answer){
	case 1:
		l.level[(int)p.getX()][(int)p.getY()] = temp;
		temp = h.goNorth(l);
		return temp;
	case 2:
		l.level[(int)p.getX()][(int)p.getY()] = temp;
		temp = h.goSouth(l);
		return temp;
	case 3:
		l.level[(int)p.getX()][(int)p.getY()] = temp;
		temp = h.goEast(l);
		return temp;
	case 4:
		l.level[(int)p.getX()][(int)p.getY()] = temp;
		temp = h.goWest(l);
		return temp;
	}
	return temp;
}
/**
 * Run Away methods that forces user to move to an adjacent wall
 * @param h
 * @param l
 * @param temp
 * @param p
 * @return
 */
public static char runAway(Hero h, Level l, char temp, Point p){
	Random r = new Random();
	int answer = r.nextInt(4)+1;
	if(p.getX() == 0 && p.getY() == 3){
		p.setLocation(3,0);
		l.level[(int)p.getX()][(int) p.getY()] = 'f';
	}
	while(inBounds(p,answer)== false){ // Keeps checking until ANSWER is in BOUNDS
		answer = r.nextInt()+1;
	//	move = answer;
	}
	switch (answer){
	case 1:
		l.level[(int)p.getX()][(int)p.getY()] = temp;
		temp = h.goNorth(l);
		return temp;
	case 2:
		l.level[(int)p.getX()][(int)p.getY()] = temp;
		temp = h.goSouth(l);
		return temp;
	case 3:
		l.level[(int)p.getX()][(int)p.getY()] = temp;
		temp = h.goEast(l);
		return temp;
	case 4:
		l.level[(int)p.getX()][(int)p.getY()] = temp;
		temp = h.goWest(l);
		return temp;
	}
	return temp;
}
/**
 * Does Action when Hero walks into one of the following doors
 * @param temp // temporarliy stores what door the hero is on 
 * @param h // the Hero
 * @param l // the level
 * @param p // location of hero
 * @throws IOException 
 */
public static void action(char temp, Hero h, Level l, Point p) throws IOException{ // direction to move
	switch (temp) {
	case 's':
		System.out.println("Store");
		System.out.println("What would you like to sell? (Type the name of the item)");
		h.displayInv();
		Scanner s = new Scanner(System.in);
		try{
		int answer = s.nextInt();
		h.collectGold(h.displayItem(answer-1).getValue());
		h.removeItem(answer-1);
		System.out.println("Item has been sold!");
		}catch(InputMismatchException e){
			System.out.println("The store keeper has run away! Try Again later");
			
		}
		
		
		
		break;
	case 'm':
		EnemyGenerator e = new EnemyGenerator();
		Enemy enemy = e.generateEnemy(1);
		System.out.println(h.getName() +" has " + h.getHp() + " health");
		System.out.println(h.getName() +" has encountered a " + enemy.getName());
		System.out.println("It has "+enemy.getHp()+" hp");
		option(enemy,h,p,l);
		break;
	case 'i':
		ItemGenerator i = new ItemGenerator();
		Item item = i.generateItem();
		System.out.println("You found a " + item.getName());
		choice(item,h);
		break;
	case 'f':
		System.out.println("Congratulations! You have found the exit!");
		h.increaseLevel();
		l.generateLevel(h.getLevel());
		h.heal(h.getHp());
		System.out.println(h.getName()+ " is now level " + h.getLevel());
		System.out.println("Saving Progress..");
		ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream("hero.dat"));
		save.writeObject(h);
		save.close();
		run(h);

		break;
	}
}
/**
 * Does specified action depending on user
 * 1. Run Away, 2. Attack, (3. Heal) if potion is in inventory
 * @param enemy
 * @param h
 * @param p
 * @param l
 * @throws FileNotFoundException
 */
public static void option(Enemy enemy, Hero h,Point p, Level l) throws FileNotFoundException{ // attack/run/use item
	if(h.checkPotion()== true){
		System.out.println("3. Heal Your Self");
	}
	Scanner s = new Scanner(System.in);
	int answer = getAnswer("What would you like to do?\n1. Run Away\n2. Attack",1,3);
	switch (answer){
	case 1:
		System.out.println("A " +enemy.getName() +" hits "+h.getName() +" while escaping." );
		enemy.attack(h);
		temp = runAway(h,l,temp,p);
		//if(move)
		break;
	case 2:
		while(enemy.getHp()!=0){
		h.attack(enemy);
		enemy.attack(h);
		System.out.println(h.getName() +" has " + h.getHp() + " health");
		System.out.println(h.getName() +" has encountered a " + enemy.getName());
		System.out.println("It has "+enemy.getHp()+" hp");
		if(enemy.getHp()!=0){
		option(enemy,h,p,l);
		}
		}
		if(enemy.getHp()<=0){
			ItemGenerator i = new ItemGenerator();
			Item item = i.generateItem();
			System.out.println(h.getName() + " has recieved " + item.getName());
			choice(item,h);
		}
		break;
	case 3:
	//	h.remove("Health Potion");
		h.heal(h.maxHp());
		System.out.println("Hero has been healed!");
	}
}
/**
 * Allows user to choose to sell an item or to keep it in inventory
 * @param i item
 * @param h hero
 */
public static void choice(Item i, Hero h){
	Scanner s = new Scanner(System.in);
	int option = getAnswer("What do you do?\n1. Keep It\n2. Sell It",1,2);
	switch (option) {
	case 1:
		h.getItem(i);
		System.out.println(i.getName() + " has been added to your inventory");
		break;
	case 2:
		System.out.println("You sell your " + i.getName() + " for " + i.getValue() + " gold.");
		h.collectGold(i.getValue());
		h.removeItem(i);
		break;
	}
}
/**
 * boolean that checks if the hero's next movement is inbounds
 * @param p Point
 * @param answer users next movement
 * @return
 */
public static boolean inBounds(Point p,int answer){
	boolean check = false;
	switch(answer){
	case 1:
		if((p.getX()-1)< 0 ){
			check = false;
		}else{
			check = true;
		}
		break;
	case 2:
		if((p.getX()+1)>3 ){
			check = false;
		}else{
			check = true;
		}
		break;
	case 3:
		if((p.getY()+1)>3 ){
			check = false;
		}else{
			check = true;
		}
		break;
	case 4:
		if((p.getY()-1)<0 ){
			check = false;
		}else{
			check = true;
		}
		break;
	}
	return check;
}

}