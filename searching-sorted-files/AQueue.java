import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public final class AQueue implements Queue {

	//variables
	private int defaultSize = 100;
    private int count = 0;
	private int size; // Maximum size of queue
	private int front; // Index prior to front item
	private int rear; // Index of rear item
    private LinkedList<QueueType> data = new LinkedList<QueueType>();
    
    // Constructor: default size
	public AQueue(){
            this.size=this.defaultSize;
        } 

	// Constructor: set size
	public AQueue(int sz){
            this.size=sz;
    } 
	
	//methods named by what they do
	
	public void clear(){// Remove all Objects from queue
            data = new LinkedList<QueueType>();
    }

	public void enqueue(QueueType item) {
           data.addLast(item);
           if(data.size()>size)
              data.removeFirst();
    }

    public QueueType dequeue() {
          return data.removeFirst();
    }

	public QueueType firstValue(){ // Return value of front Object
		return data.peekFirst();
	}
	
    public boolean isEmpty() {
    	return data.isEmpty();
    }


	//unimplemented methods
	public boolean add(Object e) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean offer(Object e) {
		 throw new UnsupportedOperationException("Not supported yet.");
	}

	public int size() {
         return data.size();
    }
	
	public boolean contains(Object o) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Iterator iterator() {
		 throw new UnsupportedOperationException("Not supported yet.");
	}

	public Object[] toArray() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public Object[] toArray(Object[] a) {
		 throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean remove(Object o) {
		 throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean containsAll(Collection c) {
		 throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean addAll(Collection c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean removeAll(Collection c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean retainAll(Collection c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean containsPage(int pageNo){
          for(QueueType data1:data){
              if(data1.pageNumber==pageNo)
                  return true;
          }
          return false;
	}

	public int[] getPageBuffer(int pageNo){
           for(QueueType data1:data){
               if(data1.pageNumber==pageNo)
                   return data1.buffer;
           }
           return null;
	}

	public boolean isFull() {
		if (data.size()==size)
			return true;
		else
			return false;
	}

    public boolean add(QueueType e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean offer(QueueType e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object poll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object element() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object peek() {
        return data.getFirst();
    }

}// class AQueue
