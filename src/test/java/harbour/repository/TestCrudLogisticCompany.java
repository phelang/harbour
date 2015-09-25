package harbour.repository;

import harbour.config.factory.LogisticCompanyFactory;
import harbour.config.factory.PackageProductFactory;
import harbour.domain.LogisticCompany;
import harbour.domain.PackageProduct;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

/*import org.springframework.test.context.web.WebAppConfiguration;import org.testng.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;import harborManagementSystem.App;*/

/**
 * Author P.Qhu  on 2015/08/11.
 */
/*@SpringApplicationConfiguration(classes= {App.class})
@WebAppConfiguration*/
public class TestCrudLogisticCompany extends AbstractTestNGSpringContextTests{
    private Long id;
    //@Autowired
    LogisticCompanyRepository repository;

   // @Test
    public void create() throws Exception {
        List<PackageProduct> packageProd = new ArrayList<PackageProduct>();
        /* CREATE PACKAGE PRODUCTS LIST */
        Map<String, String> detail = new HashMap<String, String>();
        detail.put("code", "PT0001");
        detail.put("descr", "TeraByte CPU UL");
        detail.put("type", "Processor");

        SimpleDateFormat fomatter = new SimpleDateFormat("dd/mm/yy");
        Date currDate = fomatter.parse("01/01/2016");
        PackageProduct pkgproduct1 = PackageProductFactory.createPackageProduct(
                detail, currDate, 14);          // PackageProduct 1

        Map<String, String> pkgProdDetail2 = new HashMap<String, String>();
        pkgProdDetail2.put("code", "PT0002");
        pkgProdDetail2.put("descr", "Diamond");
        pkgProdDetail2.put("type", "Crystal");

        SimpleDateFormat fomatter2 = new SimpleDateFormat("dd/mm/yy");
        Date currDate2 = fomatter2.parse("01/01/2016");
        PackageProduct pkgproduct2 = PackageProductFactory.createPackageProduct(
                pkgProdDetail2, currDate2, 1);     // PackageProduct 2

        packageProd.add(pkgproduct1);
        packageProd.add(pkgproduct2);

        /*------------------------------*/
        Map<String, String> companyDetail = new HashMap<String, String>();

        companyDetail.put("code", "LC001");
        companyDetail.put("name", "Frenzy Logistics");
        companyDetail.put("email", "frenzylogistics@frenzy.com");
        companyDetail.put("tel", "+27021800677");

        LogisticCompany company = LogisticCompanyFactory.createCompany(companyDetail, packageProd);

        this.repository.save(company);
        id = company.getId();
        Assert.assertNotNull(company.getId());
    }

    //@Test(dependsOnMethods = "create")
    public void read() throws Exception {
        LogisticCompany company = repository.findOne(id);
        Assert.assertNotNull(company);

    }
    //@Test(dependsOnMethods = "read")
    public void update() throws Exception {

        LogisticCompany company = repository.findOne(id);
        LogisticCompany newCompany = new LogisticCompany.Builder(company.getCompanyCode())
                .copy(company)
                .email("frenzy@frenzy.com")   /// update email
                .build();

        repository.save(newCompany);
        LogisticCompany updatedCompany = repository.findOne(id);
        Assert.assertEquals("frenzy@frenzy.com", updatedCompany.getEmail());

    }
    /*@Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        LogisticCompany company = repository.findOne(id);
        repository.delete(company);
        LogisticCompany deletedCompany = repository.findOne(id);
        Assert.assertNull(deletedCompany);
    }*/
}