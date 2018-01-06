package buildup.analytics.injector;

import android.content.Context;
import android.util.Log;

import java.util.Properties;

import buildup.analytics.AnalyticsReporter;
import buildup.analytics.LogAnalyticsReporter;
import buildup.analytics.mfp.MfpAnalyticsReporter;

public class AnalyticsReporterInjector {

    private static AnalyticsReporter instance;

    public static AnalyticsReporter analyticsReporter(Context context) {
        if (instance != null) {
            return instance;
        }

        try {
            Properties p = new Properties();
            p.load(context.getAssets().open("wlclient.properties"));
            String wlServerPort = p.getProperty("wlServerPort");
            if (wlServerPort != null && !wlServerPort.isEmpty()) {
                instance = new MfpAnalyticsReporter();
            }
        } catch (Exception e) {
            Log.w("AnalyticsReporter", "Could not initialize MFP", e);
            instance = new LogAnalyticsReporter();
        }
        return instance;
    }

    public static AnalyticsReporter analyticsReporter() {
        if (instance == null) {
            throw new IllegalStateException("You must call analyticsReporter(Context context) first");
        }
        return instance;
    }
}
