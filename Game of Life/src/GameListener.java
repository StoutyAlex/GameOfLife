/**
 * @author Alexander Stout
 * What the creator of the game listener needs to know about
 * UI interactions
 */
public interface GameListener {

    /**
     * Called when a cell is clicked
     * @param cell cell that is clicked
     */
    void onClickedCell(Cell cell);

    /**
     * Called when the game is started
     */
    void onStart();

    /**
     * Called when the game is stopped
     */
    void onStop();

    /**
     * Called when the board is cleared
     */
    void onClear();

    /**
     * Called when the speed has changed
     * @param speed new speed
     */
    void onSpeedChange(int speed);
}
