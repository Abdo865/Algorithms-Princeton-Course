import java.util.EmptyStackException;

public class FixedCapacityStack extends Stack{
    private int[] stack;
    private final int capacity;
    public FixedCapacityStack(int capacity){
        if (capacity <= 0)
            throw new IllegalArgumentException("Stack capacity can't be smaller than 1");
        stack = new int[capacity];
        this.capacity = capacity;
    }

    public void push(int item){
        if (size == capacity)
            throw new StackOverflowError();
        stack[size++] = item;
    }

    public int pop(){
        if (isEmpty())
            throw new EmptyStackException();
        return stack[--size];
    }

    @Override
    public int peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return stack[size];
    }
}
