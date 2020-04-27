package PO82.Skvortsov.OOP.model.tariff;

import PO82.Skvortsov.OOP.model.Service;
import PO82.Skvortsov.OOP.model.ServiceTypes;
import PO82.Skvortsov.OOP.model.list.Node;

import java.util.*;
import java.util.function.Consumer;

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
            previousNode.setNext(tail);
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
                    currentNode.getPrevious().setNext(new Node(currentNode, currentNode.getPrevious(), service));
                }
                currentNode = currentNode.getNext();
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
            for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.getNext()) {
                if (counter == index) {
                    return currentNode.getValue();
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
                    Service currentService = currentNode.getValue();
                    currentNode.setValue(service);
                    return currentService;
                }
                currentNode = currentNode.getNext();
            }
        }
        return null;
    }

    @Override
    public Service remove(int index) {
        checkIndex(index);
        int counter = 0;
        for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.getNext()) {
            if (counter == index) {
                currentNode.getPrevious().setNext(currentNode.getNext());
                currentNode.getNext().setPrevious(currentNode.getPrevious());
                size--;
                return currentNode.getValue();
            }
            counter++;
        }
        return null;
    }

    @Override
    public Service remove(String serviceName) {
        checkIsNull(serviceName);
        int counter = 0;
        for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.getNext()) {
            if (currentNode.getValue().getName().equals(serviceName)) {
                currentNode.getPrevious().setNext(currentNode.getNext());
                currentNode.getNext().setPrevious(currentNode.getPrevious());
                size--;
                return currentNode.getValue();
            }
            counter++;
        }
        throw new NoSuchElementException();
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
        for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.getNext()) {
            result.append(currentNode.getValue().toString()).append(System.lineSeparator());
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        int hashCode = 71;
        for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.getNext()) {
            hashCode *= currentNode.getValue().hashCode();
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
        for (Node currentNode = this.head; currentNode != null; currentNode = currentNode.getNext()) {
            if (!currentNode.getValue().equals(that.get(count))) {
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
            next = next.getNext();
            nextIndex++;
            return lastReturned.getValue();
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            Node lastNext = lastReturned.getNext();
            lastNext.getPrevious().setNext(lastNext.getNext());
            lastNext.getNext().setPrevious(lastNext.getPrevious());
            if (next == lastReturned) {
                next = lastNext;
            }else {
                nextIndex--;
            }
            lastReturned = null;
        }
    }
}


