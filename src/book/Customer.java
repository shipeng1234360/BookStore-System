package book;

public class Customer {
	private long customerId;
	private String customerName;
	private String customerAddress;
	private long customerPhone;
	private int bookID;
	private String bookTitle;
	
	public Customer() {
		
	}
	
	public Customer(long customerId, String customerName, String customerAddress, long customerPhone, int bookID, String bookTitle) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
		this.bookID = bookID;
		this.bookTitle = bookTitle;
	}
	
	public long getID() {
		
		return this.customerId;
	}
	
	public void setID(long customerId) {
		
		this.customerId = customerId;
	}
	
	public String getName() {
		
		return this.customerName;
	}
	
	public void setName(String customerName) {
		
		this.customerName = customerName;
	}
	
	public String getAddress() {
		
		return this.customerAddress;
	}
	
	public void setAddress(String customerAddress) {
		
		this.customerAddress = customerAddress;
	}
	
	public long getPhoneNumber() {
		
		return this.customerPhone;
	}
	
	public void setPhoneNumber(long customerPhone) {
		
		this.customerPhone = customerPhone;
	}
	
	public int getBookID() {
		
		return this.bookID;
	}
	
	public void setBooID(int bookID) {
		
		this.bookID = bookID;
	}
	
	public String getBookTitle() {
		
		return this.bookTitle;
	}
	
	public void setBookTitle(String bookTitle) {
		
		this.bookTitle = bookTitle;
	}
	
	public double calcPrice() {
		double price = 30;
		
		return price;
	}
	
	public String toString() {
		
		return "Address :" + getAddress() + "\n"
				+ "Phone Number :0" + getPhoneNumber() + "\n"
				+ "Book ID :" + getBookID() + "\n"
				+ "Book Title :" + getBookTitle() + "\n"
				+ "Book Price :" + calcPrice() + "\n";
	}
	
	
	
	
	
	
	
	
	
	
}
