package hello.mysql.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import hello.mysql.domain.Company;
import hello.mysql.domain.JoinAll;
import hello.mysql.domain.JoinAllWithCompany;
import hello.mysql.domain.JoinByGoods;
import hello.mysql.domain.OrderGoods;
import hello.mysql.domain.OrderGoodsJoinOrder;
import hello.mysql.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public List<OrderGoods> selectAll(){
		return employeeRepository.selectAll();
	}
	
	@Override
	public List<OrderGoodsJoinOrder> selectByPeriod(String startDate, String endDate, String companyno){
		return employeeRepository.selectByPeriod(startDate, endDate, companyno);
	}
	
	@Override
	public List<Company> selectAllCompany(){
		return employeeRepository.selectAllCompany();
	}
	
	@Override
	public List<JoinAll> selectByMonth(String searchYear, String searchMonth){
		return employeeRepository.selectByMonth(searchYear, searchMonth);
	}
	
	@Override
	public List<JoinAllWithCompany> selectByMonthAndComp(String searchYear, String searchMonth, String companynm){
		return employeeRepository.selectByMonthAndComp(searchYear, searchMonth, companynm);
	}
	
	@Override
	public 	List<JoinByGoods> selectByGoods(String searchYear, String searchMonth, String companynm){
		return employeeRepository.selectByGoods(searchYear, searchMonth, companynm);
	}
	
	@Override
	public String selectToday() {
		return employeeRepository.selectToday();
	}
	
	@Override
	public void addCompany(String companynm, int feerate) {
		employeeRepository.addCompany(companynm, feerate);
	}
	
	@Override
	public List<Company> searchByCompany(String companynm){
		return employeeRepository.searchByCompany(companynm);
	}
	
	public boolean isDuplicate(String companynm) {
		if(employeeRepository.searchByCompany(companynm).size() == 0) {
			return false;
		}	
		return true;
	}
	
	public Map<String, String> validateHandling(Errors errors){
		Map<String, String> validatorResult = new HashMap<>();
		
		for (FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		
		return validatorResult;
	}

}