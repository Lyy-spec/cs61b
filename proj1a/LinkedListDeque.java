/**
 * @description:
 * @author: Lyq
 */

public class LinkedListDeque<T> {
    private final Node sentinel;
    private Node last;
    private int size;

    class Node{
        Node pre;
        Node next;
        T item;

        public Node(Node pre,Node next,T item){
            this.pre = pre;
            this.next = next;
            this.item = item;
        }
    }

    public LinkedListDeque(){
        sentinel = new Node(null,null,null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        last = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque<T> other){
        sentinel = new Node(null,null,null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        last = sentinel;
        size = 0;
        Node cur = sentinel;
        Node p = other.sentinel.next;
        while (p != other.sentinel){
            cur.next = new Node(cur,sentinel,p.item);
            p = p.next;
            cur = cur.next;
        }
        size = other.size;
        last = cur;
    }

    public void addFirst(T item){
        Node node = new Node(sentinel,sentinel.next,item);
        if(sentinel.next == sentinel){
            last = node;
        }
        sentinel.next.pre = node;
        sentinel.next = node;
        size++;

    }

    public void addLast(T item){
        last.next = new Node(last,sentinel,item);
        last = last.next;
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node cur = sentinel.next;
        for(int i = 0;i < size;i++){
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        Node node = sentinel.next;
        sentinel.next = node.next;
        node.next.pre = sentinel;
        size--;
        return node.item;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        Node node = last;
        node.pre.next = sentinel;
        size--;
        return node.item;
    }

    public T get(int index){
        if(index < 0 || index >= size){
            return null;
        }
        Node node = sentinel;
        for(int i = 0;i <= index;i++){
            node = node.next;
        }
        return node.item;
    }

    public T getRecursive(int index){
        return getRecursiveHelper(sentinel,index);
    }

    public T getRecursiveHelper(Node node,int index){
        if(index < 0 || index >= size){
            return null;
        }
        if(index == 0){
            return node.next.item;
        }else{
            node = node.next;
            return getRecursiveHelper(node,index - 1);
        }
    }
}
