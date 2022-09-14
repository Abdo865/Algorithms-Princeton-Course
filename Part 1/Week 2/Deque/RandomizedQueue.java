import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
    private Item[] items;
    private int size;

    private class RandomQueueIterator implements Iterator<Item>
    {
        private int here = 0;
        private final int[] shuffled;

        RandomQueueIterator()
        {
            shuffled = new int[size];
            for (int i = 0; i < size; i++)
                shuffled[i] = i;
            StdRandom.shuffle(shuffled);
        }
        
        @Override
        public boolean hasNext()
        {
            return here != size;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next()
        {
            if (here >= size || size() == 0)
                throw new NoSuchElementException();
            if (here >= items.length)
                here -= items.length;
            return items[shuffled[here++]];
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        items = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return size;
    }

    private void resize(int capacity)
    {
        Item[] arr = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, arr, 0, size);
        items = arr;
    }
    // add the item
    public void enqueue(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == items.length)
            resize(items.length * 2);
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        int x = StdRandom.uniform(size);
        Item toReturn = items[x];
        items[x] = items[--size];
        items[size] = null;
        return toReturn;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        int x = StdRandom.uniform(size);
        return items[x];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {
        return new RandomQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
        randomQueue.enqueue("First element");
        randomQueue.enqueue("Second element");
        randomQueue.enqueue("Third element");
        randomQueue.enqueue("Fourth element");
        randomQueue.enqueue("Fifth element");

        System.out.println("Is the queue empty: " + randomQueue.isEmpty());
        System.out.println("Size of a queue is: " + randomQueue.size());
        System.out.println("Sample of random element from queue: " + randomQueue.sample());
        System.out.println("Size of a queue is: " + randomQueue.size());

        for (String s : randomQueue) {
            System.out.println(s);
        }

        while (!randomQueue.isEmpty()) {
            System.out.println(randomQueue.dequeue());
        }

        System.out.println("Is the queue empty: " + randomQueue.isEmpty());
    }
}