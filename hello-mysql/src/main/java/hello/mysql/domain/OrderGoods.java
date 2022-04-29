package hello.mysql.domain;

/**
 * aton_order_goods Å×ÀÌºí domain
 */
public class OrderGoods {
	
	private int sno;
	private int ordno;
	private int comanyno;
	private int goodsno;
	private int ea;
	private int price;
	private int coupon;
	private int discount;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getOrdno() {
		return ordno;
	}
	public void setOrdno(int ordno) {
		this.ordno = ordno;
	}
	public int getComanyno() {
		return comanyno;
	}
	public void setComanyno(int comanyno) {
		this.comanyno = comanyno;
	}
	public int getGoodsno() {
		return goodsno;
	}
	public void setGoodsno(int goodsno) {
		this.goodsno = goodsno;
	}
	public int getEa() {
		return ea;
	}
	public void setEa(int ea) {
		this.ea = ea;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCoupon() {
		return coupon;
	}
	public void setCoupon(int coupon) {
		this.coupon = coupon;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getIste() {
		return iste;
	}
	public void setIste(int iste) {
		this.iste = iste;
	}
	private int iste;
	

}
