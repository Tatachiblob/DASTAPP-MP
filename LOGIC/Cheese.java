package LOGIC;
/**
 *A special cell that would indicate the goal of a Rat Object
 * @author Yuta
 */
import java.awt.Color;
public class Cheese extends Cell {
    
    public final static Color CHEESE_COLOR = Color.YELLOW;
    
    public Cheese(double x, double y, int posX, int posY){
        super(x, y, posX, posY);
    }
    
    @Override
    public String toString(){
        return "G";
    }
}
