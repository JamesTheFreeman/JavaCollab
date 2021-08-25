/**
 * @author - James Kent
 * Class for construction of enemies, etc.
 * Can be extended for bosses or whatever
 */

package dungeonCrawlerP1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Enemy
{
	private String Name = "n";	// Enemy/type name
	private String desc;		// Enemy/type description
	private int HP, maxHP;		// HP/max of given enemy
	private int attack = -1;	// Max attack value

	String workingDir = System.getProperty("user.dir") + "\\"; // Working directory for filepaths
	
	/**
	 * Default constructor, makes enemy based on provided ID
	 * @param x		Enemy type ID to be used
	 */
	public Enemy(int x)
	{	
		Scanner stat = null;
		Scanner name = null;
		// Try/catch to handle possible error(s)
		try
		{
			stat = new Scanner(new File(workingDir + "enemystats.txt"));
			name = new Scanner(new File(workingDir + "enemydesc.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Err: enemy file not found");
			System.exit(1);
		}
		
		String y; // For holding scanner values
		
		// Gets enemy stats from "enemystats.txt"
		while (stat.hasNext())
		{
			y = stat.next();
			if (Integer.parseInt(y) == x)
			{
				HP = stat.nextInt();
				maxHP = HP;
				attack = stat.nextInt();
				break;
			}
			else if (Integer.parseInt(y) == 0)
			{
				System.out.println(stat.nextLine().trim());
				System.exit(1);
			}
			y = stat.nextLine();
		}
		stat.close();
		
		// Gets enemy name/desc from "enemydesc.txt"
		while (name.hasNext())
		{
			y = name.next();
			if (Integer.parseInt(y) == x)
			{
				Name = name.nextLine().trim();
				desc = name.nextLine().trim();
				break;
			}
			else if (Integer.parseInt(y) == 0)
			{
				System.out.println(name.nextLine().trim());
				System.exit(1);
			}
			// Skips rest of current line & next line
			y = name.nextLine();
			y = name.nextLine();
		}
		name.close();
		
		// Recognizes that the enemy hasn't been built correctly if these values aren't replaced
		if (attack == -1 || Name.equals("n"))
		{
			System.out.println("Err: Initialization of enemy type " + x + " unsuccessful");
			if (attack == -1)
			{
				if (Name.equals("n"))
				{
					System.out.println("Stat & desc retrieval both failed");
					System.exit(1);
				}
				System.out.println("Stat retrieval failed");
				System.exit(1);
			}
			else if (Name.equals("n"))
			{
				System.out.println("Desc retrieval failed");
				System.exit(1);
			}
		}
	}
	
	// METHODS
	
	/**
	 * Get method for enemy description
	 * @return Enemy description
	 */
	public String getDesc()
	{
		return desc;
	}
	/**
	 * Get method for enemy name
	 * @return Enemy name
	 */
	public String getName()
	{
		return Name;
	}
	
	/**
	 * Displays current health of enemy
	 */
	public void displayHealthNum()
	{
		System.out.print(HP + "/" + maxHP);
	}
	
	/**
	 * Prints enemy HP bar, reducing values to reduce bar size if needed
	 */
	public void displayHealthBar()
	{
		int reducedMax = maxHP;
		int reducedHP = HP;
		String reduced = "";
		if (maxHP > 50)
		{
			reducedMax /= 2;
			reducedHP /= 2;
			reduced = " x2"; // Indicates health bar has been reduced
		}
		String HPBar = "[";
		for (int i = 1; i <= reducedMax; i++)
		{
			if (i <= reducedHP)
				HPBar += "o";
			else
				HPBar += " ";
		}
		HPBar += "]";
		
		System.out.print(HPBar + reduced);
	}
	
	/**
	 * Serves as enemy attack, pulling random damage number <= their max attack
	 * @return Value of (this) attack roll
	 */
	public int attackP()
	{
		Random roll = new Random();
		return roll.nextInt(attack) + 1;
	}
	
	/**
	 * Serves as method for dealing damage to enemy, subtracting int from HP
	 * @param dmg Int to be subtracted from HP
	 */
	public void takeDamage(int dmg)
	{
		HP -= dmg;
	}
}
