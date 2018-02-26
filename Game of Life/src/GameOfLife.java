/**
 * @author Alexander Stout
 * Made for BBC Software Engineering coding challenge.
 * GitHub Link : https://github.com/StoutyAlex/BBCGameOfLife
 *
 * Game of Life class that handles the main functionality of the game.
 * contains game cells, rules and game loop
 */
public class GameOfLife implements GameListener {

    /**
     * Max number of rows in the game of life grid
     */
    public static final int MAX_HEIGHT = 50;

    /**
     * Min number of rows in the game of life grid
     */
    public static final int MIN_HEIGHT = 20;

    /**
     * Max number of columns in the game of life grid
     */
    public static final int MAX_WIDTH = 50;

    /**
     * Mix number of columns in the game of life grid
     */
    public static final int MIN_WIDTH = 20;

    /**
     * Default speed of the game loop
     */
    private static final int DEFAULT_SPEED = 100;

    /**
     * number of rows in the game grid
     */
    private int height;

    /**
     * number of columns in game grid
     */
    private int width;

    /**
     * 2D array of cells to be used in the game of life
     */
    private Cell[][] cells;

    /**
     * Game of Life status
     */
    private boolean running = false;

    /**
     * Game of Life game board
     */
    private GameBoard gameOfLifeBoard;

    /**
     * Speed of the game loop
     */
    private int speed;

    public GameOfLife (int height, int width) throws GameOfLifeBoundsException {

        //Check for valid height and width
        if((height > MAX_HEIGHT || height < MIN_HEIGHT) || (width > MAX_HEIGHT || width < MIN_HEIGHT))
            throw new GameOfLifeBoundsException();

        this.height = height;
        this.width = width;

        this.speed = DEFAULT_SPEED;

        this.cells = new Cell[height][width];

        //Initiate the cells
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.cells[x][y] = new Cell(x, y);
            }
        }

        //Create the game board
        gameOfLifeBoard = new GameOfLifeBoard(width, height, cells, this);
    }

    /**
     * Start the game loop
     * processes current and applies next generation
     */
    private void start() {
        running = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    boolean[][] nextGen = processNextGeneration(cells);
                    gameOfLifeBoard.setNewGeneration(nextGen);
                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * Process a given cell to determine whether the cell stays
     * alive, dead or the same in the next generation
     * @param cell cell to be checked
     * @return alive status of the cell
     */
    private boolean processCell(Cell cell){

        Cell[] surrounding = new Cell[8];

        int xLocation = cell.getxLocation();
        int yLocation = cell.getyLocation();

        //Get the cells surrounding cells
        surrounding[0] =  gameOfLifeBoard.getCellAt(xLocation-1,yLocation-1);
        surrounding[1] =  gameOfLifeBoard.getCellAt(xLocation, yLocation-1);
        surrounding[2] =  gameOfLifeBoard.getCellAt(xLocation+1, yLocation-1);
        surrounding[3] =  gameOfLifeBoard.getCellAt(xLocation-1, yLocation);
        surrounding[4] =  gameOfLifeBoard.getCellAt(xLocation+1, yLocation);
        surrounding[5] =  gameOfLifeBoard.getCellAt(xLocation-1, yLocation+1);
        surrounding[6] =  gameOfLifeBoard.getCellAt(xLocation, yLocation+1);
        surrounding[7] =  gameOfLifeBoard.getCellAt(xLocation+1, yLocation+1);

        //Check surrounding cells, see how many are alive
        int aliveNeighbours = 0;
        for(Cell cellSurround : surrounding){
            if(cellSurround != null) {
                if (cellSurround.isAlive())
                    aliveNeighbours++;
            }
        }

        boolean result = false;

        //Apply the rules of the game
        //Under and over population
        if ((aliveNeighbours < 2) || (aliveNeighbours > 3)) {
            result = false;
        }

        //Cell lives on to next generation
        if (aliveNeighbours == 2) {
            result = cell.isAlive();
        }

        //Cell either stays alive, or is born
        if (aliveNeighbours == 3) {
            result = true;
        }

        return result;
    }

    /**
     * Processes then next generation
     * @param cells current generation
     * @return next generation
     */
    private boolean[][] processNextGeneration(Cell[][] cells){
        boolean[][] nextGeneration = new boolean[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                nextGeneration[x][y] = processCell(cells[x][y]);
            }
        }
        return nextGeneration;
    }

    /**
     * {@inheritDoc}
     * toggles a cell alive status if game is running
     * @param cell cell to be toggled
     */
    @Override
    public void onClickedCell(Cell cell) {
        if (!running)
            cell.toggleAlive();
    }

    /**
     * {@inheritDoc}
     * starts the game
     */
    @Override
    public void onStart() {
        if(!running)
            start();
    }

    /**
     * {@inheritDoc}
     * Stops the game
     */
    @Override
    public void onStop() {
        running = false;
    }

    /**
     * {@inheritDoc}
     * clear the game board
     */
    @Override
    public void onClear(){
        if(running) return;
        boolean[][] clearGeneration = new boolean[height][width];
        for(int y = 0; y<height; y++){
            for(int x = 0; x<width; x++){
                clearGeneration[x][y] = false;
            }
        }
        gameOfLifeBoard.setNewGeneration(clearGeneration);
    }

    /**
     * {@inheritDoc}
     * Set the speed of the game loop
     * @param speed new speed
     */
    @Override
    public void onSpeedChange(int speed) {
        this.speed = speed;
    }

    public static void main(String[] args) {
        try {
            new GameOfLife(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } catch (GameOfLifeBoundsException e) {
            e.printStackTrace();
        }
    }

}
