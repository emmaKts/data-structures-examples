import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

public class MyClass {
	
	//MyClass variables
	private int buffer=1024; //array size
	private int startNumber;
	private int endNumber;
	private int N;
	AQueue queueArray;
	int [] array;


	//MyClass constructor
	public MyClass(int buffer,int startNumber,int endNumber,int N) throws IOException{
		this.buffer=buffer;
		this.startNumber=startNumber;
		this.endNumber=endNumber;
		this.N=N;
		boolean exit = false;
		String fileName = null;
		Scanner input = new Scanner(System.in) ;
		Scanner inputString = new Scanner(System.in) ;
		File MyFile= null;
        do {
            printMenu();
            System.out.println("Choose menu item: ");
            int menuItem = input.nextInt();
			switch (menuItem) {
            	case 0://"Create the file"
            		System.out.println("0. Create the file");
            		fileName= inputString.nextLine();
            		if (fileName==null)
            			fileName="numbers";
            		createFile(fileName);
                break;
                case 1://"Serial search - Print the average"
                	System.out.println("1. Serial search - Print the average");
                	System.out.println("Choose the file<press enter to use default file(numbers): ");
                	fileName= inputString.nextLine();
            		int sum1=0;
            		if (fileName.isEmpty()){
            			System.out.println("you did not give a filename,switching to default file \"numbers\"");
            			fileName="numbers";
            		}
            		MyFile=new File(fileName);
            		if(!MyFile.exists()){
            			System.out.println("File:"+fileName+" not found!aborting!");
            			break;
            		}
            		for(int i=0;i<20;i++)
            				sum1+=readFile1(fileName);
            			float average1=(float) (sum1/20.0f);
            			System.out.println("\n\nThe average 1 is: " +average1);
            			break;
                case 2://"Binary Search - Print the average"
                	System.out.println("2. Binary Search - Print the average");
                	System.out.println("Choose the file<press enter to use default file(numbers): ");
                	fileName= inputString.nextLine();
            		int sum2=0;
            		if (fileName.isEmpty()){
            			System.out.println("You did not give a filename,switching to default file \"numbers\"");
            			fileName="numbers";
            		}
            		MyFile = new File(fileName);
            		if(!MyFile.exists()){
            			System.out.println("File:"+fileName+" not found!aborting!");
            			break;
            		}
           			for(int i=0;i<20;i++)
           				sum2+=readFile2(fileName);
           			float average2=(float) (sum2/20.0f);
           			System.out.println("\n\nThe average 2 is: " +average2);
           			break;
                case 3://"Interpolation search - Print the average"
                	System.out.println("3. Interpolation search - Print the average");
                	System.out.println("Choose the file<press enter to use default file(numbers): ");
                 	fileName= inputString.nextLine();
             		int sum3=0;
             		if (fileName.isEmpty()){
             			System.out.println("You did not give a filename,switching to default file \"numbers\"");
             			fileName="numbers";
             		}
             		MyFile = new File(fileName);
             		if(!MyFile.exists()){
             			System.out.println("File:"+fileName+" not found!aborting!");
             			break;
             		}
           			for(int i=0;i<20;i++)
           				sum3+=readFile3(fileName);
           			float average3=(float) (sum3/20.0f);
      				System.out.println("\n\nThe average 3 is: " +average3);
                  	break;
                case 4://"Cache search - Print the average"
                	System.out.println("4. Cache search - Print the average");
                	System.out.println("Choose the file<press enter to use default file(numbers): ");
                	fileName= inputString.nextLine();
                	queueArray = new AQueue(100);
            		int sum4=0;
            		if (fileName.isEmpty()){
            			System.out.println("You did not give a filename,switching to default file \"numbers\"");
            			fileName="numbers";
            		}
            		MyFile =new File(fileName);
            		if(!MyFile.exists()){
            			System.out.println("File:"+fileName+" not found!aborting!");
            			break;
            		}
             		for(int i=0;i<20;i++)
             			sum4+=readFile4(fileName);
             		float average4=(float) (sum4/20.0f);
             		System.out.println("\n\nThe average 4 is: " +average4);
             		break;
                case 5://"Pointers search - Print the average"
                    System.out.println("5. Pointers search - Print the average");
                    System.out.println("Choose the file<press enter to use default file(numbers): ");
                    fileName= inputString.nextLine();
                    queueArray = new AQueue(100);
                    array=new int[this.buffer/4];
                    int sum5=0;
            		if (fileName.isEmpty()){
            			System.out.println("You did not give a filename,switching to default file \"numbers\"");
            			fileName="numbers";
            		}
            		MyFile =new File(fileName);
            		if(!MyFile.exists()){
            			System.out.println("File:"+fileName+" not found!aborting!");
            			break;
            		}
             		for(int i=0;i<20;i++)
             			sum5+=readFile5(fileName);
             		float average5=(float) (sum5/20.0f);
             		System.out.println("\n\nThe average 5 is: " +average5);
                  	break;
                case 6:
                	System.out.println("6. Exit");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!exit);
        System.out.println("Bye-bye!");
	 }

	private void createFile(String fileName) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream(this.buffer) ;
		 	DataOutputStream out = new DataOutputStream(bos);
			RandomAccessFile MyFile;
		 	try{
		 		MyFile = new RandomAccessFile (fileName, "rw");
		 		}catch(FileNotFoundException e){
		 		System.out.println("Wrong filename aborting!");
		 		return;
		 		}
		 	try{
		 		MyFile.seek(0);
		 		for (int i=0;i<N;i++){
		 			out.writeInt(startNumber+i);
		 			if ((i+1)%(this.buffer/4)==0){
		        		byte[] buf = bos.toByteArray();
		        		MyFile.write(buf);
		        		bos.reset();
		 			}
		 		}
		 		byte[] buf = bos.toByteArray();
		 		if (buf.length>0)
		        		MyFile.write(buf);
						out.close();
						MyFile.close();
		 		}catch(IOException e){
					System.out.println("Error--->"+e.toString());
		 		}
	}

