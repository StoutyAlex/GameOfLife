import javax.swing.*;
import java.awt.*;

/**
 * @author Alexander Stout
 * Game of Life cell, extends button
 * can be alive or dead
 * makes up contents of @GameOfLifeBoard grid
 */
public class Cell extends JButton {

    /**
     * Cell x location in the grid (row)
     */
    private int xLocation;

    /**
     * Cell y location in the grid (col)
     */
    private int yLocation;

    /**
     * Cells alive status
     */
    private boolean alive = false;

    public Cell(int xLocation, int yLocation){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.setBackground(Color.WHITE);
    }

    /**
     * Get the x location of the cell
     * @return xLocation
     */
    public int getxLocation(){
        return xLocation;
    }

    /**
     * Get the y location of the cell
     * @return yLocation
     */
    public int getyLocation(){
        return yLocation;
    }

    /**
     * Toggle status of the cell
     */
    public void toggleAlive(){
        alive = !alive;
        setBackground(alive);
    }

    /**
     * set the alive status of a cell
     * @param alive alive
     */
    public void setAlive(boolean alive){
        this.alive = alive;
        setBackground(alive);
    }

    /**
     * Set the background colour of the cell
     * @param alive alive
     */
    private void setBackground(boolean alive){
        if(alive){
            setBackground(Color.BLACK);
        }else{
            setBackground(Color.WHITE);
        }
    }

    /**
     * Check to see if cell is alive
     * @return alive
     */
    public boolean isAlive(){
        return alive;
    }

}
