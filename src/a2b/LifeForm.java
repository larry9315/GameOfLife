/**
 * Lifeform abstract class that contains coordinates of the x and y coordinate of the cell. 
 */
package a2b;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public abstract class LifeForm implements Serializable {
	
	protected int xCoordinate;
	protected int yCoordinate;
	
	protected boolean hasMoved;

	public LifeForm(Cell cell) {
		this.xCoordinate = cell.getX();
		this.yCoordinate = cell.getY();
	}
	
	protected boolean isValid(int x, int y) {
		return x >= 0 && x <= 49 && y >= 0 && y <= 49;
	}
	
	public void move(Cell[][] board) {

	}
	
	public ArrayList<Cell> getNeighborPlantCells(Cell[][] board) {
		int count = 0;
		ArrayList<Cell> list = new ArrayList<Cell>();
		
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					if (isValid(xCoordinate + i, yCoordinate + j)) {
						if (board[xCoordinate + i][yCoordinate + j].getLifeForm() instanceof Plant) {
							list.add(new Cell(xCoordinate + i, yCoordinate + j));
						}
					}
				}
			}
		}
		
		return list;	
	}
	
	public ArrayList<Cell> getNeighborHerbivoreCells(Cell[][] board) {
		
		ArrayList<Cell> list = new ArrayList<Cell>();
		
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					if (isValid(xCoordinate + i, yCoordinate + j)) {
						if (board[xCoordinate + i][yCoordinate + j].getLifeForm() instanceof Herbivore) {
							list.add(new Cell(xCoordinate + i, yCoordinate + j));
						}
					}
				}
			}
		}
		
		return list;	
	}
	
	public ArrayList<Cell> getNeighborCarnivoreCells(Cell[][] board) {

		ArrayList<Cell> list = new ArrayList<Cell>();
		
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					if (isValid(xCoordinate + i, yCoordinate + j)) {
						if (board[xCoordinate + i][yCoordinate + j].getLifeForm() instanceof Carnivore) {
							list.add(new Cell(xCoordinate + i, yCoordinate + j));
						}
					}
				}
			}
		}
		
		return list;	
	}
	
	public ArrayList<Cell> getNeighborOmnivoreCells(Cell[][] board) {

		ArrayList<Cell> list = new ArrayList<Cell>();
		
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					if (isValid(xCoordinate + i, yCoordinate + j)) {
						if (board[xCoordinate + i][yCoordinate + j].getLifeForm() instanceof Omnivore) {
							list.add(new Cell(xCoordinate + i, yCoordinate + j));
						}
					}
				}
			}
		}
		
		return list;	
	}

	
	public ArrayList<Cell> getEmptyNeighborCells(Cell[][] board) {
		ArrayList<Cell> list = new ArrayList<Cell>();
		
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (!(i == 0 && j == 0)) {
					if (isValid(xCoordinate + i, yCoordinate + j)) {
						if (board[xCoordinate + i][yCoordinate + j].getLifeForm() == null) {
							list.add(new Cell(xCoordinate + i, yCoordinate + j));
						}
					}
				}
			}
		}
		
		return list;
		
	}
	
	public abstract ArrayList<Cell> getFoodNeighborCells(Cell[][] board);
	
	public abstract ArrayList<Cell> choosePositionCells(Cell[][] board);
	
	public abstract void reproduce(Cell[][] board);
	
	
	public void setHasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
	}

}
