package harbour.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by student on 2015/04/30.
 */
@Embeddable
public class Location implements Serializable {
    private String tel;
    private String continent;
    private String country;
    private String city;
    private String postalCode;

    private Location(){}


    public  Location(Builder builder){
        tel = builder.tel;
        postalCode = builder.postalCode;
        continent = builder.continent;
        country = builder.country;
        city = builder.city;
    }



    public static class Builder{
        private String tel;
        private String continent;
        private String country;
        private String city;
        private String postalCode;

        public Builder(String tel){
            this.tel = tel;
        }

        public Builder continent(String continent){
            this.continent = continent;
            return this;
        }
        public Builder country(String country){
            this.country = country;
            return this;
        }

        public Builder city(String city){
            this.city = city;
            return this;
        }
        public Builder postalCode(String code){
            this.postalCode = code;
            return this;
        }

        public Location build(){
            return new Location(this);
        }
    }
}

