
import java.io.Serializable;



class Btreenode implements Serializable {

	//variables
	//N=1024 , 1024 = 4*nkeys + 4(2*nkeys-1) + 4*2*nkeys + 1, nkeys= 51
	public int nkeys= 51; //the minimum number of keys 
	public int[] keys;
	public Btreenode[] children; //array with pointers 
	public boolean leaf;
	
	
	//constructor
	public Btreenode(){
		keys=new int[2*nkeys-1];
		children=new Btreenode[2*nkeys];
	}


}
