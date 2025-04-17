package com.yaksha.assignment;

import java.util.LinkedList;

public class LinkedListOperations {

    public static void main(String[] args) {
        // **Creating a LinkedList**
        LinkedList<Integer> linkedList = new LinkedList<>();

        // **Adding items to LinkedList using addFirst() and addLast()**
        linkedList.addFirst(3);
        linkedList.addLast(1);
        linkedList.addLast(4);
        linkedList.addFirst(2);
        System.out.println("LinkedList after adding items using addFirst() and addLast(): " + linkedList);

        // **Removing items from LinkedList using removeFirst() and removeLast()**
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println("LinkedList after removing items using removeFirst() and removeLast(): " + linkedList);

        // **Getting first and last items using getFirst() and getLast()**
        Integer firstItem = linkedList.getFirst();
        Integer lastItem = linkedList.getLast();
        System.out.println("First item: " + firstItem + ", Last item: " + lastItem);
    }
}
