/**
Herbivore class that moves around the 2d array of cell class when move herbivore is called.
Each turn, herbivores move around the cell by instantiating a new herbivore to the neighbor cell and deallocating
the current position of the cell. It inherits Lifeform. It gives birth if there is at least 1 other 
Herbivore neighbors, at least two free neighboring cells, and at least 2 neighboring cells with food.
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


public class Herbivore extends LifeForm implements CarnivoreEddible, OmnivoreEddible, Serializable{
	
	private int life;

			
	/**
	 * 
	 */
	public Herbivore(Cell cell, int life) {
		super(cell);
		this.life = life;
		hasMoved = true;
	}
	
	public void move(Cell[][] board) {
		
		if (!hasMoved && life > 0) {
			board[xCoordinate][yCoordinate].setLifeForm(null);
			
			
			ArrayList<Cell> cellList = getFoodNeighborCells(board);
			if (!cellList.isEmpty()) {
				int n = RandomGenerator.nextNumber(cellList.size());
				
				xCoordinate = cellList.get(n).getX();
				yCoordinate = cellList.get(n).getY();
				
			} else {
				ArrayList<Cell> emptyList = getEmptyNeighborCells(board);
				if (!emptyList.isEmpty()) {
					int n = RandomGenerator.nextNumber(emptyList.size());
					xCoordinate = emptyList.get(n).getX();
					yCoordinate = emptyList.get(n).getY();
				}
			}


			
			if (board[xCoordinate][yCoordinate].getLifeForm() instanceof Plant) {
				life = 5;
			} else {
				life--;
			}
			
			
		
			board[xCoordinate][yCoordinate].setLifeForm(new Herbivore(board[xCoordinate][yCoordinate], life));
			board[xCoordinate][yCoordinate].setSeed(false);
		}
		
		if (life == 0) {
			board[xCoordinate][yCoordinate].setLifeForm(null);
		}

		
	}
	
	public ArrayList<Cell> choosePositionCells(Cell[][] board) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					if (isValid(xCoordinate + i, yCoordinate + j)) {
						if (board[xCoordinate + i][yCoordinate + j].getLifeForm() == null
								|| (board[xCoordinate + i][yCoordinate + j].getLifeForm() instanceof HerbivoreEddible)) {
							list.add(new Cell(xCoordinate + i, yCoordinate + j));
						}
					}
				}
			}
		}
		
		
		
		return list;
		
	}

	@Override
	public ArrayList<Cell> getFoodNeighborCells(Cell[][] board) {
		ArrayList<Cell> list = getNeighborPlantCells(board);
		
		return list;
	}
	
	public void reproduce(Cell[][] board) {
		
		if (board[xCoordinate][yCoordinate].getLifeForm().getNeighborHerbivoreCells(board).size() >= 1) {
			if (board[xCoordinate][yCoordinate].getLifeForm().getFoodNeighborCells(board).size() >= 2) {
				ArrayList<Cell> list = board[xCoordinate][yCoordinate].getLifeForm().getEmptyNeighborCells(board);					
				if (list.size() >= 2) {

					int n = RandomGenerator.nextNumber(list.size());
					board[list.get(n).getX()][list.get(n).getY()].setLifeForm(new Herbivore(board[list.get(n).getX()][list.get(n).getY()], 5));
					board[list.get(n).getX()][list.get(n).getY()].setSeed(false);
				}
			}
		}
	}

}
