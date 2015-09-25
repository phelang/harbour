package harbour.config.factory;

import harbour.domain.LogisticCompany;
import harbour.domain.PackageProduct;

import java.util.List;
import java.util.Map;

//import harborManagementSystem.domain.PackageProduct;

/**
 * Created by student on 2015/04/26.
 */
public class LogisticCompanyFactory {
    public static LogisticCompany createCompany(Map<String, String> value,
                                                List<PackageProduct> packageProd){

        LogisticCompany company = new LogisticCompany
                .Builder(value.get("code"))
                .name(value.get("name"))
                .email(value.get("email"))
                .tel(value.get("tel"))
                .packageProducts(packageProd)
                .build();

        return company;
    }
}
