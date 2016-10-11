package LOGIC;

import javafx.scene.paint.Color;

/**
 *
 * @author Yuta
 */
public class Tile extends Cell {
    
    private boolean isWall;
    public final static int IS_WALL = 1;
    public final static int NOT_WALL = 0;
    public final static Color WALL_COLOR = Color.BLACK;
    public final static Color PATH_COLOR = Color.WHITE;
    
    public Tile(int x, int y){
        super(x, y);
    }
    
    public void setWall(int wall){
        if(wall == 1){
            this.isWall = true;
        }
        else if(wall == 0){
            this.isWall = false;
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
