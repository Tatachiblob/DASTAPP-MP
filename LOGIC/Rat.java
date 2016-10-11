package LOGIC;
/**
 *A special Cell that can move around
 * @author Yuta
 */
import java.awt.Color;
public class Rat extends Cell implements PlayerListener{
    
    public final static Color RAT_COLOR = Color.GRAY;
    
    public Rat(int x, int y){
        super(x, y);
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
