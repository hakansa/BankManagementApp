
public class TransactionQueue {
	private Transaction head;
	private int queueLength;
	private int totalWaitingTime;
	private String date;
	private TransactionQueue next;
	
	public TransactionQueue(String date){
		head=null;
		queueLength=0;
		totalWaitingTime=0;
		this.date=date;
		next=null;
	}
	public TransactionQueue(String date,Transaction head){
		this.head=head;
		head.setWaiting(0);
		queueLength=0;
		totalWaitingTime=0;
		this.date=date;
		next=null;
	}
	public void insert(Transaction T) {
		if (head==null) {
			head=T;
			head.setWaiting(0);
			queueLength+=1;
			totalWaitingTime=T.getOccupation();
		}else {
			Transaction lastTransaction=getLast();
			lastTransaction.setNext(T);
			queueLength+=1;
			totalWaitingTime+=T.getOccupation();
			int waitingTime = calculateWaiting(T);
			T.setWaiting(waitingTime);
			
		}
		
	}
	public Transaction remove() {
		Transaction tempHead=head;
		head=head.getNext();
		queueLength-=1;
		totalWaitingTime-=tempHead.getOccupation();
		return tempHead;
	}
	public boolean isEmpty() {
		return queueLength==0;
	}
	public boolean clear() {
		head=null;
		queueLength=0;
		totalWaitingTime=0;
		return true;
	}
	public String toString() {
		String str = date+ " COUNTER ";
		Transaction tempHead = head;
		for (int i=0;i<queueLength;i++) {
			str+=tempHead.toString();
			tempHead=tempHead.getNext();
		}
		return str;
	}
	public Transaction getLast() {
		Transaction nextTransaction=head;
		for(int i=0;i<queueLength-1;i++) {
			nextTransaction = nextTransaction.getNext();
			
		}
		return nextTransaction;
	}
	public Transaction getHead() {
		return head;
	}
	public void setHead(Transaction head) {
		this.head = head;
	}
	public int getQueueLength() {
		return queueLength;
	}
	public void setQueueLength(int queueLength) {
		this.queueLength = queueLength;
	}
	public int getTotalWaitingTime() {
		return totalWaitingTime;
	}
	public void setTotalWaitingTime(int totalWaitingTime) {
		this.totalWaitingTime = totalWaitingTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public TransactionQueue getNext() {
		return next;
	}
	public void setNext(TransactionQueue next) {
		this.next = next;
	}
	public int calculateWaiting(Transaction T) {
		int waitingTime=head.getOccupation();
		Transaction tempTransaction= head;
		for (int i=0;i<queueLength;i++) {
			// find the previous transaction
			if (tempTransaction.getNext().equals(T)) {
				break;
				 
			}else {
				tempTransaction=tempTransaction.getNext();
			}
			waitingTime=tempTransaction.getOccupation()+tempTransaction.getWaiting();
		}
		
		
		return waitingTime;
	}
	
	
	
	

}