	private int readFile1(String fileName){
		 	int access=0;
		    // read from file
			byte[] buf1 = new byte[this.buffer];
	 	 	ByteArrayInputStream bis= new ByteArrayInputStream(buf1);
	 	 	DataInputStream ois= new DataInputStream(bis);
	 	 	RandomAccessFile MyFile;
   	 	    try{
	 		    MyFile = new RandomAccessFile (fileName, "r");
			 	}catch(FileNotFoundException e){
			 	 	System.out.println("Wrong filename aborting!");
				 	return -1;
 	 		    }
		    try{
		    	//count the pages of the file -> length
			  	int fileSize=(int)MyFile.length();
			  	int pages=fileSize/this.buffer;
			  	if (fileSize%this.buffer!=0)
			  		pages++;
			  	//prints the pages -> System.out.println("Pages: " + pages);
			    //generates the random integer
		    	Random randomGenerator = new Random();
		   		int randomNumber =randomGenerator.nextInt(endNumber) + 1;
			  	//k=counter for pages
		    	for(int k=1;k<=pages;k++){
		    		MyFile.seek((k-1)*this.buffer);
		    		//how many bytes it reads
		    		int count = MyFile.read(buf1);
		    		access++;
		    		/* prints the integers of the k pages
		    		 * for(int i=0;i<count1/4;i++){
		    			System.out.println("\nInt" + k + " = " + ois.readInt());
		    		}*/
		    		//calls the binarySearch method
		    		int index= binarySearch(convertByteArrayToIntArray(buf1), randomNumber);
		    		if (index != -1) {
		    			return access;
		    		}
		    		ois.reset();
		    	}
		    	MyFile.close();
	   		    }catch(IOException e){
			 		System.out.println("Error1--->"+e.toString());
	   		    }
		    return access;
	}

