package ms.api.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DiscoverabilityData {

    @JsonProperty("service_name")
    public String serviceName;

    @JsonProperty("service_description")
    public String serviceDescription;

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the serviceDescription
     */
    public String getServiceDescription() {
        return serviceDescription;
    }

    /**
     * @param serviceDescription the serviceDescription to set
     */
    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
    
}
