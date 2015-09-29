package harbour.model;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author P.Qhu  on 2015/09/16.
 */
 @Component
public class WarehouseResource  extends ResourceSupport {
	
    private Long resId;
    private String warehouseCode;
    private String name;
    private int packageLoad;


    /* PACKAGE PRODUCTS  WITHIN THE WAREHOUSE*/
	private List<PackageProductResource> packagedProd;

    private WarehouseResource(){}

    public Long getResId() {
        return resId;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public String getName() {
        return name;
    }

    public int getPackageLoad() {
        return packageLoad;
    }

    public List<PackageProductResource> getPackagedProd() {
        return packagedProd;
    }

    public WarehouseResource(Builder builder){
        resId = builder.resId;
        warehouseCode = builder.warehouseCode ;
        name = builder.name;
        packageLoad = builder.packageLoad;
        packagedProd = builder.packagedProd;
    }

    public static class Builder{
        private Long resId;
        private String warehouseCode;
        private String name;
        private int packageLoad;

        private List<PackageProductResource> packagedProd;

        public Builder(String warehouseCode){
            this.warehouseCode = warehouseCode;
        }

		 public Builder resId(Long resId){
            this.resId = resId;
             return this;
        }


        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder packageLoad(int load){
            this.packageLoad = load;
            return this;
        }

        public Builder packageProducts(List<PackageProductResource> packagedProd){
            this.packagedProd = packagedProd;
            return  this;
        }
        public Builder copy(WarehouseResource value){
            this.resId = value.getResId();
            this.warehouseCode = value.getWarehouseCode();
            this.name= value.getName();
            this.packageLoad = value.getPackageLoad();
            this.packagedProd=value.getPackagedProd();
            return this;
        }

        public WarehouseResource build(){
            return new WarehouseResource(this);
        }
    }

}
