package ms.monolithic.impl;

import ms.api.service.build.BuildType;
import ms.api.service.build.ServiceBuildType;

public class ServiceBuildTypeImpl implements ServiceBuildType {

    @Override
    public BuildType getBuildType() {
        return BuildType.MONOLITHIC;
    }
    
}
