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
		type = x;
		
		Scanner stat = null;
		Scanner name = null;
		// Try/catch to handle possible error
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
		
		String y;
		
		// Gets enemy stats
		while (stat.hasNext())
		{
			y = stat.next();
			if (Integer.parseInt(y) == type)
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
		
		// Gets enemy name/desc
		while (name.hasNext())
		{
			y = name.next();
			if (Integer.parseInt(y) == type)
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
			y = name.nextLine();
			y = name.nextLine();
		}
		
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
		stat.close();
		name.close();
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
