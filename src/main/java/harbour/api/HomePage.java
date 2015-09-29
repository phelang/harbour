package harbour.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author P.Qhu  on 2015/09/02.
 */

@RestController
@RequestMapping("/api/**")
public class HomePage {
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHomePage(){
        return "HARBOUR MANAGEMENT - CREATING SCALEABLE AND EFFECTION SYSTEMS";
    }
}