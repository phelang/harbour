package harbour.model;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author P.Qhu on 2015/09/16.
 */
 @Component
public class TerminalResource extends ResourceSupport{
	
    private Long resId;
    private String terminalCode;	// T1, T2 ...n
    private String teminalLocation; //East, SouthEast
    private int containerLoad;	// amount of container that can be loaded on the particular terminal
    
    private List<ContainerResource> container;

    private TerminalResource(){}

    public Long getResId() {
        return resId;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public String getTeminalLocation() {
        return teminalLocation;
    }

    public int getContainerLoad() {
        return containerLoad;
    }

    public List<ContainerResource> getContainer() {
        return container;
    }

    public TerminalResource(Builder builder){
        resId = builder.resId;
        terminalCode = builder.terminalCode;
        teminalLocation = builder.teminalLocation;
        containerLoad = builder.containerLoad;
        container = builder.container;
    }


    public static class Builder {
        private Long resId;
        private String terminalCode;    // T1, T2 ...n
        private String teminalLocation; //East, SouthEast
        private int containerLoad;    // amount of container that can be loaded on the particular terminal

        private List<ContainerResource> container;

        public Builder(String terminalCode) {
            this.terminalCode = terminalCode;
        }
		
		public Builder resId(Long resId) {
            this.resId = resId;
            return this;
        }

        public Builder teminalLocation(String loc) {
            this.teminalLocation = loc;
            return this;
        }

        public Builder containerLoad(int load) {
            this.containerLoad = load;
            return this;
        }


        public Builder containers(List<ContainerResource> c) {
            this.container = c;
            return this;
        }

        public Builder copy(TerminalResource value) {
            this.resId = value.getResId();
            this.terminalCode = value.getTerminalCode();
            this.teminalLocation = value.getTeminalLocation();
            this.containerLoad = value.getContainerLoad();
            this.container = value.getContainer();
            return this;
        }


        public TerminalResource build() {
            return new TerminalResource(this);
        }
    }

}
