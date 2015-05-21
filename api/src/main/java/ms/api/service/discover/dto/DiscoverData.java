package ms.api.service.discover.dto;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.jar.JarFile;

import javax.servlet.ServletContext;

import ms.api.service.build.ServiceBuildType;
import ms.api.service.build.BuildType;
import ms.api.service.discover.properties.ApiDiscoverProperties;
import ms.commons.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DiscoverData implements Logger {

    /**
     * Implementation-Title
     */
    @JsonProperty("service_name")
    public String serviceName;

    /**
     * Implementation-Version
     */
    @JsonProperty("service_version")
    public String serviceVersion;

    @JsonProperty("service_description")
    public String serviceDescription;

    @JsonProperty("service_heart_beat")
    public Long headrBeat;

    @JsonProperty("build_type")
    public BuildType buildType = BuildType.MICROSERVICE;

    private String servletInfo;

    private ApiDiscoverProperties apiDiscoverProperties;


    @JsonProperty("manifest")
    public HashMap<String, String> manifestFile = new HashMap<>();

    public DiscoverData(ServletContext servletContext, ServiceBuildType serviceBuildType, ApiDiscoverProperties apiDiscoverProperties) {
        if (servletContext != null) {
            servletInfo = servletContext.getServerInfo();
            this.apiDiscoverProperties = apiDiscoverProperties;

            loadManifest(servletContext);
            loadBuildType(serviceBuildType);
            loadServiceData();
        }

    }

    private void loadManifest(ServletContext servletContext) {
        try {
            InputStream is = servletContext.getResourceAsStream("./" + JarFile.MANIFEST_NAME);
            if (is == null) {
                is = getClass().getClassLoader().getResourceAsStream("./" + JarFile.MANIFEST_NAME);
            }

            if (is != null) {
                Properties prop = new Properties();
                prop.load(is);
                for (Entry<Object, Object> mfe : prop.entrySet()) {
                    manifestFile.put(mfe.getKey().toString(), mfe.getValue().toString());
                }
                trace("Manifets file: {}", manifestFile.toString());
            } else {
                debug("Can't find {} file", JarFile.MANIFEST_NAME);
                manifestFile.put("error", "Can't read manifest file from " + JarFile.MANIFEST_NAME);
            }
        } catch (IOException e) {
            debug("Can't read {} file", JarFile.MANIFEST_NAME);
            manifestFile.put("error", "Can't read manifest file from " + JarFile.MANIFEST_NAME);
        }
    }

    private void loadBuildType(ServiceBuildType serviceBuildType) {
        if (serviceBuildType != null) {
            buildType = serviceBuildType.getBuildType();
        }
    }

    private void loadServiceData() {
        if (manifestFile.containsKey("Implementation-Title") && manifestFile.containsKey("Implementation-Version")) {
            serviceName = manifestFile.get("Implementation-Title");
            serviceVersion = manifestFile.get("Implementation-Version");
            serviceDescription = manifestFile.get("Implementation-Description");
        } else if (apiDiscoverProperties != null) {
            // For development
            serviceName = apiDiscoverProperties.getName();
            serviceVersion = apiDiscoverProperties.getVersion();
            serviceDescription = apiDiscoverProperties.getDescription();
        }
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    public String getServiceVersion() {
        return serviceVersion;
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
