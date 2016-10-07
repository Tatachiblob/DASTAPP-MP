package LOGIC;
/**
 *The basic object on the map
 * @author Yuta
 */
import javax.swing.JPanel;
public abstract class Cell extends JPanel {
    
    private int x, y;
    public final static int DEFAULT_PANEL_SIZE = 25;
    /**
     * 
     * @param x position of x
     * @param y position of y 
     */
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.setSize(DEFAULT_PANEL_SIZE, DEFAULT_PANEL_SIZE);
    }
    
    public Cell(){
        this.setSize(DEFAULT_PANEL_SIZE, DEFAULT_PANEL_SIZE);
    }
    @Override
    public int getX(){return this.x;}
    @Override
    public int getY(){return this.y;}
    
}
