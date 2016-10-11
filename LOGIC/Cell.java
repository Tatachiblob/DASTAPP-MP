package LOGIC;
/**
 *The basic object on the map
 * @author Yuta
 */
public abstract class Cell{
    
    private int x, y;
    public final static int DEFAULT_PANEL_SIZE = 25;
    
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Cell(){}
    
    public int getX(){return this.x;}
    public int getY(){return this.y;}
}
