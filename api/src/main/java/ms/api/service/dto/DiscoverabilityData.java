package ms.api.service.dto;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletContext;

import ms.commons.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DiscoverabilityData implements Logger {

    @JsonProperty("service_name")
    public String serviceName;

    @JsonProperty("service_description")
    public String serviceDescription;

    @JsonProperty("service_heart_beat")
    public Long headrBeat;

    public String servletInfo;

    @JsonProperty("manifest")
    public HashMap<String, String> manifestFile = new HashMap<>();

    public DiscoverabilityData(ServletContext servletContext) {
        if (servletContext != null) {
            try {
                InputStream is = servletContext.getResourceAsStream("/META-INF/MANIFEST.MF");
                if (is != null) {
                    Properties prop = new Properties();
                    prop.load(is);
                    for (Entry<Object, Object> mfe : prop.entrySet()) {
                        manifestFile.put(mfe.getKey().toString(), mfe.getValue().toString());

                    }
                } else {
                    manifestFile.put("error", "Can't read manifest file from /META-INF/MANIFEST.MF");
                }
            }
            catch (IOException e) {
                manifestFile.put("error", "Can't read manifest file from /META-INF/MANIFEST.MF");
            }
        }

    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName
     *            the serviceName to set
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
     * @param serviceDescription
     *            the serviceDescription to set
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
     * @param headrBeat
     *            the headrBeat to set
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
     * @param servletInfo
     *            the servletInfo to set
     */
    public void setServletInfo(String servletInfo) {
        this.servletInfo = servletInfo;
    }

    /**
     * @return the manifestFile
     */
    public HashMap<String, String> getManifestFile() {
        return manifestFile;
    }

}
