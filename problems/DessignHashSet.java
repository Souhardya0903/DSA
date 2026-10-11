/**
 * Represents a single node in the bucket's linked list.
 * Stores the integer value and a reference to the next node.
 */
class Node {
    int val;
    Node next;

    public Node(int val) {
        this.val = val;
        this.next = null;
    }
}

/**
 * Implements a HashSet using separate chaining.
 * Uses a fixed-size array of linked list nodes to handle hash collisions.
 */
class MyHashSet {

    public static final int size = 10000;
    Node[] hashSet = new Node[size];

    public MyHashSet() {
        // Initializes the hash set array with empty buckets (nulls)
    }

    /**
     * Logic:
     * 1. Find the bucket index using modulo hashing (key % size).
     * 2. If the bucket is empty, place the new node directly.
     * 3. Otherwise, traverse the linked list:
     *    - If the key already exists, do nothing (sets contain unique values).
     *    - If we reach the end, append the new node.
     */
    public void add(int key) {
        Node pointer = hashSet[key % size];
        if (pointer == null) {
            hashSet[key % size] = new Node(key);
        } else {
            Node current = pointer;
            Node previous = current;
            while (current != null) {
                if (current.val == key)
                    return;
                previous = current;
                current = current.next;
            }
            previous.next = new Node(key);
        }
    }

    /**
     * Logic:
     * 1. Locate the correct bucket using the hash index.
     * 2. Traverse the linked list while keeping track of the previous node.
     * 3. When the key is found:
     *    - If it's the first node (head), update the bucket head pointer.
     *    - If it's in the middle/end, bypass it by linking previous to next.
     */
    public void remove(int key) {
        Node pointer = hashSet[key % size];
        Node previous = null;
        while (pointer != null) {
            if (pointer.val == key) {
                if (previous == null)
                    hashSet[key % size] = pointer.next;
                else
                    previous.next = pointer.next;
                return; // Exit immediately once removed
            }
            previous = pointer;
            pointer = pointer.next;
        }
    }

    /**
     * Logic:
     * 1. Go straight to the target bucket using the hash index.
     * 2. Walk through the linked list node by node.
     * 3. Return true if the key is found, or false if the end is reached without a match.
     */
    public boolean contains(int key) {
        Node pointer = hashSet[key % size];
        while (pointer != null) {
            if (pointer.val == key)
                return true;
            pointer = pointer.next;
        }
        return false;
    }
}