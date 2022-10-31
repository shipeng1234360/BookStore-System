package book;

public class NonMember extends Customer {
	
	public NonMember(long customerPhone, String customerAddress, int bookID, String bookTitle) {
		
		super.setPhoneNumber(customerPhone);
		super.setAddress(customerAddress);
		super.setBooID(bookID);
		super.setBookTitle(bookTitle);
		
	}
	
	
	public double calcDiscount() {
		double discount = 0;
		
		return discount;
	}
	
	public double calcTotal() {
		double total = calcPrice() - calcDiscount();
		
		return total;
	}
	
	public String toString() {
		
		return 	"Type :Non-Member" + "\n"
				+ super.toString() 
				+ "Discount :" + calcDiscount() + "\n"
				+ "Total Price :" + calcTotal() + "\n";
	}
}
