package LOGIC;
/**
 *A special Cell that can move around
 * @author Yuta
 */
import java.awt.Color;
public class Rat extends Cell implements PlayerListener{
    
    public final static Color RAT_COLOR = Color.GRAY;
    
    public Rat(double x, double y, int posX, int posY){
        super(x, y, posX, posY);
    }

    @Override
    public void moveLeft(Map m){
        System.out.println("(x, y) = (" + posX + ", " + posY + ")");
        if (posX > 0 && m.getMap()[posX - 1][posY] == Map.PATH) {
            this.move((int)this.getX(), (int)this.getY());
            posX--;
        }
    }

    @Override
    public void moveRight(Map m){
        System.out.println("(x, y) = (" + posX + ", " + posY + ")");
    }

    @Override
    public void moveUp(Map m){
        int x = (int)this.getX();
        int y = (int)this.getY();
        System.out.println("(x, y) = (" + posX + ", " + posY + ")");
    }

    @Override
    public void moveDown(Map m){
        int x = (int)this.getX();
        int y = (int)this.getY();
        System.out.println("(x, y) = (" + posX + ", " + posY + ")");
    }
    
    @Override
    public String toString(){
        return "S";
    }
    
}
