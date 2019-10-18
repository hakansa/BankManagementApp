
public class TransactionQueueList {
	private TransactionQueue head;
	private int listLength;
	
	public TransactionQueueList() {
		head=null;
		listLength=0;
	}
	public TransactionQueueList(TransactionQueue head) {
		this.head=head;
		listLength=0;
	}
	public TransactionQueue getHead() {
		return head;
	}
	public void setHead(TransactionQueue headTQ) {
		head=headTQ;
	}
	public TransactionQueue getTQ(String date) {
		TransactionQueue specificQueue = head;
		for(int i =0;i<listLength;i++) {
			if(specificQueue.getDate().equals(date)) {
				return specificQueue;
				
			}else {
				specificQueue=specificQueue.getNext();
			}
		}
		return null;
	}
	public boolean updateTQ(String date, TransactionQueue TQ) {
		TransactionQueue oldTQ = getTQ(date);
		if (oldTQ!=null) {
			oldTQ = TQ;
			return true;
		}
		return false;
	}
	public void insertTo(TransactionQueue TQ) {
		if (head==null) {
			head=TQ;
			listLength+=1;
			
		}else {
			TransactionQueue lastTransactionQueue=getLast();
			lastTransactionQueue.setNext(TQ);
			listLength+=1;
		}
	}
	public TransactionQueue getLast() {
		TransactionQueue nextTransactionQueue=head;
		for(int i=0;i<listLength-1;i++) {
			nextTransactionQueue = nextTransactionQueue.getNext();
			
		}
		return nextTransactionQueue;
	}
	public TransactionQueue removeTQ(String date) {
		int position= getPosition(date);
		if (position!=-1) {
			return null;
		}else {
			TransactionQueue removedTQ=null;
			TransactionQueue tempTQ=null;
			if(position==0) {
				head=head.getNext();
				listLength-=1;
				return removedTQ;
			}else if(position==listLength) {
				removedTQ=head;
				for(int i=0;i<position;i++) {
					tempTQ=tempTQ.getNext();
				}
				tempTQ=removedTQ;
				removedTQ=null;
				listLength-=1;
				return tempTQ;
			}else {
				removedTQ=head;
				tempTQ=null;
				TransactionQueue beforeTQ = null;

				for(int i=0;i<position;i++) {
					if(i==position-1) {
						// assign the TQ before removed TQ
						 beforeTQ= removedTQ;
					}
					removedTQ= removedTQ.getNext();
					
				}
				beforeTQ.setNext(removedTQ.getNext());
				tempTQ = removedTQ;
				removedTQ=null;
				listLength-=1;
				return tempTQ;
			}
			
			
		}
		
	}
	public int getPosition(String date) {
		int count=0;
		TransactionQueue checkTransaction= head;
		for (int i=0;i<listLength;i++) {
			if(checkTransaction==getTQ(date)) {
				break;
			}else {
				checkTransaction=checkTransaction.getNext();
				count+=1;
			}
		
		}
		if(count==listLength) {
			count=-1;
		}
		return count;
	}
	public boolean isEmpty() {
		return listLength==0;
	}
	public boolean clear() {
		head=null;
		return true;
	}
	public String toString() {
		String str = "";
		TransactionQueue tempHead = head;
		for (int i=0;i<listLength;i++) {
			str+=tempHead.toString()+"\n";
			tempHead=tempHead.getNext();
		}
		return str;
	}
}
