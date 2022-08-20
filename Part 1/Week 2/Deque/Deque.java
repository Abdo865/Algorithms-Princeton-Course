import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private static class Node<Item>
    {
        Item value;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item value)
        {
            this.value = value;
        }
        public Node(Item value, Node<Item> next)
        {
            this.value = value;
            this.next = next;
        }
        public Node(Item value, Node<Item> next, Node<Item> prev)
        {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private class DequeIterator implements Iterator<Item>
    {

        Node<Item> curr = first;
        int now = 0;

        @Override
        public boolean hasNext()
        {
            return now != size;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public Item next()
        {
            if (curr == null)
            {
                throw new NoSuchElementException();
            }
            now++;
            Item item = curr.value;
            curr = curr.next;
            return item;
        }
    }

    // construct an empty deque
    public Deque()
    {

    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return size == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == 0)
        {
            first = new Node<>(item);
            last = first;
        }
        else
        {
            first = new Node<>(item, first, last);
            first.next.prev = first;
            last.next = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == 0)
        {
            first = new Node<>(item);
            last = first;
        }
        else
        {
            last = new Node<>(item, first, last);
            last.prev.next = last;
            first.prev = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        Item t;
        t = first.value;
        if (size-- == 1)
        {
            first = last = null;
            return t;
        }
        first = first.next;
        last.next = first;
        return t;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty())
            throw new NoSuchElementException();
        Item t;
        t = last.value;
        if (size-- == 1)
        {
            first = last = null;
            return t;
        }
        last = last.prev;
        last.next = first;
        first.prev = last;
        return t;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<String> deque = new Deque<>();
        deque.addFirst("First Node");
        deque.addLast("Last Node");

        System.out.print("Show Deque: ");
        for (String string : deque)
        {
            System.out.print(string + ", ");
        }

        System.out.println("\n---------------");

        deque.addFirst("First First Node");
        deque.addLast("Last Last Node");

        System.out.print("Show Deque: ");
        for (String string : deque) {
            System.out.print(string + ", ");
        }

        System.out.println("\n---------------");

        System.out.print("Removed: ");
        System.out.print(deque.removeFirst() + ", ");
        System.out.println(deque.removeLast());

        System.out.print("Show Deque: ");
        for (String string : deque) {
            System.out.print(string + ", ");
        }
        System.out.println("\n---------------");

        System.out.print("Removed: ");
        System.out.print(deque.removeFirst() + ", ");
        deque.removeLast();
        System.out.print("Show Deque: ");
        for (String string : deque) {
            System.out.print(string + ", ");
        }
        System.out.println("\n---------------");
    }

}