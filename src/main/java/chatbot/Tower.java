package chatbot;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

class Tower {
    private Stack<Integer>  stack;

    Tower() {
        stack = new Stack<Integer>();
    }

    void fillTower(int count) {
        for (int i = count; i > 0; i--)
            stack.push(i);
    }

    boolean shiftDisk(Tower tower) {
        if (stack.size() == 0 || stack.peek() > tower.getUpperDisk())
            return false;
        tower.addDisk(stack.pop());
        return true;
    }

    List<Integer> getDiscs() {
        return new ArrayList<Integer>(stack);
    }

    int getCount() {
        return stack.size();
    }

    private int getUpperDisk() {
        return (stack.size() > 0) ? stack.peek() : 99;
    }

    private void addDisk(int value) {
        stack.push(value);
    }
}
