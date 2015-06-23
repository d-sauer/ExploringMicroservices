package ms.monolithic.impl;

import ms.api.service.buildType.BuildType;
import ms.api.service.buildType.ServiceBuildType;

public class ServiceBuildTypeImpl implements ServiceBuildType {

    @Override
    public BuildType getBuildType() {
        return BuildType.MONOLITHIC;
    }
    
}
