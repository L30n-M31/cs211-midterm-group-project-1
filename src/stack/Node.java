package stack;

public class Node<T> {
    Node<T> next;
    T data;

    public Node() {
        setNext(null);
        setData(null);
    } // end of default constructor

    public Node(T data) {
        setNext(null);
        setData(data);
    } // end of constructor

    public Node<T> getNext() {
        return next;
    }

    public T getData() {
        return data;
    }

    public void setNext(Node<T> node) {
        next = node;
    }

    public void setData(T data) {
        this.data = data;
    }
} // end of Node class
