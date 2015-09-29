package harbour.model;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author P.Qhu on 2015/09/16.
 */
@Component
public class CargoResource extends ResourceSupport {
    private Long resId;
    private String cargoCode;
    private String name;
    private int length;
    private int breadth;
    private int height;
    private int containerLoad;


    private List<ContainerResource> container;


    private CargoResource() {
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public Long getResId() {
        return resId;
    }


    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getBreadth() {
        return breadth;
    }

    public int getHeight() {
        return height;
    }

    public int getContainerLoad() {
        return containerLoad;
    }

    public List<ContainerResource> getContainer() {
        return container;
    }

    public CargoResource(Builder builder) {
        resId = builder.resId;
        cargoCode = builder.cargoCode;
        name = builder.name;
        length = builder.length;
        height = builder.height;
        breadth = builder.breadth;
        containerLoad = builder.containerLoad;
        container = builder.container;

    }

    public static class Builder {
        private Long resId;
        private String cargoCode;
        private String name;
        private int length;
        private int breadth;
        private int height;
        private int containerLoad;
        private List<ContainerResource> container;

        public Builder(String cargoCode) {
            this.cargoCode = cargoCode;
        }

        public Builder resid(Long resId){
            this.resId = resId;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Builder breadth(int breadth) {
            this.breadth = breadth;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder load(int load) {
            this.containerLoad = load;
            return this;
        }

        public Builder containers(List<ContainerResource> container) {
            this.container = container;
            return this;
        }

        public Builder copy(CargoResource value) {
            this.resId = value.getResId();
            this.cargoCode = value.getCargoCode();
            this.name = value.getName();
            this.breadth = value.getBreadth();
            this.length= value.getLength();
            this.height = value.getHeight();
            this.containerLoad = value.getContainerLoad();
            this.container = value.getContainer();
            return this;
        }

        public CargoResource build() {
            return new CargoResource(this);
        }
    }
}
