package book;

import java.util.Arrays;
import java.util.List;

public class MemberManage {
	private long id;
	private String name;
	private String type;
	private long phoneNumber;
	private String address;
	private int bookID;
	private String bookTitle;
	private double bookPrice;
	private double discount;
	private double totalPrice;
	
	public MemberManage(long id, String name, String type, String address, long phoneNumber, int bookID, String bookTitle , 
							double bookPrice, double discount, double totalPrice) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.bookPrice = bookPrice;
		this.discount = discount;
		this.totalPrice = totalPrice;
		
	}
	
	public long getID() {
		return this.id;
		
	}
	
	public void setID(long id) {
		this.id = id;
		
	}
	
	public String getName() {
		return this.name;
		
	}
	
	public void setName(String name) {
		this.name = name;
		
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
	
	public static MemberManage parseLine(List<String> lines) {
		long id = Long.parseLong(lines.get(0).split(":")[1]);
		String name = lines.get(1).split(":")[1];
		String type = lines.get(2).split(":")[1];
		String address = lines.get(3).split(":")[1];
		long phoneNumber = Long.parseLong(lines.get(4).split(":0")[1]);
		int bookID = Integer.parseInt(lines.get(5).split(":")[1]);
		String bookTitle = lines.get(6).split(":")[1];
		double bookPrice = Double.parseDouble(lines.get(7).split(":")[1]);
		double discount = Double.parseDouble(lines.get(8).split(":")[1]);;
		double totalPrice = Double.parseDouble(lines.get(9).split(":")[1]);
		
		return new MemberManage(id,name, type, address, phoneNumber, bookID, bookTitle,
									bookPrice, discount, totalPrice);
		
	}
	
	public List<String> toLines() {
		
		return Arrays.asList(
				"ID :" + id,
				"Name :" + name,
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
