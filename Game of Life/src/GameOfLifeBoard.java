import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Alexander Stout
 * Frame of the program that contains all ui elements, cells, buttons, sliders, panels...
 * Used to process and handle the grid
 */
public class GameOfLifeBoard extends JFrame implements ActionListener, GameBoard, ChangeListener {

    /**
     * Title of the JFrame window
     */
    private static final String TITLE = "Game of Life by Alex Stout";

    /**
     * Size of each cell
     */
    private static final int BOX_SIZE = 20;

    /**
     * GameListener, listens for game UI updates (action events)
     */
    private GameListener gameListener;

    /**
     * Width of the board (columns)
     */
    private int boardWidth;

    /**
     * Height of the board (rows)
     */
    private int boardHeight;

    /**
     * Current generation of the boards cells
     */
    private Cell[][] generation;

    /**
     * MainLayout (UI)
     */
    private MainLayout mainLayout;


    public GameOfLifeBoard(int boardWidth, int boardHeight, Cell[][] generation, GameListener gameListener) {
        super();

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.generation = generation;
        this.gameListener = gameListener;

        //Set the size of the window
        setSize(BOX_SIZE + boardWidth * BOX_SIZE, BOX_SIZE + boardHeight * BOX_SIZE);
        setTitle(TITLE);

        mainLayout = new MainLayout(boardWidth, boardHeight);

        //Add action listeners
        mainLayout.start.addActionListener(this);
        mainLayout.stop.addActionListener(this);
        mainLayout.clear.addActionListener(this);
        mainLayout.speedSlider.addChangeListener(this);

        setContentPane(mainLayout);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainLayout.gridPanel.setLayout(new GridLayout(boardHeight, boardWidth));

        //Add cells
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                mainLayout.gridPanel.add(generation[x][y]);
                generation[x][y].addActionListener(this);
            }
        }
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cell getCellAt(int xLocation, int yLocation) {
        return (xLocation < 0 || xLocation >= boardWidth || yLocation < 0 || yLocation >= boardHeight)
                ? null : this.generation[xLocation][yLocation];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNewGeneration(boolean[][] nextGen) {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                generation[x][y].setAlive(nextGen[x][y]);
            }
        }
    }

    /**
     * Calls the GameListener functions depending what event happens
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        Object object = e.getSource();

        if (object instanceof Cell){
            gameListener.onClickedCell((Cell) object);
        }
        else if (object instanceof Button){

            if(object == mainLayout.start){
                gameListener.onStart();
            }
            else if(object == mainLayout.clear){
                gameListener.onClear();
            }
            else if(object == mainLayout.stop){
                gameListener.onStop();
            }
        }

    }

    /**
     * calls GameListener functions when the value of the game speed changes
     * @param e ChangeEvent
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        gameListener.onSpeedChange(slider.getValue());
    }
}
