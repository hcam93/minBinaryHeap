

public class Node<E> {

    Node<E> left;
    Node<E> right;
    E value;

    public Node(E value){
        this.value = value;
        left = null;
        right = null;
    }

    public E getValue(Node<E> node){
        return node.value;
    }

    public static void main(String[] args) {
        
    }

}
