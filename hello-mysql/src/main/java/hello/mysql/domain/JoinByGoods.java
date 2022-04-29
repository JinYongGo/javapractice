package hello.mysql.domain;

public class JoinByGoods {
	
	private int goodsno;
	private int price;
	private int discount;
	private int payment;
	
	public JoinByGoods(int goodsno, int price, int discount, int payment) {
		this.goodsno=goodsno;
		this.price=price;
		this.discount=discount;
		this.payment=payment;
	}

	public int getGoodsno() {
		return goodsno;
	}

	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
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

}
