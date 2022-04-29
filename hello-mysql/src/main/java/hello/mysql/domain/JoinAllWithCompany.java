package hello.mysql.domain;

public class JoinAllWithCompany {
	
	private int price;
	private int discount;
	private int payment;
	private int fee;
	
	public JoinAllWithCompany(int price, int discount, int payment, int fee) {
		this.price=price;
		this.discount=discount;
		this.payment=payment;
		this.fee=fee;
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

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

}
