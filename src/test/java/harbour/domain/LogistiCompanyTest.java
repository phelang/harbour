package harbour.domain;

import harbour.config.factory.LogisticCompanyFactory;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

//import org.testng.annotations.*;

/**
 * Author P.Qhu  on 2015/08/15.
 */
public class LogistiCompanyTest {
    //@Test
    public void create() throws Exception {
        Map<String, String> companyDetail = new HashMap<String, String>();

        companyDetail.put("code", "LC001");
        companyDetail.put("name", "Frenzy Logistics");
        companyDetail.put("email", "frenzylogistics@frenzy.com");
        companyDetail.put("tel", "+27021800677");

        LogisticCompany company = LogisticCompanyFactory.createCompany(companyDetail, null);
        Assert.assertEquals("LC001", company.getCompanyCode());
        Assert.assertEquals("frenzylogistics@frenzy.com", company.getEmail());


    }
    //@Test
    public void update() throws Exception {
        Map<String, String> companyDetail = new HashMap<String, String>();

        companyDetail.put("code", "LC001");
        companyDetail.put("name", "Frenzy Logistics");
        companyDetail.put("email", "frenzylogistics@frenzy.com");
        companyDetail.put("tel", "+27021800677");
        LogisticCompany company = LogisticCompanyFactory.createCompany(companyDetail, null);

        LogisticCompany newCompany = new LogisticCompany.Builder(company.getCompanyCode())
                .copy(company)
                .email("frenzy@frenzy.com")   /// update email
                .build();

        Assert.assertEquals("frenzylogistics@frenzy.com", company.getEmail());
        Assert.assertEquals("frenzy@frenzy.com", newCompany.getEmail());

    }
}
