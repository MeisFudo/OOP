package PO82.Skvortsov.OOP.model.tariff;

import PO82.Skvortsov.OOP.model.Service;

import java.util.*;

public class EntityTariff implements Tariff {
    private static final int SERVICE_CHARGE = 50;

    private int size;
    private Node head, tail;

    public EntityTariff() {
        head = null;
        tail = null;
        size = 0;
    }

    public EntityTariff(Service... services) {
        checkIsNull(services);
        for (Service service : services) {
            add(service);
        }
    }

    @Override
    public boolean add(Service service) {
        checkIsNull(service);
        if (head == null) {
            head = new Node(null, null, service);
        } else {
            Node previousNode = tail == null ? head : tail;
            tail = new Node(null, previousNode, service);
            previousNode.next  = tail;
        }
        size++;
        return true;
    }

    @Override
    public boolean add(Service service, int index) {
        checkIsNull(service);
        checkIndex(index);
        if (index >= size) {
            Node currentNode = tail == null ? head : tail;
            tail = new Node(null, currentNode, service);

        } else {
            Node currentNode = head;
            for (int i = 0; currentNode != null; i++) {
                if (i == index) {
                    currentNode.prev.next = new Node(currentNode, currentNode.prev, service);
                }
                currentNode = currentNode.next;
            }
        }
        size++;
        return true;
    }

    @Override
    public Service get(int index) {
        checkIndex(index);
        if (index < size) {
            int counter = 0;
            for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.next) {
                if (counter == index) {
                    return currentNode.value;
                }
                counter++;
            }
        }
        return null;
    }

    @Override
    public Service set(Service service, int index) {
        checkIsNull(service);
        checkIndex(index);
        if (index >= size) {
            Node currentNode = tail == null ? head : tail;
            tail = new Node(null, currentNode, service);
            size++;
            return null;
        } else {
            Node currentNode = head;
            for (int i = 0; currentNode != null; i++) {
                if (i == index) {
                    Service currentService = currentNode.value;
                    currentNode.value = service;
                    return currentService;
                }
                currentNode = currentNode.next;
            }
        }
        return null;
    }

    @Override
    public Service remove(int index) {
        checkIndex(index);
        int counter = 0;
        for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.next) {
            if (counter == index) {
                size--;
                return unlink(currentNode);
            }
            counter++;
        }
        return null;
    }

    @Override
    public Service remove(String serviceName) {
        checkIsNull(serviceName);
        int counter = 0;
        for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.next) {
            if (currentNode.value.getName().equals(serviceName)) {
                size--;
                return unlink(currentNode);
            }
            counter++;
        }
        throw new NoSuchElementException();
    }

    Service unlink(Node node) {
        // assert node != null;
        final Service element = node.value;
        final Node next = node.next;
        final Node prev = node.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.value = null;
        size--;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Boolean remove(Service service) {
        checkIsNull(service);
        return this.remove(this.indexOf(service)) != null;
    }

    public void clear() {
        for (Node x = head; x != null; ) {
            Node next = x.next;
            x.value = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    private void checkIsNull(Object o) {
        if (Objects.isNull(o)) {
            throw new NullPointerException();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.next) {
            result.append(currentNode.value.toString()).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        int hashCode = 71;
        for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.next) {
            hashCode *= currentNode.value.hashCode();
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityTariff that = (EntityTariff) o;
        if (size != that.size()) return false;
        int count = 0;
        for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.next) {
            if (!currentNode.value.equals(that.get(count))) {
                return false;
            }
            count++;
        }
        return true;
    }

    @Override
    public Tariff clone() throws CloneNotSupportedException {
        return (Tariff) super.clone();
    }

    @Override
    public Iterator<Service> iterator() {
        return new ServiceIterator();
    }

    private class Node {
        Service value;
        Node next;
        Node prev;

        public Node(Node next, Node prev, Service value) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public Node() {
            this(null, null, null);
        }
    }

    private class ServiceIterator implements Iterator<Service> {
        private Node lastReturned;
        private Node next = head;
        private int nextIndex;

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public Service next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.value;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            Node lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }
            lastReturned = null;
        }
    }
}


