package harbour.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Author P.Qhu  on 2015/08/13.
 */

@Entity
public class Cargo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cargoCode;
    private String name;
    private int length;
    private int breadth;
    private int height;
    private int containerLoad;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cargoID")
    private List<Container> container;


    private Cargo() {
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public Long getId() {
        return id;
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

    public List<Container> getContainer() {
        return container;
    }

    public Cargo(Builder builder) {
        id = builder.id;
        cargoCode = builder.cargoCode;
        name = builder.name;
        length = builder.length;
        height = builder.height;
        breadth = builder.breadth;
        containerLoad = builder.containerLoad;
        container = builder.container;

    }

    public static class Builder {
        private Long id;
        private String cargoCode;
        private String name;
        private int length;
        private int breadth;
        private int height;
        private int containerLoad;
        private List<Container> container;

        public Builder(String cargoCode) {
            this.cargoCode = cargoCode;
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

        public Builder containers(List<Container> container) {
            this.container = container;
            return this;
        }

        public Builder copy(Cargo value) {
            this.id = value.getId();
            this.cargoCode = value.getCargoCode();
            this.name = value.getName();
            this.breadth = value.getBreadth();
            this.length= value.getLength();
            this.height = value.getHeight();
            this.containerLoad = value.getContainerLoad();
            this.container = value.getContainer();
            return this;
        }

        public Cargo build() {
            return new Cargo(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cargo)) return false;

        Cargo cargo = (Cargo) o;

        if (cargoCode != null ? !cargoCode.equals(cargo.cargoCode) : cargo.cargoCode != null) return false;
        if (id != null ? !id.equals(cargo.id) : cargo.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cargoCode != null ? cargoCode.hashCode() : 0);
        return result;
    }
}
