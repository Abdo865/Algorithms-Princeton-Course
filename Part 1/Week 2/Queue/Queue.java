public abstract class Queue {
    protected int size;
    public abstract void enqueue(int item);
    public abstract int dequeue();
    public abstract int peek();
    public boolean isEmpty() {  return size == 0;  }
    public int size() {  return size;  }

}
