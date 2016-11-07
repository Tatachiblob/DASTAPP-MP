package GUI;
/**
 *
 * @author Yuta 11512709
 */
import java.io.File;
import javax.swing.JFrame;
import java.awt.event.*;
public class MazeApp extends JFrame{
    
    private Maze maze;
    private MapEditor editor;
    
    public MazeApp(File f){
        super("Machine Project");
        this.maze = new Maze(f, this);
        this.add(maze);
        this.setSize(maze.totalX, maze.totalY);
        this.setVisible(false);
    }
    
    public MazeApp(MapEditor editor){
        super("Machine Project");
        this.editor = editor;
        
        this.add(editor);
        this.setSize(editor.totalX, editor.totalY);
        this.setVisible(false);
    }
        
}
