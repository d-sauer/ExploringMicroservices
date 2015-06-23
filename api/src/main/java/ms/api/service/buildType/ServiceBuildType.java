package ms.api.service.buildType;

public interface ServiceBuildType {

    default BuildType getBuildType() {
        return BuildType.MICROSERVICE;
    }

}
