package harbour.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by student on 2015/04/26.
 */
@Entity
public class Warehouse implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String warehouseCode;
    private String name;
    private int packageLoad;


    /* PACKAGE PRODUCTS  WITHIN THE WAREHOUSE*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "warehouseID")
    private List<PackageProduct> packagedProd;

    private Warehouse(){}

    public Long getId() {
        return id;
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

    public List<PackageProduct> getPackagedProd() {
        return packagedProd;
    }

    public Warehouse(Builder builder){
        id = builder.id;
        warehouseCode = builder.warehouseCode ;
        name = builder.name;
        packageLoad = builder.packageLoad;
        packagedProd = builder.packagedProd;
    }

    public static class Builder{
        private Long id;
        private String warehouseCode;
        private String name;
        private int packageLoad;

        private List<PackageProduct> packagedProd;

        public Builder(String warehouseCode){
            this.warehouseCode = warehouseCode;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder packageLoad(int load){
            this.packageLoad = load;
            return this;
        }

        public Builder packageProducts(List<PackageProduct> packagedProd){
            this.packagedProd = packagedProd;
            return  this;
        }
        public Builder copy(Warehouse value){
            this.id = value.getId();
            this.warehouseCode = value.getWarehouseCode();
            this.name= value.getName();
            this.packagedProd=value.getPackagedProd();
            return this;
        }

        public Warehouse build(){
            return new Warehouse(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Warehouse)) return false;

        Warehouse warehouse = (Warehouse) o;

        if (id != null ? !id.equals(warehouse.id) : warehouse.id != null) return false;
        if (warehouseCode != null ? !warehouseCode.equals(warehouse.warehouseCode) : warehouse.warehouseCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (warehouseCode != null ? warehouseCode.hashCode() : 0);
        return result;
    }
}
