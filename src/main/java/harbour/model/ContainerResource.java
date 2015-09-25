package harbour.model;


import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author P.Qhu  on 2015/09/16.
 */
 @Component
public class ContainerResource extends ResourceSupport{

    private Long resid;
    private String containerCode;
    private int width;
    private int height;

    /* PACKAGE PRODUCTS  WITHIN THE WAREHOUSE*/
    private List<PackageProductResource> packagedProd;

    private ContainerResource() {
    }


    public ContainerResource(Builder builder) {
        resid = builder.resid;
        containerCode = builder.containerCode;
        width = builder.width;
        height = builder.height;
        packagedProd = builder.packagedProd;

    }

    public Long getResId() {
        return resid;
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

    public List<PackageProductResource> getPackagedProd() {
        return packagedProd;
    }

    public static class Builder {
        private Long resid;
        private String containerCode;
        private int width;
        private int height;
        private List<PackageProductResource> packagedProd;

        public Builder (String containerCode) {
            this.containerCode = containerCode;
        }

        public Builder resid(Long id){
            this.resid = id;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder packageProducts(List<PackageProductResource> packagedProd){
            this.packagedProd = packagedProd;
            return  this;
        }


        public Builder copy(ContainerResource value){
            this.resid = value.getResId();
            this.containerCode = value.getContainerCode();
            this.width = value.getWidth();
            this.height=value.getHeight();
            this.packagedProd = value.getPackagedProd();
            return this;
        }

        public ContainerResource build() {
            return new ContainerResource(this);
        }
    }

}
