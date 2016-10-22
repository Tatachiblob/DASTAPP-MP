package LOGIC;
/**
 *Movements of what a PlayerListener can do
 * @author Yuta
 * 
 */
import GUI.Maze;
public interface PlayerListener {
    void moveLeft(Maze m);
    void moveRight(Maze m);
    void moveUp(Maze m);
    void moveDown(Maze m);
}
