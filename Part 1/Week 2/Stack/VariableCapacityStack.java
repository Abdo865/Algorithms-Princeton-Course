import java.util.EmptyStackException;

public class VariableCapacityStack extends Stack{
    private int[] stack;
    private int capacity;
    public VariableCapacityStack(int capacity){
        if (capacity <= 0)
            throw new IllegalArgumentException("Stack capacity can't be smaller than 1");
        stack = new int[capacity];
        this.capacity = capacity;
    }

    private void resize(int x){
        int[] arr = new int[x];
        System.arraycopy(stack, 0, arr, 0, size);
        stack = arr;
        capacity = x;
    }

    public void push(int item){
        if (size == capacity)
            resize(capacity * 2);
        stack[size++] = item;
    }

    public int pop(){
        if (isEmpty())
            throw new EmptyStackException();
        else if (capacity / size >= 4)
            resize(capacity / 4);
        return stack[--size];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return stack[size];
    }
}
