package LOGIC;

/**
 *A special cell that would indicate the goal of a Rat Object
 * @author Yuta
 */
import java.awt.Color;
public class Cheese extends Cell {
    
    public final static Color CHEESE_COLOR = Color.YELLOW;
    
    public Cheese(int x, int y){
        super(x, y);
    }
    
    @Override
    public String toString(){
        return "G";
    }
}
