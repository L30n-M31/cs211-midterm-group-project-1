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
        if (!isEmpty()) {
            T element = top.getData();
            top = top.getNext();
            size--;
            return element;
        }
        throw new StackUnderflowException();
    } // end of pop method

    @Override
    public T peek() throws StackUnderflowException {
        if (!isEmpty())
            return top.getData();
        throw new StackUnderflowException();
    } // end of pop method

    @Override
    public boolean isEmpty() {
        return top == null;
    } // end of isEmpty method

    @Override
    public String stackToString(String delimiter) throws StackUnderflowException {
        StringBuilder stackString = new StringBuilder();

        Stack<T> temp = new Stack<>();
        Node<T> curr = top;

        for (int i = 0; i < size; i++) {
            temp.push(curr.getData());
            curr = curr.getNext();
        }
        while (!temp.isEmpty()) {
            stackString.append(temp.pop()).append(delimiter);
        }
        return stackString.toString();
    } // end of stackToString method
} // end of Stack class
