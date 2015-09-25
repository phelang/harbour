package harbour.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by student on 2015/04/18.
 */
@Entity
public class PackageProduct implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String packageCode;
    private Date packageDate;
    private String description;
    private String itemType;
    private int quantity;


    private PackageProduct(){}


    public Long getId() {
        return id;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public Date getPackageDate() {
        return packageDate;
    }

    public String getDescription() {
        return description;
    }

    public String getItemType() {
        return itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public PackageProduct(Builder builder){
        id = builder.id;
        packageCode = builder.packageCode;
        packageDate = builder.packageDate;
        description = builder.description;
        itemType = builder.itemType;
        quantity = builder.quantity;
    }

    public static class Builder{
            private Long id;
            private String packageCode;
            private Date packageDate;
            private String description;
            private String itemType;
            private int quantity;

            public Builder(String code ){
                this.packageCode  = code;
            }

            public Builder packageDate(Date packageDate){
                this.packageDate = packageDate;
                return this;
            }
            public Builder description(String description){
                this.description = description;
                return this;
            }


            public Builder itemType(String itemType){
                this.itemType = itemType;
                return this;
            }


            public Builder quantity(int quantity){
                this.quantity = quantity;
                return this;
            }

            public Builder copy(PackageProduct value){
                this.id = value.getId();
                this.packageCode = value.getPackageCode();
                this.packageDate = value.getPackageDate();
                this.description=value.getDescription();
                this.itemType = value.getItemType();
                this.quantity = value.getQuantity();
                return this;
            }

            public PackageProduct build(){
                return new PackageProduct(this);
            }
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackageProduct)) return false;

        PackageProduct that = (PackageProduct) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (packageCode != null ? !packageCode.equals(that.packageCode) : that.packageCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (packageCode != null ? packageCode.hashCode() : 0);
        return result;
    }
}
