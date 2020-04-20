package edu.northwestern.mobiletoolbox.flanker.test_app;

import com.jakewharton.threetenabp.AndroidThreeTen;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import edu.northwestern.mobiletoolbox.flanker.test_app.inject.DaggerFlankerTestAppComponent;

public class FlankerTestApplication extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        AndroidThreeTen.init(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerFlankerTestAppComponent
                .builder()
                .application(this)
                .build();
    }
}
