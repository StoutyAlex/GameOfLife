/**
 * @author Alexander Stout
 * Exception when the user enters invalid sizes of width or height
 */
public class GameOfLifeBoundsException extends Exception {

    GameOfLifeBoundsException(){
        System.out.println("Invalid size\n" +
                "Max Height/Width = " + GameOfLife.MAX_HEIGHT + "/" + GameOfLife.MAX_WIDTH + "\n" +
                "Min Height/Width = " + GameOfLife.MIN_HEIGHT + "/" + GameOfLife.MIN_WIDTH);
        System.exit(0);
    }

}
