package harbour.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by student on 2015/04/26.
 */
@Entity
public class LogisticCompany implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String companyCode;
    private String name;
    private String email;
    private String tel;

    /* PACKAGE PRODUCTS  WITHIN THE WAREHOUSE*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "logisticID")
    private List<PackageProduct> packagedProd;

    private LogisticCompany(){}

    public Long getId() {
        return id;
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

    public List<PackageProduct> getPackagedProd() {
        return packagedProd;
    }

    public LogisticCompany(Builder builder){
        id = builder.id;
        companyCode = builder.companyCode;
        name = builder.name;
        email = builder.email;
        tel = builder.tel;
        packagedProd = builder.packagedProd;
    }

    public static class Builder {

        private Long id;
        private String companyCode;
        private String name;
        private String email;
        private String tel;
        private List<PackageProduct> packagedProd;

        public Builder(String companyCode) {
            this.companyCode = companyCode;
        }

        public Builder name(String name) {
            this.name = name;
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

        public Builder packageProducts(List<PackageProduct> packagedProd){
            this.packagedProd = packagedProd;
            return  this;
        }

        public Builder copy(LogisticCompany value){
            this.id = value.getId();
            this.companyCode = value.getCompanyCode();
            this.name= value.getName();
            this.email= value.getEmail();
            this.tel= value.getTel();
            this.packagedProd=value.getPackagedProd();
            return this;
        }

        public LogisticCompany build() {
            return new LogisticCompany(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogisticCompany)) return false;

        LogisticCompany that = (LogisticCompany) o;

        if (companyCode != null ? !companyCode.equals(that.companyCode) : that.companyCode != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        return result;
    }
}
