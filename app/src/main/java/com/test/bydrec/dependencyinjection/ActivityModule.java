package com.test.bydrec.dependencyinjection;

import com.test.bydrec.view.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
class ActivityModule {

    @Provides
    MainActivity provideMainActivity(MainActivity activity) {
        return activity;
    }
}
