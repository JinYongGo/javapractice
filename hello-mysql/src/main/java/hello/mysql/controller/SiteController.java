package hello.mysql.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.mysql.domain.Company;
import hello.mysql.domain.Site;
import hello.mysql.domain.SiteRegistration;
import hello.mysql.domain.SiteWithCompanyName;
import hello.mysql.service.EmployeeServiceImpl;
import hello.mysql.service.SiteServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SiteController {

	private SiteServiceImpl siteServiceImpl;
	private EmployeeServiceImpl employeeServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	SiteController(SiteServiceImpl siteServiceImpl, EmployeeServiceImpl employeeServiceImpl) {
		this.siteServiceImpl = siteServiceImpl;
		this.employeeServiceImpl = employeeServiceImpl;
	}
	
	// 첫 화면
	@GetMapping("/site/list")
	public String searchAllSite(Model model) {

		List<SiteWithCompanyName> siteList = siteServiceImpl.selectAllSite();
		String today = employeeServiceImpl.selectToday();

		model.addAttribute("today", today);
		model.addAttribute("siteList", siteList);

		return "crud/site";
	}
	
	// 첫 화면 조회 기능
	@GetMapping("/site/search")
	@ResponseBody
	public List<SiteWithCompanyName> searchSitePage(Model model, 
			@ModelAttribute SiteRegistration siteRegistration
			, @RequestParam("startDate") String startDate
			, @RequestParam("endDate") String endDate){
		
		List<SiteWithCompanyName> searchList = siteServiceImpl.searchSite(siteRegistration.getCompanyId()
				, siteRegistration.getSiteName()
				, siteRegistration.getSiteStatus()
				, siteRegistration.getSiteCalculate()
				, siteRegistration.getSiteUrl(), startDate, endDate);
		
		return searchList;
	}
	
	// 등록 화면
	@GetMapping("/site/register")
	public String registerPage(Model model) {
		/*
		 * List<Company> companyList = employeeServiceImpl.selectAllCompany();
		 * model.addAttribute("companyList", companyList);
		 */

		return "crud/register";
	}
	
	// 등록 화면 제휴사 목록
	@GetMapping("/site/company")
	@ResponseBody
	public List<Company> bringCompanyList(Model model){
		List<Company> companyList = employeeServiceImpl.selectAllCompany();
		return companyList;
	}

	/**
	 * @PostMapping("/site/register") public String registerSiteInfo(Model
	 * model, @RequestParam("companyId") int companyId , @RequestParam("siteName")
	 * String siteName , @RequestParam("siteStatus") String siteStatus
	 * , @RequestParam("siteCalculate") String siteCalculate
	 * , @RequestParam("siteUrl") String siteUrl
	 * , @RequestParam("siteManagerNumber") String siteManagerNumber
	 * , @RequestParam("siteManagerName") String siteManagerName
	 * , @RequestParam("siteManagerEmail") String siteManagerEmail){
	 * 
	 * siteServiceImpl.registerSite(companyId, siteName, siteStatus, siteCalculate,
	 * siteUrl, siteManagerName, siteManagerNumber, siteManagerEmail);
	 * 
	 * return "redirect:/site/list"; }
	 */
	
	// 등록 기능
	@PostMapping("site/register")
	@ResponseBody
	public Map<String, Object> registerSiteInfo(Model model, @ModelAttribute @Valid SiteRegistration siteRegistration,
			BindingResult errors) {
		
		Map<String, Object> resultData = new HashMap<>();
		
		if (errors.hasErrors()) {
			resultData.put("result", "fail");
			
			Map<String, String> validatorResult = siteServiceImpl.validateHandling(errors);
			
			for (String key : validatorResult.keySet()) {
				resultData.put("errParam", key);
				resultData.put("errMsg", validatorResult.get(key));
				//model.addAttribute(key, validatorResult.get(key));
			}

			List<Company> companyList = employeeServiceImpl.selectAllCompany();
			// model.addAttribute("companyList", companyList);
			return resultData;
		} else {
			resultData.put("result", "success");
			
			if("00".equals(siteRegistration.getSiteType())) { // 마케팅 제휴사
				siteServiceImpl.registerSite(siteRegistration.getCompanyId()
						, siteRegistration.getSiteName()
						, siteRegistration.getSiteType()
						, siteRegistration.getSiteStatus()
						, siteRegistration.getSiteCalculate()
						, siteRegistration.getSiteUrl()
						, siteRegistration.getSiteManagerName()
						, siteRegistration.getSiteManagerNumber()
						, siteRegistration.getSiteManagerEmail());

				return resultData;
			} else { // 서비스 제휴사
				siteServiceImpl.registerSiteForSv(siteRegistration.getCompanyId()
						, siteRegistration.getSiteName()
						, siteRegistration.getSiteType()
						, siteRegistration.getSiteGearingUrl()
						, siteRegistration.getSiteStatus()
						, siteRegistration.getSiteCalculate()
						, siteRegistration.getSiteUrl()
						, siteRegistration.getSiteManagerName()
						, siteRegistration.getSiteManagerNumber()
						, siteRegistration.getSiteManagerEmail());

				return resultData;
			}
			
		}
	}

	// 수정 화면
	@GetMapping("/site/modify")
	public String modifyPage(Model model, @RequestParam("siteId") int siteId) {
		
		try {
			List<SiteWithCompanyName> modifyList = siteServiceImpl.selectBySiteName(siteId);
			model.addAttribute("modifyList", modifyList);
			model.addAttribute("result", "success");
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("result", "fail");
		}

		return "crud/modify";
	}

	// 수정 기능
	@PostMapping("/site/modify")
	@ResponseBody
	public Map<String, Object> updateSiteInfoPage(Model model, @ModelAttribute @Valid SiteRegistration siteRegistration,
			BindingResult errors) {
		Map<String, Object> resultData = new HashMap<>();
		
		if (errors.hasErrors()) {
			resultData.put("result", "fail");
			
			Map<String, String> validatorResult = siteServiceImpl.validateHandling(errors);
			
			for (String key: validatorResult.keySet()) {
				resultData.put("errParam", key);
				resultData.put("errMsg", validatorResult.get(key));
			}
			
			return resultData;
		} 

		// resultData.put("result", "success");

		boolean isUpdated =
				siteServiceImpl.updateSiteInfo(siteRegistration.getSiteId()
						, siteRegistration.getSiteName()
						, siteRegistration.getSiteStatus()
						, siteRegistration.getSiteCalculate(), siteRegistration.getSiteUrl(),
						siteRegistration.getSiteManagerName(), siteRegistration.getSiteManagerNumber(),
						siteRegistration.getSiteManagerEmail());
			
		if( !isUpdated ) {
			resultData.put("result", "fail");
			return resultData;
		}
		
		resultData.put("result", "success");
		
		return resultData;

	}

	// 삭제 기능
	@PostMapping("/site/delete")
	public void deleteSiteInfoPage(Model model, @ModelAttribute SiteRegistration siteRegistration) {
		siteServiceImpl.deleteSiteInfo(siteRegistration.getSiteId());
//		return "redirect:/site/list";
	}

	// 제휴사 추가 팝업창
	@GetMapping("/site/register/company")
	public String registerCompanyPage(Model model) {
		return "crud/addCompany";
	}

	// 제휴사 추가 기능
	@PostMapping("/site/register/company")
	@ResponseBody
	public Map<String, Object> addCompany(Model model, @ModelAttribute @Valid Company company, BindingResult errors) {

		Map<String, Object> resultData = new HashMap<>();
		
		if (employeeServiceImpl.isDuplicate(company.getCompanynm())){
			resultData.put("result", "fail");
			resultData.put("errParam", "valid_companynm");
			resultData.put("errMsg", "중복된 제휴사입니다.");
			return resultData;
		}
		
		if (errors.hasErrors()) {
			resultData.put("result", "fail");

			Map<String, String> validatorResult = employeeServiceImpl.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				resultData.put("errParam", key);
				resultData.put("errMsg", validatorResult.get(key));
				/* model.addAttribute(key, validatorResult.get(key)); */
			}
			return resultData;

		}
		employeeServiceImpl.addCompany(company.getCompanynm(), company.getFeerate());

		resultData.put("result", "ok");
		return resultData;
	}

}
