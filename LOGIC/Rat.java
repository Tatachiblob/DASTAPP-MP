package LOGIC;
/**
 *A special Cell that can move around
 * @author Yuta
 */
import java.awt.Color;
import GUI.Maze;
import java.util.ArrayList;
public class Rat extends Cell implements PlayerListener{
    
    public final static Color RAT_COLOR = Color.GRAY;
    
    public Rat(double x, double y, int posX, int posY){
        super(x, y, posX, posY);
    }

    @Override
    public void moveLeft(Maze m){
        if(posX > 0) {
            if(m.getTiles()[posY][posX - 1] instanceof Tile){
                if(!((Tile)m.getTiles()[posY][posX - 1]).getWall()){
                    m.getTiles()[posY][posX].x -= 42;
                    m.getTiles()[posY][posX - 1].x += 42;
                    m.swap(posX, posY, posX - 1, posY);
                }
            }
            else if(m.getTiles()[posY][posX - 1] instanceof Cheese){
                Cheese.goal();
            }
        }
        System.out.println("(x, y) = (" + posX + ", " + posY + ")");
        
    }
    
    @Override
    public void moveRight(Maze m){
        if(posX < m.getMap().getRows() - 1){
            if(m.getTiles()[posY][posX + 1] instanceof Tile){
                if(!((Tile)m.getTiles()[posY][posX + 1]).getWall()){
                    m.getTiles()[posY][posX].x += 42;
                    m.getTiles()[posY][posX + 1].x -= 42;
                    m.swap(posX, posY, posX + 1, posY);
                }
            }
            else if(m.getTiles()[posY][posX + 1] instanceof Cheese){
                Cheese.goal();
            }
        }
        System.out.println("(x, y) = (" + posX + ", " + posY + ")");
    }

    @Override
    public void moveUp(Maze m){
        if(posY > 0){
            if(m.getTiles()[posY - 1][posX] instanceof Tile){
                if(!((Tile)m.getTiles()[posY - 1][posX]).getWall()){
                    m.getTiles()[posY][posX].y -= 42;
                    m.getTiles()[posY - 1][posX].y += 42;
                    m.swap(posX, posY, posX, posY - 1);
                }
            }
            else if(m.getTiles()[posY - 1][posX] instanceof Cheese){
                Cheese.goal();
            }
        }
        System.out.println("(x, y) = (" + posX + ", " + posY + ")");
    }

    @Override
    public void moveDown(Maze m){
        if(posY < m.getMap().getColums() - 1){
            if(m.getTiles()[posY + 1][posX] instanceof Tile){
                if(!((Tile)m.getTiles()[posY + 1][posX]).getWall()){
                    m.getTiles()[posY][posX].y += 42;
                    m.getTiles()[posY + 1][posX].y -= 42;
                    m.swap(posX, posY, posX, posY + 1);
                }
            }
            else if(m.getTiles()[posY + 1][posX] instanceof Cheese){
                Cheese.goal();
            }
        }
        System.out.println("(x, y) = (" + posX + ", " + posY + ")");
    }
    
    @Override
    public String toString(){
        return "S";
    }
    
}
