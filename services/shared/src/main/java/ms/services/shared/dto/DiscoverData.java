package ms.services.shared.dto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.jar.JarFile;

import javax.servlet.ServletContext;

import ms.commons.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DiscoverData implements Logger {

    @JsonProperty("service_name")
    public String serviceName;

    @JsonProperty("service_description")
    public String serviceDescription;

    @JsonProperty("service_heart_beat")
    public Long headrBeat;

    public String servletInfo;

    @JsonProperty("manifest")
    public HashMap<String, String> manifestFile = new HashMap<>();

    public DiscoverData(ServletContext servletContext) {
        if (servletContext != null) {
            servletInfo = servletContext.getServerInfo();

            //
            // Read MANIFEST.MF
            //
            try {
                InputStream is = servletContext.getResourceAsStream("/META-INF/" + JarFile.MANIFEST_NAME);
                if (is == null) {
                    is = getClass().getClassLoader().getResourceAsStream("/META-INF/" + JarFile.MANIFEST_NAME);
                    
                    URL url = getClass().getClassLoader().getResource("/");
                    info("Manifest url: {}", url);
                }

                if (is != null) {
                    Properties prop = new Properties();
                    prop.load(is);
                    for (Entry<Object, Object> mfe : prop.entrySet()) {
                        manifestFile.put(mfe.getKey().toString(), mfe.getValue().toString());

                    }
                }
                else {
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
     * @return the serviceDescription
     */
    public String getServiceDescription() {
        return serviceDescription;
    }

    /**
     * @return the headrBeat
     */
    public Long getHeadrBeat() {
        return headrBeat;
    }

    /**
     * @return the servletInfo
     */
    public String getServletInfo() {
        return servletInfo;
    }

    /**
     * @return the manifestFile
     */
    public HashMap<String, String> getManifestFile() {
        return manifestFile;
    }

}