package LOGIC;
/**
 *
 * @author Yuta 11512709
 */
import GUI.Maze;
import java.awt.Point;
public class Algorithm {
    
    public Stack myStack;
    public final static int NORTH = 0;
    public final static int SOUTH = 1;
    public final static int EAST = 2;
    public final static int WEST = 3;
    
    public Algorithm(){
        this.myStack = new Stack();
    }
    
    public void solveMaze(Maze maze){
        this.myStack.push(new Point(maze.findRat().getPosX(), maze.findRat().getPosY()));
        
    }
    
}
