    package LOGIC;

import java.awt.Color;

/**
 *
 * @author Yuta
 */
public class Tile extends Cell {
    
    private boolean isWall;
    
    public Tile(int x, int y){
        super(x, y);
    }
    
    public void setWall(int wall){
        if(wall == 1){
            this.isWall = true;
            this.setBackground(Color.GRAY);
        }
        else if(wall == 0){
            this.isWall = false;
            this.setBackground(Color.WHITE);
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
