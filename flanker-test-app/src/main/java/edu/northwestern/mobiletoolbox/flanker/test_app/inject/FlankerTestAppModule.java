package edu.northwestern.mobiletoolbox.flanker.test_app.inject;

import android.app.Application;
import android.content.Context;

import org.sagebionetworks.research.mobile_ui.inject.ShowStepFragmentModule;
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskActivity;
import org.sagebionetworks.research.mobile_ui.perform_task.PerformTaskFragment;
import org.sagebionetworks.research.presentation.perform_task.TaskResultProcessingManager.TaskResultProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoSet;
import edu.northwestern.mobiletoolbox.flanker.inject.FlankerShowStepFragmentModule;
import edu.northwestern.mobiletoolbox.flanker.test_app.MainActivity;
import io.reactivex.Single;

@Module
public abstract class FlankerTestAppModule {
    private static final Logger LOG = LoggerFactory.getLogger(FlankerTestAppModule.class);

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivityInjector();

    @ContributesAndroidInjector
    abstract PerformTaskActivity contributePerformTaskActivityInjector();

    @ContributesAndroidInjector(modules = {ShowStepFragmentModule.class, FlankerShowStepFragmentModule.class})
    abstract PerformTaskFragment contributesPerformTaskFragmentInjector();

    @Provides
    static Context getApplicationContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @IntoSet
    static TaskResultProcessor getTaskResultProcessor() {
        return taskResult -> Single.just(taskResult)
                .doOnSuccess(t -> LOG.info("Final TaskResult: {}", taskResult.toString()))
                .ignoreElement();
    }
}
