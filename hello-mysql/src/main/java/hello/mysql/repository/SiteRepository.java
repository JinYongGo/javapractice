package hello.mysql.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import hello.mysql.domain.SiteWithCompanyName;

@Repository
@Mapper
public interface SiteRepository {
	
	public List<SiteWithCompanyName> selectAllSite();
	
	public void registerSite(@Param("companyId") int companyId
			, @Param("siteName") String siteName
			, @Param("siteType") String siteType
			, @Param("siteStatus") String siteStatus
			, @Param("siteCalculate") String siteCalculate
			, @Param("siteProtocol") String siteProtocol
			, @Param("siteUrlDetail") String siteUrlDetail
			, @Param("siteManagerName") String siteManagerName
			, @Param("siteManagerNumber") String siteManagerNumber
			, @Param("siteManagerEmail") String siteManagerEmail);
	
	public void registerSiteForSv(@Param("companyId") int companyId
			, @Param("siteName") String siteName
			, @Param("siteType") String siteType
			, @Param("siteGearingUrl") String siteGearingUrl
			, @Param("siteStatus") String siteStatus
			, @Param("siteCalculate") String siteCalculate
			, @Param("siteProtocol") String siteProtocol
			, @Param("siteUrlDetail") String siteUrlDetail
			, @Param("siteManagerName") String siteManagerName
			, @Param("siteManagerNumber") String siteManagerNumber
			, @Param("siteManagerEmail") String siteManagerEmail);
	
	public List<SiteWithCompanyName> selectBySiteName(@Param("siteId") int siteId);
	
	public void updateSiteInfo(@Param("siteId") int siteId, @Param("siteName") String siteName
			, @Param("siteStatus") String siteStatus
			, @Param("siteCalculate") String siteCalculate
			, @Param("siteProtocol") String siteProtocol
			, @Param("siteUrlDetail") String siteUrlDetail
			, @Param("siteManagerName") String siteManagerName
			, @Param("siteManagerNumber") String siteManagerNumber
			, @Param("siteManagerEmail") String siteManagerEmail);
	
	public void deleteSiteInfo(@Param("siteId") int siteId);
	
	public List<SiteWithCompanyName> searchSite(@Param("companyId") int companyId
			, @Param("siteName") String siteName
			, @Param("siteStatus") String siteStatus
			, @Param("siteCalculate") String siteCalculate
			, @Param("siteProtocol") String siteProtocol
			, @Param("siteUrlDetail") String siteUrlDetail
			, @Param("startDate") String startDate
			, @Param("endDate") String endDate);

}
