package harbour.model;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author P.Qhu  on 2015/09/16.
 */
@Component
public class LogisticCompanyResource extends ResourceSupport{

    private Long resid;
    private String companyCode;
    private String name;
    private String email;
    private String tel;

    /* PACKAGE PRODUCTS  WITHIN THE WAREHOUSE*/

    private List<PackageProductResource> packagedProd;

    private LogisticCompanyResource(){}

    public Long getResId() {
        return resid;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public List<PackageProductResource> getPackagedProd() {
        return packagedProd;
    }

    public LogisticCompanyResource(Builder builder){
        resid = builder.resid;
        companyCode = builder.companyCode;
        name = builder.name;
        email = builder.email;
        tel = builder.tel;
        packagedProd = builder.packagedProd;
    }

    public static class Builder {

        private Long resid;
        private String companyCode;
        private String name;
        private String email;
        private String tel;
        private List<PackageProductResource> packagedProd;

        public Builder(String companyCode) {
            this.companyCode = companyCode;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder resid(Long resid){
            this.resid = resid;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder tel(String tel) {
            this.tel = tel;
            return this;
        }

        public Builder packageProducts(List<PackageProductResource> packagedProd){
            this.packagedProd = packagedProd;
            return  this;
        }

        public Builder copy(LogisticCompanyResource value){
            this.resid = value.getResId();
            this.companyCode = value.getCompanyCode();
            this.name= value.getName();
            this.email= value.getEmail();
            this.tel= value.getTel();
            this.packagedProd=value.getPackagedProd();
            return this;
        }

        public LogisticCompanyResource build() {
            return new LogisticCompanyResource(this);
        }
    }

}
