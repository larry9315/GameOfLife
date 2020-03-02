/**
Plant class that can seed and reproduce to different cell if the surrounding number of plants is
4 and blank cell is >= 3. It inherits Lifeform.
 * 
 */
package a2b;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author larry
 *
 */
public class Plant extends LifeForm implements HerbivoreEddible, OmnivoreEddible, Serializable {

	/**
	 * 
	 */
	public Plant(Cell cell) {
		// TODO Auto-generated constructor stub
		super(cell);
	}

	
	private void seed(Cell[][] board) {
		if (board[xCoordinate][yCoordinate].getLifeForm().getNeighborPlantCells(board).size() >= 2) {
			ArrayList<Cell> list = board[xCoordinate][yCoordinate].getLifeForm().getEmptyNeighborCells(board);					
			if (list.size() >= 3) {
				int n = RandomGenerator.nextNumber(list.size());
				board[list.get(n).getX()][list.get(n).getY()].setSeed(true);
			}
			
		}
	}

		
	public void reproduce(Cell[][] board) {
		seed(board);
	}

	@Override
	public void move(Cell[][] board) {
	}


	@Override
	public ArrayList<Cell> getFoodNeighborCells(Cell[][] board) {
		return null;
	}


	@Override
	public ArrayList<Cell> choosePositionCells(Cell[][] board) {
		// TODO Auto-generated method stub
		return null;
	}



	
	

}