	private int readFile2(String fileName){
		int access=0,minPageNumber=0,maxPageNumber=0,startPage=1,endPage=0,midPage,pages=0;
	    // read from file
		byte[] buf1 = new byte[this.buffer];
 	 	ByteArrayInputStream bis= new ByteArrayInputStream(buf1);
 	 	DataInputStream ois= new DataInputStream(bis);
 	 	RandomAccessFile rand;
	 	    try{
	 	    	rand = new RandomAccessFile (fileName, "r");
		 		}catch(FileNotFoundException e){
		 	 	System.out.println("Wrong filename aborting!");
			 	return -1;
	 		}
	 	    try{
	 	    	//count the pages of the file -> length
	 	    	int fileSize=(int)rand.length();
	 	    	pages=fileSize/this.buffer;
	 	    	if (fileSize%this.buffer!=0)
	 	    		pages++;
	 	    	endPage=pages;
	 	    	midPage=(pages+1)/2;
	 	    	//prints the pages -> System.out.println("Pages: " + pages);
	 	    	//generates the random integer
	 	    	Random randomGenerator = new Random();
	 	    	int randomNumber = randomGenerator.nextInt(endNumber) + 1;
	 	    	do{
	 	    		rand.seek((midPage-1)*this.buffer);
	 	    		//how many bytes it reads
	 	    		int count = rand.read(buf1);
	 	    		access++;
	 	    		int[] intValues=convertByteArrayToIntArray(buf1);
	 	    		int index= binarySearch(intValues, randomNumber);
	 	    		if (index != -1) {
	 	    			return access;
	 	    		}
	 	    		minPageNumber=intValues[0];
	 	    		maxPageNumber=intValues[intValues.length-1];
	 	    		if (randomNumber>minPageNumber && randomNumber<maxPageNumber)
	 	    			return access;
	 	    		if (randomNumber<minPageNumber){
	 	    			endPage=midPage;
	 	    			midPage=(midPage+startPage)/2;
	 	    		}
		    		//if (midPage%2!=0) mid2Page=(midPage/2)+1; else mid2Page=midPage/2;
	 	    		else if(randomNumber>maxPageNumber){
	 	    			startPage=midPage;
	 	    			midPage=(endPage+midPage)/2;
	 	    			/* a 2nd way :
	 	    			int counter=pages-midPage+1;
	 	    			if (counter%2!=0) mid2Page=midPage+counter/2; else mid2Page=midPage+(counter/2)-1;
	 	    			*/
	 	    		}
	 	    		ois.reset();
	 	    	}while (startPage!=endPage);
	 	    	rand.close();
	   		    }catch(IOException e){
			 		System.out.println("Error2--->"+e.toString());
	   		    }
	 	    return access;
	}

	private int readFile3(String fileName){
		int access=0,minPageNumber=0,maxPageNumber=0,startPage=1,endPage=0,pages=0;
	    // read from file
		byte[] buf1 = new byte[this.buffer];
 	 	ByteArrayInputStream bis= new ByteArrayInputStream(buf1);
 	 	DataInputStream ois= new DataInputStream(bis);
 	 	RandomAccessFile rand;
	 	    try{
	 	    	rand = new RandomAccessFile (fileName, "r");
		 		}catch(FileNotFoundException e){
		 	 	System.out.println("Wrong filename aborting!");
			 	return -1;
		 		}
	 	    try{
	 	    	//count the pages of the file -> length
	 	    	int fileSize=(int)rand.length();
	 	    	pages=fileSize/this.buffer;
	 	    	if (fileSize%this.buffer!=0)
	 	    		pages++;
	 	    	endPage=pages;
	 	    	//prints the pages -> // System.out.println("Pages: " + pages);
	 	    	//generates the random integer
	 	    	Random randomGenerator = new Random();
	 	    	int randomNumber = randomGenerator.nextInt(endNumber) + 1;
	 	    	//interpolation search
	 	    	int h=(int)Math.ceil((((double)randomNumber-startNumber)*pages)/(endNumber-startNumber));
	 	    	do{
	 	    		if(randomNumber==startNumber){h++;}
	 	    		rand.seek((h-1)*this.buffer);
	 	    		//how many bytes it reads
	 	    		int count = rand.read(buf1);
	 	    		access++;
	 	    		int[] intValues=convertByteArrayToIntArray(buf1);
	 	    		int index= binarySearch(intValues, randomNumber);
	 	    		if (index != -1) {
	 	    			return access;
	 	    		}
	 	    		minPageNumber=intValues[0];
	 	    		maxPageNumber=intValues[intValues.length-1];
	 	    		if (randomNumber>minPageNumber && randomNumber<maxPageNumber)
	 	    			return access;
	 	    		if (randomNumber<minPageNumber){
	 	    			pages=endPage=h;
	 	    			endNumber=maxPageNumber;
	 	    			h=(int) (startPage+(((long)randomNumber-startNumber)*endPage)/(endNumber-startNumber));
	 	    		}
		    		//if (midPage%2!=0) mid2Page=(midPage/2)+1; else mid2Page=midPage/2;
	 	    		else if(randomNumber>maxPageNumber){
	 	    			startNumber=minPageNumber;
	 	    			pages=pages-h+1;
		 	    		h=(int) (startPage+(((long)randomNumber-startNumber)*pages)/(endNumber-startNumber));
	 	    			//int counter=pages-midPage+1;
	 	    			//if (counter%2!=0) mid2Page=midPage+counter/2; else mid2Page=midPage+(counter/2)-1;
	 	    		}
	 	    		ois.reset();
	 	    	}while (endNumber!=startNumber && startPage!=endPage);
	 	    	rand.close();
	   		    }catch(IOException e){
			 		System.out.println("Error3--->"+e.toString());
	   		    }
	 	    return access;
	}

