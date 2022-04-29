package hello.mysql.domain;

public class OrderGoodsJoinOrder {
	
	private int price;
	private int discount;
	private String orderDate;
	private int total;
	
	public OrderGoodsJoinOrder(String orderDate, int price, int discount, int total) {
		this.orderDate=orderDate;
		this.price=price;
		this.discount=discount;
		this.total=total;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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
	
	public String getorderDate() {
		return orderDate;
	}
	
	public void setorderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
}
