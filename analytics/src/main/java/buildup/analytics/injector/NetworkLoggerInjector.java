package buildup.analytics.injector;

import buildup.analytics.mfp.MfpNetworkLogger;
import buildup.analytics.network.NetworkLogger;

public class NetworkLoggerInjector {

    public static NetworkLogger networkLogger() {
        return new MfpNetworkLogger();
    }

}
