package twentyquestions;

public class QNode<String> implements BinNode<String>
{
    QNode<String> left, right;
    /* The isAnswer field be assigned the type of node.
     * The data field will be assigned the question or answer contained in the node
     * */
    String isAnswer, data;

    public QNode(String isAnswer, String data) {
        this.isAnswer = isAnswer;
        this.data = data;
    }

    @Override
    public QNode left()
    {
        return (QNode) left;
    }

    @Override
    public QNode right()
    {
        return (QNode) right;
    }

    @Override
    public boolean isLeaf()
    {
        return (left == null && right == null);
    }

    @Override
    public String value()
    {
        return data;
    }

    @Override
    public void setValue(String data)
    {
        this.data = data;
    }
}
