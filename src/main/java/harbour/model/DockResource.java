package harbour.model;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

/**
 * Author P.Qhu  on 2015/09/16.
 */
 @Component
public class DockResource extends ResourceSupport {

    private Long resId;
    private String dockCode;
    private int length;
    private int breadth;

    /* A CARGO SHIP WITHIN A DOCK*/

    private CargoResource cargo;

    public Long getResId() {
        return resId;
    }

    public String getDockCode() {
        return dockCode;
    }

    public int getLength() {
        return length;
    }

    public int getBreadth() {
        return breadth;
    }

    public CargoResource getCargo() {
        return cargo;
    }

    private DockResource() {
    }


    public DockResource(Builder builder) {
        resId = builder.resId;
        cargo = builder.cargo;
        dockCode = builder.dockCode;
        length = builder.length;
        breadth = builder.breadth;
    }

    public static class Builder {
        private Long resId;
        private String dockCode;
        private int length;
        private int breadth;
        private CargoResource cargo;

        public Builder(String dockCode) {
            this.dockCode = dockCode;
        }

        public Builder resid(Long resId){
            this.resId = resId;
            return this;
        }
        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Builder cargo(  CargoResource cargo){
            this.cargo = cargo;
            return this;
        }
        public Builder breadth(int breadth) {
            this.breadth = breadth;
            return this;
        }
        public Builder cargo(int breadth) {
            this.breadth = breadth;
            return this;
        }
        public Builder copy(DockResource value){
            this.resId = value.getResId();
            this.dockCode = value.getDockCode();
            this.length= value.getLength();
            this.breadth=value.getBreadth();
            this.cargo= value.getCargo();
            return this;
        }


        public DockResource build() {
            return new DockResource(this);
        }
    }



}
