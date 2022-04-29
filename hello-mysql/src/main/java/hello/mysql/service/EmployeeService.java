package hello.mysql.service;

import java.util.List;

import hello.mysql.domain.Company;
import hello.mysql.domain.JoinAll;
import hello.mysql.domain.JoinAllWithCompany;
import hello.mysql.domain.JoinByGoods;
import hello.mysql.domain.OrderGoods;
import hello.mysql.domain.OrderGoodsJoinOrder;

public interface EmployeeService {

	List<OrderGoods> selectAll(); // 왜 public이 안 붙는지
	List<Company> selectAllCompany();
	
	List<OrderGoodsJoinOrder> selectByPeriod(String startDate, String endDate, String companyno);
	
	List<JoinAll> selectByMonth(String searchYear, String searchMonth);
	
	List<JoinAllWithCompany> selectByMonthAndComp(String searchYear, String searchMonth, String companynm);
	
	List<JoinByGoods> selectByGoods(String searchYear, String searchMonth, String companynm);
	
	String selectToday();
	
	void addCompany(String companynm, int feerate);
	
	List<Company> searchByCompany(String companynm);

}