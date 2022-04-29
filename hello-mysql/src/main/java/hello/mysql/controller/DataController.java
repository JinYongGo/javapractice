package hello.mysql.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import hello.mysql.domain.Company;
import hello.mysql.domain.JoinAll;
import hello.mysql.domain.JoinAllWithCompany;
import hello.mysql.domain.JoinByGoods;
import hello.mysql.domain.OrderGoodsJoinOrder;
import hello.mysql.repository.EmployeeRepository;
import hello.mysql.service.EmployeeServiceImpl;

@Controller
public class DataController {
	
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
	DataController(EmployeeServiceImpl employeeServiceImpl){
		this.employeeServiceImpl = employeeServiceImpl;
	}
	
	/**
	 * 첫번째 일별 통계 페이지
	 * 제휴사 목록을 받아 선택박스 구현 
	 */
	@GetMapping("/search")
	public String searchPage(Model model) throws Exception{
		List<Company> companyList = employeeServiceImpl.selectAllCompany();
		String today = employeeServiceImpl.selectToday();
		String resultCode = "";
		model.addAttribute("today", today);
		//if (companyList.isEmpty())
		if(companyList.isEmpty() == false) {
			resultCode = "0000";
			model.addAttribute("companyList", companyList);
			model.addAttribute("resultCode", resultCode);
		} else {
			resultCode = "9999";
			model.addAttribute("resultCode", resultCode);
		}
		
		return "crud/hello";
	}
	
	/** 첫번째 페이지 조회 데이터
	 * 시작일, 종료일, 제휴사를 변수로 받아 조회
	 */
	@GetMapping("/search/result")
	@ResponseBody
	public List<OrderGoodsJoinOrder> resultPage(Model model
			, @RequestParam("startDate") String startDate
			, @RequestParam("endDate") String endDate
			, @RequestParam("companyno") String companyno) {
		List<OrderGoodsJoinOrder> searchList = null;
		
		//html 내가 아닌 컨트롤러에서 처리하려면 list.isEmpty() 로직을 컨트롤러에 추가
		try {
			searchList = employeeServiceImpl.selectByPeriod(startDate, endDate, companyno);
			//list.get
			model.addAttribute("searchList", searchList);
		} catch (Exception e){
			e.printStackTrace();
			return searchList;
		}
		
		return searchList;
	}
	
	/**
	 * 두번째 조회 페이지 실행
	 */
	@GetMapping("/search/month")
	public String searchByMonth(Model model) {
		String today = employeeServiceImpl.selectToday();
		model.addAttribute("today", today);
		return "crud/hello2";
	}
	
	/**
	 * selectByMonth는 년, 월을 받아 조회하는 쿼리
	 * 
	 * @param searchYear, searchMonth 변수를 받아서 쿼리 실행
	 * @return을 이용해 조회 결과를 json으로 반환
	 */
	@GetMapping("/search/month/result")
	@ResponseBody
	public List<JoinAll> searchMonthPage(Model model
			, @RequestParam("searchYear") String searchYear
			, @RequestParam("searchMonth") String searchMonth){
		List<JoinAll> list2 = null;
		
		try {
			list2 = employeeServiceImpl.selectByMonth(searchYear, searchMonth);
			model.addAttribute("searchList2", list2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list2;
	}
	
	/**
	 * 2번째 페이지에서 실행되는 팝업창 페이지
	 * 년, 월, 회사명을 변수로 받아 쿼리 실행
	 */
	@GetMapping("/search/month/each")
	public String showPopUp(Model model, @RequestParam("searchYear") String searchYear, @RequestParam("searchMonth") String searchMonth, @RequestParam("companynm") String companynm) {
		
		List<JoinAllWithCompany> list1 = null;
		List<JoinByGoods> list2 = null;
		
		/** 월을 2자리로 바꿔주는 if문, 여기 말고 html에 만들어보기?
		 */
		if(Integer.valueOf(searchMonth)<10) {
			searchMonth="0"+searchMonth;
		}
		
		try {
			list1 = employeeServiceImpl.selectByMonthAndComp(searchYear, searchMonth, companynm);
			list2 = employeeServiceImpl.selectByGoods(searchYear, searchMonth, companynm);
			model.addAttribute("list1", list1);
			model.addAttribute("list2", list2);
			model.addAttribute("companynm", companynm);
			model.addAttribute("searchYear", searchYear);
			model.addAttribute("searchMonth", searchMonth);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return "crud/hello3";
	}
	
	/** 모달창의 데이터를 가져다주는 컨트롤러
	 */
	@GetMapping("/search/month/result2")
	@ResponseBody
	public HashMap<String, Object> ShowModalPage(Model model, @RequestParam("searchYear") String searchYear, @RequestParam("searchMonth") String searchMonth, @RequestParam("companynm") String companynm){
		
		HashMap<String, Object> result = new HashMap<>();
		
		try {
			List<JoinAllWithCompany> listSelected = employeeServiceImpl.selectByMonthAndComp(searchYear, searchMonth, companynm);
			List<JoinByGoods> listByProduct = employeeServiceImpl.selectByGoods(searchYear, searchMonth, companynm);
			Map<String, Object> param = new HashMap<>();
			
			param.put("companynm", companynm);
			param.put("searchYear", searchYear);
			param.put("searchMonth", searchMonth);
			
			result.put("list", listSelected);
			result.put("listByProduct", listByProduct);
			result.put("param", param);
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
