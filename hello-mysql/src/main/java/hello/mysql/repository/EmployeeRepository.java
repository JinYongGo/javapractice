package hello.mysql.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import hello.mysql.domain.Company;
import hello.mysql.domain.JoinAll;
import hello.mysql.domain.JoinAllWithCompany;
import hello.mysql.domain.JoinByGoods;
import hello.mysql.domain.OrderGoods;
import hello.mysql.domain.OrderGoodsJoinOrder;

@Repository
@Mapper
public interface EmployeeRepository {
	
	// public 붙이고 안 붙이고 차이?
	/**
	 * aton_order_goods 테이블 전체를 불러오는 쿼리 실행 메서드
	 */
	public List<OrderGoods> selectAll();
	
	/**
	 * aton_company 테이블 전체를 불러오는 쿼리 실행 메서드
	 */
	public List<Company> selectAllCompany();
	
	/**
	 * @param startDate, endDate, companyno 시작일, 종료일, 제휴사를 변수로 받음
	 * @return 
	 */
	public List<OrderGoodsJoinOrder> selectByPeriod(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("companyno") String companyno);
	
	public List<JoinAll> selectByMonth(@Param("searchYear") String searchYear, @Param("searchMonth") String searchMonth);
	
	public List<JoinAllWithCompany> selectByMonthAndComp(@Param("searchYear") String searchYear, @Param("searchMonth") String searchMonth, @Param("companynm") String companynm);
	
	public List<JoinByGoods> selectByGoods(@Param("searchYear") String searchYear, @Param("searchMonth") String searchMonth, @Param("companynm") String companynm);
	
	public String selectToday();
	/*
	 * public Optional<Employee> findById(int id);
	 * 
	 * public Boolean deleteEmployee(int id);
	 */
	
	public void addCompany(@Param("companynm") String companynm, @Param("feerate") int feerate);
	
	public List<Company> searchByCompany(@Param("companynm") String companynm);
}
