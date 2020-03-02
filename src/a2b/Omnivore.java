/*
 * Omnivore class that moves around the 2d array of cell class when move Omnivore is called. It eats
 * fellow Carnivore, Herbivore and Plant objects. It also gives birth Where there is 1 other Omnivore
 * , 3 free neighboring cells and 1 neighboring cells with food.
 */
package a2b;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Omnivore extends LifeForm implements CarnivoreEddible, Serializable {

	private int life;
	
	public Omnivore(Cell cell, int life) {
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

			
			if (board[xCoordinate][yCoordinate].getLifeForm() instanceof HerbivoreEddible
					|| board[xCoordinate][yCoordinate].getLifeForm() instanceof Herbivore
					|| board[xCoordinate][yCoordinate].getLifeForm() instanceof Carnivore
					|| board[xCoordinate][yCoordinate].getLifeForm() instanceof Omnivore) {
				life = 5;
			} else {
				life--;
			}
			
			
		
			board[xCoordinate][yCoordinate].setLifeForm(new Omnivore(board[xCoordinate][yCoordinate], life));
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
								|| (board[xCoordinate + i][yCoordinate + j].getLifeForm() instanceof HerbivoreEddible)
								|| (board[xCoordinate + i][yCoordinate + j].getLifeForm() instanceof Herbivore)
								|| (board[xCoordinate + i][yCoordinate + j].getLifeForm() instanceof Carnivore)) {
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
		ArrayList<Cell> list = new ArrayList<Cell>();
		
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					if (isValid(xCoordinate + i, yCoordinate + j)) {
						if ((board[xCoordinate + i][yCoordinate + j].getLifeForm() instanceof HerbivoreEddible)
								|| (board[xCoordinate + i][yCoordinate + j].getLifeForm() instanceof Herbivore)
								|| (board[xCoordinate + i][yCoordinate + j].getLifeForm() instanceof Carnivore)) {
							list.add(new Cell(xCoordinate + i, yCoordinate + j));
						}
					}
				}
			}
		}
		
		
		
		return list;
	}
	
	public void reproduce(Cell[][] board) {
		if (board[xCoordinate][yCoordinate].getLifeForm().getNeighborOmnivoreCells(board).size() >= 1) {
			if (board[xCoordinate][yCoordinate].getLifeForm().getFoodNeighborCells(board).size() == 1) {
				ArrayList<Cell> list = board[xCoordinate][yCoordinate].getLifeForm().getEmptyNeighborCells(board);					
				if (list.size() >= 3) {
					int n = RandomGenerator.nextNumber(list.size());
					board[list.get(n).getX()][list.get(n).getY()].setLifeForm(new Omnivore(board[list.get(n).getX()][list.get(n).getY()], 5));
					board[list.get(n).getX()][list.get(n).getY()].setSeed(false);
				}
			}
		}
	}

}
