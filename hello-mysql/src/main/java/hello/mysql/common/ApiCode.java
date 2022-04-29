package hello.mysql.common;

public enum ApiCode {
	Success("0000", "��ȸ ����"),
	Error("9000", "��ȸ ��� ����");
	
	private String code;
	private String msg;
	
	ApiCode(String code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}
