
public class Customer implements ICustomer {
	private String type;
	private int priority;
	private static final String DEFAULT_TYPE="NON-REGISTERED";
	
	public Customer() {
		this(DEFAULT_TYPE);
	}
	
	public  Customer(String type) {
		this.type=type;
	
		
		if (type=="NON-REGISTERED") {
			this.priority=3;
		}else if (type=="INDIVIDUAL") {
			this.priority=2;
		}else if (type=="CORPORATE") {
			this.priority=1;
		}
	}
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}
	public  void setType(String type) {
		this.type=type;
	}
	public void setPriority(int priority) {
		this.priority=priority;
	}
	public String toString() {
		return type+","+priority;
	}

}
