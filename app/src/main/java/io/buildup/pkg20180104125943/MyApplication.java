

package io.buildup.pkg20180104125943;

import android.app.Application;
import buildup.injectors.ApplicationInjector;
import android.support.multidex.MultiDexApplication;


/**
 * You can use this as a global place to keep application-level resources
 * such as singletons, services, etc.
 */
public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setApplicationContext(this);
    }
}
