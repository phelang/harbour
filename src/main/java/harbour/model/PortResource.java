package harbour.model;

import harbour.domain.Location;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author P.Qhu  on 2015/09/16.
 */
@Component
public class PortResource extends ResourceSupport {

    private Long resId;
    private String portCode;
    private String name;
    private String portType;

    private Location location;

    /*  TERMINALS WITHIN THE PORT */
    private List<TerminalResource> terminals;

    /* LOGISTIC COMPANIES WITH THE PORT */
    private List<LogisticCompanyResource> logisticCompanys;

    /* DOCK(s) WITHIN THE PORT */
    private List<DockResource> docks;

    /* WAREHOUSES WITHIN THE PORT */
    private List<WarehouseResource> warehouses;

    private PortResource(){}

    public Long getResId() {
        return resId;
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

    public List<TerminalResource> getTerminals() {
        return terminals;
    }

    public List<LogisticCompanyResource> getLogisticCompanys() {
        return logisticCompanys;
    }

    public List<DockResource> getDocks() {
        return docks;
    }

    public List<WarehouseResource> getWarehouses() {
        return warehouses;
    }

    public PortResource(Builder builder){
        resId = builder.resId;
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

        private Long resId;
        private String portCode;
        private String name;
        private String portType;
        private Location location;

        private List<TerminalResource> terminals;
        private List<LogisticCompanyResource> logisticCompanys;
        private List<DockResource> docks;
        private List<WarehouseResource> warehouses;


        public Builder(String portCode){
            this.portCode = portCode;
        }
		
		public Builder resId(Long resId){
            this.resId = resId;
            return this;
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
        public Builder terminals(List<TerminalResource> t){
            this.terminals = t;
            return this;
        }

        public Builder companys(List<LogisticCompanyResource> c){
            this.logisticCompanys = c;
            return this;
        }

        public Builder warehouses(List<WarehouseResource> w){
            this.warehouses = w;
            return this;
        }

        public Builder docks(List<DockResource> d){
            this.docks = d;
            return this;
        }
        public Builder copy(PortResource value) {
            this.resId = value.getResId();
            this.portCode = value.getPortCode();
            this.name = value.getName();
            this.portType = value.getPortType();
            this.location = value.getLocation();
            this.warehouses = value.getWarehouses();
            this.logisticCompanys= value.getLogisticCompanys();
            this.terminals = value.getTerminals();
            this.docks = value.getDocks();
            return this;
        }

        public PortResource build(){
            return new PortResource(this);
        }
    }
}
