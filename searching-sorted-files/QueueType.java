

public class QueueType {
	
	//variables
	int pageNumber;
	int[] buffer=new int[256];
	
	
	public QueueType(int pageNumber, int[] buffer){
		this.pageNumber=pageNumber;
		this.buffer=buffer;
	}

}
