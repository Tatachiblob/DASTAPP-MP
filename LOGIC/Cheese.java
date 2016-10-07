package LOGIC;
/**
 *A special cell that would indicate the goal of a Rat Object
 * @author Yuta
 */
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
public class Cheese extends Cell {
    
    public Cheese(int x, int y){
        super(x, y);
        this.setBackground(Color.BLUE);
    }
    
    @Override
    public String toString(){
        return "G";
    }
}
