package stack;

public class Stack<T> implements Stackable<T> {
    Node<T> top;
    int size;

    public Stack() {
        top = null;
        size = 0;
    } // end of default constructor

    @Override
    public int size() {
        return size;
    } // end of size method

    @Override
    public void push(T element) {
        Node<T> elementNode = new Node<>(element);
        elementNode.setNext(top);
        top = elementNode;
        size++;
    } // end of push method

    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) throw new StackUnderflowException();
        T element = top.getData();
        top = top.getNext();
        size--;
        return element;
    } // end of pop method

    @Override
    public T peek() throws StackUnderflowException {
        if (isEmpty()) throw new StackUnderflowException();
        return top.getData();
    } // end of pop method

    @Override
    public boolean isEmpty() {
        return top == null;
    } // end of isEmpty method

    @Override
    public String stackToString(String delimiter) {
        StringBuilder stackString = new StringBuilder();

        Node<T> curr = top;
        while (curr != null) {
            stackString.insert(0, curr.getData());
            if (curr.getNext() != null) {
                stackString.insert(0, delimiter);
            }
            curr = curr.getNext();
        }
        return stackString.toString();
    } // end of stackToString method
} // end of Stack class