	private int readFile4(String fileName){
		int index,count,h,access=0,minPageNumber=0,maxPageNumber=0,startPage=1,endPage=0,pages=0;
	    // read from file
		byte[] buf1 = new byte[this.buffer];
 	 	ByteArrayInputStream bis= new ByteArrayInputStream(buf1);
 	 	DataInputStream ois= new DataInputStream(bis);
 	 	RandomAccessFile rand;
	 	    try{
	 	    	rand = new RandomAccessFile (fileName, "r");
		 		}catch(FileNotFoundException e){
		 	 	System.out.println("Wrong filename aborting!");
			 	return -1;
		 		}
	 	    try{
	 	    	//count the pages of the file -> length
	 	    	int fileSize=(int)rand.length();
	 	    	pages=fileSize/this.buffer;
	 	    	if (fileSize%this.buffer!=0)
	 	    		pages++;
	 	    	//prints the pages -> // System.out.println("Pages: " + pages);
	 	    	//generates the random integer
	 	    	Random randomGenerator = new Random();
	 	    	int randomNumber = randomGenerator.nextInt(endNumber) + 1;
	 	    	h=(int)Math.ceil((((double)randomNumber-startNumber)*pages)/(endNumber-startNumber));
	 	    	do{
	 	    		if (queueArray.isEmpty()){
	 	    			if(randomNumber==startNumber){h++;}
	 	   				rand.seek((h-1)*this.buffer);
	 	   				count =rand.read(buf1);
	 	   				access++;
	 	   				QueueType it=new QueueType(h,convertByteArrayToIntArray(buf1));
	     				queueArray.enqueue(it);
	    				index= binarySearch(convertByteArrayToIntArray(buf1), randomNumber);
	    				if (index != -1) {
	    					return access;
	    				}
	    				int[] intValues=convertByteArrayToIntArray(buf1);
	 	    			minPageNumber=intValues[0];
	 	    			maxPageNumber=intValues[intValues.length-1];
	 	    			if (randomNumber>minPageNumber && randomNumber<maxPageNumber)
	 	   					return access;
	 	   				if (randomNumber<minPageNumber){
	 	   					pages=endPage=h;
	 	   					endNumber=maxPageNumber;
	     					h=(int) (startPage+(((long)randomNumber-startNumber)*endPage)/(endNumber-startNumber));
	    				}
	 	    			else if(randomNumber>maxPageNumber){
	 	   					startNumber=minPageNumber;
	 	   					pages=pages-h+1;
	 	   					h=(int) (startPage+(((long)randomNumber-startNumber)*pages)/(endNumber-startNumber));
	 	    			}
	     				ois.reset();
	 	    		}else{
	 	    			if(queueArray.containsPage(h)){
	 	    				index= binarySearch(queueArray.getPageBuffer(h), randomNumber);
	 	    				if (index != -1)
	 	    					return access;
	 	    			}else{
	 	    				 if(randomNumber==startNumber){h++;}
	 	    				 rand.seek((h-1)*this.buffer);
	 	    				 count =rand.read(buf1);
	 	    				 access++;
	 	    				 QueueType at = new QueueType(h,convertByteArrayToIntArray(buf1));
	 	    				 if(queueArray.isFull()){
	 	    				 	queueArray.dequeue();
	 	    				 	queueArray.enqueue(at);
	 	    				 }
	 	    				 else
	 	    					 queueArray.enqueue(at);
	 	    				 index= binarySearch(convertByteArrayToIntArray(buf1), randomNumber);
	 	    				 if (index != -1){
	 	    					 return access;
	 	    				 }
	 	    				 int[] intValues=convertByteArrayToIntArray(buf1);
	 	    				 minPageNumber=intValues[0];
	 	    				 maxPageNumber=intValues[intValues.length-1];
	 	    				if (randomNumber>minPageNumber && randomNumber<maxPageNumber)
	 	    					return access;
	 	    				if (randomNumber<minPageNumber){
	 	    					pages=endPage=h;
	 	    					endNumber=maxPageNumber;
	 	    					h=(int) (startPage+(((long)randomNumber-startNumber)*endPage)/(endNumber-startNumber));
	 	    				}
	 	    				else if(randomNumber>maxPageNumber){
	 	    					startNumber=minPageNumber;
	 	    					pages=pages-h+1;
	 	    					h=(int) (startPage+(((long)randomNumber-startNumber)*pages)/(endNumber-startNumber));
	 	    				}
	 	    				ois.reset();
	 	    			}
	 	    		}
	 	    			}while (endNumber!=startNumber && startPage!=endPage);
	 	    			 rand.close();
	 	    	}catch(IOException e){
		 		System.out.println("Error4--->"+e.toString());
	 	    	}
 	    return access;
	}

