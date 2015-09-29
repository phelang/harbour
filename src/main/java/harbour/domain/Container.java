package harbour.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by student on 2015/04/26.
 */
@Entity
public class Container implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String containerCode;
    private int width;
    private int height;

    /* PACKAGE PRODUCTS  WITHIN THE WAREHOUSE*/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "containerID")
    private List<PackageProduct> packagedProd;

    private Container() {
    }


    public Container(Builder builder) {
        id = builder.id;
        containerCode = builder.containerCode;
        width = builder.width;
        height = builder.height;
        packagedProd = builder.packagedProd;

    }

    public Long getId() {
        return id;
    }

    public String getContainerCode() {
        return containerCode;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<PackageProduct> getPackagedProd() {
        return packagedProd;
    }

    public static class Builder {
        private Long id;
        private String containerCode;
        private int width;
        private int height;
        private List<PackageProduct> packagedProd;

        public Builder (String containerCode) {
            this.containerCode = containerCode;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder packageProducts(List<PackageProduct> packagedProd){
            this.packagedProd = packagedProd;
            return  this;
        }


        public Builder copy(Container value){
            this.id = value.getId();
            this.containerCode = value.getContainerCode();
            this.width = value.getWidth();
            this.height=value.getHeight();
            this.packagedProd = value.getPackagedProd();
            return this;
        }

        public Container build() {
            return new Container(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Container)) return false;

        Container container = (Container) o;

        if (containerCode != null ? !containerCode.equals(container.containerCode) : container.containerCode != null)
            return false;
        if (id != null ? !id.equals(container.id) : container.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (containerCode != null ? containerCode.hashCode() : 0);
        return result;
    }
}
