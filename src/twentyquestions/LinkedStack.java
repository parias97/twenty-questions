package twentyquestions;

public class LinkedStack<T> implements StackInterface<T>
{
    protected LinkedNode<T> top;            // Pointer to first element
    protected int size;               // Number of elements
    protected int maxSize;            // Maximum size of stack

    // Constructors
    LinkedStack()
    {
        top = null;
        size = 0;
    }

    LinkedStack(int size)
    {
        top = null;
        maxSize = size;
        this.size = 0;
    }

    // Reinitialize stack
    @Override
    public void clear()
    {
        top = null;
        size = 0;
    }

    // Put "it" on stack
    @Override
    public boolean push(T it)
    {
        top = new LinkedNode<T>(it, top);
        size++;
        return true;
    }

    // Remove "it" from stack
    @Override
    public void pop()
    {
        if (top == null)
        {
            return;
        }
        T it = top.getData();
        top = top.getNext();
        size--;
    }

    // Return top value
    @Override
    public T peek()
    {
        if (top == null)
        {
            return null;
        }
        return top.getData();
    }

    // Return stack length
    @Override
    public int size() {
        return size;
    }
}