	private int readFile5(String fileName){
		int index,count,max,h,access=0,minPageNumber=0,maxPageNumber=0,startPage=1,endPage=0,pages=0;
	    // read from file
		byte[] buf1 = new byte[this.buffer];
 	 	ByteArrayInputStream bis= new ByteArrayInputStream(buf1);
 	 	DataInputStream ois= new DataInputStream(bis);
 	 	RandomAccessFile rand;
 	 	Random randomGenerator = new Random();
 	 	int randomNumber = randomGenerator.nextInt(endNumber) + 1;
	 	try{
                    rand = new RandomAccessFile (fileName, "r");
		}catch(FileNotFoundException e){
                    System.out.println("Wrong filename aborting!");
                    return -1;
		}
	 	try{
	 	    	//count the pages of the file -> length
	 	    	int fileSize=(int)rand.length();
	 	    	pages=fileSize/this.buffer;
	 	    	if (fileSize%this.buffer!=0)
	 	    		pages++;
	 	    	//prints the pages -> System.out.println("Pages: " + pages);
	 	    	//generates the random integer
	 	    	for(int i=0;i<256;i++){
	 	    		h=i*((pages/(256))-1);
	 	 	   		rand.seek(h*this.buffer);
	 	 	   		count =rand.read(buf1);
		 	   		access++;
		 	   		int[] intValues=convertByteArrayToIntArray(buf1);
		 	   	    array[i]=intValues[intValues.length-1];
	 	    		ois.reset();
	 	    	}
	 	    	for (int j=0;j<256;j++){
	 	    		if (array[j]<=randomNumber){
	 	    			startPage=j*((pages/256)-1);
	 	    			startNumber=array[j];
	 	    		}
	 	    		if (array[j]>=randomNumber){
	 	    		 	endPage=j*((pages/256)-1);
	 	    		 	endNumber=array[j];
	 	    		 	break;
	 	    		}
	 	    	}
	 	    	pages=endPage-startPage+1;
	 	    	h=(int)Math.ceil((((double)randomNumber-startNumber)*pages)/(endNumber-startNumber))+startPage;
	 	    	do{
	 	    		if (queueArray.isEmpty()){
	 	    			rand.seek(h*this.buffer);
	 	    			count =rand.read(buf1);
	 	    			access++;
	 	    			QueueType it=new QueueType(h,convertByteArrayToIntArray(buf1));
	 	    			queueArray.enqueue(it);
	 	    			index= binarySearch(convertByteArrayToIntArray(buf1), randomNumber);
	 	    			if (index != -1) {
	 	    				return access;
	 	    			}
	 	    			int[] intValues=convertByteArrayToIntArray(buf1);
	 	    			minPageNumber=intValues[0];
	 	    			maxPageNumber=intValues[intValues.length-1];
	 	    			if (randomNumber>=minPageNumber && randomNumber<=maxPageNumber)
	 	    				return access;
	 	    			if (randomNumber<minPageNumber){
	 	    				endPage=h;
	 	    				pages=endPage-startPage+1;
	 	    				endNumber=maxPageNumber;
	 	    				h=startPage+(int)Math.ceil( ((randomNumber-startNumber) *pages)/(endNumber-startNumber));
	 	    			}else if(randomNumber>maxPageNumber){
	 	    				startNumber=minPageNumber;
	 	    				startPage=h;
	 	    				pages=endPage-startPage+1;
	 	    				h=startPage+(int)Math.ceil( ((randomNumber-startNumber) *pages)/(endNumber-startNumber));
	 	    			}
	 	    			ois.reset();
	 	    		}else{
	 	    			if(queueArray.containsPage(h)){
	 	    				index= binarySearch(queueArray.getPageBuffer(h), randomNumber);
	 	    				if (index != -1)
	 	    					return access;
	 	    			}else{
	 	    				rand.seek((h-1)*this.buffer);
	 	    				count =rand.read(buf1);
	 	    				access++;
	 	    				QueueType at = new QueueType(h,convertByteArrayToIntArray(buf1));
	 	    				if(queueArray.isFull()){
	 	    					queueArray.dequeue();
	 	    					queueArray.enqueue(at);
	 	    				}
	 	    				else
	 	    					queueArray.enqueue(at);
	 	    				index= binarySearch(convertByteArrayToIntArray(buf1), randomNumber);
	 	    				if (index != -1){
	 	    					return access;
	 	    				}
                                    }
	 	    				int[] intValues=convertByteArrayToIntArray(buf1);
	 	    				minPageNumber=intValues[0];
	 	    				maxPageNumber=intValues[intValues.length-1];
	 	    				if (randomNumber>=minPageNumber && randomNumber<=maxPageNumber)
	 	    					return access;
	 	    				if (randomNumber<minPageNumber){
	 	    					endPage=h;
	 	    					pages=endPage-startPage+1;
	 	    					endNumber=maxPageNumber;
	 	    					h=startPage+(int)Math.ceil( ((randomNumber-startNumber) *pages)/(endNumber-startNumber));
	 	    				}else if(randomNumber>maxPageNumber){
	 	    					startNumber=minPageNumber;
	 	    					startPage=h;
	 	    					pages=endPage-startPage+1;
                                                        h=startPage+(int)Math.ceil( ((randomNumber-startNumber) *pages)/(endNumber-startNumber));
	 	    				        if (h==startPage && startPage<endPage)
                                                            h++;
                                                }
 	    				ois.reset();
	 	    			}
	 	    		
	 	    	}while (endNumber!=startNumber && startPage!=endPage);
 	    		rand.close();
	 	    }catch(IOException e){
	 		  System.out.println("Error5--->"+e.toString());
	 	    }
	 		return access;
	}


