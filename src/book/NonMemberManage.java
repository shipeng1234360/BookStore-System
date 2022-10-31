package book;
import java.util.Arrays;
import java.util.List;

public class NonMemberManage {
	private String type;
	private long phoneNumber;
	private String address;
	private int bookID;
	private String bookTitle;
	private double bookPrice;
	private double discount;
	private double totalPrice;
	
	public NonMemberManage(String type, String address, long phoneNumber, int bookID, String bookTitle , 
							double bookPrice, double discount, double totalPrice) {
		this.type = type;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.bookPrice = bookPrice;
		this.discount = discount;
		this.totalPrice = totalPrice;
		
	}
	
	
	public String getType() {
		return this.type;
		
	}
	
	public void setType(String type) {
		this.type = type;
		
	}
	
	public long getPhoneNumber() {
		return this.phoneNumber;
		
	}
	
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
		
	}
	
	public String getAddress() {
		return this.address;
		
	}
	
	public void setAddress(String address) {
		this.address = address;
		
	}
	
	public int getBookID() {
		return this.bookID;
		
	}
	
	public void setBookID(int bookID) {
		this.bookID = bookID;
		
	}
	
	public String getBookTitle() {
		return this.bookTitle;
		
	}
	
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
		
	}
	
	public double getBookPrice() {
		return this.bookPrice;
		
	}
	
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
		
	}
	
	public double getDiscount() {
		return this.discount;
		
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
		
	}
	
	public double getTotalPrice() {
		return this.totalPrice;
		
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
		
	}
	
	public static NonMemberManage parseLine(List<String> lines) {
		String type = lines.get(0).split(":")[1];
		String address = lines.get(1).split(":")[1];
		long phoneNumber = Long.parseLong(lines.get(2).split(":0")[1]);
		int bookID = Integer.parseInt(lines.get(3).split(":")[1]);
		String bookTitle = lines.get(4).split(":")[1];
		double bookPrice = Double.parseDouble(lines.get(5).split(":")[1]);
		double discount = Double.parseDouble(lines.get(6).split(":")[1]);;
		double totalPrice = Double.parseDouble(lines.get(7).split(":")[1]);
		
		return new NonMemberManage(type, address, phoneNumber, bookID, bookTitle,
									bookPrice, discount, totalPrice);
		
	}
	
	public List<String> toLines() {
		
		return Arrays.asList(
				"Type :" + type,
				"Address :" + address,
				"Phone Number :0" + phoneNumber,
				"BookID :" + bookID,
				"BookTitle :" + bookTitle,
				"BookPrice :" + bookPrice,
				"Discount :" + discount,
				"Total Price :" + totalPrice
				);		
	}
}
