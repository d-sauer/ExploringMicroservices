package ms.api.service;

public interface ServiceType {
    
    public static enum BuildType {
        MICROSERVICE, MONOLITHIC
    }
    

    public default BuildType getBuildType() {
        return BuildType.MICROSERVICE;
    }
    
}
