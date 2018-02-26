/**
 * @author Alexander Stout
 * Basic need for a GameBoard set a new Generation and
 * retrieve a cell.
 */
public interface GameBoard {

    /**
     * Draws the new generation to the gameboard
     * @param nextGen generation to draw
     */
    void setNewGeneration(boolean[][] nextGen);

    /**
     * Returns the cell at a specific position of the grid
     * @param x xLocation
     * @param y yLocation
     * @return cell at the x and y
     */
    Cell getCellAt(int x, int y);

}
