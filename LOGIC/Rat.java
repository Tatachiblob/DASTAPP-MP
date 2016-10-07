package LOGIC;

import java.awt.Color;
import java.awt.event.*;
/**
 *A special Cell that can move around
 * @author Yuta
 */
public class Rat extends Cell implements Player{
    
    public Rat(int x, int y){
        super(x, y);
        this.setBackground(Color.YELLOW);
    }

    @Override
    public void moveLeft() {}

    @Override
    public void moveRight() {}

    @Override
    public void moveUp() {}

    @Override
    public void moveDown() {}
    
    @Override
    public String toString(){
        return "S";
    }
    
}
