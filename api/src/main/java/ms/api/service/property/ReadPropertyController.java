package ms.api.service.property;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@RestController
public class ReadPropertyController {

    @Autowired
    private Environment environment;
    
    /**
     * @author davor
     *
     */
    @JsonSerialize
    private static class PropertyData {
        private String name;
        private String value;

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name
         *            the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the value
         */
        public String getValue() {
            return value;
        }

        /**
         * @param value
         *            the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

    }

    @RequestMapping(value = "/property",params = {"key"}, method = { RequestMethod.GET })
    public PropertyData get(@RequestParam String key, HttpServletResponse response) {
        
        try {
            String value = environment.getProperty(key);

            PropertyData prop = new PropertyData();
            prop.setName(key);
            prop.setValue(value);
            
            return prop;
        } catch (Exception e) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return null;
        }
    }

}
