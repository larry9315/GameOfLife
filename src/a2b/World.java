/**
World class that has a 2d array of Cell class. It initializes the world. Prepares the movement for animals,
moves animals, and reproduce animals, and plants.
 * 
 */
package a2b;

import java.io.Serializable;

/**
 * @author larry
 *
 */
public class World implements Serializable {

	Cell[][] board = new Cell[50][50];
	
	/**
	 * 
	 */
	public World() {
		// TODO Auto-generated constructor stub
		initializeWorld();
	}
	
	public Cell[][] getBoard() {
		return board;
	}
	
	public void initializeWorld() {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				board[i][j] = new Cell(i, j);
				
				int  n = RandomGenerator.nextNumber(99);
				
				if (n >= 80) {
					board[i][j].addLife(new Herbivore(board[i][j], 5));
				} else if (n >= 60) {
					board[i][j].addLife(new Plant(board[i][j]));
				} else if (n >= 50) {
                	board[i][j].addLife(new Carnivore(board[i][j], 5));
                } else if (n >= 45) {
                	board[i][j].addLife(new Omnivore(board[i][j], 5));
                }


			}
		}		
	}
	
	public void initializeMove() {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if (board[i][j].getLifeForm() instanceof Herbivore
						|| board[i][j].getLifeForm() instanceof Omnivore
						|| board[i][j].getLifeForm() instanceof Carnivore) {
					board[i][j].getLifeForm().setHasMoved(false);
				}
			}	
		}
	}
	
	public void moveLifeform() {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if (board[i][j].getLifeForm() instanceof Herbivore 
						|| board[i][j].getLifeForm() instanceof Omnivore
						|| board[i][j].getLifeForm() instanceof Carnivore) {
					board[i][j].getLifeForm().move(board);	
				}
			}	
		}
	}
	
	public void reproduceLifeform() {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if (board[i][j].getLifeForm() instanceof Herbivore
						|| board[i][j].getLifeForm() instanceof Plant
						|| board[i][j].getLifeForm() instanceof Omnivore
						|| board[i][j].getLifeForm() instanceof Carnivore) {
					board[i][j].getLifeForm().reproduce(board);	
				}
			}	
		}
	}
	
	public void reproducePlants() {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				
				if (board[i][j].getSeed()) {
					board[i][j].setLifeForm(new Plant(board[i][j]));
				}
				board[i][j].setSeed(false);
				
			}	
		}
	}

}
