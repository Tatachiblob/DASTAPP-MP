package LOGIC;
/**
 *
 * @author Yuta
 */
import java.awt.Color;
import javax.swing.JFrame;
public class Tile extends Cell {
    
    private boolean isWall;
    public final static int IS_WALL = 1;
    public final static int NOT_WALL = 0;
    public final static Color WALL_COLOR = Color.BLACK;
    public final static Color PATH_COLOR = Color.WHITE;
    
    public Tile(double x, double y, int posX, int posY, int width, int height){
        super(x, y, posX, posY, width, height);
    }
    
    public void setWall(int wall){
        if(wall == 1){
            this.isWall = true;
            this.status = Cell.WALL_STAT;
        }
        else if(wall == 0){
            this.isWall = false;
            this.status = Cell.PATH_STAT;
        }
    }
    
    public boolean getWall(){return this.isWall;}
    @Override
    public String toString(){
        if(isWall){
            return "#";
        }
        else{
            return " ";
        }
    }
    
}
