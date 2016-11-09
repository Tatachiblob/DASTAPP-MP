package LOGIC;
/**
 *
 * @author Yuta 11512709
 */
import GUI.*;
import java.util.ArrayList;
import javax.swing.Timer;
public class AnotherAlgo {
    
    private Queue myQueue;
    private Maze maze;
    private ArrayList<Node> visitedNode;
    public final static int NORTH = 0;
    public final static int SOUTH = 1;
    public final static int EAST = 2;
    public final static int WEST = 3;
    private Timer t;
    
    public AnotherAlgo(){
        this.myQueue = new Queue();
        this.visitedNode = new ArrayList<>();
    }
    
    public void solveMaze(Maze maze){
        Rat r = maze.findRat();
        this.isExist(new Node(r.getPosX(), r.getPosY()));
        
    }
    
    public void isExist(Node node){
        for(Node n : visitedNode){
            if(!n.equals(node)){
                visitedNode.add(node);
            }
        }
    }
    
}
