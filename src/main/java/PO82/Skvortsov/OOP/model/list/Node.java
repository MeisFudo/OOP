package PO82.Skvortsov.OOP.model.list;

import PO82.Skvortsov.OOP.model.Service;

public class Node {
    private Service value;
    private Node next, previous;

    public Node(Service value, Node next,Node previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    public Node(){
        this(null,null,null);
    }

    public Service getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setValue(Service value) {
        this.value = value;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
