package LOGIC;
/**
 *A special cell that would indicate the goal of a Rat Object
 * @author Yuta
 */
import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.*;
import javax.imageio.ImageIO;
public class Cheese extends Cell {
    
    public final static Color CHEESE_COLOR = Color.YELLOW;
    public final static String GOAL_IMG = "../DASTAPPMP/resource/Goal.jpg";
    //public final static String GOAL_IMG = "C:\\Users\\Yuta\\Documents\\NetBeansProjects\\DASTAPPMP\\resource\\Goal.jpg";
    protected JFrame frame;
    
    public Cheese(double x, double y, int posX, int posY, int width, int height, JFrame frame){
        super(x, y, posX, posY, width, height);
        this.frame = frame;
        this.curColor = CHEESE_COLOR;
        this.status = Cell.CHEESE_STAT;
        try{
            img = ImageIO.read(new File(GOAL_IMG));
        }catch(IOException e){
            System.out.println("(Cheese:Cheese) " + e.toString());
        }
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
