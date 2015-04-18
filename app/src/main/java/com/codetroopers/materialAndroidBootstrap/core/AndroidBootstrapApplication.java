package com.codetroopers.materialAndroidBootstrap.core;

import android.app.Application;

import com.codetroopers.materialAndroidBootstrap.BuildConfig;
import com.codetroopers.materialAndroidBootstrap.core.components.ActivityScopeComponent;
import com.codetroopers.materialAndroidBootstrap.core.components.DaggerActivityScopeComponent;
import com.codetroopers.materialAndroidBootstrap.core.components.DaggerSingletonComponent;
import com.codetroopers.materialAndroidBootstrap.core.components.SingletonComponent;

import timber.log.Timber;

public class AndroidBootstrapApplication extends android.app.Application {
    private static Application instance;
    private ActivityScopeComponent component;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Uncomment to add crashlytics
        //Fabric.with(this, new Crashlytics());
        instance = this;

        initLoggers();
        initInjectors();
    }

    public ActivityScopeComponent injector() {
        return component;
    }

    /**
     * Timber init
     */
    private void initLoggers() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } /** else {
         // A tree which logs important information for crash reporting
         // custom implementations can be inserted by extending HollowTree
         Timber.plant(new Timber.HollowTree() {...});
         } **/
    }

    /**
     * Dagger components init
     */
    private void initInjectors() {
        final SingletonComponent singletonComponent = DaggerSingletonComponent.create();
        component = DaggerActivityScopeComponent.builder().singletonComponent(singletonComponent).build();
    }
}