package hello.mysql.domain;

/**
 * aton_order Å×ÀÌºí domain
 */
public class Order {

	private int ordno;
	private int mno;
	private String orddt;
	
	public int getOrdno() {
		return ordno;
	}
	
	public void setOrdno(int ordno) {
		this.ordno = ordno;
	}
	
	public int getMno() {
		return mno;
	}
	
	public void setMno(int mno) {
		this.mno = mno;
	}
	
	public String getOrddt() {
		return orddt;
	}
	
	public void setOrddt(String orddt) {
		this.orddt = orddt;
	}
	
}
