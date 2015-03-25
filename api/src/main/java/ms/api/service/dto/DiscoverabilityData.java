package ms.api.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DiscoverabilityData {

    @JsonProperty("service_name")
    public String serviceName;

    @JsonProperty("service_description")
    public String serviceDescription;
    
    @JsonProperty("service_heart_beat")
    public Long headrBeat;

    public String servletInfo;

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

    /**
     * @return the headrBeat
     */
    public Long getHeadrBeat() {
        return headrBeat;
    }

    /**
     * @param headrBeat the headrBeat to set
     */
    public void setHeadrBeat(Long headrBeat) {
        this.headrBeat = headrBeat;
    }

    /**
     * @return the servletInfo
     */
    public String getServletInfo() {
        return servletInfo;
    }

    /**
     * @param servletInfo the servletInfo to set
     */
    public void setServletInfo(String servletInfo) {
        this.servletInfo = servletInfo;
    }
    
}
