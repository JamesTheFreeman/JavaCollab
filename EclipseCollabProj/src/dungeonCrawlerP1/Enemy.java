/**
 * @author - James Kent
 * Class for construction of enemies, etc.
 * Can be extended for bosses or whatever
 */

package dungeonCrawlerP1;

public class Enemy
{
	private String name;	// Enemy/type name
	private String desc;	// Enemy/type description
	private int HP, maxHP;	// HP/max of given enemy
	private int type;		// Enemy type ID (for txt)
	
	/**
	 * Default constructor, makes enemy based on provided ID
	 * @param x		Enemy type ID to be used
	 */
	public Enemy(int x)
	{
		type = x;
		// Add scanner to pull stats/info from txt
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
}
