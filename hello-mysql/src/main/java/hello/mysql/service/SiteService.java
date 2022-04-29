package hello.mysql.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import hello.mysql.domain.SiteWithCompanyName;

public interface SiteService {
	
	List<SiteWithCompanyName> selectAllSite();
	
	void registerSite(int companyId
			, String siteName
			, String siteType
			, String siteStatus
			, String siteCalculate
			, String siteUrl
			, String siteManagerName
			, String siteManagerNumber
			, String siteManagerEmail);
	
	void registerSiteForSv(int companyId
			, String siteName
			, String siteType
			, String siteGearingUrl
			, String siteStatus
			, String siteCalculate
			, String siteUrl
			, String siteManagerName
			, String siteManagerNumber
			, String siteManagerEmail);
	
	List<SiteWithCompanyName> selectBySiteName(int siteId);

	boolean updateSiteInfo(int siteId
			, String siteName
			, String siteStatus
			, String siteCalculate
			, String siteUrl
			, String siteManagerName
			, String siteManagerNumber
			, String siteManagerEmail);
	
	void deleteSiteInfo(int siteId);
	
	List<SiteWithCompanyName> searchSite(int companyId
			, String siteName
			, String siteStatus
			, String siteCalculate
			, String siteUrl
			, String startDate
			, String endDate);
}
