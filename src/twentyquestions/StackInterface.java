package twentyquestions;

interface StackInterface<E>
{
    // Reinitialize the stack.
    public void clear();

    // Push "it" onto the top of the stack
    public boolean push(E it);

    // Remove and return the element at the top of the stack
    public void pop();

    // Return a copy of the top element
    public E peek();

    // Return the number of elements in the stack
    public int size();
}