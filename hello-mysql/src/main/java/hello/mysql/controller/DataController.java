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
	 * ù��° �Ϻ� ��� ������
	 * ���޻� ����� �޾� ���ùڽ� ���� 
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
	
	/** ù��° ������ ��ȸ ������
	 * ������, ������, ���޻縦 ������ �޾� ��ȸ
	 */
	@GetMapping("/search/result")
	@ResponseBody
	public List<OrderGoodsJoinOrder> resultPage(Model model
			, @RequestParam("startDate") String startDate
			, @RequestParam("endDate") String endDate
			, @RequestParam("companyno") String companyno) {
		List<OrderGoodsJoinOrder> searchList = null;
		
		//html ���� �ƴ� ��Ʈ�ѷ����� ó���Ϸ��� list.isEmpty() ������ ��Ʈ�ѷ��� �߰�
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
	 * �ι�° ��ȸ ������ ����
	 */
	@GetMapping("/search/month")
	public String searchByMonth(Model model) {
		String today = employeeServiceImpl.selectToday();
		model.addAttribute("today", today);
		return "crud/hello2";
	}
	
	/**
	 * selectByMonth�� ��, ���� �޾� ��ȸ�ϴ� ����
	 * 
	 * @param searchYear, searchMonth ������ �޾Ƽ� ���� ����
	 * @return�� �̿��� ��ȸ ����� json���� ��ȯ
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
	 * 2��° ���������� ����Ǵ� �˾�â ������
	 * ��, ��, ȸ����� ������ �޾� ���� ����
	 */
	@GetMapping("/search/month/each")
	public String showPopUp(Model model, @RequestParam("searchYear") String searchYear, @RequestParam("searchMonth") String searchMonth, @RequestParam("companynm") String companynm) {
		
		List<JoinAllWithCompany> list1 = null;
		List<JoinByGoods> list2 = null;
		
		/** ���� 2�ڸ��� �ٲ��ִ� if��, ���� ���� html�� ������?
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
	
	/** ���â�� �����͸� �������ִ� ��Ʈ�ѷ�
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
