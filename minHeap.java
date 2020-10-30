
import java.util.Collections;
import java.util.Comparator;


class minHeap<E> implements heapInterface<E> {
    
    int size;
    Node<E>[] data;
    private Comparator<E> comparator; 
    

    public minHeap(Comparator<E> compare, E fieldType) {
        size = 0;
        this.data = (Node<E>[]) new Object[10];
        this.comparator = compare;
    }

    public void add(Node<E> node){
                     
        if(size == data.length){
            increaseListSize();
        }
        size++;
        data[size - 1] = node;
        percUp(size - 1);
    }

    public Node<E> remove(){
        if(size == 0){
            return null;
        }
        Node<E> min = data[0];
        data[0] = data[size - 1];
        data[size - 1] = null;
        size--;

        percDown(0);
        return min;
    }

    public Node<E> getRoot(){return size != 0 ? data[0] : null;}

    public int size(){return size;}

    public void percUp(int nodeLocation){

        int parentLocation = Math.floorDiv(nodeLocation - 1, 2);
        if( parentLocation >= 0){
           E parent = data[Math.floorDiv(nodeLocation - 1, 2)].value;
           if(comparator.compare(parent, data[nodeLocation].value) > 0){
                swap(parentLocation, nodeLocation);
                percUp(parentLocation);
           }
        }
    }

    public void percDown(int index){

        if(size == 1 || index >= Math.floorDiv(size, 2) + 1){ return; }

        int smallestIndex = 0;
        int childIndex = (index * 2) + 1;
        int[] childrenIndexArray = new int[2];
        E smallestChild = null;
        short childrenCounter = 0;
        //stores childrenIndex that are valid
        for(int i = 0; i < 2; i++){
            if(childIndex + i >= size){
                childrenIndexArray[i] = -1;
            }else {
                childrenIndexArray[i] = childIndex + i;
                childrenCounter++;
                if(smallestChild == null){
                    smallestChild = data[childrenIndexArray[i]].value;
                    smallestIndex = childrenIndexArray[i];
                }
                if(comparator.compare(smallestChild, data[childrenIndexArray[i]].value) > 0){
                    smallestChild = data[childrenIndexArray[i]].value;
                    smallestIndex = childrenIndexArray[i];
                }
            }
        }
        if(childrenCounter == 0) {return; }
        if(comparator.compare(smallestChild, data[index].value) < 0){ swap(smallestIndex, index); }
        percDown(smallestIndex);
    }

    public void increaseListSize(){
        Node<E>[] newArray = (Node<E>[]) new Object[size * 2];
        for(int i = 0; i < size; i++){
            newArray[i] = data[i];
        }
        data = newArray;
    }

    public void swap(int thisLocation, int thatLocation){
        Node<E> temp = data[thisLocation];
        data[thisLocation] = data[thatLocation];
        data[thatLocation] = temp; 
    }

    public void clear(){
        size = 0;
        this.data = (Node<E>[]) new Object[10];
    }


    
}
