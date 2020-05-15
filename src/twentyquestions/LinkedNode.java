package twentyquestions;

public class LinkedNode<E>
{
    private LinkedNode next;
    private E data;

    public LinkedNode(E data, LinkedNode next)
    {
        this.next = next;
        this.data = data;
    }

    public LinkedNode(E data)
    {
        this.next = null;
        this.data = data;
    }

    public LinkedNode getNext()
    {
        return next;
    }

    public void setNext(LinkedNode next)
    {
        this.next = next;
    }

    public E getData()
    {
        return data;
    }

    public void setData(E data)
    {
        this.data = data;
    }
}
