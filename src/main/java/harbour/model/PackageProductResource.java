package harbour.model;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Author P.Qhu  on 2015/09/16.
 */
 @Component
public class PackageProductResource extends ResourceSupport{

    private Long resId;
    private String packageCode;
    private Date packageDate;
    private String description;
    private String itemType;
    private int quantity;


    private PackageProductResource(){}


    public Long getResId() {
        return resId;
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

    public PackageProductResource(Builder builder){
        resId = builder.resId;
        packageCode = builder.packageCode;
        packageDate = builder.packageDate;
        description = builder.description;
        itemType = builder.itemType;
        quantity = builder.quantity;
    }

    public static class Builder{
        private Long resId;
        private String packageCode;
        private Date packageDate;
        private String description;
        private String itemType;
        private int quantity;

        public Builder(String code ){
            this.packageCode  = code;
        }

        public Builder resid(Long resId){
            this.resId = resId;
            return this;
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

        public Builder copy(PackageProductResource value){
            this.resId = value.getResId();
            this.packageCode = value.getPackageCode();
            this.packageDate = value.getPackageDate();
            this.description=value.getDescription();
            this.itemType = value.getItemType();
            this.quantity = value.getQuantity();
            return this;
        }

        public PackageProductResource build(){
            return new PackageProductResource(this);
        }
    }

}