	private int[] convertByteArrayToIntArray(byte[] array1){
		 	int [] array2=new int[array1.length/4];
		 	ByteArrayInputStream bis= new ByteArrayInputStream(array1);
		 	DataInputStream ois= new DataInputStream(bis);
		 	int count=0;
		 	for (int i=0;i<array1.length/4;i++){
		 		try{
		 			array2[count++]=ois.readInt();
		 		}catch(IOException e){
		 			System.out.println("Error!");
		 			return null;
		 		}
		 	}
		 	return array2;
	}

	//http://www.roseindia.net/tutorial/java/core/binarySearch.html
	public int binarySearch(int[] buf1, int randomKey){
	       int start, end, middle;
		   start = 0;
	       end = buf1.length-1;
	       while (start <= end) {
	       	    middle = (start + end) / 2;
	      	 	if (buf1[middle] == randomKey) {
		            return middle;
	                } else if (buf1[middle] < randomKey) {
	                	start = middle + 1;
		            } else {
		           		end = middle - 1;
		        }
		   }
	       return -1;
	}

	private void printMenu() {
        System.out.println("\n==============================================");
        System.out.println("|        THIS IS THE MENU                     |");
        System.out.println("==============================================");
        System.out.println("|  Options:                                   |");
        System.out.println("0. Create the file");
        System.out.println("1. Serial search - Print the average");
        System.out.println("2. Binary Search - Print the average");
        System.out.println("3. Interpolation search - Print the average");
        System.out.println("4. Cache search - Print the average");
        System.out.println("5. Pointers search - Print the average");
        System.out.println("6. Exit");
        System.out.println("==============================================");
    }

	//main
	public static void main(String[] args) throws IOException {
				new MyClass(1024,1,(int)Math.pow(10,7),(int)Math.pow(10,7));
	}
}









