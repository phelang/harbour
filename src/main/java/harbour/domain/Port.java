package harbour.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Author P.Qhu on 2015/04/30.
 */
@Entity
public class Port implements Serializable{
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    private Long id;
    private String portCode;
    private String name;
    private String portType;

    @Embedded
    private Location location;

    /*  TERMINALS WITHIN THE PORT */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "portID")
    private List<Terminal> terminals;

    /* LOGISTIC COMPANIES WITH THE PORT */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "portID")
    private List<LogisticCompany> logisticCompanys;

    /* DOCK(s) WITHIN THE PORT */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "portID")
    private List<Dock> docks;

    /* WAREHOUSES WITHIN THE PORT */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( name = "portID")
    private List<Warehouse> warehouses;

    private Port(){}

    public Long getId() {
        return id;
    }

    public String getPortCode() {
        return portCode;
    }

    public String getName() {
        return name;
    }

    public String getPortType() {
        return portType;
    }

    public Location getLocation() {
        return location;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public List<LogisticCompany> getLogisticCompanies() {
        return logisticCompanys;
    }

    public List<Dock> getDocks() {
        return docks;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public Port(Builder builder){
        id = builder.id;
        name = builder.name;
        portCode = builder.portCode;
        portType = builder.portType;
        location = builder.location;

        terminals = builder.terminals;
        logisticCompanys = builder.logisticCompanys;
        docks = builder.docks;
        warehouses = builder.warehouses;

    }


    public static class Builder{

        private Long id;
        private String portCode;
        private String name;
        private String portType;
        private Location location;

        private List<Terminal> terminals;
        private List<LogisticCompany> logisticCompanys;
        private List<Dock> docks;
        private List<Warehouse> warehouses;


        public Builder(String portCode){
            this.portCode = portCode;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder portType(String portType){
            this.portType = portType;
            return this;
        }
        public Builder location(Location loc){
            this.location = loc;
            return this;
        }
        public Builder terminals(List<Terminal> t){
            this.terminals = t;
            return this;
        }

        public Builder companys(List<LogisticCompany> c){
            this.logisticCompanys = c;
            return this;
        }

        public Builder warehouses(List<Warehouse> w){
            this.warehouses = w;
            return this;
        }

        public Builder docks(List<Dock> d){
            this.docks = d;
            return this;
        }
        public Builder copy(Port value) {
            this.id = value.getId();
            this.portCode = value.getPortCode();
            this.name = value.getName();
            this.location = value.getLocation();
            this.warehouses = value.getWarehouses();
            this.portType = value.getPortType();
            this.warehouses = value.getWarehouses();
            this.logisticCompanys= value.getLogisticCompanies();
            this.terminals = value.getTerminals();
            this.docks = value.getDocks();
            return this;
        }


        public Port build(){
            return new Port(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Port)) return false;

        Port port = (Port) o;

        if (id != null ? !id.equals(port.id) : port.id != null) return false;
        if (portCode != null ? !portCode.equals(port.portCode) : port.portCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (portCode != null ? portCode.hashCode() : 0);
        return result;
    }
}
