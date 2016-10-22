package LOGIC;
/**
 *The basic object on the map
 * @author Yuta
 */
import java.awt.Rectangle;
public abstract class Cell extends Rectangle{
    
    protected double x, y;
    protected int posX, posY;
    public final static int DEFAULT_PANEL_SIZE = 25;
    
    public Cell(double x, double y, int posX, int posY){
        this.x = x;
        this.y = y;
        this.posX = posX;
        this.posY = posY;
        this.width = 40;
        this.height = 40;
    }
    
    public Cell(){}
    public int getPosX(){return this.posX;}
    public int getPosY(){return this.posY;}
    public void setPosX(int x){this.posX = x;}
    public void setPosY(int y){this.posY = y;}
    @Override
    public double getX(){return this.x;}
    @Override
    public double getY(){return this.y;}
}
