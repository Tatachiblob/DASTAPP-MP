package LOGIC;

import java.util.ArrayList;

public class Stack {

    private ArrayList<Object> list;

    public Stack() {
        list = new ArrayList<>();
    }

    public void push(Object E) {
        list.add(E);
    }

    public Object pop() {
        if (list.size() != 0) {
            return list.remove(list.size() - 1);
        } else {
            return null;
        }
    }

    public Object peek() {
        if (list.size() != 0) {
            return list.get(list.size() - 1);
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("");
        for (int i = 0; i < list.size(); i++) {
            s.append(list.toString() + "\n");
        }
        return s.toString();
    }
}
