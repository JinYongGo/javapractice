package hello.mysql.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * aton_company ���̺� domain
 */
// @Lombok
public class Company {
	
	private int companyno;
	
	@NotBlank
	@Size(max=30, message = "30�� ���Ϸ� �Է��ϼ���.")
	private String companynm;
	
	@Max(value = 100, message = "0 �̻� 100 ���Ϸ� �Է��ϼ���.")
	@Min(value = 0, message = "0 �̻� 100 ���Ϸ� �Է��ϼ���.")
	private int feerate;
	
	public int getCompanyno() {
		return companyno;
	}
	
	public void setCompanyno(int companyno) {
		this.companyno = companyno;
	}
	
	public String getCompanynm() {
		return companynm;
	}
	
	public void setCompanynm(String companynm) {
		this.companynm = companynm;
	}
	
	public int getFeerate() {
		return feerate;
	}
	
	public void setFeerate(int feerate) {
		this.feerate = feerate;
	}
}
