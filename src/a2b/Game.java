/**
Game class that controls turns with play game method. It first initializes the herbivore movement status to false,
then moves the animals, reproduce lifeforms which include Omnivore, Carnivore, Herbivore and Plant according to their
set rules.
 * 
 */
package a2b;

/**
 * @author larry
 *
 */
public class Game {

	private World world;
	/**
	 * 
	 */
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public Game() {
		// TODO Auto-generated constructor stub
		world = new World();
	}
	
	public void playGame() {
		world.initializeMove();
		
		world.moveLifeform();
		world.reproduceLifeform();
		world.reproducePlants();
		
		
	}
	

}
