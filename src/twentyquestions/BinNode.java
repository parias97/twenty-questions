package twentyquestions;

interface BinNode<E>
{
    // Get and set the element value
    public E value();
    public void setValue(E v);

    // return the children
    public BinNode<E> left();
    public BinNode<E> right();

    // return TRUE if a leaf node, FALSE if not
    public boolean isLeaf();
}
