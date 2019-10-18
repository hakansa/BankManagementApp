import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class FileIO {
	private static Customer[] customerArr = new Customer[1000];
	private static Transaction[] array_01 = new Transaction[100];
	private static Transaction[] array_02 = new Transaction[100];
	private static Transaction[] array_03 = new Transaction[100];
	private static Transaction[] array_04 = new Transaction[100];
	private static Transaction[] array_05 = new Transaction[100];
	private static TransactionQueue queue_01= new TransactionQueue("01.05.2019");
	private static TransactionQueue queue_02= new TransactionQueue("02.05.2019");
	private static TransactionQueue queue_03= new TransactionQueue("03.05.2019");
	private static TransactionQueue queue_04= new TransactionQueue("04.05.2019");
	private static TransactionQueue queue_05= new TransactionQueue("05.05.2019");
	private static TransactionQueueList queueList = new TransactionQueueList();
	public static void getCustomers() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"src/CENG112_HW3_Transactions.txt"));
			
			String line = reader.readLine();
			String[] transactionArr=null;
			int indexOfArr=0;
			while (line != null) {
				
				transactionArr=line.split(",");
				
				if (transactionArr[2].equals("NON-REGISTERED")) {
					transactionArr[2]="NON-REGISTERED";
				}else if (transactionArr[2].equals("INDIVIDUAL")) {
					transactionArr[2]="INDIVIDUAL";
				}else if (transactionArr[2].equals("CORPORATE")) {
					transactionArr[2]="CORPORATE";
				}
				
				ICustomer newCustomer = new Customer(transactionArr[2]);
				
				
				customerArr[indexOfArr]=(Customer) newCustomer;
				indexOfArr+=1;
				Transaction newTransaction = new Transaction(Integer.parseInt(transactionArr[1]), newCustomer, Integer.parseInt(transactionArr[3]), null);
				// Date : 01.05.2019
				if(transactionArr[0].equals("01.05.2019")) {
					appendTransactionToArray(array_01, newTransaction);
				}else if(transactionArr[0].equals("02.05.2019")) {
					appendTransactionToArray(array_02, newTransaction);
				}else if(transactionArr[0].equals("03.05.2019")) {
					appendTransactionToArray(array_03, newTransaction);
				}else if(transactionArr[0].equals("04.05.2019")) {
					appendTransactionToArray(array_04, newTransaction);
				}else if(transactionArr[0].equals("05.05.2019")) {
					appendTransactionToArray(array_05, newTransaction);
				}
				// read next line
				line = reader.readLine();
			}
			
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		bubbleSort(array_01);
		bubbleSort(array_02);
		bubbleSort(array_03);
		bubbleSort(array_04);
		bubbleSort(array_05);
		
		connectTransactions(array_01,queue_01);
		connectTransactions(array_02,queue_02);
		connectTransactions(array_03,queue_03);
		connectTransactions(array_04,queue_04);
		connectTransactions(array_05,queue_05);
		
		queueList.insertTo(queue_01);
		queueList.insertTo(queue_02);
		queueList.insertTo(queue_03);
		queueList.insertTo(queue_04);
		queueList.insertTo(queue_05);
		
		
		// REPORTING
		
			// COUNTER
		System.out.println(queueList.toString());
		
			// Total Transaction Count in 01.05.2019
			System.out.println("Total transaction count in "+queueList.getHead().getDate()+" : "+queueList.getHead().getQueueLength());
			// Total Transaction Count in 02.05.2019
			System.out.println("Total transaction count in "+queueList.getHead().getNext().getDate()+" : "+queueList.getHead().getNext().getQueueLength());
			
			// Total Waiting Time in 01.05.2019
			System.out.println("Total waiting time in "+queueList.getHead().getDate()+" : "+queueList.getHead().getTotalWaitingTime());
			System.out.println("Average waiting time in "+queueList.getHead().getDate()+" : "+queueList.getHead().getTotalWaitingTime()/queueList.getHead().getQueueLength());

			// Total  Waiting Time in 02.05.2019
			System.out.println("Total waiting time in "+queueList.getHead().getNext().getDate()+" : "+queueList.getHead().getNext().getTotalWaitingTime());
			System.out.println("Average waiting time in "+queueList.getHead().getNext().getDate()+" : "+queueList.getHead().getNext().getTotalWaitingTime()/queueList.getHead().getNext().getQueueLength());

			TransactionQueue firstDayQueue=queueList.getHead();
			TransactionQueue secondDayQueue=queueList.getHead().getNext();
			Transaction firstDayTransaction = firstDayQueue.getHead();
			int firstDayCorporateCounter=0;
			int secondDayCorporateCounter=0;
			Transaction secondDayTransaction = secondDayQueue.getHead();
			for (int i=0;i<firstDayQueue.getQueueLength();i++) {
				if(firstDayTransaction.getCustomer().getPriority()==1) {
					firstDayCorporateCounter+=firstDayTransaction.getWaiting();
				}
				firstDayTransaction=firstDayTransaction.getNext();
				
			}
			System.out.println("Total waiting time for CORPORATE in 01.05.2019 : "+secondDayCorporateCounter);
			for (int i=0;i<secondDayQueue.getQueueLength();i++) {
				if(secondDayTransaction.getCustomer().getPriority()==1) {
					secondDayCorporateCounter+=secondDayTransaction.getWaiting();
				}
				secondDayTransaction=secondDayTransaction.getNext();
				
			}
			System.out.println("Total waiting time for CORPORATE in 02.05.2019 : "+secondDayCorporateCounter);
			
	}
	public static void appendTransactionToArray(Transaction[] transactionArr, Transaction newTransaction) {
		
		for (int i=0;i<transactionArr.length;i++) {
			if (transactionArr[i]==null) {
				transactionArr[i]=newTransaction;
				break;
			}
		}
	}
	public static void bubbleSort(Transaction[] arr) {
		int numberOfEntries=0;
		for (int i=0;i<arr.length;i++) {
			if (arr[i]!=null) {
				numberOfEntries+=1;
			}else {
				break;
			}
		}
        int n = numberOfEntries;  
        Transaction temp = null;  
         for(int i=0; i < n; i++){  
        	 for(int j=1; j < (n-i); j++){    	 		
        		 if(arr[j-1].getCustomer().getPriority() > arr[j].getCustomer().getPriority()){  
        			 //swap elements  
        			 temp = arr[j-1];  
        			 arr[j-1] = arr[j];  
        			 arr[j] = temp;  
        		 }  
                          
        	 }  
         }  
    }
	public static void connectTransactions(Transaction[] arrTransaction, TransactionQueue queueTransaction) {
		for(int i=0;i<(arrTransaction.length);i++) {
			if(arrTransaction[i]!=null) {
				queueTransaction.insert(arrTransaction[i]);
			}
		}
	}
	
	
}
	    


