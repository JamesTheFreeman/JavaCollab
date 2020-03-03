/**
 * @author - James Kent
 * Class for construction of enemies, etc.
 * Can be extended for bosses or whatever
 */

package dungeonCrawlerP1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Enemy
{
	private String Name = "n";	// Enemy/type name
	private String desc;		// Enemy/type description
	private int HP, maxHP;		// HP/max of given enemy
	private int type;			// Enemy type ID (for txt)
	private int attack = -1;	// Max attack value
	
	/**
	 * Default constructor, makes enemy based on provided ID
	 * @param x		Enemy type ID to be used
	 */
	public Enemy(int x)
	{
		type = x; // Enemy type ID
		
		Scanner stat = null;
		Scanner name = null;
		// Try/catch to handle possible error(s)
		try
		{
			stat = new Scanner(new File("enemystats.txt"));
			name = new Scanner(new File("enemydesc.txt"));
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
}
