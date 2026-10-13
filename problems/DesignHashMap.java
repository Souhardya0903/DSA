/**
 * Implementation of a HashMap without using any built-in hash table libraries.
 * This implementation is similar to that of a HashSet, but it stores key-value pairs instead of just keys.
 * It uses separate chaining to handle hash collisions, where each bucket is a linked list of nodes
 */

class Node
{
    int key;
    int val;
    Node next;

    public Node(int key,int val)
    {
        this.key=key;
        this.val=val;
        this.next=null;
    }
}

class MyHashMap {

    public static final int size=1000000;
    Node[] hashMap=new Node[size];

    public MyHashMap() {

    }

    public void put(int key, int value) {
        int index=key%size;
        Node pointer=hashMap[index];
        while(pointer!=null)
        {
            if(pointer.key==key)
            {
                pointer.val=value;
                return;
            }
            pointer=pointer.next;
        }
        Node current=new Node(key,value);
        current.next=hashMap[index];
        hashMap[index]=current;
    }

    public int get(int key) {
        int index=key%size;
        Node pointer=hashMap[index];
        while(pointer!=null)
        {
            if(pointer.key==key)
            {
                return pointer.val;
            }
            pointer=pointer.next;
        }
        return -1;
    }

    public void remove(int key) {
        int index=key%size;
        Node pointer=hashMap[index];
        Node previous=null;
        while(pointer!=null)
        {
            if(pointer.key==key)
            {
                if(previous==null)
                    hashMap[index]=pointer.next;
                else
                    previous.next=pointer.next;

                return;
            }
            previous=pointer;
            pointer=pointer.next;
        }
        return;
    }
}
