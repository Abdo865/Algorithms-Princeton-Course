import java.util.EmptyStackException;

public abstract class Stack{
    protected int size;
    public abstract void push(int item);
    public abstract int pop();
    public abstract int peek();
    public boolean isEmpty() {  return size == 0;  }
    public int size() {  return size;  }
}
