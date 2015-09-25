package harbour.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by student on 2015/04/30.
 */
@Entity
public class Dock implements Serializable {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String dockCode;
    private int length;
    private int breadth;

    /* A CARGO SHIP WITHIN A DOCK*/
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "cargoID")
    private Cargo cargo;

    public Long getId() {
        return id;
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

    public Cargo getCargo() {
        return cargo;
    }

    private Dock() {
    }


    public Dock(Builder builder) {
        id = builder.id;
        cargo = builder.cargo;
        dockCode = builder.dockCode;
        length = builder.length;
        breadth = builder.breadth;
    }

    public static class Builder {
        private Long id;
        private String dockCode;
        private int length;
        private int breadth;
         private Cargo cargo;

        public Builder(String dockCode) {
            this.dockCode = dockCode;
        }

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Builder cargo(  Cargo cargo){
            this.cargo = cargo;
            return this;
        }
        public Builder breadth(int breadth) {
            this.breadth = breadth;
            return this;
        }
        public Builder copy(Dock value){
            this.id = value.getId();
            this.dockCode = value.getDockCode();
            this.length= value.getLength();
            this.breadth=value.getBreadth();
            this.cargo= value.getCargo();
            return this;
        }


        public Dock build() {
            return new Dock(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dock)) return false;

        Dock dock = (Dock) o;

        if (dockCode != null ? !dockCode.equals(dock.dockCode) : dock.dockCode != null) return false;
        if (id != null ? !id.equals(dock.id) : dock.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dockCode != null ? dockCode.hashCode() : 0);
        return result;
    }
}