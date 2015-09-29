package harbour.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by student on 2015/04/30.
 */
@Entity
public class Terminal implements Serializable{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String terminalCode;	// T1, T2 ...n
    private String teminalLocation; //East, SouthEast
    private int containerLoad;	// amount of container that can be loaded on the particular terminal
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "terminalID")
    private List<Container> container;

    private Terminal(){}

    public Long getId() {
        return id;
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

    public List<Container> getContainer() {
        return container;
    }

    public Terminal(Builder builder){
        id = builder.id;
        terminalCode = builder.terminalCode;
        teminalLocation = builder.teminalLocation;
        containerLoad = builder.containerLoad;
        container = builder.container;
    }


    public static class Builder {
        private Long id;
        private String terminalCode;    // T1, T2 ...n
        private String teminalLocation; //East, SouthEast
        private int containerLoad;    // amount of container that can be loaded on the particular terminal

        private List<Container> container;

        public Builder(String terminalCode) {
            this.terminalCode = terminalCode;
        }

        public Builder teminalLocation(String loc) {
            this.teminalLocation = loc;
            return this;
        }

        public Builder containerLoad(int load) {
            this.containerLoad = load;
            return this;
        }

        public Builder containers(List<Container> c) {
            this.container = c;
            return this;
        }

        public Builder copy(Terminal value) {
            this.id = value.getId();
            this.terminalCode = value.getTerminalCode();
            this.teminalLocation = value.getTeminalLocation();
            this.containerLoad = value.getContainerLoad();
            this.container = value.getContainer();
            return this;
        }


        public Terminal build() {
            return new Terminal(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Terminal)) return false;

        Terminal terminal = (Terminal) o;

        if (id != null ? !id.equals(terminal.id) : terminal.id != null) return false;
        if (terminalCode != null ? !terminalCode.equals(terminal.terminalCode) : terminal.terminalCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (terminalCode != null ? terminalCode.hashCode() : 0);
        return result;
    }
}
