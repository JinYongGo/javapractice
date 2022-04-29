package hello.mysql.domain;

public class JoinAll {

	private String companynm;
	private int price;
	private int discount;
	private int total;
	
	public JoinAll(String companynm, int price, int discount, int total) {
		this.companynm=companynm;
		this.price=price;
		this.discount=discount;
		this.total=total;
	}
	
	public String getCompanynm() {
		return companynm;
	}
	
	public void setCompanynm(String companynm) {
		this.companynm = companynm;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getDiscount() {
		return discount;
	}
	
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
}
