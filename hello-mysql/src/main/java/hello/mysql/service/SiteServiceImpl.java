package hello.mysql.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import hello.mysql.domain.Site;
import hello.mysql.domain.SiteWithCompanyName;
import hello.mysql.repository.SiteRepository;

@Service
public class SiteServiceImpl implements SiteService{
	
	private SiteRepository siteRepository;
	
	@Autowired
	SiteServiceImpl(SiteRepository siteRepository){
		this.siteRepository = siteRepository;
	}

	@Override
	public List<SiteWithCompanyName> selectAllSite(){
		return siteRepository.selectAllSite();
	}
	
	@Override
	public void registerSite(int companyId
			, String siteName
			, String siteType
			, String siteStatus
			, String siteCalculate
			, String siteUrl
			, String siteManagerName
			, String siteManagerNumber
			, String siteManagerEmail) {
		
		String siteProtocol = siteUrl.split("://")[0];
		String siteUrlDetail = siteUrl.split("://")[1];
				
		siteRepository.registerSite(companyId
				, siteName
				, siteType
				, siteStatus
				, siteCalculate
				, siteProtocol
				, siteUrlDetail
				, siteManagerName
				, siteManagerNumber
				, siteManagerEmail);
	}
	
	@Override
	public void registerSiteForSv(int companyId
			, String siteName
			, String siteType
			, String siteGearingUrl
			, String siteStatus
			, String siteCalculate
			, String siteUrl
			, String siteManagerName
			, String siteManagerNumber
			, String siteManagerEmail) {
		
		String siteProtocol = siteUrl.split("://")[0];
		String siteUrlDetail = siteUrl.split("://")[1];
				
		siteRepository.registerSiteForSv(companyId
				, siteName
				, siteType
				, siteGearingUrl
				, siteStatus
				, siteCalculate
				, siteProtocol
				, siteUrlDetail
				, siteManagerName
				, siteManagerNumber
				, siteManagerEmail);
	}
	
	@Override
	public List<SiteWithCompanyName> selectBySiteName(int siteId){
		return siteRepository.selectBySiteName(siteId);
	}
	
	@Override
	public boolean updateSiteInfo(int siteId
			, String siteName
			, String siteStatus
			, String siteCalculate
			, String siteUrl
			, String siteManagerName
			, String siteManagerNumber
			, String siteManagerEmail){
		
		String siteProtocol = siteUrl.split("://")[0];
		String siteUrlDetail = siteUrl.split("://")[1];
		try {
		siteRepository.updateSiteInfo(siteId
				, siteName
				, siteStatus
				, siteCalculate
				, siteProtocol
				, siteUrlDetail
				, siteManagerName
				, siteManagerNumber
				, siteManagerEmail);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	@Override
	public void deleteSiteInfo(int siteId) {
		siteRepository.deleteSiteInfo(siteId);
	}
	
	@Override
	public List<SiteWithCompanyName> searchSite(int companyId
			, String siteName
			, String siteStatus
			, String siteCalculate
			, String siteUrl
			, String startDate
			, String endDate){
		
		String siteProtocol;
		String siteUrlDetail;
		
		if(siteUrl == null || siteUrl == "" || siteUrl == " ") {
			siteProtocol = "";
			siteUrlDetail = "";
		} else {
			siteProtocol = siteUrl.split("://")[0];
			siteUrlDetail = siteUrl.split("://")[1];
		}
		
		return siteRepository.searchSite(companyId, siteName, siteStatus, siteCalculate, siteProtocol, siteUrlDetail, startDate, endDate);				
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
