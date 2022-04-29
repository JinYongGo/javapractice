package hello.mysql.common;

public enum ApiCode {
	Success("0000", "조회 성공"),
	Error("9000", "조회 결과 없음");
	
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
