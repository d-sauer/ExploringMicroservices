package ms.monolithic.impl;

import ms.api.service.ServiceType;

public class ServiceTypeImpl implements ServiceType {

    @Override
    public BuildType getBuildType() {
        return ServiceType.BuildType.MONOLITHIC;
    }
    
}
