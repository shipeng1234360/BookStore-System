package book;

public class Member extends Customer {
	private long memberID;
	private String memberName;
	
	public Member(long customerId, String customerName, String customerAddress, long customerPhone, int bookID, String bookTitle) {
		
		super(customerId, customerName, customerAddress, customerPhone, bookID, bookTitle);
		
		this.memberID = customerId;
		this.memberName = customerName;
	}
	
	public long getMemberID() {
		
		return this.memberID;
	}
	
	public void setMemberID(int customerID) {
		
		this.memberID = customerID;
	}
	
	public String getMemberName() {
		
		return this.memberName;
	}
	
	public void setMemberName(String customerName) {
		
		this.memberName = customerName;
	}
	
	
	public double calcDiscount() {
		double discount = calcPrice() * 0.10;
		
		return discount;
	}
	
	public double calcTotal() {
		double total = calcPrice() - calcDiscount();
		
		return total;
	}
	
	public String toString() {
		
		return "ID :" + getMemberID() + "\n"
				+ "Name :" + getMemberName() + "\n"
				+ "Type :Member" + "\n"
				+ super.toString() 
				+ "Discount :" + calcDiscount() + "\n"
				+ "Total Price :" + calcTotal() + "\n";
	}
}
