package LOGIC;
/**
 *A special cell that would indicate the goal of a Rat Object
 * @author Yuta
 */
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class Cheese extends Cell {
    
    public final static Color CHEESE_COLOR = Color.YELLOW;
    public final static String GOAL_IMG = "../DASTAPPMP/resource/Goal.jpg";
    protected JFrame frame;
    
    public Cheese(double x, double y, int posX, int posY, int width, int height, JFrame frame){
        super(x, y, posX, posY, width, height);
        this.frame = frame;
        this.curColor = CHEESE_COLOR;
        this.status = Cell.CHEESE_STAT;
        setImg(GOAL_IMG);
    }
    
    public Cheese(double x, double y, int posX, int posY, int width, int height){
        super(x, y, posX, posY, width, height);
    }
    
    public void goal(){
        JOptionPane.showMessageDialog(null, "Congratulations", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
    }
    
    @Override
    public String toString(){
        return "G";
    }
}
