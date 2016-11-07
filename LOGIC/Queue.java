package LOGIC;
/**
 *
 * @author Yuta 11512709
 */
import java.util.ArrayList;
public class Queue {
    
    private ArrayList<Object> list;
    
    public Queue(){
        this.list = new ArrayList<>();
    }
    
    public void enqueue(Object o){
        list.add(o);
    }
    
    public Object dequeue(){
        
    }
    
}
