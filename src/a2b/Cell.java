/**
Cell class that has a LifeForm class objects and information on what that cell has such as x and y coordinates,
information about herbivore and plants and seeded cell.
 * 
 */
package a2b;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author larry
 *
 */
public class Cell implements Serializable {

	private int xCoordinate;
	private int yCoordinate;
	
	private LifeForm lifeForm;

	
	private boolean isSeed;
		/**
	 * 
	 */
	public Cell(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate; 
		this.yCoordinate = yCoordinate;
		lifeForm = null;

		isSeed = false;
	}
	
	public LifeForm getLifeForm() {
		return lifeForm;
	}
	
	public void setLifeForm(LifeForm lifeForm) {
		this.lifeForm = lifeForm;
	}
	
	public void setHerbivore(boolean truthValue, int steps) {
		if (truthValue) {
			lifeForm = new Herbivore(this, steps);
			isSeed = false;
		}
		else {
			lifeForm = null;
		}

	}
	
	public void setPlant(boolean truthValue) {
		if (truthValue) {
			lifeForm = new Plant(this);
		} else {
			lifeForm = null;
		}

		
	}
	
	
	
	public int getX() {
		return xCoordinate;
	}
	
	public int getY() {
		return yCoordinate;
	}

	public boolean getSeed() {
		return isSeed;
	}

	public void setSeed(boolean isSeed) {
		this.isSeed = isSeed;
	}
	
	public Rectangle draw(int i, int j) {
		Rectangle rectangle;
		if (lifeForm == null) {
			rectangle = new Rectangle(i * 10, j * 10, 10, 10);
		
			rectangle.setFill(Color.WHITE);
			rectangle.setStroke(Color.BLACK);
		} else if (lifeForm instanceof Herbivore) {
			rectangle = new Rectangle(i * 10, j * 10, 10, 10);
			
			rectangle.setFill(Color.YELLOW);
			rectangle.setStroke(Color.BLACK);
		} else if (lifeForm instanceof Plant) {
			rectangle = new Rectangle(i * 10, j * 10, 10, 10);
			
			rectangle.setFill(Color.GREEN);
			rectangle.setStroke(Color.BLACK);
		} else if (lifeForm instanceof Carnivore){
			rectangle = new Rectangle(i * 10, j * 10, 10, 10);
			
			rectangle.setFill(Color.RED);
			rectangle.setStroke(Color.BLACK);
		} else {
			rectangle = new Rectangle(i * 10, j * 10, 10, 10);
			
			rectangle.setFill(Color.BLUE);
			rectangle.setStroke(Color.BLACK);
		}
		
		return rectangle;
	}
	
	public void addLife(LifeForm lifeForm) {
		this.lifeForm = lifeForm;
		if (!(lifeForm instanceof Plant)) {
			isSeed = false;
		}
	}

}
