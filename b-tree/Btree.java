import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Btree implements Serializable{

	//variables
	private int n;
	private int N; //DataPageSize=1024
	Btreenode root;
	
	//constructor
	public Btree(int n,int N) throws ClassNotFoundException, IOException{
		this.setN(n);
		this.N=N;
		root=new Btreenode();
		root.leaf=true;
		boolean exit = false;
		Scanner input = new Scanner(System.in);
		Scanner inputString = new Scanner(System.in) ;
        do {
            printMenu();
            System.out.println("Name of the file: ");
            String fileName = inputString.nextLine();
            System.out.println("Choose menu item: ");
            int menuItem = input.nextInt();
			switch (menuItem) {
            	case 0://"Insert key in the BTree"
            		 System.out.println("0.Insert key in the BTree");
            		 System.out.println("Insert: ");
            		 int iKey = input.nextInt();
            		 add(iKey,fileName);
                break;
                case 1://"Search key in the BTree"
                	 System.out.println("1.Search key in the BTree");
                	 System.out.println("Search: ");
                	 int sKey = input.nextInt();
                	 search(sKey,fileName);
            	break;
                case 2://"Print the average"
                	 System.out.println("2.Print the average");
                	 Random rand = new Random(); 
                	 int M=input.nextInt(); //M = Math.pow(10,x), x=3, 4, 5, 6
            		 for (int j=0;j<M;j++)
            		 {  
            	       	add(rand.nextInt(),fileName);
            		 }
            		 int sum=0;
            		 for(int j=0;j<50;j++){
            			 sum+=search(rand.nextInt(),fileName);
            		 }
            		 float average=(float) (sum/50.0f);
            		 System.out.println("The average is: " +average);
            		 
           		break;
                default:
                    System.out.println("Invalid choice.");
			}
        } while (!exit);
        System.out.println("Bye-bye!");
	}
	
	//add method
	public void add(int key, String fileName) throws ClassNotFoundException, IOException{
		Btreenode rootNode=root;
		if(!update(root,key,fileName)){
			if(rootNode.nkeys==(2*n-1)){
				Btreenode newRoot=new Btreenode();
				root=newRoot;
				newRoot.leaf=false;
				root.children[0]=rootNode;
				split(newRoot, 0, rootNode); //split rootNode and move its median into newRoot
				insert(newRoot,key,fileName); //insert the key into Btree with root newRoot
				diskWrite(fileName, root);
			}else{
				insert(rootNode,key,fileName); //insert the key into Btree with root rootNode
				diskWrite(fileName, rootNode);
			}
		}
	}
	
	//insert an element into the Btree -> leaf node
	public void insert(Btreenode node, int key, String fileName) throws ClassNotFoundException, IOException{
			diskRead(fileName, node);
			int i=node.nkeys-1;
			if(node.leaf){
				//since the node is not a full node insert the new element
				while(i>=0 && key<node.keys[i]){
					node.keys[i+1]=node.keys[i];
					i--;
				}
				i++;
				node.keys[i]=key;
				node.nkeys++;
			}else{
				//move back from the last key node until we find the child pointer
				//that is the new root node of the subtree where the new element should be placed
				while(i>=0 && key < node.keys[i]){
					i--;
				}
				i++;
				if(node.children[i].nkeys==(2*n-1)){
					split(node,i,node.children[i]);
					if (key>node.keys[i])
						i++;
				}
				insert(node.children[i], key,fileName);
				diskWrite(fileName,node);
		}
	}

	//search method
	public int helpSearch(Btreenode node, int key,String fileName) throws ClassNotFoundException, IOException{
		int access=0;
		diskRead(fileName,node);
		while(node!=null){
			access++;
			int i=0;
			while(i<node.nkeys && key>node.keys[i]){
				i++;
			}
			if(i<node.nkeys && key==node.keys[i]){
				return access;
			}
			if(node.leaf){
				return access;
			}else{
				node=node.children[i];
			}
		}
		return access;
	}
	
	public int search(int key, String fileName) throws ClassNotFoundException, IOException{
		return helpSearch(root , key, fileName);
	}
	
	private boolean update(Btreenode node, int key,String fileName) throws ClassNotFoundException, IOException{
		diskRead(fileName, node);
		while(node!=null){
			int i=0;
			while(i<node.nkeys && key>node.keys[i]){
				i++;
			}
			if(i<node.nkeys && key==node.keys[i]){
				return true;
			}
			if(node.leaf){
				return false;
			}else{
				node=node.children[i];
			}
		}
		return false;
	}
	
	//split method called only if node is full
	public void split(Btreenode parent, int i, Btreenode node){
		Btreenode newNode=new Btreenode();
		newNode.leaf=node.leaf;
		newNode.nkeys=n-1;
		for(int j=0;j<n-1;j++){//copy the last n-1 elements of node into newNode
			newNode.keys[j]=node.keys[j];
		}
		if(!newNode.leaf){
			for(int j=0;j<n-1;j++){
				newNode.children[j]=node.children[j];
			}
			for (int j=n;j<=node.nkeys;j++){
				node.children[j]=null;
			}
		}
		for(int j=n;j<node.nkeys;j++){
			node.keys[j]=0;
		}
		node.nkeys=n-1;
	}
	
	public void diskWrite(String fileName,Btreenode node){
			ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
			ObjectOutputStream out;
			RandomAccessFile MyFile;
			try {
				out = new ObjectOutputStream(bos);
				out.writeObject(root);
				out.close();
				byte[] buf = bos.toByteArray();
			    if (root != null) {
			    	MyFile = new RandomAccessFile (fileName, "rw");
			    	long pos = 0;
			    	MyFile.seek(pos);
			    	MyFile.write(buf);
			    	pos += buf.length;
			    	MyFile.close();
			    }
			}catch (IOException e) {
			    System.out.println("Error--->"+e.toString());
			}
	}
	
	public Btreenode diskRead(String fileName, Btreenode node) throws ClassNotFoundException {
		RandomAccessFile MyFile;
		try {
			MyFile = new RandomAccessFile (fileName, "r");
			long pos=MyFile.length();
			if(pos==0)
				return null;
			else{
				try {
					byte[] buf=new byte[N];
					pos-=N;
					MyFile.seek(pos);
					MyFile.read(buf);
					ByteArrayInputStream bis = new ByteArrayInputStream(buf);
				    ObjectInputStream in = new ObjectInputStream(bis);
				    MyFile.close();
				    return (Btreenode) in.readObject();
					} catch (IOException e) {
						System.out.println("Error--->"+e.toString());
					}
			}
		} catch (IOException w) {
			System.out.println("Wrong filename aborting!");
			return null;
		}
		return null;
}
	
	private void printMenu() {
        System.out.println("\n==============================================");
        System.out.println("|        THIS IS THE MENU                     |");
        System.out.println("==============================================");
        System.out.println("|  Options:                                   |");
        System.out.println("0.Insert key in the BTree");
        System.out.println("1.Search key in the BTree");
        System.out.println("2.Print the average");
        System.out.println("3. Exit");
        System.out.println("==============================================");
    }
	
	//setters and getters
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	
	//main
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		new Btree(4,1024);
	}
	
}
