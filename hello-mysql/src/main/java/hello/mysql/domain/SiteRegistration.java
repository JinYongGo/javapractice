package hello.mysql.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SiteRegistration {
	
	private int siteId;
	
	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	private int companyId;
	
	@NotBlank(message = "����Ʈ ���� �Է��ϼ���.")
	@Size(max=30)
	private String siteName;
	
	private String siteType;
	
	@Pattern(regexp = "^((http|https)://)([a-zA-Z0-9]+)\\.[a-z]+([a-zA-z0-9.?#]+)?"
			, message="�߸��� URL �����Դϴ�.")
	private String siteGearingUrl;

	private String siteStatus;
	
	private String siteCalculate;
	
	@Pattern(regexp = "^((http|https)://)([a-zA-Z0-9]+)\\.[a-z]+([a-zA-z0-9.?#]+)?"
			, message="�߸��� URL �����Դϴ�.")
	private String siteUrl;
	
	@NotBlank
	@Size(max = 10, message = "10�� ���Ϸ� �Է��ϼ���.")
	private String siteManagerName;
	
	@Pattern(regexp = "^\\d{3}\\d{3,4}\\d{4}$", message = "�߸��� ����ó �����Դϴ�.")
	private String siteManagerNumber;
	
	@NotBlank
	@Email(message = "�߸��� �̸��� �����Դϴ�.")
	private String siteManagerEmail;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getSiteGearingUrl() {
		return siteGearingUrl;
	}

	public void setSiteGearingUrl(String siteGearingUrl) {
		this.siteGearingUrl = siteGearingUrl;
	}

	public String getSiteStatus() {
		return siteStatus;
	}

	public void setSiteStatus(String siteStatus) {
		this.siteStatus = siteStatus;
	}

	public String getSiteCalculate() {
		return siteCalculate;
	}

	public void setSiteCalculate(String siteCalculate) {
		this.siteCalculate = siteCalculate;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getSiteManagerName() {
		return siteManagerName;
	}

	public void setSiteManagerName(String siteManagerName) {
		this.siteManagerName = siteManagerName;
	}

	public String getSiteManagerNumber() {
		return siteManagerNumber;
	}

	public void setSiteManagerNumber(String siteManagerNumber) {
		this.siteManagerNumber = siteManagerNumber;
	}

	public String getSiteManagerEmail() {
		return siteManagerEmail;
	}

	public void setSiteManagerEmail(String siteManagerEmail) {
		this.siteManagerEmail = siteManagerEmail;
	}
	
}
