import java.util.EmptyStackException;

public class LinkedStack extends Stack {
    private Node top;
    private static class Node{
        int item;
        Node next;
        Node (int item){  this.item = item;  }
        Node (){}
    }
    public LinkedStack(){
        top = new Node();
        size = 0;
    }

    @Override
    public void push(int item){
        Node n = new Node(item);
        n.next = top;
        top = n;
        size++;
    }

    @Override
    public int pop(){
        if (top == null)
            throw new EmptyStackException();
        int item = top.item;
        top = top.next;
        size--;
        return item;
    }

    @Override
    public int peek() {
        return top.item;
    }
}
